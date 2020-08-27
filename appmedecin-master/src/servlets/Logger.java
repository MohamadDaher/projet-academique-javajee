package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UsersHome;
import model.Users;
import dao.HibernateCommons;

/**
 * Servlet implementation class Logger
 */
@WebServlet("/logger")
public class Logger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("username");
		String pass = request.getParameter("pass");
		UsersHome usrH = new UsersHome();
		try {
			Session session = HibernateCommons.getInstance().getSessionFactory().getCurrentSession();
			Transaction t = session.beginTransaction();
			Users u = usrH.findByMail(mail);
			if(u == null) {
				try {
					u = usrH.findByPhone(new Long(mail));
				}catch(java.lang.NumberFormatException e) {
					u = null;
				}
			}
			t.commit();
			if(org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass).equals(u.getMdp())&& null != u) {
				request.getSession().setAttribute("user", u);
				if(!ServletsCommons.redirectIfNotFullyRegistered(request, response)) {
					response.sendRedirect(request.getContextPath()+"/maps");
				}
			}else {
				throw new Exception("Identifiants ou mot de passe incorectes");
			}
		}catch(Exception e) {
			request.setAttribute("err", e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/login");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
