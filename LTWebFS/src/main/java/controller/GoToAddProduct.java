package controller;

import database.BrandDAO;
import database.ProductUnitDAO;
import database.UnitDAO;
import model.Brand;
import model.Datee;
import model.ProductUnit;
import sun.management.counter.Units;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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

        ProductUnit pu = new ProductUnit(id,idUnit);
        pu.setYearMade(2022);
        request.setAttribute("productUnit", pu);
        request.setAttribute("action","add");
        ArrayList<Brand> brandList = BrandDAO.getInstance().selectAll();


        Datee dateimportDatee = new Datee(java.time.LocalDate.now().getYear(),java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
//        request.setAttribute("status",status);
//        pu.setDateImport(dateimportDatee.toString());
        pu.setDateImport(java.time.LocalDate.now().toString());
        request.setAttribute("brandList", brandList);


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateProduct.jsp");
        rd.forward(request, response);

    }
}
