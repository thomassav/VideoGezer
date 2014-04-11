package servlet.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter page = response.getWriter();
			request.getRequestDispatcher("header").include(request, response);
			request.getRequestDispatcher("sidebar").include(request, response);
			page.println(formulaire());
			request.getRequestDispatcher("footer").include(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private String formulaire() {
		// TODO Auto-generated method stub
		return "<!-- Content -->"+
				"<div id='content'>"+				
				"<div id='content-inner'>"+
				formulaireConnexion()+
				formulaireInscription()+
				"</div></div>";
	}
	private String formulaireInscription(){
		return "<div id='sidebarcentral'><form method='post' action='Inscription'>"+
 
   "<fieldset>"+
       "<legend>Inscription</legend> <!-- Titre du fieldset -->"+ 
       "<label for='email'>Quel est votre e-mail ?</label>"+
       "<input type='email' name='email' id='email' />"+
       "<label for='prenom'>Mot de passe?</label>"+
       "<input type='password' name='prenom' id='prenom' />"+
       "<input type='submit' name='inscription' id='inscription' value='inscription' />"+
   "</fieldset>"+
"</form></div>";
	}
	private String formulaireConnexion(){
		return "<div id='sidebarcentral2'><form method='post' action='servletConnexion'>"+			 
   "<fieldset>"+
       "<legend>Connexion</legend> <!-- Titre du fieldset -->"+ 
       "<label for='email'>Quel est votre e-mail ?</label>"+
       "<input type='email' name='email' id='email' />"+
       "<label for='prenom'>Mot de passe?</label>"+
       "<input type='password' name='prenom' id='prenom' />"+
       "<input type='submit' name='connexion' id='connexion' value='connexion' />"+
   "</fieldset>"+
"</form></div>";
	}
}
