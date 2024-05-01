package controller;

import database.DeOrderDAO;
import database.DecartDAO;
import database.UserDAO;
import model.Datee;
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

@WebServlet("/update-cart")
public class UpdateCartController extends HttpServlet {

    public void destroy() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        int idcart = Integer.parseInt(request.getParameter("cartId"));
        int idproduct = Integer.parseInt(request.getParameter("productId"));
        int qty = Integer.parseInt(request.getParameter("quantity"));

        if(qty == 0) {
            DecartDAO.getInstance().deleteDecart(idcart,idproduct);
        }
        else {
            DecartDAO.getInstance().updateDecart(idcart,idproduct,qty);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Số lượng đã được cập nhật thành công cho sản phẩm có ID: " + idcart);

    }
}