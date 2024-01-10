package com.example.ltwebfs;

import database.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logup", urlPatterns = {"/logup"})
public class LogupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("logup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int level = 0;
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String info = request.getParameter("info");

        User user = new User(name, email, password, level, phone, address, info);
        UserDAO userDAO = UserDAO.getInstance();
        UserService userService = new UserService();
        if (userService.validate(name, email, password, phone, address, info)) {
            if (userDAO.insert(user) > 0) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("logup.jsp");
            }
        } else {
            response.sendRedirect("logup.jsp");
        }
    }
}
