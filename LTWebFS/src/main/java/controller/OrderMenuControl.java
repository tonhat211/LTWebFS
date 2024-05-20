package controller;

import database.OrderDAO;
import database.ProductUnitDAO;
import model.Brand;
import model.Order;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ordermenu")
public class OrderMenuControl extends HttpServlet {
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        String currentMenu;
        String menu = request.getParameter("adminMenu");
        HttpSession session = request.getSession();
        ArrayList<Order> orders = new ArrayList<>();
        Map<Order,String> orderWithDatails = new HashMap<>();
        ArrayList<Integer> statuses = new ArrayList<>();


        RequestDispatcher rd;
        switch (menu){
            case "all":
                currentMenu = "all";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu",currentMenu);

                orderWithDatails = OrderDAO.getInstance().getAllOrderAndDatail();
                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderManagement.jsp");
                rd.forward(request, response);
                break;
            case "confirm":
                currentMenu = "confirm";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.WAITING);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderConfirm.jsp");
                rd.forward(request, response);
                break;

            case "cancel":
                currentMenu = "cancel";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.WAITING);
                statuses.add(Order.CONFIRM);
                statuses.add(Order.PACKAGED);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderCancel.jsp");
                rd.forward(request, response);
                break;

            case "package":
                currentMenu = "package";
                session.setAttribute("currentAdminMenu",currentMenu);
//                statuses.add(Order.WAITING);
                statuses.add(Order.CONFIRM);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderPackage.jsp");
                rd.forward(request, response);
                break;

            case "delivery":
                currentMenu = "delivery";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.PACKAGED);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderDelivery.jsp");
                rd.forward(request, response);
                break;

            case "complete":
                currentMenu = "complete";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.DELIVERYING);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderComplete.jsp");
                rd.forward(request, response);
                break;
            case "confirmReturn":
                currentMenu = "confirmReturn";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.DELIVERYING);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderConfirmReturn.jsp");
                rd.forward(request, response);
                break;
            case "return":
                currentMenu = "return";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.CONFIRM_RETURN);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderReturn.jsp");
                rd.forward(request, response);
                break;
            case "completedReturn":
                currentMenu = "completedReturn";
                session.setAttribute("currentAdminMenu",currentMenu);
                statuses.add(Order.RETURNING);
                orderWithDatails = OrderDAO.getInstance().getOrderAndDatailIn(statuses);

                request.setAttribute("orders", orderWithDatails);
                rd = getServletContext().getRequestDispatcher("/orderCompletedReturn.jsp");
                rd.forward(request, response);
                break;

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