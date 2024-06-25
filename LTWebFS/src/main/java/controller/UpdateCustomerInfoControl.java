package controller;

import database.AddressDAO;
import database.BranchDAO;
import database.EmployeeDAO;
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

@WebServlet("/update-info")
public class UpdateCustomerInfoControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String object = request.getParameter("object");
        String action = request.getParameter("action");
        String ac = action.toUpperCase();
        switch (ac) {
            case "INIT" :{
                if(object.equalsIgnoreCase("customer")) {
                    User u = (User) session.getAttribute("userloging");

                    request.setAttribute("customer", u);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateCustomerInfo.jsp");
                    rd.forward(request, response);
                } else if (object.equalsIgnoreCase("employee")) {

                    ArrayList<Branch> branches = BranchDAO.getInstance().selectAll();
                    request.setAttribute("branchList", branches);

                    String status = (String) request.getAttribute("status");
                    if(status != null) {
                        request.setAttribute("status",status);
                    } else {
                        request.setAttribute("status",null);
                    }

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateEmployeeInfo.jsp");
                    rd.forward(request, response);
                }

                break;
            }
            case "UPDATE": {
                if(object.equalsIgnoreCase("customer")) {
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String sex = request.getParameter("sex");
                    String birthday = request.getParameter("birthday");
                    String info = sex + "=" + birthday;
                    String address = request.getParameter("address");
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                            Integer.parseInt(dateInTokens[2]));

                    User u = new User(idin,name,email,phone,address,info,dateinDatee);

                    UserDAO.getInstance().updateCustomer(u);
                    User newUser = UserDAO.getInstance().selectById(idin);
                    session.setAttribute("userloging",newUser);
                    String html ="";
                    String message= "Cập nhật thông tin thành công";
                    html =  "<script> showSuccessToast(\"" + message + "\"); </script> ";
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                } else if (object.equalsIgnoreCase("employee")) {
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
//
                    String address = request.getParameter("address");
                    String imgurl = request.getParameter("imgurl");


                    Image i = new Image(imgurl,idin);

                    Employee e = new Employee(idin, name, email,phone, address, info, imgurl);


                    EmployeeDAO.getInstance().updateEmployeeInfo(e,i);
                    Employee e1 = EmployeeDAO.getInstance().selectById(idin);

                    session.setAttribute("adminloging",e1);
                    String html ="";
                    String message= "Cập nhật thông tin thành công";
                    html =  "<script> showSuccessToast(\"" + message + "\"); </script> ";
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }

                break;
            }
            case "CHANGEPASSWORD": {
                String currentPwd = request.getParameter("currentPwd");
                String newPwd = request.getParameter("newPwd");
                int id = Integer.parseInt(request.getParameter("id"));
                User u = (User) UserDAO.getInstance().selectById(id);

                String tempPwd = User.encodePwd(currentPwd);
                User u2 = UserDAO.getInstance().selectByEmailAndPwd(u.getEmail(),tempPwd);
                if(u2!=null) {
                    if(currentPwd.equals(newPwd)) {
                        response.getWriter().write("thesamePwd");
                    } else {
                        newPwd = User.encodePwd(newPwd);
                        UserDAO.getInstance().updatePassword(u.getId(),newPwd);
                        response.getWriter().write("success");
                    }
                } else {
                    response.getWriter().write("wrongPwd");
                }
            }
            case "UPDATEADDRESS": {
                int id = Integer.parseInt(request.getParameter("id"));
                Address add = AddressDAO.getInstance().selectById(id);
//                html
                break;
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        doGet(req,resp);
    }
}