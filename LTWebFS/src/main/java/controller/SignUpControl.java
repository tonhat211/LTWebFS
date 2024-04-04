package controller;

import database.*;
import model.*;
import model.JavaMail.EmailService;
import model.JavaMail.IJavaMail;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/signup")
public class SignUpControl extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        request.setAttribute("email", email);
//        if (userService.validate(name, email, password, phone, address, info)) {
        userDAO.insert(user);
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

        VerifyCodeDAO.getInstance().insertNewCode(code);



        RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
        rd.forward(request, response);
//        } else {
//            response.sendRedirect("logup.jsp");
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
}