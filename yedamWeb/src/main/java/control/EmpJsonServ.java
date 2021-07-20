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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import common.EmpDAO;
import common.Employee;

@WebServlet("/EmpJsonServ")
public class EmpJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpJsonServ() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// {"data": [ [ ],[ ], [ ], [ ], [ ] ]} //empl_demo
		PrintWriter out = response.getWriter(); //응답객체에 가지고 있는 getWriter로써
		Gson gson = new Gson(); //순서 없이 출력됨.
		new GsonBuilder().create(); // 순서 의미를 갖고 출력.
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList(); // 사원 전체 정보를 가지고옴 
		JsonArray oAry = new JsonArray();
		for(Employee emp: list) {
			JsonArray iAry = new JsonArray();
			iAry.add(emp.getEmployeeId());
			iAry.add(emp.getFirstName());
			iAry.add(emp.getLastName());
			iAry.add(emp.getEmail());
			iAry.add(emp.getHireDate());
			iAry.add(emp.getSalary());
			
			oAry.add(iAry);
		}
		JsonObject obj = new JsonObject(); // key value 형식으로 값을 만들어 주는 클래스임
		obj.add("data",oAry);
		
		out.println(gson.toJson(obj));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
