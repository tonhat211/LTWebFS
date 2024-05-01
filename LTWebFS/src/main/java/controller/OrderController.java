package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import database.DeOrderDAO;
import database.DecartDAO;
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



        Order o = new Order(id,totalMoney,userId,deliveryFee,0);
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


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-cart");
        rd.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(request,response);

    }

    public void doPost1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");

        String cartJSON = request.getParameter("selectedProducts");

        Gson gson = new Gson();
        DeCart[] selectedProducts = gson.fromJson(cartJSON, DeCart[].class);

        ArrayList<Integer> idpros = new ArrayList<>();
            for (int i = 0; i < selectedProducts.length; i++) {
                idpros.add(selectedProducts[i].getIdProduct());
            }
            int idcart = selectedProducts[0].getIdCart();

            ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart, idpros);

//            request.setAttribute("cartTemp", cartTemp);

        ObjectMapper mapper = new ObjectMapper();

        cartitem[] cart = new cartitem[cartTemp.size()];
        for(int i=0; i >cartTemp.size();i++) {
            cart[i] = cartTemp.get(i);
        }

        String json = mapper.writeValueAsString(cart);

        response.getWriter().write(json);

    }


}
