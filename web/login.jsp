<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<body>

    <div class="contenu">
        <h1>Connexion</h1>

        <%
            String erreur = request.getParameter("erreur");
            if (erreur != null) {
        %>
            <p style="color: red;">Login ou mot de passe incorrect.</p>
        <%
            }
        %>

        <form action="login" method="post">
            <div class="champ">
                <label for="login">Login :</label>
                <input type="text" id="login" name="login">
            </div>

            <div class="champ">
                <label for="motDePasse">Mot de passe :</label>
                <input type="password" id="motDePasse" name="motDePasse">
            </div>

            <input type="submit" value="Se connecter">
        </form>
    </div>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
