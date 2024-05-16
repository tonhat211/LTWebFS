package controller;

import database.CustomerDAO;
import database.EmployeeDAO;
import model.Customer;
import model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-employee-admin")
public class GoToAdminEmployeeController extends HttpServlet {
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        HttpSession session  =request.getSession();
        String status = request.getParameter("status");
        if(status != null) {
            request.setAttribute("status", "addSuccessful");
        }
        ArrayList<Employee> employeeList = EmployeeDAO.getInstance().selectAll();

        request.setAttribute("employeeList", employeeList);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminEmployee.jsp");
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
