package controller;

import database.ProductUnitDAO;
import model.Brand;
import model.ProductUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin-search-product")
public class AdminProductSearchControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String input = request.getParameter("search");
//        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductUnit> pus = new ArrayList<>();
        ArrayList<Brand> brandList = new ArrayList<>();
        ArrayList<String> countryList = new ArrayList<>();

        HttpSession session = request.getSession();
        session.setAttribute("adminCurrentSearchProduct", input);

        RequestDispatcher rd;
//        pus = ProductUnitDAO.getInstance().selectByKindAndName("A",input);
        if(ProductUnitDAO.getInstance().selectByKindOrName(input).size()>1){
            pus = ProductUnitDAO.getInstance().selectByKindOrName(input);
            request.setAttribute("productUnitList", pus);
            rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
            rd.forward(request, response);
        }

    }
}