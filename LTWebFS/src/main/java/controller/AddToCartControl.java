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

@WebServlet("/add-to-cart")
public class AddToCartControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        User u = (User) session.getAttribute("userloging");

        if(u==null) {

            session.setAttribute("userloging",null);
            request.setAttribute("status","loginForUsingCart");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request,response);
        } else {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int qty = Integer.parseInt(request.getParameter("qty"));


//            kiem tra xem san pham da co trong gio hang chua
            DeCart dc = DecartDAO.getInstance().selectOneByCusaProID(u.getId(),productID);
            if(dc.getIdCart() == 0) { //chua co trong gio hang
                cartitem cart = new cartitem(u.getId(),productID,qty);
                DecartDAO.getInstance().insert(cart);
            } else { // da co trong gio hang
                int newQty = dc.getQty() + qty;
                DecartDAO.getInstance().updateDecart(u.getId(),productID,newQty);
            }

            request.setAttribute("status","addToCartSuccessful");
            request.setAttribute("id",productID);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/productDetail");
            rd.forward(request,response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req,resp);
    }
}