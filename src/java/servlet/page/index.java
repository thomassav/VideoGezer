package servlet.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class index extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		try {

			try {
				request.getRequestDispatcher("header").include(request, response);
                                defaultPage(response);
				request.getRequestDispatcher("sidebar").include(request, response);
				request.getRequestDispatcher("footer").include(request, response);

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void defaultPage(HttpServletResponse response) throws IOException{
		PrintWriter page = response.getWriter();
		page.println("<!-- Content -->"+
				"<div id='content'>"+
				"<div id='content-inner'>"+
				"<video id='example_video_1' class='video-js vjs-default-skin video-wrapper'"+
				"controls preload='auto' width='640' height='264'"+
				"poster='http://video-js.zencoder.com/oceans-clip.png'"+
				"data-setup='{'example_option':true}'>"+
				"<source src='http://video-js.zencoder.com/oceans-clip.mp4' type='video/mp4' />"+
				"<source src='http://video-js.zencoder.com/oceans-clip.webm' type='video/webm' />"+
				"<source src='http://video-js.zencoder.com/oceans-clip.ogv' type='video/ogg' />"+
				"</video></div></div>");
	}
}
