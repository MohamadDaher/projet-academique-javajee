package editBeans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateCommons;

public class MonitorBean<T> implements Serializable {

	private Class<T> x;
	private Collection<T> col;
	private String vue;
	private static final String USER_MODEL = "/WEB-INF/monitorUsers.jsp";
	private static final String DISPO_MODEL = "/WEB-INF/monitorDispo.jsp";
	private static final String MISS_MODEL = "/WEB-INF/monitorMission.jsp";
	
	
	private static final String USER_edit="/WEB-INF/sign_in.jsp";
	private static final String DISPO_edit="/WEB-INF/createDispoTravail.jsp";
	private static final String MISS_edit="/WEB-INF/createMission.jsp";
	private static final long serialVersionUID = 1L;

	public MonitorBean(Class<T> x, Collection<T> col) throws Exception {
		super();
		this.x = x;
		this.col = col;
		this.vue = createVue();
	}
	
public String getHeader() {
		Map<Integer, String> relFieldsMap      = new HashMap<Integer, String>();
		
		
		Field [] fields = getX().getDeclaredFields();
		int relFieldsNum =0;
		String table ="";
		table += "<table id='Table' style='border:1px solid #000;' >\n"
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
		return table;
}
	private String createVue() throws Exception {
		if(getX().getSimpleName().equals("Users")) {
			return USER_MODEL;
		}else {
			if(getX().getSimpleName().equals("Dispotravail")) {
				return DISPO_MODEL;
			}else {
				if(getX().getSimpleName().equals("Mission")) {
					return MISS_MODEL;
				}else {
					
					throw new Exception("can't find adapted vue to monitor this Bean");
				}
			}
		}
		
	}
	
	public Class<T> getX(){
		return this.x;
	}
	public String getVue() {
		return this.vue;
	}

	public Collection<T> getCol(){
		return this.col;
	}

	public void editOrDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		if(request.getParameter("spr").equals("true")) {
			Session s =  HibernateCommons.getInstance().getSession();
			Transaction t = s.beginTransaction();
			T obj = s.get(getX(), Long.parseLong((request.getParameter("todosmt"))));
			s.delete(obj);
			t.commit();
		}else {
			if(request.getParameter("edi").equals("true")) {
				System.out.println("EDIT");
				Session s =  HibernateCommons.getInstance().getSession();
				Transaction t = s.beginTransaction();
				T obj = s.get(getX(), Long.parseLong((request.getParameter("todosmt"))));
				if(getX().getSimpleName().equals("Users")) {
					request.setAttribute("utilisateur", obj);
					 request.getRequestDispatcher(USER_edit).forward(request, response);
				}else {
					if(getX().getSimpleName().equals("Dispotravail")) {
						request.setAttribute("dispo", obj);
						request.getRequestDispatcher(DISPO_edit).forward(request, response);
					}else {
						if(getX().getSimpleName().equals("Mission")) {
							request.setAttribute("mission", obj);
							request.getRequestDispatcher(MISS_edit).forward(request, response);
						}else {
							
							throw new Exception("can't find adapted vue to monitor this Bean");
						}
					}
				}
				
			}else {
				
			}
		}
	}

	@Override
	public String toString() {
		return "MonitorBean [x=" + x + ", col=" + col + ", vue=" + vue + "]";
	}
	
	

}