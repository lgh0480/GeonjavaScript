package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;

/**
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/EmpListServlet")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpListServlet() {
        super();
    }

    //http://localhost/yedamWeb/EmpListServlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//out.println("{\"id\":1, \"first_name\": \"Hong\",\"last_name\":\"gildong\"}"); // JSON 데이터 만들기 {"id":1, "first_name": "Hong","last_name":"gildong"}
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list));
		
		//JSON으로 만들어 주는거임 ㅎ
//		out.println("[");
//		int cnt= 1;
//		for(Employee emp : list) {	
//			out.println("{\"id\":" + emp.getEmployeeId()	
//						+",\"first_name\":\"" + emp.getFirstName()
//						+"\",\"last_name\":\""+ emp.getLastName()
//						+"\",\"email\":\"" +emp.getEmail()
//						+"\",\"hire_date\":\""+ emp.getHireDate()
//						+ "\"}");
//			if (cnt++ != list.size() ) {
//				out.println(","); 
//			}
//		}
//		out.println("]");
//		System.out.println("hhhhhhhhhh");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
