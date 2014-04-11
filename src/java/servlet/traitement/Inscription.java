package servlet.traitement;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import data.bdd.Bdd;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.object.Profil;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.util.StringLogger;

public class Inscription extends HttpServlet{
        @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request,response);
	}
        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			Profil p = new Profil(request.getParameter("email"),request.getParameter("mdp"));

                        //Si le mail n'est pas utiliser on inscrit
                        if(! p.exists(p.getEmail())){
                            p.sauvegardeProfilV2();
                            HttpSession session = request.getSession(true);
                            session.setAttribute("profil", p);
                            try {
                                String verification = "Inscription réussi";
                                request.setAttribute("infos", verification);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                System.out.println(verification);
                            } catch (ServletException e) {
                                    e.printStackTrace();
                            }
                        }
                        //Sinon erreur
                        else{
                            try{
                                String verification = "Email déjà existant, veuillez choisir un autre email";
                                request.setAttribute("infos", verification);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                System.out.println(verification);
                            }
                            catch(ServletException e){
                                e.printStackTrace();
                            }
                        }
            	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
