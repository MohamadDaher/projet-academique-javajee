package servlets.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import editBeans.MonitorBean;
import model.Users;
import servlets.ServletsCommons;

/**
 * Servlet implementation class Monitor
 */
@WebServlet("/MonitorUsers")
public class MonitorUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static MonitorBean<Users> mBU;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorUsers() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Collection<Users> usrs = new ArrayList<Users>();
		usrs.add(ServletsCommons.uRedirectIfNotLogged(request, response));
		try {
			mBU = new MonitorBean<Users>(Users.class, usrs);
			//String t = ServletsCommons.afficheBean(request,response, Users.class,usrs );
			request.setAttribute("tablearguments", mBU.getCol());
			request.getRequestDispatcher(mBU.getVue()).include(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(mBU != null) {
			if(request.getParameter("spr").equals("true") || request.getParameter("edi").equals("true")){
				try {
					mBU.editOrDelete(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBU = null;
				if(request.getParameter("spr").equals("true") ) {
				doGet(request,response);
				}
			}else {
				if(request.getParameter("emp").equals("true")) {
					request.setAttribute("id", request.getParameter("todosmt"));
					request.getRequestDispatcher("/MonitorProfil").forward(request, response);
				}else {
					if(request.getParameter("dis").equals("true")) {
						request.setAttribute("id", request.getParameter("todosmt"));
						request.getRequestDispatcher("/MonitorDispo").forward(request, response);
					}else {
						doGet(request,response);
					}
				}
			}
		}else {
			doGet(request, response);
		}

		response.getWriter().append(request.getParameter("todosmt").toString());
	}

}
