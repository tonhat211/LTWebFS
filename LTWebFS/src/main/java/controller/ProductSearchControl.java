package controller;

import database.BrandDAO;
import database.ProductDAO;
import database.ProductUnitDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;
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

@WebServlet("/searchProduct")
public class ProductSearchControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String input = request.getParameter("Search");
//        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductUnit> pus = new ArrayList<>();
        ArrayList<Brand> brandList = new ArrayList<>();
        ArrayList<String> countryList = new ArrayList<>();

        HttpSession session = request.getSession();
        session.setAttribute("currentMenu", "product");
        session.setAttribute("currentSearch", input);

        RequestDispatcher rd;
//        pus = ProductUnitDAO.getInstance().selectByKindAndName("A",input);
        if(ProductUnitDAO.getInstance().selectByKindAndName("A",input).size()>1){
            pus = ProductUnitDAO.getInstance().selectByKindAndName("A",input);
            ArrayList<Integer> idBrandAdded = new ArrayList<>();
            for(ProductUnit pu : pus ){
                if(!idBrandAdded.contains(pu.getBrandID())){
                    brandList.add(new Brand(pu.getBrandID(), pu.getBrand(),pu.getMadeIn(),1));
                    countryList.add(pu.getMadeIn());
                    idBrandAdded.add(pu.getBrandID());

                }
            }

            request.setAttribute("productUnitList", pus);
            request.setAttribute("brandList", brandList);
            request.setAttribute("countryList", countryList);
            request.setAttribute("currentKind", "A");
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        } else if(ProductUnitDAO.getInstance().selectByKindAndName("B",input).size()>1){
            pus = ProductUnitDAO.getInstance().selectByKindAndName("B",input);
            ArrayList<Integer> idBrandAdded = new ArrayList<>();
            for(ProductUnit pu : pus ){
                if(!idBrandAdded.contains(pu.getBrandID())){
                    brandList.add(new Brand(pu.getBrandID(), pu.getBrand(),pu.getMadeIn(),1));
                    countryList.add(pu.getMadeIn());
                    idBrandAdded.add(pu.getBrandID());

                }
            }

            request.setAttribute("productUnitList", pus);
            request.setAttribute("brandList", brandList);
            request.setAttribute("countryList", countryList);
            request.setAttribute("currentKind", "B");
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        } else if(ProductUnitDAO.getInstance().selectByKindAndName("C",input).size()>1){
            pus = ProductUnitDAO.getInstance().selectByKindAndName("C",input);
            ArrayList<Integer> idBrandAdded = new ArrayList<>();
            for(ProductUnit pu : pus ){
                if(!idBrandAdded.contains(pu.getBrandID())){
                    brandList.add(new Brand(pu.getBrandID(), pu.getBrand(),pu.getMadeIn(),1));
                    countryList.add(pu.getMadeIn());
                    idBrandAdded.add(pu.getBrandID());

                }
            }

            request.setAttribute("productUnitList", pus);
            request.setAttribute("brandList", brandList);
            request.setAttribute("countryList", countryList);
            request.setAttribute("currentKind", "C");
            rd = getServletContext().getRequestDispatcher("/product.jsp");
            rd.forward(request, response);
        }







        // Hello

    }
}