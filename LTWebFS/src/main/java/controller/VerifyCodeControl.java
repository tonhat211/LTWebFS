package controller;

import database.UserDAO;
import database.VerifyCodeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verifyEmail")
public class VerifyCodeControl extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String code = request.getParameter("codeInput");
        String emaill = request.getParameter("emaill");

        boolean isOK =  VerifyCodeDAO.getInstance().isVerifyOk(code);
        if(isOK == true){
            UserDAO.getInstance().trueTheUser(emaill);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("email",emaill);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
            rd.forward(request, response);

        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}