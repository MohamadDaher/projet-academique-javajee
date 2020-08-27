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
import formBeans.Sign_inBean;
import model.Users;
import servlets.ServletsCommons;


/**
 * Servlet implementation class Sign_in
 */
@WebServlet("/sign_in")
public class Sign_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_EMP = "employeur";
	public static final String VUE_USR = "/WEB-INF/sign_in.jsp";
	public static final String VUE_EMP = "/WEB-INF/createEmployeur.jsp";
	public static final String VUE_MED = "/WEB-INF/createMedecinremp.jsp";
	private Users utilisateur;
	private boolean isEmployeur = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign_in() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletsCommons.isLogged(request.getSession())) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher dispatch = request.getRequestDispatcher(VUE_USR);
	        dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 /* Préparation de l'objet formulaire */
        Sign_inBean form = new Sign_inBean();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        try {
        	if(request.getParameter("isEmp")!=null) {
        		if(request.getParameter("isEmp").equals("on")) {
        			this.isEmployeur = true;
        		}
        	}
			utilisateur = form.inscrireUtilisateur( request );
			utilisateur.setIsemployeur(isEmployeur);
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			String nextVue;
			if(isEmployeur) {
				nextVue = ServletsCommons.vueCreateOrEdit(String.valueOf(utilisateur.getId()), request.getContextPath()+"/createEmployeur", request.getContextPath()+"/monitorUsers");
			}else {
				nextVue = ServletsCommons.vueCreateOrEdit(String.valueOf(utilisateur.getId()), request.getContextPath()+"/createMedecinremp", request.getContextPath()+"/monitorUsers");
			}
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(utilisateur);
			t.commit();
			request.getSession().setAttribute("user", utilisateur);
			response.sendRedirect(nextVue);
		} catch (Exception e) {
			e.printStackTrace();
			form.setResultat("Échec de l'inscription."+e.getMessage());
			 /* Stockage du formulaire et du bean dans l'objet request */
	        request.setAttribute( ATT_FORM, form );
	        request.setAttribute( ATT_USER, utilisateur );
	        request.getRequestDispatcher(VUE_USR).forward( request, response );
		}
		
       
	}


}
