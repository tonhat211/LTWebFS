package controller;

import database.CustomerDAO;
import database.OrderDAO;
import model.Customer;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-history-buying")
public class GoToHistoryBuying extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

//        int id = UserDAO.getInstance().selectTheMaxID() +1;
//        int status = Integer.parseInt(request.getParameter("status"));
        int cusID = Integer.parseInt(request.getParameter("cusID"));
        ArrayList<Order> os = OrderDAO.getInstance().selectOrderByCusId(cusID);

        Customer cus = CustomerDAO.getInstance().selectById(cusID);

        request.setAttribute("customer", cus);
        request.setAttribute("orderList",os);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/historyBuying.jsp");
        rd.forward(request, response);

    }
}
