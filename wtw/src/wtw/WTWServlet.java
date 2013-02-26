package wtw;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WTWServlet
 */
public class WTWServlet extends HttpServlet {
	public WTWServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("url");
		if(url != null) {
			HttpSession session = request.getSession();
			WTWClient client = (WTWClient)session.getAttribute("client");
			if(client == null) {
				client = new WTWClient();
				session.setAttribute("client", client);
			}
			if(!url.startsWith("http://")) url = "http://" + url;
			System.out.println("url:"+url);
			String content = client.getHtmlContent(url);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(content);
			out.close();
		}
	}
}
