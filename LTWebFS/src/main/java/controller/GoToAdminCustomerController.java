package controller;

import database.CustomerDAO;
import database.ProductUnitDAO;
import model.Customer;
import model.ProductUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-customer-admin")
public class GoToAdminCustomerController extends HttpServlet {
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        HttpSession session  =request.getSession();
        ArrayList<Customer> cuss = CustomerDAO.getInstance().selectAll();

        request.setAttribute("customerList", cuss);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminCustomer.jsp");
        rd.forward(request, response);

    }
}
