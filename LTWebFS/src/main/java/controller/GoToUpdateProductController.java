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

@WebServlet("/go-to-update-product")
public class GoToUpdateProductController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        ProductUnit pu = ProductUnitDAO.getInstance().selectOneByID(id);
        request.setAttribute("productUnit", pu);


//        request.getRequestDispatcher("product.jsp").forward(request,response);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdate.jsp");
        rd.forward(request, response);

    }
}
