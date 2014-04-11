package servlet.include;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class footer extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		try {
			PrintWriter page = response.getWriter();
			page.println("</div></body></html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
