/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet.page;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ervin
 */
@WebServlet(name = "Profil", urlPatterns = {"/Profil"})
public class Profil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Profil</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profil at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter page = response.getWriter();
			request.getRequestDispatcher("header").include(request, response);
                        page.println(contenuProfil());
			request.getRequestDispatcher("sidebar").include(request, response);
			request.getRequestDispatcher("footer").include(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String contenuProfil(){
        return "<div id=\"content\">\n" +
"						<div id=\"content-inner\">"+							
                "<!-- Post -->\n" +
"								<article class=\"is-post is-post-excerpt\">\n" +
"									<header>\n" +
"										<h2><a href=\"#\">Lorem ipsum dolor sit amet</a></h2>\n" +
"										<span class=\"byline\">Feugiat interdum sed commodo ipsum consequat dolor nullam metus</span>\n" +
"									</header>\n" +
"									<div class=\"info\">\n" +
"										<span class=\"date\"><span class=\"month\">Jan<span>uary</span></span> <span class=\"day\">8</span><span class=\"year\">, 2013</span></span>\n" +
"										<ul class=\"stats\">\n" +
"											<li><a href=\"#formUploadVideo\" class=\"fa fa-upload\">16</a></li>\n" +
"											<li><a href=\"#mesVideos\" class=\"fa fa-video-camera\">32</a></li>\n" +
"											<li><a href=\"#Gezer\" class=\"fa fa-user\">64</a></li>\n" +
"											<li><a href=\"#followers\" class=\"fa fa-users\">128</a></li>\n" +
"										</ul>\n" +
"									</div>\n" +
"									<a href=\"#\" class=\"image image-full\"><img src=\"images/fotogrph-dark-stairwell.jpg\" alt=\"\" /></a>\n" +
"									<p>\n" +
"										Quisque vel sapien sit amet tellus elementum ultricies. Nunc vel orci turpis. Donec id malesuada metus. \n" +
"										Nunc nulla velit, fermentum quis interdum quis, tate etiam commodo lorem ipsum dolor sit amet dolore. \n" +
"										Quisque vel sapien sit amet tellus elementum ultricies. Nunc vel orci turpis. Donec id malesuada metus. \n" +
"										Nunc nulla velit, fermentum quis interdum quis, convallis eu sapien. Integer sed ipsum ante.\n" +
"									</p>\n" +
                affichageUploadVideo()+
"								</article></div></div>";
    }
    
    public String affichageUploadVideo(){
        return "<form method=\"post\" id='formUploadVideo' action='ServletUploadVideo' enctype=\"multipart/form-data\">\n" +
"     <label for=\"mon_fichier\">Video à uploader (tous formats | max. 1 Mo) :</label><br />\n" +
"     <input type=\"hidden\" name=\"MAX_FILE_SIZE\" value=\"1048576\" />\n" +
"     <input type=\"file\" name=\"mon_fichier\" id=\"mon_fichier\" /><br />\n" +
"     <label for=\"titre\">Titre du fichier (max. 50 caractères) :</label><br />\n" +
"     <input type=\"text\" name=\"titre\" value=\"Titre du fichier\" id=\"titre\" /><br />\n" +
"     <label for=\"description\">Description de votre fichier (max. 255 caractères) :</label><br />\n" +
"     <textarea name=\"description\" id=\"description\"></textarea><br />\n" +
"     <input type=\"submit\" name=\"submit\" value=\"Envoyer\" />\n" +
"</form>";
    }
}
