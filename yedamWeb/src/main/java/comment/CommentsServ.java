package comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CommentsServ")
public class CommentsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentsServ() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 요청정보를 담아서 처리해주는것 서블릿이라는 클래스에서 처리해줌 ㅎ
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter(); // 응답정보 어떤 정보를 실어서 보여주겠다라는것!
		String cmd = request.getParameter("cmd"); // cmd=insert & id=?

		if (cmd == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("<result>"); // xml 데이타
			sb.append("<code>error</code>");
			sb.append("<data>");
			sb.append("명령문 없음 cmd null"); // 명령문 없음이라고 뜨게 해줄꺼임
			sb.append("</data>");
			sb.append("</result>");
			out.print(sb.toString());
			// <code>error</code> <data>error명령문 없음 cmd null</data>
			// {"code": "error", "data":"cmd null"} <-JSON 타입

		} else if (cmd.equals("selectAll")) { // 전체조회 기능을 여기다가 부여하겠음.
			List<HashMap<String, Object>> list = CommentsDAO.getInstance().selectAll();
			out.print(selectAll(list));
		} else if (cmd.equals("insert")) {
			//
			Comments comment = new Comments();
			comment.setContent(request.getParameter("content"));
			comment.setName(request.getParameter("name"));
			HashMap<String, Object> map = CommentsDAO.getInstance().insert(comment);
			out.println(toXML(map));
		} else if(cmd.equals("update")) {
			response.setContentType("text/xml;charset=utf-8");
			
			Comments comment = new Comments();
			comment.setId(request.getParameter("id"));
			comment.setName(request.getParameter("name"));
			comment.setContent(request.getParameter("content"));
			HashMap<String, Object> map = CommentsDAO.getInstance().update(comment);
			out.println(toXML(map)); // Ajax 호출
		
		} else if (cmd.equals("delete")) {
			response.setContentType("text/xml;charset=utf-8");
			
			Comments comment = new Comments();
			comment.setId(request.getParameter("id"));
			HashMap<String, Object> map = CommentsDAO.getInstance().delete(comment);
			out.println(toXML(map));
		}
	
	
	}
	
	private String toXML(HashMap<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<result>");
		sb.append("<code>");
		sb.append(map.get("code"));
		sb.append("</code>");
		sb.append("<data>");
		Gson gson = new GsonBuilder().create();   //여기 구문은 이해가 되지않는다 
		sb.append(gson.toJson(map));
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString();
	}
	
	private String selectAll(List<HashMap<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append("<data>");
		sb.append("[");
		for(int i =0; i<list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			sb.append("{ ");
			sb.append("id:" + map.get("id"));
			sb.append(", name:\'" + map.get("name"));
			sb.append("\', content:\'" + map.get("content"));
			sb.append("\'}");
			if(i != list.size() -1) {
				sb.append(",");
			}
		}
		sb.append("]");
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
