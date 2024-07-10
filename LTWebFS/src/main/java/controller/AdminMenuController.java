package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin-menu-controller")
public class AdminMenuController extends HttpServlet {

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
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/dashboard?action=init");
                rd.forward(request, response);
                break;
            }
            case "customer": {
                currentMenu = "customer";
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/customer?action=init");
                rd.forward(request, response);
                break;
            }
            case "employee": {
                currentMenu = "employee";
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/employee?action=init");
                rd.forward(request, response);
                break;
            }
            case "product": {
                currentMenu = "product";
                session.setAttribute("currentAdminMenu", currentMenu);
                session.removeAttribute("adminCurrentSearchProduct");
                rd = getServletContext().getRequestDispatcher("/admin-product?action=init");
                rd.forward(request, response);
                break;
            }
            case "order": {
                currentMenu = "order";
                session.setAttribute("currentAdminMenu", currentMenu);
                session.removeAttribute("adminCurrentSearchProduct");

                rd = getServletContext().getRequestDispatcher("/admin-order?action=init");
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
                session.setAttribute("currentAdminMenu", currentMenu);
                rd = getServletContext().getRequestDispatcher("/adminNews.jsp");
                rd.forward(request, response);
                break;
            }
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
