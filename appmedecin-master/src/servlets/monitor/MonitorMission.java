package servlets.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import dao.MissionHome;
import dao.HibernateCommons;
import editBeans.MonitorBean;
import model.Mission;
import servlets.ServletsCommons;

/**
 * Servlet implementation class Monitor
 */
@WebServlet("/monitorMission")
public class MonitorMission extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static MonitorBean<Mission> mBM;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorMission() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private Collection<Mission> getCollection(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	if(request.getAttribute("id") != null) {
    		return new MissionHome().findByUserId(request.getAttribute("id").toString());
    	}
    	
    	if(request.getParameter("mi_id") != null) {
    		ArrayList<Mission> c = new ArrayList<Mission>();
    		c.add(new MissionHome().findById(Long.parseLong(request.getParameter("mi_id").toString())));
    		return c;
    	}
    
		return new MissionHome().findByUserId(String.valueOf(ServletsCommons.uRedirectIfNotLogged(request,response).getId())) ;
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
		Collection<Mission> disps = getCollection(request, response);
		t.commit();
		
			mBM = new MonitorBean<Mission>(Mission.class, disps);
			//String t = ServletsCommons.afficheBean(request,response, Users.class,usrs );
			request.setAttribute("tablearguments", mBM.getCol());
			request.getRequestDispatcher(mBM.getVue()).include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(mBM != null) {
			if(request.getParameter("spr").equals("true") || request.getParameter("edi").equals("true")){
				try {
					mBM.editOrDelete(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBM = null;
				if(request.getParameter("spr").equals("true") ) {
				response.sendRedirect(request.getContextPath()+"/monitorMission");
				}
			}else {
				doGet(request,response);
			}
		}else {
			doGet(request, response);
		}
	}

}
