package com.example.ltwebfs;

import database.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logup", urlPatterns = {"/logup"})
public class LogupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String info = request.getParameter("info");

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.register(name, email, password, phone, address, info);
        response.sendRedirect("login.jsp");

    }
}
