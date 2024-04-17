package controller;

import database.CustomerDAO;
import database.DeOrderDAO;
import database.OrderDAO;
import model.Customer;
import model.DeOrder;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-order-detail")
public class GoToOrderDetail extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int ordID = Integer.parseInt(request.getParameter("ordID"));
        ArrayList<DeOrder> dos = DeOrderDAO.getInstance().selectByOID(ordID);

        Order o = OrderDAO.getInstance().selectById(ordID);


        request.setAttribute("order", o);
        request.setAttribute("dos",dos);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/orderDetail.jsp");
        rd.forward(request, response);

    }
}
