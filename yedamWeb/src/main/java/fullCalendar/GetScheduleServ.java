package fullCalendar;

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

@WebServlet("/GetScheduleServ")
public class GetScheduleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetScheduleServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		
		CalDAO dao = new CalDAO();
		List<FullCalendar> list =dao.getSchedules();
		
		JsonArray ary = new JsonArray();
		for(FullCalendar cal: list) {
			JsonObject obj = new JsonObject();
			obj.addProperty("title", cal.getTilte());
			obj.addProperty("start", cal.getStartDate());
			obj.addProperty("end", cal.getEndDate());
			ary.add(obj);
		}
		
		response.getWriter().println(gson.toJson(ary));		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
