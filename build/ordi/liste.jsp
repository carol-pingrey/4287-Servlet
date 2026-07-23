<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordinateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des ordinateurs</title>
</head>
<body>

    <div class="contenu">
        <h1>Parc informatique</h1>

        <a href="ShowForm">Ajouter un ordinateur</a>
        &nbsp;|&nbsp;
        <a href="utilisateurs">Voir les utilisateurs</a>
        &nbsp;|&nbsp;
        <a href="ordiEtat">Changer l'etat d'un ordinateur</a>
        &nbsp;|&nbsp;
        <a href="ordi/liste/json">Voir la liste en JSON</a>
        &nbsp;|&nbsp;
        <%
            if (session.getAttribute("connecte") == null) {
        %>
            <a href="login.jsp">Se connecter</a>
        <%
            } else {
        %>
            <a href="logout">Se deconnecter</a>
        <%
            }
        %>

        <!-- <form action="statistique" method="get">
            Statistiques du (jj/mm/aaaa) :
            <input type="text" name="date" placeholder="06/07/2026">
            <input type="submit" value="Voir">
        </form> -->

        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Marque</th>
                    <th>Modèle</th>
                    <th>Processeur</th>
                    <th>RAM</th>
                    <th>Disque dur</th>
                    <th>Etat actuel</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Ordinateur> liste = (List<Ordinateur>) request.getAttribute("ordinateurs");
                    for (int i = 0; i < liste.size(); i++) {
                        Ordinateur o = liste.get(i);
                %>
                <tr>
                    <td><%= o.id %></td>
                    <td><%= o.libelleMarque %></td>
                    <td><%= o.libelleModele %></td>
                    <td><%= o.processeur %></td>
                    <td><%= o.ram %> Go</td>
                    <td><%= o.disqueDur %> Go</td>
                    <td><%= (o.etatActuel != null) ? o.etatActuel : "-" %></td>
                    <td>
                        <a href="ShowForm?id=<%= o.id %>">Modifier</a>
                        &nbsp;|&nbsp;
                        <a href="historique?id=<%= o.id %>">Historique</a>
                        &nbsp;|&nbsp;
                        <a href="ordi?id=<%= o.id %>" onclick="return confirm('Supprimer cet ordinateur ?');">Supprimer</a>
                    </td>
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
