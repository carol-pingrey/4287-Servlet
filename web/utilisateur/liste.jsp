<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des utilisateurs</title>
</head>
<body>

    <div class="contenu">
        <h1>Utilisateurs</h1>

        <p>Connecte en tant que : <%= session.getAttribute("connecte") %> (<%= session.getAttribute("role") %>)</p>

        <a href="ordi">Retour au parc informatique</a>

        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Utilisateur> liste = (List<Utilisateur>) request.getAttribute("utilisateurs");
                    for (int i = 0; i < liste.size(); i++) {
                        Utilisateur u = liste.get(i);
                %>
                <tr>
                    <td><%= u.id %></td>
                    <td><%= u.login %></td>
                    <td><%= u.role %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
