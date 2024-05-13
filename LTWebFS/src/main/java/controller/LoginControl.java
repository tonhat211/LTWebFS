package controller;

import database.*;
import model.Employee;
import model.JavaMail.EmailService;
import model.JavaMail.IJavaMail;
import model.Log;
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
import java.util.HashMap;
import java.util.Map;
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
        int isExist = UserDAO.getInstance().checkExistUser(email);

//        neu user ton tai
        if(isExist!=0){
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
        String ipAddress = request.getRemoteAddr();


//        neu user ton tai
        if(id!=0){

//            kiem tra xem tai khoan co dang bi tam khoa khong
            int avai = UserDAO.getInstance().checkAvailable(email);
            if(avai == -1) {
                session.setAttribute("userloging",null);
                request.setAttribute("status","lockedTemporarilyAccount");
                request.setAttribute("email",email);
                Random rand = new Random();
                String code = "";
                for (int i = 0; i < 6; i++) {
                    code += String.valueOf(rand.nextInt(9) + 1);
                }

                String to = email;
                String subject = "Mã xác minh email web thietbiyte";
                String message = "Ai đó đã cố đăng nhập vào tài khoản của bạn, để bảo vệ tài khoản của bạn, chúng tôi đã tạm khóa tài khoản, hãy dùng mã xác thực bên dưới để xác minh lại tài khoản của bạn. Mã xác minh email của  bạn là: " + code;
                IJavaMail emailService = new EmailService();
                //  q       guwir code toi email khach hang
                emailService.send(to, subject, message);
                VerifyCodeDAO.getInstance().insertNewCode(code, email);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
                rd.forward(request, response);

            } else {
                //            kiem tra dang nhap
                password = User.encodePwd(password);
                User u = UserDAO.getInstance().selectByEmailAndPwd(email,password);

//            dang nhap thanh cong
                if(u != null) {
//                isAdmin = u.isAdmin();
//                userloging = u;
                    if(u.getAvailable()==0 || u.getAvailable() == -1) {//kiem tra available cua tai khoan
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
                    } else {// dang nhap thanh cong
                        session.removeAttribute("logingFailedCount");
                        if(u.getLevel() > 0) {
                            Employee e = EmployeeDAO.getInstance().selectById(u.getId());
                            String roles = e.getRole();
                            String url = "/goto-dashboard-admin";
                            String currentMenu = "dashboard";
                            if(roles!=null) {
                                String firstRole = roles.split("=")[0];
                                switch (firstRole) {
                                    case "employee" :{
                                        url = "/goto-employee-admin";
                                        currentMenu = "employee";
                                        break;
                                    }
                                    case "customer" :{
                                        url = "/goto-customer-admin";
                                        currentMenu = "customer";
                                        break;
                                    }
                                    case "dashboard" :{
                                        url = "/goto-dashboard-admin";
                                        currentMenu = "dashboard";
                                        break;
                                    }
                                    case "product" :{
                                        url = "/goto-product-admin";
                                        currentMenu = "product";
                                        break;
                                    }
                                    case "order" :{
                                        url = "/goto-product-admin";
                                        currentMenu = "product";
                                        break;
                                    }
                                }
                            }


//                        xu ly ghi log
                            Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào hệ thống admin." ,"trống",e.getEmail(),1 );
                            LogDAO.getInstance().insert(log);

                            session.removeAttribute("userloging");
                            session.setAttribute("adminloging",e);
                            session.setAttribute("currentAdminMenu", currentMenu);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            session.setAttribute("userloging",u);
                            session.removeAttribute("adminloging");
                            Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào website." ,"trống",u.getEmail(),1 );
                            LogDAO.getInstance().insert(log);

                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
                            rd.forward(request, response);
                        }
                    }

                }
//            dang nhap khong thanh cong
                else {
                    Map<Integer,Integer> logingFailedCount = (Map<Integer, Integer>) session.getAttribute("logingFailedCount");
                    if(logingFailedCount!=null) {
                        if(!logingFailedCount.containsKey(id)) {
                            logingFailedCount.put(id,1);
                        } else {
                            int count = logingFailedCount.get(id) +1;
                            logingFailedCount.put(id,count);
                            if(count == 5) {
                                UserDAO.getInstance().updateAvailableByUserID(id,-1);

                                Log log = new Log(ipAddress,"system" + " | login ","Hệ thống đã tạm khóa tài khoản " + email  + " do đăng nhập không thành công 5 liên tiếp." ,u.getEmail() + " | 1",u.getEmail() + " | -1",3 );
                                LogDAO.getInstance().insert(log);

                            }
                        }
                    } else {
                        logingFailedCount = new HashMap<>();
                        logingFailedCount.put(id,1);
                    }

                    session.setAttribute("logingFailedCount",logingFailedCount);

                    int failedCount = logingFailedCount.get(id);
                    Log log = new Log(ipAddress,email + " | login ","Đăng nhập không thành công lần thứ " + failedCount +".","trống","trống",2 );
                    LogDAO.getInstance().insert(log);




//                userloging = null;
                    session.setAttribute("userloging",null);
                    request.setAttribute("status","loginFailed");
                    request.setAttribute("email",email);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
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