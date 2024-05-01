package controller;

import model.Brand;
import model.ProductDetail;
import model.ProductHeader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//@WebServlet("/productDetail")
public class ProductDetailControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        String kind = request.getParameter("kind");
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        ProductDetail productDetail = new ProductDetail(id);
        request.setAttribute("productDetail", productDetail);


//        request.getRequestDispatcher("product.jsp").forward(request,response);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/productDetail.jsp");
        rd.forward(request, response);
    }
}