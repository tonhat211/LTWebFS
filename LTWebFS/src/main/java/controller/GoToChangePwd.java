package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import database.DecartDAO;
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

@WebServlet("/goto-changePwd")
public class GoToChangePwd extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String object = request.getParameter("object");
        request.setAttribute("object",object);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/changePwd.jsp");
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
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(cart);

    }


}