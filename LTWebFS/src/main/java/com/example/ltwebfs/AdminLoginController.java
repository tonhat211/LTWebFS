package com.example.ltwebfs;

import database.LoginDAO;
import database.UserDAO;
import model.User;
import service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminLoginController", value = "/AdminLoginController")
//@WebServlet(name = "login", urlPatterns = {"/login"})
public class AdminLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = UserDAO.getInstance();
        try {
            ArrayList<User> admins = userDAO.findAdmin();
            for (User user : admins) {
                if (user.getEmail().equals(email) && user.getPwd().equals(password)) {
                    response.sendRedirect("/admin.html");
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
