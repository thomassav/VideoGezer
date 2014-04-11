/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet.page;

import data.object.Video;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ervin
 */
@WebServlet(name = "affichageRechercheVideo", urlPatterns = {"/affichageRechercheVideo"})
public class affichageRechercheVideo extends HttpServlet {

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
            out.println("<title>Servlet affichageRechercheVideo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet affichageRechercheVideo at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        PrintWriter page = response.getWriter();
        List<Video> listeVideo = (ArrayList<Video>) request.getAttribute("listVideo");
        
        request.getRequestDispatcher("header").include(request, response);
                                afficherVideo(response,listeVideo);
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

    public void afficherVideo(HttpServletResponse response,List<Video> listeVideo)  throws  IOException{
        PrintWriter page = response.getWriter();
        System.out.println(listeVideo.size());
        page.println("<!-- Content -->"+
				"<div id='content'>"+
				"<div id='content-inner'>"+ formulaireRecherche());
        for(Video v : listeVideo){
            page.println("<section><aside style= 'width: 500px;float: right;'><a href=''><h4>"+v.getNom()+"</h4></a></aside>"+
				"<article><video id='example_video_1' class='video-js vjs-default-skin video-wrapper'"+
				"controls preload='auto' width='184' height='104'"+
				"data-setup='{'example_option':true}'>"+
				"<source src='"+v.getEmplacement()+"' type='video/mp4' /></video></article></section><br>");
				
        }
        page.println("</div></div>");
    }
    
    public String formulaireRecherche(){
        return "<!-- Search -->"+
							"<section class='is-search'>"+
								"<form method='post' action='BarreDeRecherche2'>"+
									"<input type='text' class='text' name='search2' placeholder='Search' />"+
								"</form>"+
							"</section>";
    }
}
