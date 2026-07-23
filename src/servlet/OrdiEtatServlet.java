package servlet;

import model.Ordinateur;
import model.Etat;
import model.Etatpanne;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdiEtatServlet extends HttpServlet {

    private static final String PHP_URL = "http://localhost/ParcInformatique/php/ordi_etat.php";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Ordinateur> ordis = Ordinateur.findAll();
            List<Etat> etats = Etat.findAll();
            List<Etatpanne> pannes = Etatpanne.findAll();
            request.setAttribute("ordinateurs", ordis);
            request.setAttribute("etats", etats);
            request.setAttribute("pannes", pannes);
            request.getRequestDispatcher("/ordi/etat.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("connecte") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String idOrdi = request.getParameter("idOrdi");
        String idEtat = request.getParameter("idEtat");
        String idPanne = request.getParameter("idPanne");
        String date = request.getParameter("date");
        String observation = request.getParameter("observation");

        String connecte = (String) request.getSession().getAttribute("connecte");

        try {
            String data = "idOrdi=" + idOrdi
                    + "&idEtat=" + idEtat
                    + "&idPanne=" + idPanne
                    + "&date=" + date
                    + "&observation=" + java.net.URLEncoder.encode(observation, "UTF-8")
                    + "&connecte=" + java.net.URLEncoder.encode(connecte, "UTF-8");

            URL url = new URL(PHP_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.flush();
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String reponse = br.readLine();
            br.close();

            if (reponse != null && reponse.startsWith("ok")) {
                response.sendRedirect(request.getContextPath() + "/ordi");
            } else {
                request.setAttribute("error", "Erreur cote PHP : " + reponse);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
