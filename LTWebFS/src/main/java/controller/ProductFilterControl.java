//package controller;
//
//import model.Brand;
//import model.Product;
//import model.ProductHeader;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/product")
//public class ProductFilterControl extends HttpServlet {
//
//    public void destroy() {
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html; charset=UTF-8");
//        String order = request.getParameter("order");
//
//
//        // Lấy dữ liệu sản phẩm từ cơ sở dữ liệu hoặc nguồn dữ liệu khác
//        List<ProductHeader> products = getDataFromDatabase(order);
//
//
//        request.setAttribute("products", products);
//        request.setAttribute("order", order);
//
////        request.getRequestDispatcher("product.jsp").forward(request,response);
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
//        rd.forward(request, response);
//    }
//}