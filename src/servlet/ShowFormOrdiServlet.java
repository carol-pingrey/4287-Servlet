package servlet;

import model.Ordinateur;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowFormOrdiServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String[]> modeles = Ordinateur.findModeles();
            request.setAttribute("modeles", modeles);

            String id = request.getParameter("id");

            if (id != null && !id.equals("")) {
                Ordinateur o = Ordinateur.findById(Integer.parseInt(id));
                request.setAttribute("ordinateur", o);
            }

            request.getRequestDispatcher("/ordi/form.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
