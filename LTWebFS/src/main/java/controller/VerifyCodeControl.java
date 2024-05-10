package controller;

import database.LogDAO;
import database.UserDAO;
import database.VerifyCodeDAO;
import model.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verifyEmail")
public class VerifyCodeControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String code = request.getParameter("codeInput");
        String email = request.getParameter("email");

        boolean isOK =  VerifyCodeDAO.getInstance().isVerifyOk(code,email);
        if(isOK == true){
            int status = UserDAO.getInstance().checkAvailable(email);
            UserDAO.getInstance().availableUser(email);
            request.setAttribute("status", "verifySuccessful");

            Log log = new Log(request.getRemoteAddr(),email + " | verify_code ","Xác minh tài khoản thành công." ,email + " | " + status,email + " | 1",1 );
            LogDAO.getInstance().insert(log);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);

        } else {

            request.setAttribute("email",email);
            request.setAttribute("status","verifyFailed");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
            rd.forward(request, response);

        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}