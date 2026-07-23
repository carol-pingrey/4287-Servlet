<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordinateur" %>
<%@ page import="model.OrdinateurEtat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historique des etats</title>
</head>
<body>

    <div class="contenu">
        <%
            Ordinateur o = (Ordinateur) request.getAttribute("ordinateur");
        %>
        <h1>Historique des etats - #<%= o.id %> <%= o.libelleMarque %> <%= o.libelleModele %></h1>

        <a href="ordi">Retour a la liste</a>

        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Etat</th>
                    <th>Observation</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<OrdinateurEtat> historique = (List<OrdinateurEtat>) request.getAttribute("historique");
                    if (historique.isEmpty()) {
                %>
                <tr>
                    <td colspan="3">Aucun etat enregistre pour cet ordinateur.</td>
                </tr>
                <%
                    } else {
                        for (int i = 0; i < historique.size(); i++) {
                            OrdinateurEtat oe = historique.get(i);
                %>
                <tr>
                    <td><%= oe.date %></td>
                    <td><%= oe.libelle %></td>
                    <td><%= (oe.observation != null) ? oe.observation : "" %></td>
                </tr>
                <%
                        }
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
