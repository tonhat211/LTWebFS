package controller;

import database.OrderDAO;
import model.Employee;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/admin-order")
public class AdminOrderController extends HttpServlet {
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        Map<Order,String> orders = OrderDAO.getInstance().getAllOrderAndDatail();
        Employee adminloging = (Employee) session.getAttribute("adminloging");

        String action = request.getParameter("action");
        action=action.toUpperCase();
        switch (action) {
            case "INIT": {
                request.setAttribute("orders", orders);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminOrder.jsp");
                rd.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);

    }
}
