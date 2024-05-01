package controller;

import database.ProductUnitDAO;
import model.ProductDetail;
import model.ProductUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productDetail")
public class ProductDetailController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id");
        if(idString == null ) {
            idString = String.valueOf(request.getAttribute("id"));
        }
        int id = Integer.parseInt(idString);

        String status = (String) request.getAttribute("status");

        ProductUnit pu = ProductUnitDAO.getInstance().selectById(id);


        request.setAttribute("productDetail", pu);
        if(status != null) {
            request.setAttribute("status",status);

        }

//        request.getRequestDispatcher("product.jsp").forward(request,response);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/productDetail.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req,resp);
    }

}