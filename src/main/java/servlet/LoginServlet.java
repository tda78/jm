package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = UserServiceImpl.getInstance().login(
                    req.getParameter("name"),
                    req.getParameter("password"));
            if (UserServiceImpl.getInstance().getTempUser().getRole().equals("admin")) {
                List<User> users = UserServiceImpl.getInstance().getAllUsers();
                req.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            if (UserServiceImpl.getInstance().getTempUser().getRole().equals("user")) {
                req.setAttribute("user", UserServiceImpl.getInstance().getTempUser());
                getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);

            }
            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
