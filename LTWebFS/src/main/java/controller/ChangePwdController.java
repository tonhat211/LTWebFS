package controller;

import database.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/change-pwd")
public class ChangePwdController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        User u = (User) session.getAttribute("userloging");

        String currentPwd = request.getParameter("currentPwd");
        String newPwd = request.getParameter("newPwd");
        String renewPwd = request.getParameter("renewPwd");

        currentPwd = User.encodePwd(currentPwd);
        User u2 = UserDAO.getInstance().selectByEmailAndPwd(u.getEmail(),currentPwd);
       if(u2!=null) {
           if(u.getId() == u2.getId()) {
               if (newPwd.equals(renewPwd)) {
                   newPwd = User.encodePwd(newPwd);
                   UserDAO.getInstance().updatePassword(u.getId(),newPwd);
                   request.setAttribute("status", "changePwdSuccessful");
                   request.setAttribute("id",u.getId());
                   RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer-info");
                   rd.forward(request, response);
               } else {
                   request.setAttribute("status", "notTheSamePwd");
//
                   RequestDispatcher rd = getServletContext().getRequestDispatcher("/changePwd.jsp");
                   rd.forward(request, response);
               }
           } else {
               request.setAttribute("status", "incorrectPwd");
               request.setAttribute("id",u.getId());
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/changePwd.jsp");
               rd.forward(request, response);
           }
       } else {
           request.setAttribute("status", "incorrectPwd");
           request.setAttribute("id",u.getId());
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/changePwd.jsp");
           rd.forward(request, response);
       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
}