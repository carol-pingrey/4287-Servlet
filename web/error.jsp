<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Erreur</title>
</head>
<body>

    <div class="contenu">
        <h1>Une erreur est survenue</h1>

        <%
            Object error = request.getAttribute("error");
        %>
        <p style="color: red;">
            <%= error != null ? error : "Erreur inconnue." %>
        </p>

        <a href="ordi">Retour a la liste</a>
    </div>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
