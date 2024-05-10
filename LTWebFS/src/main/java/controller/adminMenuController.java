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

@WebServlet("/admin-menu-controller")
public class adminMenuController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        String currentMenu;
        String menu = request.getParameter("adminMenu");
        HttpSession session = request.getSession();

        RequestDispatcher rd;
        switch (menu){
            case "dashboard": {
                currentMenu = "dashboard";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/goto-dashboard-admin");
                rd.forward(request, response);
                break;
            }
            case "customer": {
                currentMenu = "customer";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/goto-customer-admin");
                rd.forward(request, response);
                break;
            }
            case "employee": {
                currentMenu = "employee";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/goto-employee-admin");
                rd.forward(request, response);
                break;
            }
            case "product": {
                currentMenu = "product";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                session.removeAttribute("adminCurrentSearchProduct");
                rd = getServletContext().getRequestDispatcher("/goto-product-admin");
                rd.forward(request, response);
                break;
            }
            case "order": {
                currentMenu = "order";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/adminOrder.jsp");
                rd.forward(request, response);
                break;
            }
            case "log": {
                currentMenu = "log";
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/goto-logManagement");
                rd.forward(request, response);
                break;
            }
            case "news": {
                currentMenu = "news";
//                request.setAttribute("currentMenu",currentMenu);
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/adminNews.jsp");
                rd.forward(request, response);
                break;
            }


        }



        // Hello

    }

}
