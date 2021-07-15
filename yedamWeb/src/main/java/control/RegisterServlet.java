package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String eid = request.getParameter("eid"); // 파라미터를 적어주면 해당되는 값을 받아줌.
		String last_name = request.getParameter("last_name");
		String fn = request.getParameter("first_name");
		String em  = request.getParameter("email");
		String hd = request.getParameter("hire_date");
		
		EmpDAO dao = new EmpDAO();
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(eid));
		emp.setFirstName(fn);
		emp.setLastName(last_name);
		emp.setEmail(em);
		emp.setHireDate(hd);
		
		dao.insertEmp(emp);
		
		
		// {"id":?, "first_name":?, "last_name":?, "email":?,"hire_date":?}
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(emp));
//		out.println("{\"id\":"+emp.getEmployeeId() ㄴ
//					+ ",\"first_name\":\"" + emp.getFirstName() 
//					+ ",\"last_name\":\"" +emp.getLastName() 
//					+ ",\"email\":\""+emp.getEmail() 
//					+ ",\"hire_date\":"+ emp.getHireDate()
//					+ "\"}"
//					);
//		System.out.println(eid + ", "+ last_name + ","+ fn + ","+ em +"," +hd);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
