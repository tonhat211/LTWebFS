package controller;

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

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case  "LOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    UserDAO.getInstance().updateAvailableByUserID(idin,0);
                    request.setAttribute("id", idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer");
                    rd.forward(request, response);
                    break;

                }
                case  "UNLOCK":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    UserDAO.getInstance().updateAvailableByUserID(idin,1);
                    request.setAttribute("id", idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer");
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
                    String info = sex + "=" + birthday;
                    String datein = request.getParameter("datein");
                    String[] dateInTokens = datein.split("-");
                    Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                            Integer.parseInt(dateInTokens[1]),
                                    Integer.parseInt(dateInTokens[2]));
//                    '16-2022-4'
//                    Datee dateimportDatee = new Datee(01,01,2022);


                    User u = new User(idin,name,email,phone,info,dateinDatee);

                    UserDAO.getInstance().updateCustomer(u);


                    request.setAttribute("id",idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer");
                    rd.forward(request, response);
                    break;
                }
                case "ADD":{
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

                    User u = new User(idin,name,email,"1234",0,phone,address,0,info,dateinDatee,null,0);
                    UserDAO.getInstance().insert(u);

                    int status= Integer.parseInt(request.getParameter("status"));

                    request.setAttribute("id",idin);
                    request.setAttribute("status",status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-add-customer");
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