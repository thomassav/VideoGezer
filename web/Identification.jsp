<%-- 
    Document   : Identification
    Created on : 12 mars 2014, 10:49:53
    Author     : manuelrodrigues
--%>
<jsp:include page="<%="includes/header.jsp"%>"/>
<jsp:include page="<%="includes/sidebar.jsp"%>"/>

    <div id='container'>
        <form method='post' action='Inscription'>
            <fieldset>
                Mon formulaire d'inscription
                <legend>Inscription</legend>
                <label style="width:100px; margin-left: 400px;" for='email'>Email</label>
                <input style="width:100px; margin-left: 400px;" name="email" type="text"/>
                <label style="width:100px; margin-left: 400px;" for='password'>Mot de passe</label>
                <input style="width:100px; margin-left: 400px;" type='password' name='password' id='password' />
                <input style="width:100px; margin-left: 400px;" type='submit' name='submit' id='submit' value="M'inscrire" />
            </fieldset>
        </form>
    </div>


    <div id='container'>
        <form method='post' action='Connexion'>
            <fieldset>
                Mon formulaire de connexion
                <legend>Connexion</legend>
                <label style="width:100px; margin-left: 400px;" for='email'>Email</label>
                <input style="width:100px; margin-left: 400px;" type='email' name='email' id='email' />
                <label style="width:100px; margin-left: 400px;" for='password'>Mot de passe</label>
                <input style="width:100px; margin-left: 400px;" type='password' name='password' id='password' />
                <input style="width:100px; margin-left: 400px;" type='submit' name='submit' id='submit' value="Me connecter" />
            </fieldset>
        </form>
    </div>

        
<jsp:include page="<%="includes/footer.jsp"%>"/>
