package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import database.AddressDAO;
import database.DecartDAO;
import model.Address;
import model.DeCart;
import model.User;
import model.cartitem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-payment")
public class GoToPayment extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");

        ArrayList<Address> addresses = AddressDAO.getInstance().selectByUserID(u.getId());

        String[] idproString =  request.getParameterValues("check");
        ArrayList<Integer> idpros = new ArrayList<>();
        if(idproString != null) {
            for(int i = 0; i<idproString.length;i++) {
                idpros.add(Integer.parseInt(idproString[i]));

            }

            int idcart = u.getId();
            ArrayList<cartitem> cartTemp = DecartDAO.getInstance().getCartItemsByCaP(idcart,idpros);

            request.setAttribute("cartTemp", cartTemp);
            request.setAttribute("addresses", addresses);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/payment.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("status","noProductChosen");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-cart");
            rd.forward(request,response);
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
