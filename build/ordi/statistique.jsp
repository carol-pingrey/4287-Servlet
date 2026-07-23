<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Statistique" %>
<%@ page import="model.OrdinateurEtat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Statistiques</title>
</head>
<body>

    <div class="contenu">
        <%
            Statistique stat = (Statistique) request.getAttribute("stat");
            String date = (String) request.getAttribute("date");
        %>

        <h1>Statistiques du <%= date %></h1>

        <a href="ordi">Retour a la liste</a>

        <ul>
            <li>Nombre d'ordinateurs : <%= stat.nbTotal %></li>
            <li>Ordinateurs fonctionnels : <%= stat.nbFonctionnel %></li>
            <li>Ordinateurs non fonctionnels : <%= stat.nbNonFonctionnel %></li>
        </ul>

        <h2>Detail des ordinateurs non fonctionnels (par type de panne)</h2>
        <table>
            <thead>
                <tr>
                    <th>Type de panne</th>
                    <th>Nombre</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Alimentation</td>
                    <td><%= stat.nbAlimentation %></td>
                </tr>
                <tr>
                    <td>Disque Dur</td>
                    <td><%= stat.nbDisqueDur %></td>
                </tr>
                <tr>
                    <td>Carte mere</td>
                    <td><%= stat.nbCarteMere %></td>
                </tr>
            </tbody>
        </table>

        <%
            List<OrdinateurEtat> lignes = (List<OrdinateurEtat>) request.getAttribute("lignes");
        %>

        <!-- <h2>Ordinateurs fonctionnels a cette date</h2>
        <table>
            <thead>
                <tr>
                    <th>Ordinateur</th>
                    <th>Observation</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < lignes.size(); i++) {
                        OrdinateurEtat oe = lignes.get(i);
                        if (oe.libelle.equals("Fonctionnel")) {
                %>
                <tr>
                    <td><%= oe.libelleOrdi %></td>
                    <td><%= (oe.observation != null) ? oe.observation : "" %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <h2>Ordinateurs non fonctionnels a cette date</h2>
        <table>
            <thead>
                <tr>
                    <th>Ordinateur</th>
                    <th>Panne</th>
                    <th>Observation</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < lignes.size(); i++) {
                        OrdinateurEtat oe = lignes.get(i);
                        if (oe.libelle.equals("Non fonctionnel")) {
                %>
                <tr>
                    <td><%= oe.libelleOrdi %></td>
                    <td><%= (oe.libellePanne != null) ? oe.libellePanne : "-" %></td>
                    <td><%= (oe.observation != null) ? oe.observation : "" %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table> -->
    </div>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
