package servlets.create;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateCommons;
import formBeans.DispoTravailBean;
import model.Dispotravail;
import model.Users;
import servlets.ServletsCommons;

/**
 * Servlet implementation class CreateEmployeur
 */
@WebServlet("/createDispoTravail")
public class CreateDispoTravail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String ATT_DISP = "dispo";
	public static final String VUE_USR = "/WEB-INF/sign_in.jsp";
	public static final String VUE_DISP = "/WEB-INF/createDispoTravail.jsp";
	public static final String VUE_MAP = "/WEB-INF/map.jsp";
	private Dispotravail dispo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDispoTravail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletsCommons.redirectIfNotLogged(request, response)) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher dispatch = request.getRequestDispatcher(VUE_DISP);
	        dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			Users utilisateur = ServletsCommons.uRedirectIfNotLogged(request, response);
			DispoTravailBean form2 = new DispoTravailBean();
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			try {
				dispo = form2.createDispoTravail(request, utilisateur);
				String nextVue = ServletsCommons.vueCreateOrEdit(String.valueOf(dispo.getId()), request.getContextPath()+"/createDispoTravail", request.getContextPath()+"/MonitorDispo");
				Transaction t = session.beginTransaction();
				session.saveOrUpdate(dispo);
				t.commit();
				//request.setAttribute( ATT_EMP, employeur );
				response.sendRedirect(nextVue);
				
			}catch(Exception e) {
				e.printStackTrace();
				form2.setResultat("Échec de la création de disponibilité.\n"+e.getMessage());
				 /* Stockage du formulaire et du bean dans l'objet request */
		        request.setAttribute( ATT_FORM, form2 );
		        request.setAttribute( ATT_DISP, dispo );
		        request.getRequestDispatcher(VUE_DISP).forward( request, response );
			}
		
	}

}
