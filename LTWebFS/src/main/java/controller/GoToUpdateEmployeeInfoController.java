package controller;

import database.BranchDAO;
import database.EmployeeDAO;
import model.Branch;
import model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-update-employee-info")
public class GoToUpdateEmployeeInfoController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String idString = (String) request.getParameter("id");
        String currentMenu = request.getParameter("currentMenu");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);

    }
}
