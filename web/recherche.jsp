<%-- 
    Document   : recherche
    Created on : 20 mars 2014, 11:29:09
    Author     : Ervin
--%>
<%@include file="includes/header.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="data.object.Video"%>
<% List<Video> listeVideo = (ArrayList<Video>) request.getAttribute("listVideo");
%>
<!-- Wrapper -->
<div id="wrapper">
    <!-- Content -->
    <div id="content">
       
        <div id="content-inner">
             <div><span>filtre</span>
                 <a href="BarreDeRecherche?tri=Pertinence&amp;search=<% out.print((String) request.getAttribute("search")); %>">Pertinence</a>
            <a href="test/test.jsp">Nombre de vues</a>
            <a href="BarreDeRecherche?tri=Recent&amp;search=<% out.print((String) request.getAttribute("search")); %>">Date d'upload</a>
        </div>
            <!-- Post -->
            <%
                out.print("Resultats : " + listeVideo.size() +  " video(s)");
                for (Video v : listeVideo) {
            %>
            <% out.print(v.getEmplacement()); %>
            <section><aside style= 'width: 500px;float: right;'><a href="regarder.jsp?video=<% out.print(v.getId());%>" ><% out.print(v.getNom());%></a></aside>
                    <video id="video<% out.print(v.getId());%>" controls preload="none" poster="" width="200" height="100" class="video-js vjs-default-skin">
                        <source src=<% out.print(v.getEmplacement()); %> type="video/mp4" data-res="HD" />
                    </video>
                <script type="text/javascript">
                    vjs('video<% out.print(v.getId());%>', {
                        plugins: {
                            resolutions: true
                        }
                    });
                </script>
            </section><br>
                <% }%>
        </div>
        <div class="pager"></div>
    </div>
    <%@include file="includes/sidebar.jsp" %>
</div>
<%@include file="includes/footer.jsp" %>
