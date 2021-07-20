package fullCalendar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteScheduleServ")
public class DeleteScheduleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteScheduleServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		CalDAO dao = new CalDAO();
		String result = dao.deleteSchedule(request.getParameter("title"));
		
		out.print(result);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
