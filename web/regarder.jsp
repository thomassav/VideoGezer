<%-- 
    Document   : regarder
    Created on : 20 mars 2014, 10:40:03
    Author     : Ervin
--%>

<%@page import="java.util.List"%>
<%@page import="data.object.Vue"%>
<%@page import="data.object.Video"%>
<%@include file="includes/header.jsp" %>
<jsp:useBean id="maVideo" scope="page" class="data.object.Video">
</jsp:useBean>
<%    Video.rechercherVideoParId(maVideo, request.getParameter("video"));

    Vue v = (Vue) session.getAttribute("vue");
    if (v == null || v.finVue()) {
        out.println("ici");
        v = new Vue(maVideo.getNom());
        session.setAttribute("vue", v);
        //Ajout de vue dans la base de données
        maVideo.addVue();
        Video.ajouterVue(maVideo);
    }
%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<!-- Wrapper -->
<div id="wrapper">
    <!-- Content -->
    <div id="content">
        <div id="content-inner">
            <!-- Post -->
            <article class="is-post is-post-excerpt">
                <header>
                    <!--
                            Note: Titles and bylines will wrap automatically when necessary, so don't worry
                            if they get too long. You can also remove the "byline" span entirely if you don't
                            need a byline.
                    -->
                    <h2><a href="#"><jsp:getProperty name="maVideo" property="nom" /></a></h2>
                    <span class="byline">A free, fully responsive HTML5 site template by AJ for HTML5 UP</span>
                </header>
                <div class="info">
                    <!--
                            Note: The date should be formatted exactly as it's shown below. In particular, the
                            "least significant" characters of the month should be encapsulated in a <span>
                            element to denote what gets dropped in 1200px mode (eg. the "uary" in "January").
                            Oh, and if you don't need a date for a particular page or post you can simply delete
                            the entire "date" element.
                            
                    -->
                    <span class="date"><span class="month">Jan<span>uary</span></span> <span class="day">14</span><span class="year">, 2013</span></span>
                    <!--
                            Note: You can change the number of list items in "stats" to whatever you want.
                    -->
                    <ul class="stats">
                        <li><a href="#commentaires" class="fa fa-comment">16</a></li>
                        <li><a href="#" class="fa fa-play"><jsp:getProperty name="maVideo" property="nbVues" /></a></li>
                        <li><a class="fa fa-twitter">64</a></li>
                        <li><a class="fa fa-facebook">128</a></li>
                    </ul>
                </div>
                Date upload :<jsp:getProperty name="maVideo" property="dateUpload" />
                <video id="home_video" controls preload="auto" poster="/img/poster.jpg" width="720" height="320" class="video-js vjs-default-skin">
                    <%  for(int i =0; i< maVideo.nbResolution();i++) { %>
                        <source src="video/<jsp:getProperty name="maVideo" property="idBdd" />?hd" type="video/mp4" data-res="HD" />
                    <% }%>
                </video>
            </article>



            <div id="slider">  <br>
                <%
                    List<Video> videoRecommander = Video.rechercheVideo(" ", " ", "recommandation");
                    for(Video video : videoRecommander){
                %><section>
                    <video id="video<%out.print(video.getId());%>" controls preload="none" poster="" width="200" height="100" class="video-js vjs-default-skin">
                        <source src="<% out.print(video.getEmplacement());%>" type="video/mp4" data-res="HD" >
                    </video>
                </section> <% } %></div> 
            <script type="text/javascript">
                vjs('home_video', {
                    plugins: {
                        resolutions: true
                    }
                });
            </script>
        </div>
    </div>
    <%@include file="includes/sidebar.jsp" %>
</div>
<%@include file="includes/footer.jsp" %>

