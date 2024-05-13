package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import database.DecartDAO;
import model.DeCart;
import model.User;
import model.cartitem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-payment1")
public class GoToPayment1 extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");

        String cartJSON = request.getParameter("selectedProducts");

        Gson gson = new Gson();
        DeCart[] selectedProducts = gson.fromJson(cartJSON, DeCart[].class);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{ \"success\": true }");

//        if(selectedProducts.length < 1) {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
//            rd.forward(request, response);
//        } else {
//            ArrayList<Integer> idpros = new ArrayList<>();
//            for(int i=0; i<selectedProducts.length;i++) {
//                idpros.add(selectedProducts[i].getIdProduct());
//            }
//            int idcart = selectedProducts[0].getIdCart();
//
//            ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart,idpros);
//
//            request.setAttribute("cartTemp", cartTemp);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/payment.jsp");
//            rd.forward(request, response);
//
//
//        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

//        if (selectedProducts.length < 1) {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
//            rd.forward(request, response);
//        } else {
//            ArrayList<Integer> idpros = new ArrayList<>();
//            for (int i = 0; i < selectedProducts.length; i++) {
//                idpros.add(selectedProducts[i].getIdProduct());
//            }
//            int idcart = selectedProducts[0].getIdCart();
//
//            ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart, idpros);
//
//            request.setAttribute("cartTemp", cartTemp);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/payment.jsp");
//            rd.forward(request, response);
//
//
//        }
    }


}
