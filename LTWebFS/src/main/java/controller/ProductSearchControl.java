package controller;

import database.BrandDAO;
import database.ProductDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/searchProduct")
public class ProductSearchControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String input = request.getParameter("Search");
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductHeader> productHeaderArrayList = new ArrayList<>();
        ArrayList<Brand> brandArrayList = new ArrayList<>();
        ArrayList<String> countryArrayList = new ArrayList<>();

        HttpSession session = request.getSession();
        session.setAttribute("currentMenu", "product");
        session.setAttribute("currentSearch", input);

        RequestDispatcher rd;
        if(ProductDAO.getInstance().selectProsByNameAndKind("A",input).size()>1){
            productArrayList = ProductDAO.getInstance().selectProsByNameAndKind("A",input);
            for(Product p : productArrayList){
                productHeaderArrayList.add((new ProductHeader(p)));
                Brand b = BrandDAO.getInstance().selectById(p.getBrandID());
                if(!brandArrayList.contains(b)) {
                    brandArrayList.add(b);
                    if(!countryArrayList.contains(b.getCountry()))
                        countryArrayList.add(b.getCountry());
                }
            }
            request.setAttribute("brandList", brandArrayList);
            request.setAttribute("countryList", countryArrayList);
            request.setAttribute("productHeaderList", productHeaderArrayList);
            request.setAttribute("currentKind", "A");
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        } else if(ProductDAO.getInstance().selectProsByNameAndKind("B",input).size()>1){
            productArrayList = ProductDAO.getInstance().selectProsByNameAndKind("A",input);
            for(Product p : productArrayList){
                productHeaderArrayList.add((new ProductHeader(p)));
                Brand b = BrandDAO.getInstance().selectById(p.getBrandID());
                if(!brandArrayList.contains(b)) {
                    brandArrayList.add(b);
                    if(!countryArrayList.contains(b.getCountry()))
                        countryArrayList.add(b.getCountry());
                }
            }
            request.setAttribute("brandList", brandArrayList);
            request.setAttribute("countryList", countryArrayList);
            request.setAttribute("productHeaderList", productHeaderArrayList);
            request.setAttribute("currentKind", "B");
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        } else if(ProductDAO.getInstance().selectProsByNameAndKind("C",input).size()>1){
            productArrayList = ProductDAO.getInstance().selectProsByNameAndKind("A",input);
            for(Product p : productArrayList){
                productHeaderArrayList.add((new ProductHeader(p)));
                Brand b = BrandDAO.getInstance().selectById(p.getBrandID());
                if(!brandArrayList.contains(b)) {
                    if(!countryArrayList.contains(b.getCountry()))
                        countryArrayList.add(b.getCountry());
                }
            }
            request.setAttribute("brandList", brandArrayList);
            request.setAttribute("countryList", countryArrayList);
            request.setAttribute("currentKind", "C");
            request.setAttribute("productHeaderList", productHeaderArrayList);
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        }







        // Hello

    }
}