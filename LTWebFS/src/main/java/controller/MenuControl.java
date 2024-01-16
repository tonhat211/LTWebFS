package controller;

import model.Brand;
import model.ProductHeader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        RequestDispatcher rd;
        switch (menu){
            case "index":
                currentMenu = "index";
                request.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;
            case "product":
                currentMenu = "product";
                request.setAttribute("currentMenu",currentMenu);
                String kind = request.getParameter("kind");
                ControllerProduct qly = new ControllerProduct(kind);

                ArrayList<ProductHeader> productHeaderList = qly.getProductHeaderList();
                ArrayList<Brand> brandList = qly.getBrandList();
                ArrayList<String> countryList = qly.getCountries();
                request.setAttribute("productHeaderList", productHeaderList);
                request.setAttribute("brandList", brandList);
                request.setAttribute("countryList", countryList);
                request.setAttribute("currentKind",kind);

//        request.getRequestDispatcher("product.jsp").forward(request,response);
                rd = getServletContext().getRequestDispatcher("/product.jsp");
                rd.forward(request, response);
                break;
            case "news":
                currentMenu = "news";
                request.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/news.jsp");
                rd.forward(request, response);
                break;
            case "aboutus":
                currentMenu = "aboutus";
                request.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/aboutUs.jsp");
                rd.forward(request, response);
                break;
        }



        // Hello

    }
}