package controller;

import database.CustomerDAO;
import database.EmployeeDAO;
import database.LogDAO;
import database.UserDAO;
import model.*;
import model.JavaMail.EmailService;
import model.JavaMail.IJavaMail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet("/customer")
public class AdminCustomerControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        Employee e = (Employee) session.getAttribute("adminloging");

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case "INIT": {
                    ArrayList<Customer> cuss = CustomerDAO.getInstance().selectAll();
                    request.setAttribute("customerList", cuss);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminCustomer.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "SEARCH": {
                    String input = request.getParameter("input");
                    ArrayList<Customer> customerList = CustomerDAO.getInstance().selectByCusNameOrEmailOrPhone(input);
                    request.setAttribute("customerList", customerList);
                    request.setAttribute("currentSearch",input);
                    String html="";
                    for(int i=0; i<customerList.size();i++) {
                        html+="            <tr class=\""+ (i%2==0 ?"roww":"")+"\">\n" +
                                "                    <td>"+customerList.get(i).getName() +"</td>\n" +
                                "                    <td>"+customerList.get(i).getEmail() +" <br> "+customerList.get(i).getPhone()+" <br> "+customerList.get(i).getAddress() +" </br></td>\n" +
                                "                    <td>"+customerList.get(i).getDateIn() +"</td>\n" +
                                "                    <td>   <fmt:formatNumber value=\""+customerList.get(i).getTotalSpend() +"\" pattern=\"#,##0.00\"/>\n" +
                                "                        VND</td>\n" +
                                "                    <td><a href=\"goto-history-buying?cusID="+customerList.get(i).getId()+"\">Lịch sử mua hàng</a></td>\n" +
                                "                    <td><a class=\"btn-update-product\" href=\"customer?action=prepareUpdate&&id="+customerList.get(i).getId()+"\">Cập nhật</a></td>\n" +
                                "                </tr>";
                    }
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }
                case "PREPAREUPDATE": {
//                    String idString = (String) request.getParameter("id");
                    int id = Integer.parseInt(request.getParameter("id"));
                    User u = UserDAO.getInstance().selectById(id);
                    request.setAttribute("customer", u);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateCustomer.jsp");
                    rd.forward(request, response);

                    break;
                }
                case "PREPAREADD": {
                    int id = UserDAO.getInstance().selectTheMaxID();
//        xu ly bug id
//        3039 -> 3040 sai
//        3039 -> 30310 dung
                    String idString = String.valueOf(id);
                    if(idString.length() ==4 && idString.endsWith("9")){
                        idString = idString.substring(0,idString.length()-1) + "10";

                        id = Integer.valueOf(idString);
                    } else {
                        id = id +1;
                    }
                    User u = new User(id);

                    request.setAttribute("customer", u);
                    request.setAttribute("action","add");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateCustomer.jsp");
                    rd.forward(request, response);
                    break;
                }
                case  "LOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    User u = UserDAO.getInstance().selectById(idin);

                    UserDAO.getInstance().updateAvailableByUserID(idin,-2);
                    request.setAttribute("id", idin);

                    User afterU = UserDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã khóa tài khoản." ,u.getEmail() + " | " + u.getAvailable(),afterU.getEmail() + " | " + afterU.getAvailable(),1 );
                    LogDAO.getInstance().insert(log);

                    String html = renderHtml(afterU,"Đã khóa tài khoản " + afterU.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;



                }
                case  "UNLOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    User u = UserDAO.getInstance().selectById(idin);

                    UserDAO.getInstance().updateAvailableByUserID(idin,1);
                    request.setAttribute("id", idin);

                    User afterU = UserDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã mở khóa tài khoản." ,u.getEmail() + "|" + u.getAvailable(),u.getEmail() + " | " + afterU.getAvailable(),1 );
                    LogDAO.getInstance().insert(log);

                    String html= renderHtml(afterU,"Đã mở khóa tài khoản " + afterU.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);

                    break;

                }
                case "UPDATE":{
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String sex = request.getParameter("sex");
                    String birthday = request.getParameter("birthday");
                    String info = sex + "=" + birthday;
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                                    Integer.parseInt(dateInTokens[2]));
//                    '16-2022-4'
//                    Datee dateimportDatee = new Datee(01,01,2022);
                    User preu = UserDAO.getInstance().selectById(idin);

                    User u = new User(idin,name,email,phone,address,info,dateinDatee);

                    UserDAO.getInstance().updateCustomer(u);

                    User afterU = UserDAO.getInstance().selectById(idin);


                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã cập nhật thông tin tài khoản." ,preu.toString(),afterU.toString(), 1 );
                    LogDAO.getInstance().insert(log);

                    String html = renderHtml(afterU,"Cập nhật thành công tài khoản " + afterU.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }
                case "ADD":{
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly them
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String sex = request.getParameter("sex");
                    String birthday = request.getParameter("birthday");
                    String info = sex + "=" + birthday;
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                            Integer.parseInt(dateInTokens[2]));

                    String[] birthdayTokens = birthday.split("-");
                    String pwd;
                    if(birthday.equalsIgnoreCase(""))
                        pwd="01012000";
                    else
                        pwd = birthdayTokens[2] + birthdayTokens[1] + birthdayTokens[0];
                    String pwdEncoded = User.encodePwd(pwd);

                    User u = new User(idin,name,email,pwdEncoded,0,phone,address,info,dateinDatee,0);
                    CustomerDAO.getInstance().insertCustomer(u);

                    User afterU = UserDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã thêm một tài khoản mới." ,"trống",afterU.toString(), 1 );
                    LogDAO.getInstance().insert(log);

//                    gui mail thong bao tao tai khoan thanh cong
                    Random rand = new Random();
                    int ranNum = rand.nextInt(9)+1;
                    String code ="";
                    for(int i=0;i<6; i++) {
                        code+= String.valueOf(rand.nextInt(9)+1);
                    }
                    String to =email;
                    String subject="Tạo tài khoản thành công";
                    String message = "Email đã được dùng để đăng ký tài khoản website thiết bị y tế\nMật khẩu của bạn là: " + pwd +"\nMã xác minh tài khoản của bạn là " +code;
                    IJavaMail emailService = new EmailService();
                    //  q       gui code toi email khach hang
                    emailService.send(to,subject,message);

                    String html = renderHtml(afterU,"Thêm thành công tài khoản " + afterU.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);

                    break;
                }
//                default:{
//
//                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String renderHtml(User afterU, String message) {
        String info = afterU.getInfo();
        String sex = "";
        String birthday="";
        if(info!=null){
            String infoTokens[] = info.split("=");

            switch (infoTokens.length) {
                case 1: {
                    sex = infoTokens[0];
                    break;
                }
                case 2: {
                    sex = infoTokens[0];
                    birthday = infoTokens[1];
                    break;
                }
                default: {
                    sex = "";
                    birthday ="";
                    break;
                }
            }

            if(!birthday.equals("")) {
                String[] bdTokens = birthday.split("-");
                if((bdTokens[1].length()<2))
                    bdTokens[1]="0" + bdTokens[1];
                if((bdTokens[2]).length()<2)
                    bdTokens[2]="0" + bdTokens[2];
                birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];
            }
        }



//
        String html ="";
        html =   "<script> showSuccessToast(\"" + message + "\"); </script> " +
//                "<form action=\"addUpdate-customer\" method=\"post\" id=\"customerInfoForm\">" +
                "                    <div class=\"show-flex-row\">" +
                "                        <h4>"+ (afterU.getName()==""?"Thêm khách hàng mới":"Cập nhật thông tin khách hàng") + "</h4>" +

                "                    </div>" +
                "                    <div class=\"show-flex-row\">" +
                "                        <div class=\"grid__row img-showing\">" +
                "                            <div class=\"disabled-showing" + (afterU.getAvailable()<1?"active":"") + " \" style=\" "+ (afterU.getName()==""?"position: relative;  left: 0":"")  + " \" >" +
                "                                <div class=\"disabled-showing-content\">" +
                "                                    " + (afterU.getAvailable()==0?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA/TẠM KHÓA") +
                "                                </div>" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class=\"stop_reSale " + (afterU.getName()==""?"hide":"") +" \" >" +
                "                            <div class=\"btn btn-stop-pro " + (afterU.getAvailable()>0?"active":"") + " \"  onclick=\"showModal()\">Khóa tài khoản</div>" +
                "                            <div class=\"btn btn-resale-pro " +  (afterU.getAvailable()<0?"active":"") +" \"  onclick=\"showModalUnlock()\">Mở khóa tài khoản</div>" +
                "                            <div class=\"btn btn-resale-pro " +  (afterU.getAvailable()==0?"active":"") +" \"  onclick=\"showModalUnlock()\">Kích hoạt tài khoản</div>" +
                "                        </div>" +
                "                    </div>" +

                "                    <div class=\"form-group w-50\">" +
                "                        <label class=\"w-20\" for=\"id\">ID</label>" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"id\" name=\"id\" aria-describedby=\"emailHelp\" placeholder=\"ID\" value=\""+afterU.getId()+"\" readonly>" +
                "                    </div>" +

                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"name\">Họ và tên: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"name\" name=\"name\" aria-describedby=\"\" placeholder=\"Nhập tên\" value=\""+afterU.getName()+"\">" +
                "                        <div class=\"required\" hidden>Không được để trống mục này</div>" +
                "                    </div>" +

                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"email\">Email</label>" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"email\" name=\"email\" aria-describedby=\"\" placeholder=\"Nhập email\" value=\""+afterU.getEmail()+"\">" +
                "                        <div class=\"error\" hidden>Email không hợp lệ</div>" +
                "                        <div class=\"required\" hidden>Không được để trống mục này</div>" +

                "                    </div>" +
                "                    <div class=\"form-group w-80\">" +
                "                        <label class=\"w-20\" for=\"phone\">Số điện thoại: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"phone\" name=\"phone\" aria-describedby=\"\" placeholder=\"Nhập số điện thoại\" value=\""+afterU.getPhone()+"\">" +
                "                        <div class=\"required\" hidden>Không được để trống mục này</div>" +
                "                        <div class=\"error\" hidden>Số điện thoại không hợp lệ</div>" +

                "                    </div>" +
                "                    <div class=\"form-group w-100\">" +
                "                        <label class=\"w-20\" for=\"address\">Địa chỉ: </label>" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"address\" name=\"address\" aria-describedby=\"\" placeholder=\"Nhập Địa chỉ\" value=\""+afterU.getAddress()+"\">" +
                "                        <div class=\"required\" hidden>Không được để trống mục này</div>" +

                "                    </div>" +

                "                    <div class=\"show-flex-row\">" +

                "                        <div id=\"sex\" class=\"info-container\">" +
                "                            <div class=\"info-container__title\">" +
                "                                <label for=\"sex\">Giới tính" +
                "                                </label>" +
                "                            </div>" +

                "                            <div class=\"info-container__content\">" +
                "                                <div class=\"form-check\">" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault1\" value=\"nam\" "+ (sex.equalsIgnoreCase("nam")?"checked":"") +">" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault1\">" +
                "                                        Nam" +
                "                                    </label>" +
                "                                </div>" +
                "                                <div class=\"form-check\">" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault2\" value=\"nu\" "+ (sex.equalsIgnoreCase("nu") ?"checked" : "")+">" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault2\">" +
                "                                        Nữ" +
                "                                    </label>" +
                "                                </div>" +
                "                            </div>" +

                "                        </div>" +
                "                        <div class=\"form-group w-50\">" +
                "                            <label  class=\"w-20\"  for=\"birthday\">Ngày sinh: </label>" +
                "                            <input type=\"date\" class=\"form-control w-80\" id=\"birthday\" name=\"birthday\"  aria-describedby=\"\" placeholder=\"Enter date import\" value=\""+birthday+"\">" +
                "                            <div class=\"error\" hidden>Chưa đủ 16 tuổi</div>" +

                "                        </div>" +
                "                    </div>" +
                "                    <div class=\"form-group w-50\">" +
                "                        <label  class=\"w-20\"  for=\"datein\">Ngày tham gia: </label>" +
                "                        <input type=\"date\" class=\"form-control w-80\" id=\"datein\" name=\"datein\"  aria-describedby=\"\" placeholder=\"Nhập ngày tham gia\" value=\""+afterU.getDateIn().getDateInMonthDayYearSql() +"\">" +
                "                        <div class=\"error\" hidden>Ngày tham gia không hợp lệ</div> " +

                "                    </div>" +

                "                    <div class=\"form-group\" style=\"display: none\">" +
                "                        <label class=\"w-40\" for=\"action\" class=\"input-title\">action</label>" +
                "                        <input type=\"text\" class=\"form-control\" id=\"action\" name=\"action\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\""+(afterU.getName()==""?"add":"update") +"\">" +
                "                    </div>" +
                "                    <div class=\"form-group\" style=\"display: none\">" +
                "                        <label class=\"w-40\" for=\"status\" class=\"input-title\">status</label>" +
                "                        <input type=\"text\" class=\"form-control\" id=\"status\" name=\"status\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\"1\">" +
                "                    </div>" +

                "                    <div class=\"show-flex-row\">" +
                "                        <div class=\"ad_func-container\">" +
                "                            <div><a class=\"btn btn-third\" href=\"goto-customer-admin\">Hủy</a></div>" +
                "                        </div>" +
                "                        <div class=\"ad_func-container\">" +
                "                            <button class=\"btn btn-primary\" type=\"submit\">"+(afterU.getName()==""?"Thêm":"Lưu") +"</button>" +
                "                        </div>" +
                "                    </div>" +
                "                    <!--                    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>-->"
//                "                </form>"
                ;
        return html;
    }


}