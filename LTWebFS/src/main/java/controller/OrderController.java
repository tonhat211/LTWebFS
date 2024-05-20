package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
import java.util.Arrays;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");

        int id = OrderDAO.getInstance().getMaxID() +1;
        int userId = u.getId();
        float totalMoney = Float.parseFloat(request.getParameter("orderMoney"));
        float deliveryFee = Float.parseFloat(request.getParameter("deliveryFee"));
        String receiver = request.getParameter("receiver");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String receiverInfo = receiver + "=" + phone + "=" + address;

        Order o = new Order(id,totalMoney,userId,receiverInfo,deliveryFee,0);
        OrderDAO.getInstance().insert(o);

        String[] productIDs = request.getParameterValues("productID");
        String[] priceUnits = request.getParameterValues("priceUnit");
        String[] qtys = request.getParameterValues("qty");

        ArrayList<DeOrder> dos = new ArrayList<>();
        ArrayList<Integer> idpros = new ArrayList<>();

        for(int i=0; i<productIDs.length;i++) {
            int proid = Integer.parseInt(productIDs[i]);
            float priceunit = Float.parseFloat(priceUnits[i]);
            int qty = Integer.parseInt(qtys[i]);
            dos.add(new DeOrder(o.getId(),proid,priceunit,qty));

            idpros.add(proid);
        }

        DeOrderDAO.getInstance().insertDeOrderList(dos);

        DecartDAO.getInstance().deleteDecarts(userId,idpros);

        request.setAttribute("status","orderSuccessful");


        Log log = new Log(request.getRemoteAddr(),u.getEmail() + " | payment ","Đã đặt thành công một đơn hàng." ,"trống","Mã đơn hàng: " + o.getId(),1 );
        LogDAO.getInstance().insert(log);


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-cart");
        rd.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);

    }


}
