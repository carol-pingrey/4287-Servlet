package servlet;

import model.Statistique;
import model.OrdinateurEtat;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatistiqueServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("connecte") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            String dateParam = request.getParameter("date");

            String[] p = dateParam.split("/");
            String dateSql = p[2] + "-" + p[1] + "-" + p[0];

            Statistique stat = Statistique.pourDate(dateSql);
            List<OrdinateurEtat> lignes = OrdinateurEtat.findByDate(dateSql);

            request.setAttribute("date", dateParam);
            request.setAttribute("stat", stat);
            request.setAttribute("lignes", lignes);
            request.getRequestDispatcher("/ordi/statistique.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
