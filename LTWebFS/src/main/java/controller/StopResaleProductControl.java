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
import java.util.ArrayList;

@WebServlet("/stopresale-product")
public class StopResaleProductControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String action = request.getParameter("action");
        int idin = Integer.parseInt(request.getParameter("id"));

       if(action.equalsIgnoreCase("resale")){
           UnitDAO.getInstance().availableToTrue(idin);
       } else if(action.equalsIgnoreCase("stop")){
           UnitDAO.getInstance().availableToFalse(idin);
       }

        ArrayList<ProductSuperDetail> psds = new ArrayList<>();
        ProductSuperDetail psd = ProductSuperDetailDAO.getInstance().selectById(idin);
        psds.add(psd);

//       request.setAttribute("currentID",  idin);
       request.setAttribute("productList",  psds);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
        rd.forward(request, response);












    }
}