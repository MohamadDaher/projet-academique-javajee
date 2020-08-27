package servlets.monitor;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import dao.DispotravailHome;
import dao.HibernateCommons;
import editBeans.MonitorBean;
import model.Dispotravail;
import servlets.ServletsCommons;

/**
 * Servlet implementation class Monitor
 */
@WebServlet("/MonitorDispo")
public class MonitorDispo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static MonitorBean<Dispotravail> mBD;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorDispo() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private Collection<Dispotravail> getCollection(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	if(request.getAttribute("id") != null) {
    		return new DispotravailHome().findByUserId(request.getAttribute("id").toString());
    	}
    
		return new DispotravailHome().findByUserId(String.valueOf(ServletsCommons.uRedirectIfNotLogged(request,response).getId())) ;
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
		Collection<Dispotravail> disps = getCollection(request, response);
		t.commit();
		
			mBD = new MonitorBean<Dispotravail>(Dispotravail.class, disps);
			//String t = ServletsCommons.afficheBean(request,response, Users.class,usrs );
			request.setAttribute("tablearguments", mBD.getCol());
			request.getRequestDispatcher(mBD.getVue()).include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(mBD != null) {
			if(request.getParameter("spr").equals("true") || request.getParameter("edi").equals("true")){
				try {
					mBD.editOrDelete(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBD = null;
				if(request.getParameter("spr").equals("true") ) {
				doGet(request,response);
				}
			}else {
				doGet(request,response);
			}
		}else {
			doGet(request, response);
		}
	}

}
