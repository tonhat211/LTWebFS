package controller;

import database.DecartDAO;
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

@WebServlet("/loginFB")
public class LoginFBControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        FacebookLogin fb = new FacebookLogin();
        try {

            String token = fb.getToken(code);

            // Lấy thông tin người dùng từ Google
            FacebookAccount fbaccount = new FacebookAccount();
            User user = fbaccount.getUserInfo(token);
            int idin = user.getId();
            String name = user.getName();
            int level = user.getLevel();
            // Xử lý đăng nhập người dùng

            String ipAddress = request.getRemoteAddr();
            //ktr ton tai
            int id = UserDAO.getInstance().checkExistUserFB(name);
            if(id != 0) { // neu ton tai
                if(level > 0) {
                    Employee e = EmployeeDAO.getInstance().selectById(idin);
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
                    Log log = new Log(ipAddress,name + " | login ","Đăng nhập thành công vào hệ thống admin." ,"trống",name,1 );
                    LogDAO.getInstance().insert(log);

                    session.removeAttribute("userloging");
                    session.setAttribute("adminloging",e);
                    session.setAttribute("currentAdminMenu", currentMenu);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    session.setAttribute("userloging",user);
                    session.removeAttribute("adminloging");
                    Log log = new Log(ipAddress,name + " | login ","Đăng nhập thành công vào website." ,"trống",name,1 );
                    LogDAO.getInstance().insert(log);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
                    rd.forward(request, response);
                }
            } else {
                //chua ton tai
                UserDAO.getInstance().insertUser(user);
                DecartDAO.getInstance().insertCart(idin);

                //ghi log
                Log t = new Log(ipAddress,name + " | signup ","Thêm tài khoản mới vào hệ thống","trống",user.toString(),1 );
                LogDAO.getInstance().insert(t);

                if(level > 0) {
                    Employee e = EmployeeDAO.getInstance().selectById(idin);
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
                    Log log = new Log(ipAddress,name + " | login ","Đăng nhập thành công vào hệ thống admin." ,"trống",name,1 );
                    LogDAO.getInstance().insert(log);

                    session.removeAttribute("userloging");
                    session.setAttribute("adminloging",e);
                    session.setAttribute("currentAdminMenu", currentMenu);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    session.setAttribute("userloging",user);
                    session.removeAttribute("adminloging");
                    Log log = new Log(ipAddress,name + " | login ","Đăng nhập thành công vào website." ,"trống",name,1 );
                    LogDAO.getInstance().insert(log);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
                    rd.forward(request, response);
                }

            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
