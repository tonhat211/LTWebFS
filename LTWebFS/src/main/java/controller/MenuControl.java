package controller;

import database.ProductUnitDAO;
import model.Brand;
import model.ProductHeader;
import model.ProductUnit;
//import org.hsqldb.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/menucontrol")
public class MenuControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        String currentMenu;
        String menu = request.getParameter("menu");
        HttpSession session = request.getSession();

        RequestDispatcher rd;
        switch (menu){
            case "index":
                currentMenu = "index";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;
            case "product":
                currentMenu = "product";
                session.setAttribute("currentMenu",currentMenu);
                String kind = request.getParameter("kind");
//                ControllerProduct qly = new ControllerProduct(kind);
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
                request.setAttribute("productUnitList", pus);
                request.setAttribute("brandList", brandList);
                request.setAttribute("countryList", countryList);
                request.setAttribute("currentKind",kind);

//        request.getRequestDispatcher("product.jsp").forward(request,response);
                rd = getServletContext().getRequestDispatcher("/product.jsp");
                rd.forward(request, response);
                break;
            case "news":
                currentMenu = "news";
                session.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/news.jsp");
                rd.forward(request, response);
                break;
            case "aboutus":
                currentMenu = "aboutus";
                session.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/aboutUs.jsp");
                rd.forward(request, response);
                break;
        }



        // Hello

    }
}