package controller;

import database.LogDAO;
import database.ProductUnitDAO;
import database.UnitDAO;
import database.UserDAO;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addUpdate-customer")
public class AddUpdateCustomerControl extends HttpServlet {

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
                case  "LOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    User u = UserDAO.getInstance().selectById(idin);

                    UserDAO.getInstance().updateAvailableByUserID(idin,-2);
                    request.setAttribute("id", idin);

                    User afterU = UserDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã khóa tài khoản." ,u.getEmail() + " | " + u.getAvailable(),afterU.getEmail() + " | " + afterU.getAvailable(),1 );
                    LogDAO.getInstance().insert(log);


//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer");
//                    rd.forward(request, response);
//                    break;

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


//                    request.setAttribute("id",idin);
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer");
//                    rd.forward(request, response);
//                    break;

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

                    String pwd = User.encodePwd(birthday);

                    User u = new User(idin,name,email,pwd,0,phone,address,0,info,dateinDatee,null,0,null);
                    UserDAO.getInstance().insert(u);

//                    int status= Integer.parseInt(request.getParameter("status"));

                    User afterU = UserDAO.getInstance().selectById(idin);


                    Log log = new Log(request.getRemoteAddr(),e.getEmail() + " | update_customer ","Đã thêm một tài khoản mới." ,"trống",afterU.toString(), 1 );
                    LogDAO.getInstance().insert(log);

//                    request.setAttribute("id",idin);
//                    request.setAttribute("status",status);
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-add-customer");
//                    rd.forward(request, response);

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

            if(infoTokens.length <2){
                sex = "trống";
                birthday="trống";
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