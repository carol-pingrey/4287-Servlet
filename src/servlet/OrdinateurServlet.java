package servlet;

import model.Ordinateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdinateurServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getRequestURI().endsWith("/ordi/liste/json")) {
                List<Ordinateur> liste = Ordinateur.findAll();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(liste);

                response.getWriter().print(json);
                return;
            }

            String id = request.getParameter("id");

            if (id != null && !id.equals("")) {
                if (request.getSession().getAttribute("connecte") == null) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }
                Ordinateur.delete(Integer.parseInt(id));
            }

            List<Ordinateur> liste = Ordinateur.findAll();
            request.setAttribute("ordinateurs", liste);
            request.getRequestDispatcher("/ordi/liste.jsp").forward(request, response);
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

        try {
            String id = request.getParameter("id");
            int idModele = Integer.parseInt(request.getParameter("idModele"));
            String processeur = request.getParameter("processeur");
            int ram = Integer.parseInt(request.getParameter("ram"));
            int disqueDur = Integer.parseInt(request.getParameter("disqueDur"));

            Ordinateur o = new Ordinateur(idModele, processeur, ram, disqueDur);

            if (id == null || id.equals("")) {
                Ordinateur.save(o);
            } else {
                o.setId(Integer.parseInt(id));
                Ordinateur.update(o);
            }

            response.sendRedirect(request.getContextPath() + "/ordi");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
