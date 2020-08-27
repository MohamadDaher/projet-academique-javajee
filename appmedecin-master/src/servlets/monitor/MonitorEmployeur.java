package servlets.monitor;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import dao.EmployeurHome;
import dao.HibernateCommons;
import editBeans.MonitorBean;
import model.Employeur;
import servlets.ServletsCommons;

/**
 * Servlet implementation class Monitor
 */
@WebServlet("/MonitorEmployeur")
public class MonitorEmployeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static MonitorBean<Employeur> mBE;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorEmployeur() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private Collection<Employeur> getCollection(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	if(request.getAttribute("id") != null) {
    		return new EmployeurHome().findByUserId(request.getAttribute("id").toString());
    	}
    
		return new EmployeurHome().findByUserId(String.valueOf(ServletsCommons.uRedirectIfNotLogged(request,response).getId())) ;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
		ServletsCommons.uRedirectIfNotLogged(request, response);
		Session s = HibernateCommons.getInstance().getSession();
		org.hibernate.Transaction t = s.getTransaction();
		t.begin();
		Collection<Employeur> disps = getCollection(request, response);
		response.getWriter().append(ServletsCommons.afficheBean(request, response, Employeur.class, disps));
		t.commit();
		
			mBE = new MonitorBean<Employeur>(Employeur.class, disps);
			//String t = ServletsCommons.afficheBean(request,response, Users.class,usrs );
			request.setAttribute("tablearguments", mBE.getCol());
			request.getRequestDispatcher(mBE.getVue()).include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(mBE != null) {
			if(request.getParameter("spr").equals("true") || request.getParameter("edi").equals("true")){
				try {
					mBE.editOrDelete(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBE = null;
				doGet(request,response);
			}else {
				doGet(request,response);
			}
		}else {
			doGet(request, response);
		}
	}

}
