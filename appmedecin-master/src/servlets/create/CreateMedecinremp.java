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
import formBeans.MedecinRempBean;
import model.Medecinremp;
import model.Users;
import servlets.ServletsCommons;

/**
 * Servlet implementation class CreateEmployeur
 */
@WebServlet("/createMedecinRemp")
public class CreateMedecinremp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String ATT_MED = "medrp";
	public static final String VUE_USR = "/WEB-INF/sign_in.jsp";
	public static final String VUE_MED = "/WEB-INF/createMedecinremp.jsp";
	public static final String VUE_MAP = "/WEB-INF/map.jsp";
	private Medecinremp medrp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMedecinremp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletsCommons.redirectIfNotLogged(request, response)) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher dispatch = request.getRequestDispatcher(VUE_MED);
	        dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			Users utilisateur = ServletsCommons.uRedirectIfNotLogged(request, response);
			MedecinRempBean form2 = new MedecinRempBean();
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			try {
				medrp = form2.inscrireMedecinRemp(request, utilisateur);
				Transaction t = session.beginTransaction();
				session.save(medrp);
				t.commit();
				//request.setAttribute( ATT_MED, medrp );
				response.sendRedirect(request.getContextPath()+"/maps");
			}catch(Exception e) {
				form2.setResultat("Échec de la création de profil medecin remplaçant.\n"+e.getMessage());
				 /* Stockage du formulaire et du bean dans l'objet request */
		        request.setAttribute( ATT_FORM, form2 );
		        request.setAttribute( ATT_MED, medrp );
		        request.getRequestDispatcher(VUE_MED).forward( request, response );
			}
		
	}

}
