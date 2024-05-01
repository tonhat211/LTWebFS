package controller;

import database.CustomerDAO;
import database.EmployeeDAO;
import database.ProductUnitDAO;
import model.Brand;
import model.Customer;
import model.Employee;
import model.ProductUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin-search")
public class AdminSearchControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String object = request.getParameter("object");
        String input = request.getParameter("search");
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
//        session.setAttribute("currentSearch", input);
        RequestDispatcher rd;

        switch(object) {
            case "customer": {
                ArrayList<Customer> customerList = CustomerDAO.getInstance().selectByCusNameOrEmailOrPhone(input);
                request.setAttribute("customerList", customerList);
                request.setAttribute("currentSearch",input);
                rd = getServletContext().getRequestDispatcher("/adminCustomer.jsp");
                rd.forward(request, response);
                break;

            }
            case "employee": {
                ArrayList<Employee> employeeList = EmployeeDAO.getInstance().selectByNameOrEmailOrPhone(input);
                request.setAttribute("employeeList", employeeList);
                request.setAttribute("currentSearch",input);
                request.setAttribute("status",status);
                rd = getServletContext().getRequestDispatcher("/adminEmployee.jsp");
                rd.forward(request, response);
                break;

            }
        }

    }
}