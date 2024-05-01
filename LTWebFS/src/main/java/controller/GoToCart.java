package controller;

import database.BrandDAO;
import database.DecartDAO;
import database.ProductUnitDAO;
import database.UnitDAO;
import model.Brand;
import model.ProductUnit;
import model.User;
import model.cartitem;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-cart")
public class GoToCart extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userloging");
        if (u!=null) {
            String status = (String) request.getAttribute("status");
            if(status ==  null) {
                request.setAttribute("status",null);
            }

            int id = u.getId();

            ArrayList<cartitem> cart = DecartDAO.getInstance().getCartItems(id);

            request.setAttribute("cart", cart);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("status", "loginForUsingCart");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
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
