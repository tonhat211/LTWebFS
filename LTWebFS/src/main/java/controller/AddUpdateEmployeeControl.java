package controller;

import database.EmployeeDAO;
import database.LogDAO;
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

@WebServlet("/addUpdate-employee")
public class AddUpdateEmployeeControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        Employee adminloging = (Employee) session.getAttribute("adminloging");

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case  "LOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    Employee eTemp = EmployeeDAO.getInstance().selectById(idin);

                    EmployeeDAO.getInstance().updateAvailableByEmployID(idin,-2);
                    request.setAttribute("status","updateSuccessful");
                    request.setAttribute("id",idin);

                    String actionHelper = "";
                    if(adminloging.getId() == eTemp.getId()) {
                        actionHelper = " của chính mình";
                    }

                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã khóa tài khoản admin" + actionHelper + "." ,eTemp.getEmail() + "|" + eTemp.getAvailable(),eTemp.getEmail() + " | -2",1 );
                    LogDAO.getInstance().insert(log);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-employee");
                    rd.forward(request, response);
                    break;

                }
                case  "UNLOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    Employee eTemp = EmployeeDAO.getInstance().selectById(idin);

                    EmployeeDAO.getInstance().updateAvailableByEmployID(idin,1);
//                    request.setAttribute("id", idin);
                    request.setAttribute("status","updateSuccessful");
                    request.setAttribute("id",idin);


                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã mở khóa tài khoản admin." ,eTemp.getEmail() + "|" + eTemp.getAvailable(),eTemp.getEmail() + " | 1",1 );
                    LogDAO.getInstance().insert(log);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-employee");
                    rd.forward(request, response);

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
                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã cập nhật thông tin tài khoản admin" + actionHelper + "." ,eTemp.toString(),e.toString(),1 );
                    LogDAO.getInstance().insert(log);

                    EmployeeDAO.getInstance().updateEmployee(e,i);

//                    request.setAttribute("search",idin);
//                    request.setAttribute("object","employee");
                    request.setAttribute("status","updateSuccessful");
                    request.setAttribute("id",idin);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-employee");
                    rd.forward(request, response);

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

                    Image i = new Image(imgurl,idin);
                    Employee e = new Employee(idin,name,email,birthday,1,phone,address,branchID,branch,info,dateinDatee,null,0,0,imgurl,role);

                    EmployeeDAO.getInstance().insertEmployee(e,i);


                    Log log = new Log(request.getRemoteAddr(),adminloging.getEmail() + " | update_employee","Đã thêm một tài khoản admin mới." ,"trống",e.toString(),1 );
                    LogDAO.getInstance().insert(log);

                    int status= Integer.parseInt(request.getParameter("status"));

                    request.setAttribute("search",idin);
                    request.setAttribute("object","employee");
                    request.setAttribute("status",status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin-search?search=" + idin + "&object=employee&status=addSuccessful");
                    rd.forward(request, response);
                    break;
                }
//                default:{
//
//                }
            }
        }
    }
}