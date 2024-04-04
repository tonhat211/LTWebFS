package controller;

import database.BrandDAO;
import database.ProductDAO;
import database.ProductSuperDetailDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;
import model.ProductSuperDetail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/searchProductDetail")
public class ProductDetailSearchControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String input = request.getParameter("searchProInfo");
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductHeader> productHeaderArrayList = new ArrayList<>();
        ArrayList<Brand> brandArrayList = new ArrayList<>();
        ArrayList<String> countryArrayList = new ArrayList<>();

        HttpSession session = request.getSession();
        session.setAttribute("currentMenu", "product");
        session.setAttribute("currentSearch", input);

        RequestDispatcher rd;

        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<ProductSuperDetail> psds = new ArrayList<>();

        if(input.split(" ").length==1){
            if(input.startsWith("101")){
                ProductSuperDetail psd = ProductSuperDetailDAO.getInstance().selectById(Integer.parseInt(input));
                psds.add(psd);
            } else  {
                psds = ProductSuperDetailDAO.getInstance().selectByKind(input);

            }

        } else {
            psds  = ProductSuperDetailDAO.getInstance().selectByName(input);
        }



        request.setAttribute("productList", psds);
        rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
        rd.forward(request, response);






    }
}