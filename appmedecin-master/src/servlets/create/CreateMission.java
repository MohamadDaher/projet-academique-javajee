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
import formBeans.MissionBean;
import model.Mission;
import model.Users;
import servlets.ServletsCommons;

/**
 * Servlet implementation class CreateEmployeur
 */
@WebServlet("/createMission")
public class CreateMission extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_FORM = "form";
	public static final String ATT_EMP = "mission";
	public static final String VUE_USR = "/WEB-INF/sign_in.jsp";
	public static final String VUE_EMP = "/WEB-INF/createMission.jsp";
	public static final String VUE_MAP = "/WEB-INF/map.jsp";
	private Mission mission;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletsCommons.redirectIfNotLogged(request, response)&&ServletsCommons.redirectIfNotEmp(request, response)) {
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
			MissionBean form2 = new MissionBean();
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			try {
				mission = form2.createMission(request, utilisateur.getEmployeur());
				String nextVue = ServletsCommons.vueCreateOrEdit(String.valueOf(mission.getId()), request.getContextPath()+"/createMission", request.getContextPath()+"/monitorMission");
				Transaction t = session.beginTransaction();
				session.saveOrUpdate(mission);
				t.commit();
				//request.setAttribute( ATT_EMP, employeur );
				response.sendRedirect(nextVue);
			}catch(Exception e) {
				form2.setResultat("Échec de la création de profil employeur.\n"+e.getMessage());
				 /* Stockage du formulaire et du bean dans l'objet request */
		        request.setAttribute( ATT_FORM, form2 );
		        request.setAttribute( ATT_EMP, mission );
		        request.getRequestDispatcher(VUE_EMP).forward( request, response );
			}
		
	}

}
