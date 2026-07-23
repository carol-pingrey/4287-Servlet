<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordinateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un ordinateur</title>
</head>
<body>

    <div class="contenu">

        <%
            Ordinateur ordi = (Ordinateur) request.getAttribute("ordinateur");
        %>

        <h1><% if (ordi != null) { %>Modifier un ordinateur<% } else { %>Ajouter un ordinateur<% } %></h1>

        <form action="ordi" method="post">

            <input type="hidden" name="id" value="<%= (ordi != null) ? ordi.id : "" %>">

            <div class="champ">
                <label for="idModele">Modèle :</label>
                <select id="idModele" name="idModele">
                    <%
                        List<String[]> modeles = (List<String[]>) request.getAttribute("modeles");
                        for (int i = 0; i < modeles.size(); i++) {
                            String[] m = modeles.get(i);
                            boolean selected = (ordi != null) && (Integer.parseInt(m[0]) == ordi.idModele);
                    %>
                        <option value="<%= m[0] %>" <% if (selected) { %>selected<% } %>><%= m[1] %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="champ">
                <label for="processeur">Processeur :</label>
                <input type="text" id="processeur" name="processeur" value="<%= (ordi != null) ? ordi.processeur : "" %>">
            </div>

            <div class="champ">
                <label for="ram">RAM (Go) :</label>
                <input type="number" id="ram" name="ram" min="1" value="<%= (ordi != null) ? ordi.ram : "" %>">
            </div>

            <div class="champ">
                <label for="disqueDur">Disque dur (Go) :</label>
                <input type="number" id="disqueDur" name="disqueDur" min="1" value="<%= (ordi != null) ? ordi.disqueDur : "" %>">
            </div>

            <input type="submit" value="Enregistrer">
        </form>

        <br>
        <a href="ordi">Retour à la liste</a>
    </div>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
