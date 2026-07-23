package servlet;

import model.Utilisateur;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("login");
            String motDePasse = request.getParameter("motDePasse");

            Utilisateur u = Utilisateur.checkLogin(login, motDePasse);

            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("connecte", u.login);
                session.setAttribute("role", u.role);

                response.sendRedirect(request.getContextPath() + "/ordi");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp?erreur=1");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
