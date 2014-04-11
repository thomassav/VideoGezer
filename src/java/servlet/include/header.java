package servlet.include;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class header extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            PrintWriter page = response.getWriter();
            page.println("<html>"
                    + "<head>"
                    + "<title>Striped by HTML5 UP</title>"
                    + "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
                    + "<meta name='description' content='' />"
                    + "<meta name='keywords' content='' />"
                    + "<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400italic,700|Open+Sans+Condensed:300,700' rel='stylesheet' />"
                    + "<script src='/VideoGezer/js/jquery.min.js'></script>"
                    + "<script src='/VideoGezer/js/skel.min.js'></script>"
                    + "<script src='/VideoGezer/js/skel-panels.min.js'></script>"
                    + "<script src='/VideoGezer/js/init.js'></script>"
                    + "<script type='text/javascript' src='/VideoGezer/video-js/video.dev.js'></script>\n"
                    + "<script type='text/javascript' src='video-js-resolutions-master/video-js-resolutions.js'></script>\n"
                    + "<link href='video-js/video-js.min.css' rel='stylesheet' type='text/css'/>\n"
                    + "<link href='video-js-resolutions-master/video-js-resolutions.css' rel='stylesheet' type='text/css'/>"
                    + "<noscript>"
                    + "<link rel='stylesheet' href='/css/skel-noscript.css' />"
                    + "<link rel='stylesheet' href='/css/style.css' />"
                    + "<link rel='stylesheet' href='/css/style-desktop.css' />"
                    + "<link rel='stylesheet' href='/css/style-wide.css' />"
                    + "</noscript>"
                    + "<!--[if lte IE 9]><link rel='stylesheet' href='css/ie9.css' /><![endif]-->"
                    + "<!--[if lte IE 8]><script src='/js/html5shiv.js'></script><link rel='stylesheet' href='css/ie8.css' /><![endif]-->"
                    + "<!--[if lte IE 7]><link rel='stylesheet' href='/css/ie7.css' /><![endif]-->"
                    + "</head>"
                    + "<!--"
                    + "Note: Set the body element's class to 'left-sidebar' to position the sidebar on the left."
                    + "Set it to 'right-sidebar' to, you guessed it, position it on the right."
                    + "-->"
                    + "<body class='left-sidebar'><div id='wrapper'>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
