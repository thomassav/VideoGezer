package servlet.include;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.object.Profil;

public class SideBar extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7661810096989508753L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		try {
			PrintWriter page = response.getWriter();
			page.println("<!-- Sidebar -->"+
					"<div id='sidebar'>"+
						"<!-- Logo -->"+
							"<div id='logo'>"+
								"<h1>Videogezer</h1>"+
							"</div>"+
						"<!-- Nav -->"+
							"<nav id='nav'>"+
								"<ul>"+
									menuConnexion(session)+
									"<li><a href='#'>Chaines</a></li>"+
									"<li><a href='#'>Surprise</a></li>"+
									"<li><a href='#'>Contact Us</a></li>"+
								"</ul>"+
							"</nav>"+
                                                        affichageProfil(session)+
						"<!-- Search -->"+
							"<section class='is-search'>"+
								"<form method='post' action='BarreDeRecherche'>"+
									"<input type='text' class='text' name='search' placeholder='Search' />"+
								"</form>"+
							"</section>"+
					
						"<!-- Text -->"+
							"<section class='is-text-style1'>"+
								"<div class='inner'>"+
									"<p>"+
										"<strong>Striped:</strong> A free and fully responsive HTML5 site>"+
										"template designed by <a href='http://n33.co/'>AJ</a> for <a href='http://html5up.net/'>HTML5 up!</a>"+
									"</p>"+
								"</div>"+
							"</section>"+
					
						"<!-- Recent Upload -->"+
							"<section class='is-recent-posts'>"+
								"<header>"+
									"<h2>Dernières vidéo uploadé</h2>"+
								"</header>"+
								"<ul>"+
									"<li><a href='#'>Nothing happened</a></li>"+
									"<li><a href='#'>My Dearest Cthulhu</a></li>"+
									"<li><a href='#'>The Meme Meme</a></li>"+
									"<li><a href='#'>Now Full Cyborg</a></li>"+
									"<li><a href='#'>Temporal Flux</a></li>"+
								"</ul>"+
							"</section>"+
					
						"<!-- Recent Comments -->"+
							"<section class='is-recent-comments'>"+
								"<header>"+
									"<h2>Derniers commentaires</h2>"+
								"</header>"+
								"<ul>"+
									"<li>case on <a href='#'>Now Full Cyborg</a></li>"+
									"<li>molly on <a href='#'>Untitled Post</a></li>"+
									"<li>case on <a href='#'>Temporal Flux</a></li>"+
								"</ul>"+
							"</section>"+						
						"<!-- Copyright -->"+
							"<div id='copyright'>"+
								"<p>"+
									"&copy; 2013 An Untitled Site.<br />"+
									"Images: <a href='http://n33.co'>n33</a>, <a href='http://fotogrph.com'>fotogrph</a><br />"+
									"Design: <a href='http://html5up.net/'>HTML5 UP</a>"+
								"</p>"+
							"</div>"+
					"</div>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String menuConnexion(HttpSession session){
		Object o = session.getAttribute("profil");
		if(o == null){
			return "<li class='current_page_item'><a href='Connexion'>Connexion</a></li>";
		}else{
			//Profil p =(Profil) session.getAttribute("profil");
			return "<li class='current_page_item'><a href='Profil'>Profil</a></li>";
		}
		
	}
        
        private String affichageProfil(HttpSession session){
		Object o = session.getAttribute("profil");
		if(o != null){
                        Profil p = (Profil) o;
			return "<nav id=\"nav\"><ul><a href='Connexion'>"+p.getEmail()+"</a></li></ul>\n</nav>";
		}else{
			//Profil p =(Profil) session.getAttribute("profil");
			return "";
		}
		
	}
}
