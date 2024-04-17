package controller;

import database.ProductUnitDAO;
import database.UnitDAO;
import database.UserDAO;
import model.Datee;
import model.ProductUnit;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goto-add-customer")
public class GoToAddCustomer extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

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


        int status = Integer.parseInt(request.getParameter("status"));

        int preID = id -1;
        String message = "";
        if(status ==1){
            message = "Da them mot san pham voi id " + preID;
        }

        User u = new User(id);



        request.setAttribute("customer", u);
        request.setAttribute("action","add");
        request.setAttribute("status",status);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateCustomer.jsp");
        rd.forward(request, response);

    }
}
