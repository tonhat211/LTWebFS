package controller;

import database.ProductUnitDAO;
import model.Brand;
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
                session.setAttribute("currentMenu",currentMenu);
                rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;
            case "product":
                currentMenu = "product";
                session.setAttribute("currentMenu",currentMenu);

                rd = getServletContext().getRequestDispatcher("/product?action=init");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
}