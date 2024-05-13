package controller;

import database.ProductUnitDAO;
import database.UserDAO;
import model.ProductUnit;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goto-update-customer")
public class GoToUpdateCustomerController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idString = (String) request.getParameter("id");
//        String idString = "1015";
        int id = Integer.parseInt(idString);

        User u = UserDAO.getInstance().selectById(id);
        request.setAttribute("customer", u);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateCustomer.jsp");
        rd.forward(request, response);

    }
}
