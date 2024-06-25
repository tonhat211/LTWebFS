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

        String pwd = User.encodePwd(password);
        User u = new User(idin,name,email,password,0,phone,address,0,info,dateinDatee,null,0,null);


//        ktra xem co truong bat buoc nao bi bo trong khong
//        if(name.equals("") || phone.equals("") || email.equals("") || password.equals("") || repassword.equals("")) {
//            String status = "";
//            if(name.equals("")) status += "nameRequired" + "=";
//            if(phone.equals("")) status += "phoneRequired" + "=";
//            if(email.equals("")) status += "emailRequired" + "=";
//            if(password.equals("")) status += "passwordRequired" + "=";
//            if(repassword.equals("")) status += "repasswordRequired" + "=";
//
//            String html = renderHtml(u,status);
//            response.setContentType("text/html");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(html);
//
//        } else { //ktra email da duoc dky truoc do chua
//
//        }


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
                System.out.println("call resend get");
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("call resend post");
        doGet(request,response);
    }

    public String renderHtml(User u, String status) {
        String msg ="Không được để trống mục này";
        String sex = "Nam";
        String birthday="";
        if(u!= null) {
            String info = u.getInfo();

            if(info!=null){
                String infoTokens[] = info.split("=");

                if(infoTokens.length <2){
                    sex = "Nam";
                    birthday="";
                }
                else {
                    sex = infoTokens[0];
                    birthday = infoTokens[1];
                    String[] bdTokens = birthday.split("-");
                    if((bdTokens[1].length()<2))
                        bdTokens[1]="0" + bdTokens[1];
                    if((bdTokens[2]).length()<2)
                        bdTokens[2]="0" + bdTokens[2];

                    birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];

                }
            }
        }

        String emailError ="";
        if(status.contains("emailRequired")) {
            emailError = "<div class=\"error\"> "+ msg +" </div>\n";

        } else if(status.contains("existedEmail")) {
            emailError = "<div class=\"error\"> Email đã được dùng để đăng ký trước đó </div>\n";
        }
        String html ="";
        html =
                "            <div class=\"mb-3\">\n" +
                "                <label for=\"name\" class=\"form-label\">Họ và tên</label>\n" +
                "                <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\"  value=\" " + u.getName()  +" \" placeholder=\"Nhập họ và tên\">\n" +
                "                <div class=\"error\" "+ (status.contains("nameRequired")?"": "hidden")  + " > "+ msg +" </div>\n" +
                "\n" +
                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <label for=\"phone\" class=\"form-label\">Số điện thoại</label>\n" +
                "                <input type=\"text\" class=\"form-control\" id=\"phone\" name=\"phone\"  value=\" " + u.getPhone() +" \" placeholder=\"Nhập số điện thoại\">\n" +
                "                <div class=\"error\" "+ (status.contains("phoneRequired")?"": "hidden")  + " > "+ msg +" </div>\n" +

                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <label for=\"email\" class=\"form-label\">Email</label>\n" +
                "                <input type=\"text\" class=\"form-control\" id=\"email\" name=\"email\" aria-describedby=\"emailHelp\"  value=\" " + u.getEmail()  +" \" placeholder=\"Nhập email\">\n" +
                "                <div class=\"error\" "+ (status.contains("emailRequired")?"": "hidden")  + " > "+ msg +" </div>\n" +
                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <label for=\"password\" class=\"form-label\">Mật khẩu</label>\n" +
                "                <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\"  placeholder=\"Nhập mật khẩu\">\n" +
                "                <div class=\"error\" "+ (status.contains("passwordRequired")?"": "hidden")  + " > "+ msg +" </div>\n" +

                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <label for=\"repassword\" class=\"form-label\">Nhập lại mật khẩu</label>\n" +
                "                <input type=\"password\" class=\"form-control\" id=\"repassword\" name=\"repassword\"  placeholder=\"Nhập lại mật khẩu\">\n" +
                "                <div class=\"error\" "+ (status.contains("repasswordRequired")?"": "hidden")  + " > "+ msg +" </div>\n" +

                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <div id=\"sex\" class=\"info-container\">\n" +
                "                    <div class=\"info-container__title\">\n" +
                "                        <label for=\"sex\">Giới tính\n" +
                "                        </label>\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div class=\"info-container__content\">\n" +
                "                        <div class=\"form-check\">\n" +
                "                            <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault1\" value=\"nam\"  " + (sex.equalsIgnoreCase("nam")?"checked":"")  +" >\n" +
                "                            <label class=\"form-check-label\" for=\"flexRadioDefault1\">\n" +
                "                                Nam\n" +
                "                            </label>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-check\">\n" +
                "                            <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault2\" value=\"nu\"   " + (sex.equalsIgnoreCase("nu")?"checked":"") +" >\n" +
                "                            <label class=\"form-check-label\" for=\"flexRadioDefault2\">\n" +
                "                                Nữ\n" +
                "                            </label>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <div class=\"form-group\">\n" +
                "                    <label  class=\"w-20\"  for=\"birthday\">Ngày sinh: </label>\n" +
                "                    <input type=\"date\" class=\"form-control w-80\" id=\"birthday\" name=\"birthday\"  aria-describedby=\"\" placeholder=\"Nhập ngày sinh\"  value=\" " +birthday +" \" >\n" +
                "                    <div class=\"error\" hidden></div>\n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "            <div class=\"mb-3\">\n" +
                "                <div class=\"form-group\">\n" +
                "                    <label class=\"w-20\" for=\"address\">Địa chỉ: </label>\n" +
                "                    <input type=\"text\" class=\"form-control w-80\" id=\"address\" name=\"address\" aria-describedby=\"\" placeholder=\"Nhập địa chỉ\"  value=\" " + u.getAddress()  +" \">\n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"display: flex; justify-content: center\">\n" +
                "                <button type=\"submit\" class=\"btn btn-primary\">Đăng ký</button>\n" +
                "            </div>\n" +
                "            <div style=\"margin-top: 40px; text-align: center\">\n" +
                "                <a href=\"login.jsp\">Đã có tài khoản? Đăng nhập</a>\n" +
                "            </div>\n";


        return html;
    }


}