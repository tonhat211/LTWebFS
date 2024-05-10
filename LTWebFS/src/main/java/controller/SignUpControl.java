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

        int idin = UserDAO.getInstance().selectTheMaxID() +1;
//                  xu ly cap nhat
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");


        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String info = sex + "=" + birthday;


        Datee dateinDatee = Datee.getToday();

        User u = new User(idin,name,email,"1234",0,phone,address,0,info,dateinDatee,null,0,null);


//        kiem tra mat khau co da khop chua
        if(!password.equals(repassword)) { //mat khau chua khop
            request.setAttribute("status","differentpwd");
            request.setAttribute("userTemp",u);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
            rd.forward(request, response);
        } else { //mat khau da khop
//            kiem tra email da duoc dang ki tu truoc chua
            int id = UserDAO.getInstance().checkExistUser(email);
            if(id != 0) {
                request.setAttribute("status","existeduser");
                request.setAttribute("userTemp",u);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
                rd.forward(request, response);
            } else {
//                mat khau va email da dat yeu cau
//                tien hanh them tai khoan
                u.encodePwd();
                UserDAO.getInstance().insert(u);

                request.setAttribute("email", email);
                //        xu ly tao code gui ve email de xac thuc
                Random rand = new Random();
                int ranNum = rand.nextInt(9)+1;
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

                DecartDAO.getInstance().insertCart(idin);

//                ghi log
                String ipAddress = request.getRemoteAddr();
                Log t = new Log(ipAddress,email + " | signup ","Thêm tài khoản mới vào hệ thống","trống",u.toString(),1 );
                LogDAO.getInstance().insert(t);

                VerifyCodeDAO.getInstance().insertNewCode(code,email);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
                rd.forward(request, response);

            }
        }
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