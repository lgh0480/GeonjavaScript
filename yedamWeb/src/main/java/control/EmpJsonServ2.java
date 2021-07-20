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

@WebServlet("/EmpJsonServ2")
public class EmpJsonServ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpJsonServ2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// {"data": [ { }, { }, { }, { }, { },]}; //empl_demo
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson(); // 순서 없이 출력 
		new GsonBuilder().create(); // 순서 의미를 갖고 출력 
	
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		JsonArray aAry = new JsonArray();
		for(Employee emp : list) {
			JsonObject oAry = new JsonObject(); //{id:??, name:??} 이런식으로 맹금
			oAry.addProperty("employeeId", emp.getEmployeeId());
			oAry.addProperty("firstName", emp.getFirstName());
			oAry.addProperty("lastName", emp.getLastName());
			oAry.addProperty("email", emp.getEmail());
			oAry.addProperty("hireData", emp.getHireDate());
			oAry.addProperty("salary", emp.getSalary());
			
			aAry.add(oAry);
		}
		JsonObject obj = new JsonObject(); 
		obj.add("data", aAry);
		
		out.println(gson.toJson(obj));
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
