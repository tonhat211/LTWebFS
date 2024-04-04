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

@WebServlet("/add-product")
public class AddProductControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

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

        String dateIn = request.getParameter("pro-dateImport");
        String des =  request.getParameter("pro-brand")!= null?  request.getParameter("pro-brand") : null; ;

        Brand b = BrandDAO.getInstance().selectByName(brand);
        int brandID =-1;
        if(b==null){
            int bIDNew =  BrandDAO.getInstance().selectTheMaxID() +1;
            String bNameNew = brand;
            String bCountryNew = country;
            BrandDAO.getInstance().insert(new Brand(bIDNew, bNameNew, bCountryNew, 1));
            brandID = bIDNew;

        } else {
            brandID=b.getId();
        }

        Area a = AreaDAO.getInstance().selectByName(area);
        int areaID=-1;
        if(a==null){
            int aIDNew =  AreaDAO.getInstance().selectTheMaxID() +1;
            String aNameNew = area;
            AreaDAO.getInstance().insert(new Area(aIDNew, aNameNew, 1));
            areaID = aIDNew;

        } else  {
            areaID = a.getId();
        }

        int pIDNew = ProductDAO.getInstance().selectTheMaxID() + 1;

        Product p = new Product(pIDNew, name, brandID,  areaID,kind, des);
        ProductDAO.getInstance().insert(p);

        int uIDMax = UnitDAO.getInstance().selectTheMaxID();
        if(type!=null){
            String[] typeTokens = type.split("-");
            for(int  i=0; i< typeTokens.length;i++){
                uIDMax++;
                Unit u  =  new Unit(uIDMax, pIDNew, null, typeTokens[i],0, price,amount,yearMade,dateIn,1);
                UnitDAO.getInstance().insert(u);
            }
        } else {
            uIDMax++;
            Unit u  =  new Unit(uIDMax, pIDNew, null, null,0, price,amount,yearMade,dateIn,1);
            UnitDAO.getInstance().insert(u);
        }


        int iIDNew = ImageDAO.getInstance().selectTheMaxID();
        String[] imgTokens = imgs.split("==");
        for(int i=0;i<imgTokens.length;i++){
            if(imgTokens[i]=="" || imgTokens[i] == null) continue;
            iIDNew++;
            Image img = new Image(iIDNew,imgTokens[i],pIDNew);
            ImageDAO.getInstance().insert(img);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
        rd.forward(request, response);












    }
}