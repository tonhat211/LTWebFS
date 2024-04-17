package controller;

import database.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-product")
public class UpdateProduct extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String action = request.getParameter("action");
        int idin = Integer.parseInt(request.getParameter("id"));
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case "STOP":{
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,0);
                    request.setAttribute("idin",idin);
//                    request.setAttribute("action","update");
//            request.setAttribute("currentID", idin);
//                    session.setAttribute("currentSearch",idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);
                    break;
                }
                case "RESALE":{
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,1);
                    request.setAttribute("idin",idin);
//
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);
                    break;
                }
                case "UPDATE":{
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,1);
                    request.setAttribute("idin",idin);

                    String name = request.getParameter("pro-name");
                    String country = request.getParameter("pro-country")!=null ? request.getParameter("pro-country") : null;
                    String brand = request.getParameter("pro-brand");
                    String kind = request.getParameter("pro-kind");
                    String area = request.getParameter("pro-area");
                    String type = request.getParameter("pro-type");
                    String imgs = request.getParameter("pro-imgs");
                    double price = Double.parseDouble(request.getParameter("pro-price"));
                    int amount = Integer.parseInt(request.getParameter("pro-amount"));
                    int yearMade = Integer.parseInt(request.getParameter("pro-yearMade"));
//
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);
                    break;
                }
//                default:{
//
//                }
            }
        }


    }
}
