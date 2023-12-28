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

//@WebServlet(name = "AdminLoginController", value = "/AdminLoginController")
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
                response.sendRedirect("admin.html");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}