package servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.Users;

public class ServletsCommons {
	public static boolean isLogged(HttpSession ses) {
		if(null != ses.getAttribute("user")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return true if logged false if not
	 * @throws ServletException
	 * @throws IOException
	 */
	public static boolean redirectIfNotLogged(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(isLogged(request.getSession())) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		return false;
	}
	
	public static Users uRedirectIfNotLogged(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(isLogged(request.getSession())) {
			return (Users) request.getSession().getAttribute("user") ;
		}else {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		return null;
	}
	
	public static boolean redirectIfNotFullyRegistered(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		boolean b = false;
		Users user = uRedirectIfNotLogged(request, response);
		if(user.getIsemployeur()&& user.getEmployeur()==null) {
			b = true;
			response.sendRedirect(request.getContextPath()+"/createEmployeur");
		}else {
			if(!user.getIsemployeur()&& user.getMedecinremp()==null) {
				b = true;
				response.sendRedirect(request.getContextPath()+"/createMedecinremp");
			}
		}
		return b;
	}
	
	public static boolean redirectIfNotEmp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		boolean b = true;
		Users user = uRedirectIfNotLogged(request, response);
		if(!user.getIsemployeur()) {
			b = false;
			response.sendRedirect(request.getContextPath()+"/maps");
		}
		
		return b;
	}
	
public static <T> String afficheBean(HttpServletRequest request,HttpServletResponse response,Class<T> x,Collection<T> objs) {
		 
		
		Map<Integer, String> relFieldsMap      = new HashMap<Integer, String>();
		String table ="";
		String prefix = "tablearguments";
		
		Field [] fields = x.getDeclaredFields();
		int relFieldsNum =0;
		table += "<table id='"+prefix+"Table' style='border:1px solid #000;' >\n"
				+ "<tr>";
		
		for(int i =0;i< fields.length;i++) {
			
			String name = fields[i].getName();
			if(!name.contains("id")&&!name.contains("ID") &&!name.contains("Id")) {
				table += "<th>"+ name+"</th>\n";
				relFieldsMap.put(relFieldsNum, name);
				relFieldsNum++;
			}
		}
		table += "</tr>\n";
		for(int j =0; j< objs.size();j++) {
			try{
				table+= "<tr>";
				for(int k =0; k< relFieldsMap.size();k++) {
					table+="<td style='border:1px solid #000;'>";
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("${");
					stringBuilder.append(prefix);
					stringBuilder.append(".");
					stringBuilder.append(relFieldsMap.get(k));
					stringBuilder.append("}");
					table+="<c:out value='"+stringBuilder.toString()+"'/>";
					table+="</td>\n";
				}
				table += "</tr>\n";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		table+="</table>";
		request.setAttribute( prefix, objs );
       return table;
	}

	public static void setEdit(HttpServletRequest request,HttpServletResponse response) {
		Object x = request.getAttribute("e");
		if(x != null) {
			request.setAttribute("e",x);
		}
	}
	
	public static String vueCreateOrEdit(String id, String createVue, String editVue) {
		System.out.println("id : "+id);
		if(id == ""|| id == null || id.equals("0")) {
			return createVue;
		}
		return editVue;
	}
	
	public static void requestSetLink(HttpServletRequest request, Users u) {
		if(u.getIsemployeur()) {
			request.setAttribute("pp", "/WEB-INF/lienEmp.jsp");
		}else {
			request.setAttribute("pp", "/WEB-INF/lienMed.jsp");
		}
	}
}
