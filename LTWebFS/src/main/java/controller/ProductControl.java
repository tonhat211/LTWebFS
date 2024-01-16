package controller;

import database.ProductDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/product")
public class ProductControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        String kind = request.getParameter("kind");

        ControllerProduct qly = new ControllerProduct(kind);
        ArrayList<ProductHeader>  productHeaderList = qly.getProductHeaderList();
        ArrayList<Brand> brandList = qly.getBrandList();
        ArrayList<String> countryList = qly.getCountries();
        request.setAttribute("productHeaderList", productHeaderList);
        request.setAttribute("brandList", brandList);
        request.setAttribute("countryList", countryList);
        request.setAttribute("currentKind",kind);

//        request.getRequestDispatcher("product.jsp").forward(request,response);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
        rd.forward(request, response);
    }
}