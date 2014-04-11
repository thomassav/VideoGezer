<%-- 
    Document   : index
    Created on : 29 mars 2014, 16:33:15
    Author     : Ervin
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="data.object.Video"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
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
                    <h3>Video du moment</h3>
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
                        <li><a href="#" class="fa fa-heart">32</a></li>
                        <li><a class="fa fa-twitter">64</a></li>
                        <li><a class="fa fa-facebook">128</a></li>
                    </ul>
                </div>
            </article>
            <% List<Video> listVideo = Video.rechercheVideo(" ", " ", "aleatoire"); 
                for(Video v : listVideo){
                    %>
                    <section><aside style= 'width: 500px;float: right;'><a href="regarder.jsp?video=<% out.print(v.getId());%>" target="_blank"><% out.print(v.getNom());%></a></aside>
                    <video id="video<% out.print(v.getId());%>" controls preload="none" poster="" width="200" height="100" class="video-js vjs-default-skin" data-setup="{}">
                        <source src=<% out.print(v.getEmplacement()); %>'?HD' type="video/mp4" data-res="HD" />
                        <source src=<% out.print(v.getEmplacement()); %>'?SD' type="video/mp4" data-res="SD" data-default="true" />
                    </video>
                <script type="text/javascript">
                    vjs('video<% out.print(v.getId());%>', {
                        plugins: {
                            resolutions: true
                        }
                    });
                </script>
            </section><br>
                    <%
                }
            %>
            <div id="slider"></div>
        </div>
    </div>
    <%@include file="includes/sidebar.jsp" %>
</div>
<%@include file="includes/footer.jsp" %>
