package controller;

import database.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/cus-order")
public class CusOrderController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");
        String action = request.getParameter("action");
        action = action.toUpperCase();
        switch (action) {
            case "INIT": {
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOf(u.getId());
                request.setAttribute("orderList",os);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/orderManagement.jsp");
                rd.forward(request, response);
                break;
            } case "ALL": {
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOf(u.getId());
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            } case "WAITING": {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(0);
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOfStatus(u.getId(),s);
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            } case "CONFIRM-PACKAGED": {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(1);
                s.add(2);
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOfStatus(u.getId(),s);
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            } case "DELIVERYING": {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(3);
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOfStatus(u.getId(),s);
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            }  case "COMPLETED": {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(4);
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOfStatus(u.getId(),s);
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            } case "CANCELED": {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(-1);
                s.add(-2);
                s.add(-3);
                s.add(-4);
                Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = OrderDAO.getInstance().getOrderOfStatus(u.getId(),s);
                request.setAttribute("orderList",os);
                String html = renderHtml(os);
                response.getWriter().write(html);
                break;
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
    
    public String renderHtml(Map<Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os) {
        String html="";

        if(os == null) {
            html ="<div class=\"order-container\">\n" +
                    "                    <div class=\"show-flex-row\">\n" +
                    "                        <p>Mã đơn hàng: <span class=\"orderID\">...</span> </p>\n" +
                    "                        <p class=\"orderStatus blue-color\">Không xác định</p>\n" +
                    "                    </div>\n" +
                    "                \n" +
                    "                    <div class=\"order-item\" onclick=\"showDetailsOrder.call(this)\">\n" +
                    "                        <div class=\"show-flex-row\" style=\"justify-content: left; align-items: start\">\n" +
                    "                            <div class=\"product-img-container\" style=\"width: 10%\">\n" +
                    "                                <img src=\"\" alt=\"\" class=\"produc-img\" style=\"width: 100%\">\n" +
                    "                            </div>\n" +
                    "                            <div class=\"product-info show-flex-col\" style=\"width: 90%; height: 100%; justify-content: space-between\">\n" +
                    "                                <p class=\"product-name\" style=\"font-size: 20px; font-weight: 600\">Không có đơn hàng nào</p>\n" +
                    "                                <div class=\"show-flex-row\" style=\"justify-content: space-between; height: 100%\">\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"seperate-horizontal-linear\"></div>\n" +
                    "                    </div>";
        } else {
            for (Map.Entry<Order, ArrayList<Map.Entry<DeOrder,ProductUnit>>> item : os.entrySet()) {
                ArrayList<Map.Entry<DeOrder, ProductUnit>> ps = item.getValue();
                String status = item.getKey().getStatusBefore();
                html += "<div class=\"order-container\">\n" +
                        "        <div class=\"show-flex-row\">\n" +
                        "            <p>Mã đơn hàng: <span class=\"orderID\">" + item.getKey().getId() + "</span> </p>\n" +
                        "            <span class=\"orderStatusNumber\" style=\"display: none\">" + item.getKey().getStatus() + "</span>\n" +
                        "            <span class=\"orderReceiver\" style=\"display: none\">" + item.getKey().getReceiver() + "</span>\n" +
                        "            <span class=\"orderReceiverPhone\" style=\"display: none\">" + item.getKey().getReceiverPhone() + "</span>\n" +
                        "            <span class=\"orderAddress\" style=\"display: none\">" + item.getKey().getAddress() + "</span>\n" +
                        "            <p class=\"orderStatus " + item.getKey().getColorByStatus() + "\">" + status + "</p>\n" +
                        "        </div>\n";

                for (Map.Entry<DeOrder, ProductUnit> p : ps) {
                    html +=
                            "        <div class=\"order-item\" onclick=\"showDetailsOrder.call(this)\">\n" +
                                    "             <div class=\"show-flex-row\" style=\"justify-content: left; align-items: start\">\n" +
                                    "                  <div class=\"product-img-container\" style=\"width: 10%\">\n" +
                                    "                       <img src=\"" + p.getValue().getImg() +"\" alt=\"\" class=\"produc-img\" style=\"width: 100%\">"+
                                    "                   </div>\n" +
                                    "                   <div class=\"product-info show-flex-col\" style=\"width: 90%; height: 100%; justify-content: space-between\">\n" +
                                    "                       <p class=\"product-name\" style=\"font-size: 20px; font-weight: 600\">" + p.getValue().getName() + "</p>\n" +
                                    "                            <div class=\"show-flex-row\" style=\"justify-content: space-between; height: 100%\">\n" +
                                    "                                 <p><span class=\"product-price\">" + ProductUnit.formatPrice(p.getValue().getPrice()) + "</span>VND</p>\n" +
                                    "                                 <p style=\"display: block\">Số lượng: <span class=\"product-qty\">" + p.getKey().getQty() + "</span></p>\n" +
                                    "                    </div>\n" +
                                    "             </div>\n" +
                                    "        </div>\n" +
                                    "        <div class=\"seperate-horizontal-linear\"></div>\n" +
                                    " </div>\n";
                }
                html +=
                        "                    <div class=\"show-flex-row\">\n" +
                                "                        <div>\n" +
                                "                            <p>Ngày đặt: <span class=\"order-dateSet\">"+item.getKey().getDateSet()+"</span></p>\n" +
                                "                            <p>Ngày hoàn thành: <span class=\"order-dateFinish\">"+item.getKey().getCompleteDateSet()+"</span></p>\n" +
                                "                        </div>\n" +
                                "                        <div class=\"show-flex-col\" style=\"align-items: end\">\n" +
                                "                            <p>Tổng tiền: <span class=\"order-Price\">"+ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</span> (VND)</p>\n";
                if (item.getKey().getStatus() >= 0 && item.getKey().getStatus() < 3) {
                    html += "<button type=\"button\" class=\"btn btn-third\" >Hủy</button>\n";
                } else if (item.getKey().getStatus() == 4) {
                    html += "                             <button type=\"button\" class=\"btn btn-primary\" >Đã nhận hàng</button>";

                }
                html +=
                        "                        </div>\n" +
                                "                    </div>\n" +
                                "                </div>\n";
            }

        }


        return html;
    }


}
