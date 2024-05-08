package controller;

import dao.FactoryUser;
import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmpassword");

        UserDAO userDAO = FactoryUser.getInstance().getUserDAO();
        if (username != null && password.equals(confirmPassword)) {
            User newUser = userDAO.addUser(username, password);
            if(newUser != null) {
                req.getSession().setAttribute("username", username);
                resp.sendRedirect(req.getContextPath() + "/showUsers.jsp");
                System.out.println("success");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                System.out.println("fail");
            }
        } else {
            System.out.println("fail");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
