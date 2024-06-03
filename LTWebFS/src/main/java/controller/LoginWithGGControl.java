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

@WebServlet("/loginGG")
public class LoginWithGGControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        GoogleLogin gg = new GoogleLogin();
        try {
            String accessToken = gg.getToken(code);

            // Lấy thông tin người dùng từ Google
            GoogleAccount googleAccount = new GoogleAccount();
            User user = googleAccount.getUserInfo(accessToken);
            int idin = user.getId();
            String email = user.getEmail();
            int level = user.getLevel();
            // Xử lý đăng nhập người dùng

            String ipAddress = request.getRemoteAddr();
            //ktr ton tai
            int id = UserDAO.getInstance().checkExistUser(email);
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
                    Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào hệ thống admin." ,"trống",email,1 );
                    LogDAO.getInstance().insert(log);

                    session.removeAttribute("userloging");
                    session.setAttribute("adminloging",e);
                    session.setAttribute("currentAdminMenu", currentMenu);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    session.setAttribute("userloging",user);
                    session.removeAttribute("adminloging");
                    Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào website." ,"trống",email,1 );
                    LogDAO.getInstance().insert(log);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
                    rd.forward(request, response);
                }
            } else {
                //chua ton tai
                UserDAO.getInstance().insertUser(user);
                DecartDAO.getInstance().insertCart(idin);

                //ghi log
                Log t = new Log(ipAddress,email + " | signup ","Thêm tài khoản mới vào hệ thống","trống",user.toString(),1 );
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
                    Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào hệ thống admin." ,"trống",email,1 );
                    LogDAO.getInstance().insert(log);

                    session.removeAttribute("userloging");
                    session.setAttribute("adminloging",e);
                    session.setAttribute("currentAdminMenu", currentMenu);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    session.setAttribute("userloging",user);
                    session.removeAttribute("adminloging");
                    Log log = new Log(ipAddress,email + " | login ","Đăng nhập thành công vào website." ,"trống",email,1 );
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
