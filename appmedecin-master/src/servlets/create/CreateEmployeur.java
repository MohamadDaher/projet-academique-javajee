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

import dao.EmployeurHome;
import dao.HibernateCommons;
import formBeans.EmployeurBean;
import model.Employeur;
import model.Users;
import servlets.ServletsCommons;

/**
 * Servlet implementation class CreateEmployeur
 */
@WebServlet("/createEmployeur")
public class CreateEmployeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String ATT_EMP = "employeur";
	public static final String VUE_USR = "/WEB-INF/sign_in.jsp";
	public static final String VUE_EMP = "/WEB-INF/createEmployeur.jsp";
	public static final String VUE_MAP = "/WEB-INF/map.jsp";
	private Employeur employeur;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEmployeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletsCommons.redirectIfNotLogged(request, response)) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher dispatch = request.getRequestDispatcher(VUE_EMP);
	        dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			Users utilisateur = ServletsCommons.uRedirectIfNotLogged(request, response);
			EmployeurBean form2 = new EmployeurBean();
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			try {
				employeur = form2.inscrireEmployeur(request, utilisateur);
				Transaction t = session.beginTransaction();
				session.save(employeur);
				t.commit();
				//request.setAttribute( ATT_EMP, employeur );
				response.sendRedirect(request.getContextPath()+"/maps");
			}catch(Exception e) {
				form2.setResultat("Échec de la création de profil employeur.\n"+e.getMessage());
				 /* Stockage du formulaire et du bean dans l'objet request */
		        request.setAttribute( ATT_FORM, form2 );
		        request.setAttribute( ATT_EMP, employeur );
		        request.getRequestDispatcher(VUE_EMP).forward( request, response );
			}
		
	}

}
