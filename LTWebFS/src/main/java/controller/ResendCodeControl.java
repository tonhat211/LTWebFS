package controller;

import database.UserDAO;
import database.VerifyCodeDAO;
import model.JavaMail.EmailService;
import model.JavaMail.IJavaMail;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/resendCode")
public class ResendCodeControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.getRequestDispatcher("logup.jsp").forward(request, response);
        String email = request.getParameter("email");
        Random rand = new Random();
        int ranNum = rand.nextInt(9)+1;
        String code ="";
        for(int i=0;i<6; i++) {
            code+= String.valueOf(rand.nextInt(9)+1);
        }

        String to =email;
        String subject="Mã xác minh email web thietbiyte63";
        String message = "Mã xác minh email của  bạn là: " +code;
        IJavaMail emailService = new EmailService();
        //  q       guwir code toi email khach hang
        emailService.send(to,subject,message);
        VerifyCodeDAO.getInstance().insertNewCode(code, email);
        request.setAttribute("email", email);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Random rand = new Random();
//        int ranNum = rand.nextInt(9)+1;
//        String code ="";
//        for(int i=0;i<6; i++) {
//            code+= String.valueOf(rand.nextInt(9)+1);
//        }
//        VerifyCodeDAO.getInstance().insertNewCode(code,);
//  q       guwir code toi email khach hang

//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
//        rd.forward(request, response);
    }
}