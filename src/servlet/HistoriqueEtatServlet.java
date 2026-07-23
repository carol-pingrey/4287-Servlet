package servlet;

import model.Ordinateur;
import model.OrdinateurEtat;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoriqueEtatServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Ordinateur o = Ordinateur.findById(id);
            List<OrdinateurEtat> historique = OrdinateurEtat.findByOrdi(id);

            request.setAttribute("ordinateur", o);
            request.setAttribute("historique", historique);
            request.getRequestDispatcher("/ordi/historique.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
