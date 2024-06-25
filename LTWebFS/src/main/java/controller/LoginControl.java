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
        HttpSession session = request.getSession();
        int id = UserDAO.getInstance().checkExistUser(email);
        String ipAddress = request.getRemoteAddr();

        if(id!=0){//        neu user ton tai
            int avai = UserDAO.getInstance().checkAvailable(email);
            if(avai==-1) {// kiem tra xem tai khoan co dang bi tam khoa khong
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

                if(u != null) {// dang nhap thanh cong
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
                        //  gui code toi email khach hang
                        emailService.send(to,subject,message);
                        VerifyCodeDAO.getInstance().insertNewCode(code,email);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/VerifyCode.jsp");
                        rd.forward(request, response);
                    } else {// dang nhap that bai
                        session.removeAttribute("logingFailedCount");
                        if(u.getLevel() > 0) {
                            Employee e = EmployeeDAO.getInstance().selectById(u.getId());
                            String roles = e.getRole();
                            String url = "/admin-menu-controller?adminMenu=";
                            String currentMenu = "";
                            if(roles!=null) {
                                if(roles.split("=").length==0) {
                                    request.setAttribute("status","noPermission");
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                                    rd.forward(request, response);
                                } else {
                                    String firstRole = roles.split("=")[0];
                                    switch (firstRole) {
                                        case "employee" :{
                                            url += "employee";
                                            currentMenu = "employee";
                                            break;
                                        }
                                        case "customer" :{
                                            url += "customer";
                                            currentMenu = "customer";
                                            break;
                                        }
                                        case "dashboard" :{
                                            url += "dashboard";
                                            currentMenu = "dashboard";
                                            break;
                                        }
                                        case "product" :{
                                            url += "product";
                                            currentMenu = "product";
                                            break;
                                        }
                                        case "order" :{
                                            url += "order";
                                            currentMenu = "product";
                                            break;
                                        }
                                        case "log": {
                                            url += "log";
                                            currentMenu = "log";
                                            break;
                                        }
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

                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product");
                            rd.forward(request, response);
                        }
                    }

                }

                else {// dang nhap that bai
                    Map<Integer,Integer> logingFailedCount = (Map<Integer, Integer>) session.getAttribute("logingFailedCount");
                    if(logingFailedCount!=null) {
                        if(!logingFailedCount.containsKey(id)) {
                            logingFailedCount.put(id,1);
                        } else {
                            int count = logingFailedCount.get(id) +1;
                            logingFailedCount.put(id,count);
                            if(count == 5) {
                                UserDAO.getInstance().updateAvailableByUserID(id,-1);//tam khoa user
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
    public String renderMessage() {
        String html="";
        html="        <div class=\"modal confirm-stop active\" style=\"z-index:99\">\n" +
                "            <div class=\"modal__overlay\">\n" +
                "                <div class=\"modal__confirm-content\" onclick=\"event.stopPropagation()\">\n" +
                "                    <div class=\"confirm__message\">\n" +
                "                        <h6>Bạn chưa có quyền truy cập vào các chức năng trong trang quản trị <br/>\n" +
                "                            Vui lòng liên hệ bộ phận kĩ thuật để được hỗ trợ</h6>\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\" style=\"justify-content: center\">\n" +
                "                        <div class=\"btn btn-primary confirm-btn no-confirm\" style=\"margin-top: 20px\">Cám ơn</div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <script>\n" +
                "                function hideMessage() {\n" +
                "                    $(\".confirm-stop\").removeClass(\"active\");\n" +
                "                    console.log(\"yes\")\n" +
                "                }\n" +
                "\n" +
                "                $(\".modal__overlay\").click(hideMessage);\n" +
                "                $(\".no-confirm\").click(hideMessage);\n" +
                "\n" +
                "\n" +
                "            </script>\n" +
                "        </div>";
        return html;
    }


}