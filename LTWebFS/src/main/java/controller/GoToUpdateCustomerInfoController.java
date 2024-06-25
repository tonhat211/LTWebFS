package controller;

import database.UserDAO;
import model.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/goto-update-customer-info")
public class GoToUpdateCustomerInfoController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");

        String status = (String) request.getAttribute("status");
        if(status != null) {
            request.setAttribute("status",status);
        } else {
            request.setAttribute("status",null);
        }

        request.setAttribute("customer", u);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateCustomerInfo.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);
    }
}
