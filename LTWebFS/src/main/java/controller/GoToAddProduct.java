package controller;

import database.ProductUnitDAO;
import database.UnitDAO;
import model.ProductUnit;
import sun.management.counter.Units;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goto-add-product")
public class GoToAddProduct extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = ProductUnitDAO.getInstance().selectMaxID() +1;
        int idUnit  = UnitDAO.getInstance().selectTheMaxID() +1;
        int status = Integer.parseInt(request.getParameter("status"));

        int preID = id -1;
        String message = "";
        if(status ==1){
            message = "Da them mot san pham voi id " + preID;
        }


        ProductUnit pu = new ProductUnit(id,idUnit);

        request.setAttribute("productUnit", pu);
        request.setAttribute("action","add");
        request.setAttribute("status",status);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateProduct.jsp");
        rd.forward(request, response);

    }
}
