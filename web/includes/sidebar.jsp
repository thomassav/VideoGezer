<%-- 
    Document   : sidebar
    Created on : 12 mars 2014, 11:33:13
    Author     : manuelrodrigues
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id='sidebar'>
    <!-- Logo -->
    <div id='logo'>
        <h1>Videogezer</h1>
    </div>
    <!-- Nav -->
    <nav id='nav'>
        <ul>
            <%
                if(session.getAttribute("profil") != null){
                    out.println("Bonjour "+ session.getAttribute("profil").toString());
                    out.println("<form action='Deconnexion' method='post' ><input type='hidden' name='deco' value='1'/><input type='submit' value='Me deconnecter'/></form>");
                    %> <li><a href='Profil'>Profil</a></li><%
                }
                else out.println("<li class='current_page_item'><a href='Identification.jsp'>Connexion</a></li>");                
            %>
            <li><a href='#'>Chaines</a></li>
            <li><a href='#'>Surprise</a></li>
            <li><a href='#'>Contact Us</a></li>
        </ul>
    </nav>
    <section class='is-search'>
        <form method='post' action='BarreDeRecherche'>
            <input type='text' class='text' name='search' placeholder='Search' />
        </form>
    </section>

    <!-- Text -->
    <section class='is-text-style1'>
        <div class='inner'>
            <p>
                <strong>Striped:</strong> A free and fully responsive HTML5 site>
                template designed by <a href='http://n33.co/'>AJ</a> for <a href='http://html5up.net/'>HTML5 up!</a>
            </p>
        </div>
    </section>

</div>