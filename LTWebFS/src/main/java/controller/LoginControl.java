package controller;

import database.DecartDAO;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/check-login")
public class LoginControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isAdmin = false;
        HttpSession session = request.getSession();
        User userloging=null;
        int id = UserDAO.getInstance().checkExistUser(email);
//        neu user ton tai
        if(id!=0){

//            kiem tra dang nhap
            User u = UserDAO.getInstance().selectByEmailAndPwd(email,password);

//            dang nhap thanh cong
            if(u != null) {
                isAdmin = u.isAdmin();
                userloging = u;
                session.setAttribute("userloging",userloging);

                 if(isAdmin) {
                     RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin_dashboard.jsp");
                     rd.forward(request, response);
                 } else {
                     RequestDispatcher rd = getServletContext().getRequestDispatcher("/get-all-product");
                     rd.forward(request, response);
                 }
            }
//            dang nhap khong thanh cong
            else {
//                userloging = null;
                session.setAttribute("userloging",null);
                request.setAttribute("status","loginFailed");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }
//        tai khoan khong ton tai
        else {
            session.setAttribute("userloging",null);
            request.setAttribute("status","notUser");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isAdmin = false;
        HttpSession session = request.getSession();
        User userloging=null;
        int id = UserDAO.getInstance().checkExistUser(email);
//        neu user ton tai
        if(id!=0){

//            kiem tra dang nhap
            password = User.encodePwd(password);
            User u = UserDAO.getInstance().selectByEmailAndPwd(email,password);

//            dang nhap thanh cong
            if(u != null) {
//                isAdmin = u.isAdmin();
//                userloging = u;
                if(u.getAvailable()<1) {//kiem tra available cua tai khoan
                    session.setAttribute("userloging",null);
                    request.setAttribute("status","unavailableAccount");
                    request.setAttribute("email",email);
                    Random rand = new Random();

                    String code ="";
                    for(int i=0;i<6; i++) {
                        code+= String.valueOf(rand.nextInt(9)+1);
                    }

                    String to =email;
                    String subject="Mã xác minh email web thietbiyte";
                    String message = "Mã xác minh email của  bạn là: " +code;
                    IJavaMail emailService = new EmailService();
                    //  q       guwir code toi email khach hang
                    emailService.send(to,subject,message);
                    VerifyCodeDAO.getInstance().insertNewCode(code,email);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
                    rd.forward(request, response);
                } else {
                    session.setAttribute("userloging",u);

                    if(u.getLevel() > 0) {


                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-dashboard-admin");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
                        rd.forward(request, response);
                    }
                }

            }
//            dang nhap khong thanh cong
            else {
//                userloging = null;
                session.setAttribute("userloging",null);
                request.setAttribute("status","loginFailed");
                request.setAttribute("email",email);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }
//        tai khoan khong ton tai
        else {
            session.setAttribute("userloging",null);
            request.setAttribute("status","notUser");
            request.setAttribute("email",email);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}