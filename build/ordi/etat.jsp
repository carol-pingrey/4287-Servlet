<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordinateur" %>
<%@ page import="model.Etat" %>
<%@ page import="model.Etatpanne" %>
<!DOCTYPE html>
<html>
<head>
    <title>Changer l'etat d'un ordinateur</title>
</head>
<body>

    <div class="contenu">
        <h1>Changer l'etat d'un ordinateur</h1>

        <form action="ordiEtat" method="post">

            <div class="champ">
                <label for="idOrdi">Ordinateur :</label>
                <select id="idOrdi" name="idOrdi">
                    <%
                        List<Ordinateur> ordis = (List<Ordinateur>) request.getAttribute("ordinateurs");
                        for (int i = 0; i < ordis.size(); i++) {
                            Ordinateur o = ordis.get(i);
                    %>
                        <option value="<%= o.id %>">#<%= o.id %> - <%= o.libelleMarque %> <%= o.libelleModele %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="champ">
                <label for="idEtat">Etat :</label>
                <select id="idEtat" name="idEtat">
                    <%
                        List<Etat> etats = (List<Etat>) request.getAttribute("etats");
                        for (int i = 0; i < etats.size(); i++) {
                            Etat e = etats.get(i);
                    %>
                        <option value="<%= e.id %>"><%= e.libelle %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="champ" id="champPanne">
                <label for="idPanne">Type de panne :</label>
                <select id="idPanne" name="idPanne">
                    <option value="">-- aucune --</option>
                    <%
                        List<Etatpanne> pannes = (List<Etatpanne>) request.getAttribute("pannes");
                        for (int i = 0; i < pannes.size(); i++) {
                            Etatpanne p = pannes.get(i);
                    %>
                        <option value="<%= p.id %>"><%= p.libelle %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="champ">
                <label for="date">Date :</label>
                <input type="date" id="date" name="date">
            </div>

            <div class="champ">
                <label for="observation">Observation :</label>
                <input type="text" id="observation" name="observation" placeholder="ex: ecran eteint, probleme d'alimentation">
            </div>

            <input type="submit" value="OK">
        </form>

        <br>
        <a href="ordi">Retour a la liste</a>
    </div>

    <script>
        var selectEtat = document.getElementById("idEtat");
        var champPanne = document.getElementById("champPanne");

        function majPanne() {
            var texte = selectEtat.options[selectEtat.selectedIndex].text;
            if (texte === "Non fonctionnel") {
                champPanne.style.display = "";
            } else {
                champPanne.style.display = "none";
                document.getElementById("idPanne").value = "";
            }
        }

        selectEtat.onchange = majPanne;
        majPanne(); 
    </script>

    <footer style="position: fixed; bottom: 0; left: 0; width: 100%; text-align: center;">
        ETU4287 - Projet Parc Informatique
    </footer>

</body>
</html>
