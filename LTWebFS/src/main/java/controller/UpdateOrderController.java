package controller;

import database.DeOrderDAO;
import database.DecartDAO;
import database.LogDAO;
import database.OrderDAO;
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
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/update-order")
public class UpdateOrderController extends HttpServlet {
    static final int WAITING = 0;
    static final int CONFIRM = 1;
    static final int PACKAGED = 2;
    static final int DELIVERYING = 3;
    static final int COMPLETED = 4;
    static final int CANCELED = -1;
    static final int CONFIRM_RETURN = -2;
    static final int RETURNING = -3;
    static final int COMPLETED_RETURN = -4;
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
//        User u = (User) session.getAttribute("userloging");

        int id = -1;

        String action = request.getParameter("action");
        String actionTemp = action.toUpperCase();
        if(!actionTemp.equalsIgnoreCase("SEARCH")) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String method = request.getParameter("method");
        Order oCheck = OrderDAO.getInstance().selectById(id);
        if(oCheck.getId()==0) {
            System.out.println("khong thay don hang");
            String html = renderHtmlNotFoundToAuto(id,OrderDAO.getInstance().getAllOrderAndDatail());
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(html);
            return;
        }
        switch (actionTemp) {
            case "SEARCH" : {
//                int searchStatus = Integer.parseInt(request.getParameter("searchStatus"));
                String input = request.getParameter("input");
                String inputTokens[] = input.split("/");
                if(inputTokens.length>0) {
                    input = "";
                    for(int i=0;i<inputTokens.length;i++) {
                        input += inputTokens[i] + "-";
                    }
                    input = input.substring(0,input.length()-1);
                }
//                System.out.println("search status " + searchStatus);
//                System.out.println("search " + search);
//                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                Map<Order, String> orders = new LinkedHashMap<>();
                if(input.equals("all")) orders = OrderDAO.getInstance().getAllOrderAndDatail();
                else orders = OrderDAO.getInstance().searchOrderAndDatailBy(input);
                if(orders==null) {
                    String html = renderHtmlNotFound();
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                } else {
                    String html = renderHtmlFind(orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }

            case "CONFIRM" : {
                int fitStatus = Order.WAITING; // status phu hop doi voi action
                int newStatus = Order.CONFIRM;// status se duoc cap nhat cho order
                ArrayList<Integer> statusCheck = new ArrayList<>();// danh sach status co the thuc hien voi action
                statusCheck.add(Order.WAITING);

                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }


                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }

            case "CANCEL" : {
//                int fitStatus = Order.WAITING;
                int newStatus = Order.CANCELED;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.WAITING);
                statusCheck.add(Order.CONFIRM);
                statusCheck.add(Order.PACKAGED);


                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatailIn(statusCheck);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatailIn(statusCheck);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }

            case "PACKAGE" : {
                int fitStatus = Order.CONFIRM;
                int newStatus = Order.PACKAGED;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.WAITING);
                statusCheck.add(Order.CONFIRM);
//                statusCheck.add(Order.PACKAGED);


                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            case "DELIVERY" : {
                int fitStatus = Order.PACKAGED;
                int newStatus = Order.DELIVERYING;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.WAITING);
                statusCheck.add(Order.CONFIRM);
                statusCheck.add(Order.PACKAGED);


                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            case "COMPLETE" : {
                int fitStatus = Order.DELIVERYING;
                int newStatus = Order.COMPLETED;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.WAITING);
                statusCheck.add(Order.CONFIRM);
                statusCheck.add(Order.PACKAGED);
                statusCheck.add(Order.DELIVERYING);

                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            case "CONFIRMRETURN" : {
                int fitStatus = Order.DELIVERYING;
                int newStatus = Order.CONFIRM_RETURN;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.DELIVERYING);

                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            case "RETURN" : {
                int fitStatus = Order.CONFIRM_RETURN;
                int newStatus = Order.RETURNING;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.DELIVERYING);
                statusCheck.add(Order.CONFIRM_RETURN);

                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            case "COMPLETEDRETURN" : {
                int fitStatus = Order.RETURNING;
                int newStatus = Order.COMPLETED_RETURN;
                ArrayList<Integer> statusCheck = new ArrayList<>();
                statusCheck.add(Order.DELIVERYING);
                statusCheck.add(Order.CONFIRM_RETURN);
                statusCheck.add(Order.RETURNING);

                int currentStatus = OrderDAO.getInstance().getOrderStatus(id);
                if(statusCheck.contains(currentStatus)) {
                    OrderDAO.getInstance().updateOrderStatus(id,newStatus);
                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    if(orders==null) {
                        String html = renderHtmlNull(id,"auto",action,OrderDAO.getInstance().getAllOrderAndDatail());
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);

                    } else {
                        String html = renderHtml(id,method,action,currentStatus,"successful",orders);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }


                } else {

                    Map<Order, String> orders = OrderDAO.getInstance().getOrderAndDatail(fitStatus);
                    String html = renderHtml(id,method,action,currentStatus,"error",orders);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                }
                break;
            }
            default: {
                System.out.println("Default");
                Map<Order, String> orders = OrderDAO.getInstance().getAllOrderAndDatail();
                String html = renderHtmlWrongAction(id,orders);
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(html);
                break;
            }
        }



//

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);

    }

    public String renderHtmlNull(int id, String method, String action,Map<Order,String> orders) {
        System.out.println("order null");
        String ac = translate(action);
        String html="";
        System.out.println("Het don hang, method: " + method);
        if(method.equalsIgnoreCase("auto")) {
            int i =0;
            for (Map.Entry<Order, String> item : orders.entrySet()) {
                i++;
                html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                        "                <td>" + item.getKey().getId()+"</td>\n" +
                        "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                        "                <td>" +item.getValue()+"</td>\n" +
                        "                <td>" + ProductUnit.formatPrice(item.getKey().getTotalPrice()) +"</td>\n" +
                        "                <td class=\""+item.getKey().getColorByStatus()+"\">"+item.getKey().getStatusBefore()+"</td>"+
                        "            </tr>\n";
            }
            html +="<script> showSuccessToast2(\"" + ac + " " +  id + " thành công. "+ "\"); " +
                    "updateExcelList("+ id + ",\"successful\"); </script>";

        } else {
            html = "<script> showInfoToast2(\"Hết đơn hàng cần " + translate(action) +" \"); </script>";
        }
        return html;

    }

    public String renderHtmlNotFound() {
        return
                "<script> showInfoToast2(\"Không thấy đơn hàng cần tìm\"); </script>";
    }
    public String renderHtmlWrongAction(int id, Map<Order,String> orders) {
        String html="";
        int i =0;
        for (Map.Entry<Order, String> item : orders.entrySet()) {
            i++;
            html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                    "                <td>" + item.getKey().getId()+"</td>\n" +
                    "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                    "                <td>" +item.getValue()+"</td>\n" +
                    "                <td>" +ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</td>\n" +
                    "                <td class=\""+item.getKey().getColorByStatus()+"\">"+item.getKey().getStatusBefore()+"</td>"+
                    "            </tr>\n";
        }
        html+="<script> showInfoToast2(\"Hành động không hợp lệ\"); updateExcelList("+ id + ",\"notfound\");</script>";
        return html;
    }
    public String renderHtmlNotFoundToAuto(int id, Map<Order,String> orders) {
        String html="";
        int i =0;
        for (Map.Entry<Order, String> item : orders.entrySet()) {
            i++;
            html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                    "                <td>" + item.getKey().getId()+"</td>\n" +
                    "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                    "                <td>" +item.getValue()+"</td>\n" +
                    "                <td>" +ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</td>\n" +
                    "                <td class=\""+item.getKey().getColorByStatus()+"\">"+item.getKey().getStatusBefore()+"</td>"+
                    "            </tr>\n";
        }
        html+="<script> showInfoToast2(\"Không tìm thấy đơn hàng\"); updateExcelList("+ id + ",\"notfound\");</script>";
        return html;
    }

    public String renderHtml(int id, String method, String action, int orderStatus, String funcStatus, Map<Order,String> orders) {
        String ac = translate(action);
        String oStatus = getStatus(orderStatus);
        String html="";
        String statuss = "";
        if(funcStatus.equalsIgnoreCase("successful")) statuss = "thành công";
        else statuss = "thất bại";
        int i;
        System.out.println("Con don hang, method:" + method);
        if(method.equalsIgnoreCase("auto")) {
            orders = OrderDAO.getInstance().getAllOrderAndDatail();
            i =0;
            for (Map.Entry<Order, String> item : orders.entrySet()) {
                i++;
                html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                        "                <td>" + item.getKey().getId()+"</td>\n" +
                        "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                        "                <td>" +item.getValue()+"</td>\n" +
                        "                <td>" +ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</td>\n" +
                        "                <td class=\""+item.getKey().getColorByStatus()+"\">"+item.getKey().getStatusBefore()+"</td>"+
                        "            </tr>\n";
            }
            html +="<script> updateExcelList("+ id + ",\""+ funcStatus+"\"); </script>";

        } else {
            i =0;
            for (Map.Entry<Order, String> item : orders.entrySet()) {
                i++;
                html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                        "                <td>" + item.getKey().getId()+"</td>\n" +
                        "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                        "                <td>" +item.getValue()+"</td>\n" +
                        "                <td>" +ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</td>\n" +
                        "                <td>" +item.getKey().getStatusBefore()+"</td>\n" +
                        "                <td><button class=\"btn_confirm_order " + action + "\" onclick=\"updateOrder('" +action + "',"+item.getKey().getId()+");\">"+ translate(action) +"</button> </td>\n" +

                        "            </tr>\n";
            }
        }




        if(funcStatus.equalsIgnoreCase("successful")) {
            html +="<script> showSuccessToast2(\"" + ac + " " +  id + " "+ statuss +". "+ "\"); </script>";

        } else if(funcStatus.equalsIgnoreCase("error")){
            html +="<script> showErrorToast2(\"" + ac + " " +  id + " "+ statuss +". Đơn hàng " + oStatus +".\"); </script>";

        }


        return html;

    }

    public String renderHtmlFind(Map<Order,String> orders) {

        String html="";
        String statuss = "";
        int i =0;
        for (Map.Entry<Order, String> item : orders.entrySet()) {
            i++;
            html += "            <tr class=\""+ (i%2==0 ? "roww": "") +"\" >\n" +
                    "                <td>" + item.getKey().getId()+"</td>\n" +
                    "                <td>" +item.getKey().getDateSet()+ "<br/>"+ item.getKey().getTimeSet()+"</td>\n" +
                    "                <td>" +item.getValue()+"</td>\n" +
                    "                <td>" +ProductUnit.formatPrice(item.getKey().getTotalPrice())+"</td>\n" +
                    "                <td class=\"" + item.getKey().getColorByStatus()+"\">" +item.getKey().getStatusBefore()+"</td>\n" +

                    "            </tr>\n";
        }


        return html;

    }

    public String translate(String ac) {
        ac = ac.toUpperCase();
        switch (ac) {
            case "CONFIRM" :{
                return "Xác nhận";
            }
            case "CANCEL" : return "Hủy";
            case "PACKAGE": return "Đóng gói";
            case "DELIVERY": return "Bàn giao vận chuyển";
            case "COMPLETE" : return "Hoàn thành";
            case "CONFIRMRETURN" : return "Xác nhận trả";
            case "RETURN" : return "Bàn giao trả";
            case "COMPLETEDRETURN" : return "Trả hàng thành công";

        }
        return "Không xác định";
    }

     public String renderHtmlFailed(int id, String action, String status,Map<Order,String> orders) {
        String ac = "";
        ac = translate(action);

        return "<script> showErrorToast2(\"" + ac +" " + id + " khong thanh cong. " + status +"\"); </script>";
     }

     public String getStatus(int status) {
        switch (status) {
            case 0: return "Chờ xác nhận";
            case 1:
                return "Đã xác nhận";
            case 2:
                return "Đã được đóng gói";
            case 3:
                return "Đã bàn giao cho đơn vị vận chuyển";
            case 4:
                return "Đã hoàn thành";
            case -1:
                return "Đã hủy";
            case -2:
                return "Đã xác nhận trả";
            case -3:
                return "Đã bàn giao trả";
            case -4:
                return "Đã trả thành công";
        }
        return "Không xác định";
     }


}
