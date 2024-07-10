package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
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
        System.out.println(u);
        if(u != null) {
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

            String html = "<script> showSuccessToast(\"Thêm sản phẩm vào giỏ hàng thành công\"); </script> ";
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(html);
//            response.getWriter().flush();

        } else {
            request.setAttribute("status","loginForUsingCart");
            String html = "<script> gotoLogin(); </script> ";
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(html);


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