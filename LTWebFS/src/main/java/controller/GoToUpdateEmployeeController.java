package controller;

import database.BranchDAO;
import database.EmployeeDAO;
import database.UserDAO;
import model.Branch;
import model.Employee;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-update-employee")
public class GoToUpdateEmployeeController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idString = (String) request.getParameter("id");
//        String idString = "1015";
        int id = Integer.parseInt(idString);
        Employee e = EmployeeDAO.getInstance().selectById(id);
        String status = (String) request.getAttribute("status");
        ArrayList<Branch> branches = BranchDAO.getInstance().selectAll();
        request.setAttribute("employee", e);
        request.setAttribute("branchList", branches);
        request.setAttribute("status",status);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateEmployee.jsp");
        rd.forward(request, response);

    }
}
