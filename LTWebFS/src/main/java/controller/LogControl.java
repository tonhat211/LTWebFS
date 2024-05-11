package controller;

import database.EmployeeDAO;
import database.LogDAO;
import database.UserDAO;
import database.VerifyCodeDAO;
import model.Employee;
import model.JavaMail.EmailService;
import model.JavaMail.IJavaMail;
import model.Log;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet("/log-control")
public class LogControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        switch (action) {
            case "alert": {
                LogDAO.getInstance().deleteAlert();

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin-menu-controller?adminMenu=log&index=1");
                rd.forward(request, response);
                break;
            }
        }




    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }


}