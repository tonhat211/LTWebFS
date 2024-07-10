package controller;

import database.BranchDAO;
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

@WebServlet("/employee")
public class AdminEmployeeControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        Employee adminloging = (Employee) session.getAttribute("adminloging");
        ArrayList<Branch> branches = BranchDAO.getInstance().selectAll();

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case "INIT": {

                    ArrayList<Employee> employeeList = EmployeeDAO.getInstance().selectAll();

                    request.setAttribute("employeeList", employeeList);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminEmployee.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "SEARCH": {
                    String input = request.getParameter("input");
                    ArrayList<Employee> employeeList = EmployeeDAO.getInstance().selectByNameOrEmailOrPhone(input);
                    request.setAttribute("employeeList", employeeList);
                    request.setAttribute("currentSearch",input);
                    
                    String html ="";
                    for(int i=0; i<employeeList.size();i++) {
                        html +="           <tr class=\""+ (i%2==0 ?"roww" :"") +"\"  onclick=\"showDetail("+employeeList.get(i).getId()+")\">\n" +
                                "                            <td style=\"height: 100px\" ><img src=\""+employeeList.get(i).getImgurl()+"\" alt=\"\" style=\"height: 100%\"></td>\n" +
                                "                            <td>"+employeeList.get(i).getName() +"</td>\n" +
                                "                            <td>"+employeeList.get(i).getEmail() +" <br> "+employeeList.get(i).getPhone()+" <br> "+employeeList.get(i).getAddress() +" </br></td>\n" +
                                "                            <td>"+employeeList.get(i).getPosition() +"<br> "+employeeList.get(i).getArea()+"</td>\n" +
                                "                            <td>"+employeeList.get(i).getBranch() +"</td>\n" +
                                "                            <td><a href=\"employee?action=prepareUpdate&&id="+employeeList.get(i).getId()+"\">Cập nhật</a></td>\n" +
                                "                        </tr>";
                    }

                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }

                case "SHOWINFO": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Employee e = EmployeeDAO.getInstance().selectById(id);
                    String html="<div class=\"confirm__message\">" +
                            "       <div class=\"info-container\">" +
                            "           <div class=\"info-container__title\">" +
                            "               <img class=\"w-50\" src=\""+e.getImgurl()+"\" alt=\"\">" +
                            "           </div>" +
                            "           <div class=\"info-container__content\">" +
                            "               <h6 style=\"font-size: 30px\">"+e.getName()+"</h6>" +
                            "               <p>Chức vụ: <span>"+e.getPosition()+"</span></p>" +
                            "               <p>Phòng ban: <span>"+e.getArea()+"</span></p>" +
                            "           </div>" +
                            "       </div>" +
                            "   <div class=\"info-container\">" +
                            "       <div class=\"info-container__title\">" +
                            "           <p>Thông tin liên hệ:</p>" +
                            "       </div>" +
                            "       <div class=\"info-container__content\">" +
                            "           <p>Email: <span>"+e.getEmail()+"</span></p>" +
                            "           <p>Số điện thoại: <span>"+e.getPhone()+"</span></p>" +
                            "           <p>Chi nhánh công tác: <span>"+e.getBranch()+"</span></p>" +
                            "       </div>" +
                            "   </div>" +
                            "   <div class=\"info-container\">" +
                            "       <div class=\"info-container__title\">" +
                            "           <p>Thông tin công tác:</p>" +
                            "       </div>" +
                        "           <div class=\"info-container__content\">" +
                            "           <p>Ngày vào làm: <span>"+e.getDatein()+"</span></p>" +
                            "       </div>" +
                            "   </div>" +
                            "   <div class=\"info-container\">" +
                            "       <div class=\"info-container__title\">" +
                            "           <p>Thông tin cá nhân:</p>" +
                            "       </div>" +
                            "       <div class=\"info-container__content\">" +
                            "           <p>Giới tính: <span>"+e.getSex()+"</span></p>" +
                            "           <p>Ngày sinh: <span>"+e.getBirthday()+"</span></p>" +
                        "               <p>Địa chỉ nơi ở hiện tại: <span>"+e.getAddress()+"</span></p>" +
                            "       </div>" +
                            "   </div>" +
                            "</div>";
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;

                }
                case "PREPAREUPDATE": {
                    String idString = (String) request.getParameter("id");
                    int id = Integer.parseInt(idString);
                    Employee e = EmployeeDAO.getInstance().selectById(id);
                    String status = (String) request.getAttribute("status");
                    request.setAttribute("employee", e);
                    request.setAttribute("branchList", branches);
                    request.setAttribute("status",status);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateEmployee.jsp");
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

                    Employee e = new Employee(id);
                    e.setLevel(1);

                    request.setAttribute("branchList", branches);
                    request.setAttribute("employee", e);
                    request.setAttribute("action","add");
                    request.setAttribute("status","");

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateEmployee.jsp");
                    rd.forward(request, response);
                    break;
                }

                case  "LOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    Employee e = EmployeeDAO.getInstance().selectById(idin);

                    EmployeeDAO.getInstance().updateAvailableByEmployID(idin,-2);
                    request.setAttribute("id",idin);

                    String actionHelper = "";
                    if(adminloging.getId() == e.getId()) {
                        actionHelper = " của chính mình";
                    }

                    Employee afterE = EmployeeDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã khóa tài khoản admin" + actionHelper + "." ,e.getEmail() + "|" + e.getAvailable(),afterE.getEmail() + " | " +afterE.getAvailable(),1 );
                    LogDAO.getInstance().insert(log);


                    String html = renderHtml(afterE,branches,"Đã khóa tài khoản " + afterE.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;

                }
                case  "UNLOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    Employee eTemp = EmployeeDAO.getInstance().selectById(idin);

                    EmployeeDAO.getInstance().updateAvailableByEmployID(idin,1);
                    request.setAttribute("id",idin);

                    Employee afterE = EmployeeDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã mở khóa tài khoản admin." ,eTemp.getEmail() + "|" + eTemp.getAvailable(),afterE.getEmail() + " | " + afterE.getAvailable(),1 );
                    LogDAO.getInstance().insert(log);

                    String html= renderHtml(afterE,branches,"Đã mở khóa tài khoản " + afterE.getEmail());
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
                    String sex = request.getParameter("sex");
                    String birthday = request.getParameter("birthday");
                    String position = request.getParameter("position");
                    String area = request.getParameter("area");
                    String info = sex + "=" + birthday +"=" + position + "=" + area;
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                                    Integer.parseInt(dateInTokens[2]));
//
                    String branchinfo = request.getParameter("branch");
                    String branchTokens[] = branchinfo.split("=");
                    int branchID = Integer.parseInt(branchTokens[0]);
                    String branch = branchTokens[1];

                    String address = request.getParameter("address");
                    String imgurl = request.getParameter("imgurl");

                    String dashboard = request.getParameter("dashboard");
                    String customer = request.getParameter("customer");
                    String employee = request.getParameter("employee");
                    String product = request.getParameter("product");
                    String order = request.getParameter("order");


                    ArrayList<String> roles = new ArrayList<>();
                    roles.add(dashboard);
                    roles.add(customer);
                    roles.add(employee);
                    roles.add(product);
                    roles.add(order);

                    String role="";
                    for (String s : roles) {
                        if(s!=null) {
                            role += s+"=";
                        }
                    }

                    Image i = new Image(imgurl,idin);

                    Employee eTemp = EmployeeDAO.getInstance().selectById(idin);

                    Employee e = new Employee(idin,name,email,birthday,1,phone,address,branchID,branch,info,dateinDatee,null,0,0,imgurl,role);

                    String actionHelper = "";
                    if(adminloging.getId() == e.getId()) {
                        actionHelper = " của chính mình";
                    }

                    EmployeeDAO.getInstance().updateEmployee(e,i);

                    Employee afterE = EmployeeDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã cập nhật thông tin tài khoản admin" + actionHelper + "." ,eTemp.toString(),afterE.toString(),1 );
                    LogDAO.getInstance().insert(log);

                    request.setAttribute("id",idin);

                    String html = renderHtml(afterE,branches,"Cập nhật thành công tài khoản " + afterE.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;

                }
                case "ADD":{
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String sex = request.getParameter("sex");
                    String birthday = request.getParameter("birthday");
                    String position = request.getParameter("position");
                    String area = request.getParameter("area");
                    String info = sex + "=" + birthday +"=" + position + "=" + area;
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                            Integer.parseInt(dateInTokens[2]));
//
                    String branchinfo = request.getParameter("branch");
                    String branchTokens[] = branchinfo.split("=");
                    int branchID = Integer.parseInt(branchTokens[0]);
                    String branch = branchTokens[1];

                    String address = request.getParameter("address");
                    String imgurl = request.getParameter("imgurl");

                    String employee = request.getParameter("employee");
                    String customer = request.getParameter("customer");
                    String product = request.getParameter("product");
                    String order = request.getParameter("order");
                    String dashboard = request.getParameter("dashboard");

                    ArrayList<String> roles = new ArrayList<>();
                    roles.add(employee);
                    roles.add(customer);
                    roles.add(product);
                    roles.add(order);
                    roles.add(dashboard);
                    String role="";
                    for (String s : roles) {
                        if(s!=null) {
                            role += s+"=";
                        }
                    }

                    String[] birthdayTokens = birthday.split("-");
                    String pwd;
                    if(birthday.equalsIgnoreCase(""))
                        pwd="000000";
                    else
                        pwd = birthdayTokens[2] + birthdayTokens[1] + birthdayTokens[0];
                    String pwdEncoded = User.encodePwd(pwd);
                    int level =1;
                    Image i = new Image(imgurl,idin);
                    System.out.println("img: " + imgurl);
//                    Employee e = new Employee(30327, "Tô Minh Nhật", "pharmacity@gmail.com", "4848485049484948", 1, "0587044673","", 1, "===", new Datee(2024,6,5), 0,"");
                    Employee e = new Employee(idin,name,email,pwdEncoded,level,phone,address,branchID,info,dateinDatee,0,role);

                    EmployeeDAO.getInstance().insertEmployee(e,i);

                    Employee afterE = EmployeeDAO.getInstance().selectById(idin);

                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã thêm một tài khoản admin mới." ,e.toString(),afterE.toString(),1 );
                    LogDAO.getInstance().insert(log);

                    //                    gui mail thong bao tao tai khoan thanh cong
                    Random rand = new Random();
                    int ranNum = rand.nextInt(9)+1;
                    String code ="";
                    for(int j=0;j<6; j++) {
                        code+= String.valueOf(rand.nextInt(9)+1);
                    }
                    String to =email;
                    String subject="Tạo tài khoản nhân viên thành công";
                    String message = "Email đã được dùng để đăng ký tài khoản cho nhân viên của website thiết bị y tế\nMật khẩu của bạn là: " + pwd +"\nMã xác minh tài khoản của bạn là " +code;
                    IJavaMail emailService = new EmailService();
                    //  q       gui code toi email khach hang
                    emailService.send(to,subject,message);

                    String html = renderHtml(afterE,branches,"Thêm thành công tài khoản " + afterE.getEmail());
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }

            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String renderHtml(Employee afterE,ArrayList<Branch> branchList, String message) {
        String info = afterE.getInfo();
        String sex = "";
        String birthday="";
        String position="";
        String area ="";
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
                case 3: {
                    sex = infoTokens[0];
                    birthday = infoTokens[1];
                    position = infoTokens[2];
                    break;
                }
                case 4: {
                    sex = infoTokens[0];
                    birthday = infoTokens[1];
                    position = infoTokens[2];
                    area = infoTokens[3];
                    break;
                }
                default: {
                    sex = "";
                    birthday="";
                    position="";
                    area="";
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

        String options ="";

        for(Branch b : branchList) {
            options+="<option "+ (b.getId()==afterE.getBranchID() ? "selected" : "") +" value=\""+b.getId()+"="+b.getName()+"\">"+b.getName() +"</option>\n";

        }

        String roles = afterE.getRole();
        if(roles == null) {
            roles="";
        }

        String html="";
        html =  "<script> showSuccessToast(\"" + message + "\"); </script> " +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <h4>"+ (afterE.getName()==""?"Thêm nhân viên mới":"Cập nhật thông tin nhân viên") +"</h4>\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"grid__row img-showing\">\n" +
                "                            <div class=\"disabled-showing " + (afterE.getAvailable()<1?"active":"") +" style=\" " + (afterE.getName()==""?"position: relative;  left: 0":"") + " \" >\n" +
                "                                <div class=\"disabled-showing-content\">\n" +
                "                                    " + (afterE.getAvailable()==0?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA/TẠM KHÓA") +"\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"stop_reSale " + (afterE.getName()==""?"hide":"") + "\" >\n" +
                "                            <div class=\"btn btn-stop-pro " + (afterE.getAvailable()>0?"active":"") +"\"  onclick=\"showModal()\">Khóa tài khoản</div>\n" +
                "                            <div class=\"btn btn-stop-pro " + (afterE.getAvailable()==0?"active":"") +"\" onclick=\"showModalUnlock()\">Kích hoạt tài khoản</div>\n" +
                "                            <div class=\"btn btn-resale-pro " + (afterE.getAvailable()<0?"active":"") +"\" onclick=\"showModalUnlock()\">Mở khóa tài khoản</div>\n" +
                "\n" +
                "\n" +
                "                        </div>\n" +
                "                    </div>\n" +

                "                    <div class=\"form-group w-50\">\n" +
                "                        <label class=\"w-20\" for=\"id\">ID</label>\n" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"id\" name=\"id\" aria-describedby=\"emailHelp\" placeholder=\"ID\" value=\"" +afterE.getId()+"\" readonly>\n" +
                "                        <!--                       <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your email with anyone else.</small>-->\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"name\">Họ và tên: </label>\n" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"name\" name=\"name\" aria-describedby=\"\" placeholder=\"Nhập họ và tên\" value=\"" +afterE.getName() +"\">\n" +
                "                        <div class=\"error\" hidden></div>\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"position\">Chức vụ</label>\n" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"position\" name=\"position\" aria-describedby=\"\" placeholder=\"Nhập chức vụ\" value=\"" +position +"\">\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"area\">Phòng ban: </label>\n" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"area\" name=\"area\" aria-describedby=\"\" placeholder=\"Nhập phòng ban\" value=\"" +area +"\">\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"email\">Email</label>\n" +
                "                        <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"email\" name=\"email\" aria-describedby=\"\" placeholder=\"Nhập email\" value=\"" +afterE.getEmail() +"\">\n" +
                "                        <div class=\"error\" hidden></div>\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"phone\">Số điện thoại: </label>\n" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"phone\" name=\"phone\" aria-describedby=\"\" placeholder=\"Nhập số điện thoại\" value=\"" +afterE.getPhone() +"\">\n" +
                "                        <div class=\"error\" hidden></div>\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"branch\">Chi nhánh: </label>\n" +
                "                        <select class=\"form-select w-80\" aria-label=\"Default select example\" id=\"branch\" name=\"branch\">\n" +
                                                 options +
                "\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "\n" +

                "                    <div id=\"role\" class=\"info-container\">\n" +
                "                        <div class=\"info-container__title\">\n" +
                "                            <label for=\"role\">Quyền truy cập\n" +
                "                            </label>\n" +
                "                        </div>\n" +
                "                        <div class=\"info-container__content\">\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"employee\"name=\"employee\" value=\"employee\" " + (roles.contains("employee")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"employee\">\n" +
                "                                    Nhân viên\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"customer\" name=\"customer\" value=\"customer\" " +(roles.contains("customer")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"customer\">\n" +
                "                                    Khách hàng\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"product\" name=\"product\" value=\"product\" " +(roles.contains("product")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"product\">\n" +
                "                                    Sản phẩm\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"order\" name=\"order\" value=\"order\" " +(roles.contains("order")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"order\">\n" +
                "                                    Đơn hàng\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"dashboard\" name=\"dashboard\" value=\"dashboard\" " +(roles.contains("dashboard")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"dashboard\">\n" +
                "                                    Thống kê\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-check\">\n" +
                "                                <input class=\"form-check-input\" type=\"checkbox\" id=\"log\" name=\"log\" value=\"log\" " +(roles.contains("log")?"checked":"")+">\n" +
                "                                <label class=\"form-check-label\" for=\"log\">\n" +
                "                                    Log\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "\n" +
                "                        <div id=\"sex\" class=\"info-container\">\n" +
                "                            <div class=\"info-container__title\">\n" +
                "                                <label for=\"sex\">Giới tính\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "\n" +
                "                            <div class=\"info-container__content\">\n" +
                "                                <div class=\"form-check\">\n" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault1\" value=\"nam\" " + (sex.equalsIgnoreCase("nam") ? "checked" : "")+">\n" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault1\">\n" +
                "                                        Nam\n" +
                "                                    </label>\n" +
                "                                </div>\n" +
                "                                <div class=\"form-check\">\n" +
                "                                    <input class=\"form-check-input\" type=\"radio\" name=\"sex\" id=\"flexRadioDefault2\" value=\"nu\"  " + (sex.equalsIgnoreCase("nu") ? "checked" : "") +">\n" +
                "                                    <label class=\"form-check-label\" for=\"flexRadioDefault2\">\n" +
                "                                        Nữ\n" +
                "                                    </label>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "                        </div>\n" +
                "\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label  class=\"w-20\"  for=\"birthday\">Ngày sinh: </label>\n" +
                "                            <input type=\"date\" class=\"form-control w-80\" id=\"birthday\" name=\"birthday\"  aria-describedby=\"\" placeholder=\"Nhập ngày sinh\" value=\"" +birthday+"\">\n" +
                "                             <div class=\"error\" hidden></div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-100\">\n" +
                "                        <label class=\"w-20\" for=\"address\">Địa chỉ: </label>\n" +
                "                        <input type=\"text\" class=\"form-control w-80\" id=\"address\" name=\"address\" aria-describedby=\"\" placeholder=\"Nhập Địa chỉ\" value=\"" +afterE.getAddress() +"\">\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-50\">\n" +
                "                        <label  class=\"w-40\"  for=\"datein\">Ngày vào làm: </label>\n" +
                "                        <input type=\"date\" class=\"form-control w-80\" id=\"datein\" name=\"datein\"  aria-describedby=\"\" placeholder=\"Nhập ngày vào làm\" value=\"" + afterE.getDatein().getDateInMonthDayYearSql()+"\">\n" +
                "                        <div class=\"error\" hidden></div>\n" +

                "                    </div>\n" +
                "                   <div class=\"form-group\" id=\"img-container\">\n" +
                "                        <label class=\"w-40\" for=\"myfile\">Ảnh đại diện: </label>\n" +
                "                        <input type=\"file\" id=\"myfile\" name=\"myfile\" accept=\".jpg, .png\" onchange=\"preview()\">\n" +
                "                        <div id=\"imgs-container\">\n" +
                "                            <div class=\"chosen-img\">\n" +
                "                                <i class=\"fa-solid fa-circle-xmark delete-img-btn\" onclick=\"deleteImg(this)\"></i>\n" +
                "                                <img src=\""+afterE.getImgurl() +"\" alt=\"\" style=\"width: 100px\" >\n" +
                "                                <input class=\"img-name\" name=\"imgurl\" value=\""+afterE.getImgurl()+"\" hidden>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>"+
                "                    <div class=\"form-group\" style=\"display: none\">\n" +
                "                        <label class=\"w-20\" for=\"action\" class=\"input-title\">action</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" id=\"action\" name=\"action\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\"" + (afterE.getName()==""?"add":"update") +"\">\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\" style=\"display: none\">\n" +
                "                        <label class=\"w-20\" for=\"status\" class=\"input-title\">status</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" id=\"status\" name=\"status\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\"1\">\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"ad_func-container\">\n" +
                "                            <div><a class=\"btn btn-third\" href=\"admin-menu-controller?adminMenu=employee\">Hủy</a></div>\n" +
                "                        </div>\n" +
                "                        <div class=\"ad_func-container\">\n" +
                "                            <button class=\"btn btn-primary\" type=\"submit\">" + (afterE.getName()==""?"Thêm":"Lưu") +"</button>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!--                    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>-->\n";


        return html;
    }


}