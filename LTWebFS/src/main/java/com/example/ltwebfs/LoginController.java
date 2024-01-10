package com.example.ltwebfs;

import database.LoginDAO;
import database.UserDAO;
import model.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();
        LoginDAO loginDAO = new LoginDAO();
        UserDAO userDAO = new UserDAO();
        User user = loginDAO.checkLogin(email, password);
        User user2 = userDAO.getUserByEmail(email);
        if (user != null) {
            if (user2.getLevel() == 2) {
                request.setAttribute("admin_name", user2.getName());
                request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
            } else {
                request.setAttribute("user_name", user2.getName());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}