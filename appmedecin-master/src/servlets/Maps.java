package servlets;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;

import dao.HibernateCommons;
import dao.MissionHome;
import model.Mission;
import model.Users;

/**
 * Servlet implementation class Maps
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/maps" })
public class Maps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users u = ServletsCommons.uRedirectIfNotLogged(request, response);
		if(u!=null) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			MissionHome mH = new MissionHome();
			LocalDate ld = LocalDate.now();
			Transaction t = HibernateCommons.getInstance().getSession().beginTransaction();
			Collection<Mission> cm = mH.findByDate(ld);
			t.commit();
			request.setAttribute("cm", cm);
			ServletsCommons.requestSetLink(request, u);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/map.jsp");
	        dispatch.forward(request, response);
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
