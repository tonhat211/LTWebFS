package controller;

import database.BrandDAO;
import database.ProductDAO;
import database.ProductUnitDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;
import model.ProductUnit;

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String kind = request.getParameter("kind");
        String input = (String) session.getAttribute("currentSearch");
        if(input==null) {
//            ControllerProduct qly = new ControllerProduct(kind);
//            ArrayList<ProductHeader> productHeaderList = qly.getProductHeaderList();
            ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByKind(kind);
            ArrayList<Integer> idBrandAdded = new ArrayList<>();
            ArrayList<Brand> brandList = new ArrayList<>();
            ArrayList<String> countryList = new ArrayList<>();
            for(ProductUnit pu : pus ){
                if(!idBrandAdded.contains(pu.getBrandID())){
                    brandList.add(new Brand(pu.getBrandID(), pu.getBrand(),pu.getMadeIn(),1));
                    countryList.add(pu.getMadeIn());
                    idBrandAdded.add(pu.getBrandID());

                }
            }

//            ArrayList<Brand> brandList = qly.getBrandList();
//            ArrayList<String> countryList = qly.getCountries();
            request.setAttribute("productUnitList", pus);
            request.setAttribute("brandList", brandList);
            request.setAttribute("countryList", countryList);
            request.setAttribute("currentKind", kind);

//        request.getRequestDispatcher("product.jsp").forward(request,response);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        } else {
            ArrayList<ProductUnit> pus = new ArrayList<>();
            ArrayList<ProductHeader> productHeaderArrayList = new ArrayList<>();
            ArrayList<Brand> brandArrayList = new ArrayList<>();
            ArrayList<String> countryArrayList = new ArrayList<>();
            session.setAttribute("currentMenu", "product");
            session.setAttribute("currentSearch", input);

            RequestDispatcher rd;
            pus = ProductUnitDAO.getInstance().selectByKindAndName(kind,input);
            ArrayList<Integer> idBrandAdded = new ArrayList<>();
            ArrayList<Brand> brandList = new ArrayList<>();
            ArrayList<String> countryList = new ArrayList<>();
            for(ProductUnit pu : pus ){
                if(!idBrandAdded.contains(pu.getBrandID())){
                    brandList.add(new Brand(pu.getBrandID(), pu.getBrand(),pu.getMadeIn(),1));
                    countryList.add(pu.getMadeIn());
                    idBrandAdded.add(pu.getBrandID());

                }
            }

//            ArrayList<Brand> brandList = qly.getBrandList();
//            ArrayList<String> countryList = qly.getCountries();
            request.setAttribute("productUnitList", pus);
            request.setAttribute("brandList", brandList);
            request.setAttribute("countryList", countryList);
            request.setAttribute("currentKind", kind);
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        }


    }
}