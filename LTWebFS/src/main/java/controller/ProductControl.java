package controller;

import database.BrandDAO;
import database.ProductDAO;
import database.ProductUnitDAO;
import model.Brand;
import model.Product;
import model.ProductHeader;
import model.ProductUnit;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
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

        String kind = (String) session.getAttribute("kind");
        if(kind==null) kind ="A";
        String input = (String) session.getAttribute("currentSearch");
        String action = request.getParameter("action");
        action = action.toUpperCase();
        switch (action) {
            case "INIT": {
                session.setAttribute("kind","A");
                ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByKind("A",0,0,TOP);
                ArrayList<Brand> topBrandList = BrandDAO.getInstance().selectTopOf("A",6);
                ArrayList<String> countryList = BrandDAO.getInstance().selectAllCountry();

                request.setAttribute("productUnitList", pus);
                request.setAttribute("topBrandList", topBrandList);
                request.setAttribute("countryList", countryList);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
                rd.forward(request, response);
                break;
            }
            case "QUERY": {
                String kindin = request.getParameter("kind");
                session.setAttribute("kind",kindin);
                ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectByKind(kind,0,0,TOP);

                ArrayList<Brand> topBrandList = BrandDAO.getInstance().selectTopOf(kind,6);
                ArrayList<String> countryList = BrandDAO.getInstance().selectAllCountry();
                kind = kind.toUpperCase();
                request.setAttribute("kind",kind);
                request.setAttribute("productUnitList", pus);
                request.setAttribute("topBrandList", topBrandList);
                request.setAttribute("countryList", countryList);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/product.jsp");
                rd.forward(request, response);
                break;
            }
            case "MORE": {
                int index = Integer.parseInt(request.getParameter("index"));
                String country = request.getParameter("country");
                int brandID = Integer.parseInt(request.getParameter("brandID"));
                int arrange = Integer.parseInt(request.getParameter("arrange"));
                country = country.toUpperCase();

                ArrayList<ProductUnit> pus = new ArrayList<>();

                if(brandID==-2 && country.equalsIgnoreCase("Tất cả"))  { //all brand, all country
                    pus = ProductUnitDAO.getInstance().selectByKind(kind,arrange,index,TOP);
                } else if(!country.equalsIgnoreCase("Tất cả")) { // filter theo country
                    pus = ProductUnitDAO.getInstance().selectByCountry(kind,country,arrange,index,TOP);
                } else { // filter theo brand
                    if(brandID==-1) {//khac
                        ArrayList<Brand> brands = BrandDAO.getInstance().selectOtherOf(kind,BrandDAO.TOP);
                        ArrayList<Integer> brIDs = new ArrayList<>();
                        for(Brand b : brands) {
                            brIDs.add(b.getId());
                        }
                        pus = ProductUnitDAO.getInstance().selectByNotBrandAndKind(brIDs,kind,arrange,index,TOP);
                    } else {
                        pus = ProductUnitDAO.getInstance().selectByBrandAndKind(brandID,kind,arrange,index,TOP);
                    }
                }
                String html = renderHtml(pus);
                response.getWriter().write(html);
                break;
            }
            case "BRAND": {
                int brandID = Integer.parseInt(request.getParameter("brandID"));
                int arrange = Integer.parseInt(request.getParameter("arrange"));
                ArrayList<ProductUnit> pus = new ArrayList<>();
                if(brandID==-2) {//all
                    pus = ProductUnitDAO.getInstance().selectByKind(kind, arrange,0,TOP);
                } else if(brandID==-1) {//khac
                    ArrayList<Brand> brands = BrandDAO.getInstance().selectOtherOf(kind,BrandDAO.TOP);
                    ArrayList<Integer> brIDs = new ArrayList<>();
                     for(Brand b : brands) {
                        brIDs.add(b.getId());
                    }
                    pus = ProductUnitDAO.getInstance().selectByNotBrandAndKind(brIDs,kind,arrange,0,TOP);
                } else {
                    pus = ProductUnitDAO.getInstance().selectByBrandAndKind(brandID,kind,arrange,0,TOP);
                }


                String html = renderHtml(pus);
                response.getWriter().write(html);
                break;
            }
            case "COUNTRY": {
                String country = request.getParameter("country");
                int arrange = Integer.parseInt(request.getParameter("arrange"));
                ArrayList<ProductUnit> pus = new ArrayList<>();
                if(country.equals("Tất cả")) {
                    pus = ProductUnitDAO.getInstance().selectByKind(kind,arrange,0,TOP);
                } else {
                    pus = ProductUnitDAO.getInstance().selectByCountry(kind,country,arrange,0,TOP);
                }
                String html = renderHtml(pus);
                response.getWriter().write(html);
                break;
            }
            case "ARRANGE": {
                int brandID = Integer.parseInt(request.getParameter("brandID"));
                String country = request.getParameter("country");
                int arrange = Integer.parseInt(request.getParameter("arrange"));
                ArrayList<ProductUnit> pus = new ArrayList<>();

                if(country.equalsIgnoreCase("Tất cả")) {//neu chi co brandID
                    if(brandID==-2) {//all
                        System.out.println("brandID: " + brandID);
                        pus = ProductUnitDAO.getInstance().selectByKind(kind, arrange,0,TOP);
                    } else if(brandID==-1) {//khac
                        ArrayList<Brand> brands = BrandDAO.getInstance().selectOtherOf(kind,6);
                        ArrayList<Integer> brIDs = new ArrayList<>();
                        System.out.println("brandID: " + brandID);

                        for(Brand b : brands) {
                            brIDs.add(b.getId());
                        }
                        pus = ProductUnitDAO.getInstance().selectByNotBrandAndKind(brIDs,kind,arrange,0,TOP);
                    } else {
                        System.out.println("brandID: " + brandID);

                        pus = ProductUnitDAO.getInstance().selectByBrandAndKind(brandID,kind,arrange,0,TOP);
                    }
                } else {
                    System.out.println("country: " + country);

                    pus = ProductUnitDAO.getInstance().selectByCountry(kind,country,arrange,0,TOP);

                }

                String html = renderHtml(pus);
                response.getWriter().write(html);
                break;

            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String renderHtml(ArrayList<ProductUnit> pus) {
        String html="";
        if (pus.isEmpty()) {
            return "";
        }

        for(ProductUnit p : pus) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
            String priceString = numberFormat.format(p.getPrice());
            html +=  "                    <div class=\"grid-col-2 mt20 oneProduct proA\">\n" +
                    "                        <div class=\"product-item\">\n" +
                    "                            <a href=\"productDetail?id="+p.getId()+"\" class=\"product-item-link\">\n" +
                    "                                <div class=\"product-img\">\n" +
                    "                                    <img src=\"./assets/img/products/"+p.getImg()+"\"  alt=\"\">\n" +
                    "                                </div>\n" +
                    "\n" +
                    "                                <div class=\"product-info\">\n" +
                    "                                    <div class=\"product-name\"><p>"+p.getName()+"</p></div>\n" +
                    "                                    <p class=\"product-price\">\n" +
                                                                            priceString +
//                    "                                        <fmt:formatNumber value=\""+p.getPrice()+"\" pattern=\"#,##0.00\"/>\n" +
                    "                                        VND\n" +
                    "                                    </p>\n" +
                    "\n" +
                    "                                </div>\n" +
                    "                            </a>\n" +
                    "                        </div>\n" +
                    "                    </div>";;
        }
               
        return html;
    }

    public static final int TOP = 24;
}