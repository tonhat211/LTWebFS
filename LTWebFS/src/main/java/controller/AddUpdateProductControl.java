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

@WebServlet("/addUpdate-product")
public class AddUpdateProductControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case "UPDATE":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    ProductSuperDetail psd = ProductSuperDetailDAO.getInstance().selectById(idin);
                    request.setAttribute("product",psd);
                    request.setAttribute("action","update");
//            request.setAttribute("currentID", idin);
                    session.setAttribute("currentSearch",idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdate.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "ADD":{
                    request.setAttribute("action","add");

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdate.jsp");
                    rd.forward(request, response);
                    break;
                }
//                default:{
//
//                }
            }
        } else {
            String idstr = request.getParameter("pro-id")!= "" ? request.getParameter("pro-id") : "";
            String imeistr = request.getParameter("pro-imei")!= "" ? request.getParameter("pro-imei") : "";
//            int idin = Integer.parseInt(request.getParameter("pro-id"));
//            if update
            if (idstr.equalsIgnoreCase("")){
                idstr = String.valueOf(ProductDAO.getInstance().selectTheMaxID()+1);
            }
            int idin =-1;
            boolean isUpdate = false;
            if(idstr !=  ""){
                isUpdate = true;

            }
            idin = Integer.parseInt(idstr);
            Product ptemp = new Product();
            ptemp.setId(idin);
            Unit utemp = new Unit();
            utemp.setProductID(idin);
            ProductDAO.getInstance().delete(ptemp);
            UnitDAO.getInstance().delete(utemp);


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
            String des =  request.getParameter("pro-des")!= null?  request.getParameter("pro-des") : null; ;

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


            int pIDNew = -1;
            int uIDMax=-1;
            int uIDNew=-1;
            if(isUpdate==true){
                pIDNew = idin;
//                uIDMax=
            } else  {
                pIDNew = ProductDAO.getInstance().selectTheMaxID() + 1;
//                uIDMax = UnitDAO.getInstance().selectTheMaxID();
            }

            Product p = new Product(pIDNew, name, brandID,  areaID,kind, des);
            ProductDAO.getInstance().insert(p);



            if(isUpdate==true){
//                if(type!=null){
//                    String[] typeTokens = type.split("-");
//                    for(int  i=0; i< typeTokens.length;i++){
//                        uIDMax++;
//                        Unit u  =  new Unit(uIDMax, pIDNew, null, typeTokens[i],0, price,amount,yearMade,dateIn,1);
//                        UnitDAO.getInstance().insert(u);
//                    }
//                } else {
//                    uIDMax++;
//                    Unit u  =  new Unit(uIDMax, pIDNew, null, null,0, price,amount,yearMade,dateIn,1);
//                    UnitDAO.getInstance().insert(u);
//                }
                uIDNew = Integer.parseInt(imeistr);
            } else {
                uIDNew = UnitDAO.getInstance().selectTheMaxID() +1;
            }
            Unit u  =  new Unit(uIDMax, pIDNew, null, null,0, price,amount,yearMade,dateIn,1);
            UnitDAO.getInstance().delete(u);
            UnitDAO.getInstance().insert(u);



            int iIDNew = ImageDAO.getInstance().selectTheMaxID();
            String[] imgTokens = imgs.split("==");
            for(int i=0;i<imgTokens.length;i++){
                if(imgTokens[i]=="" || imgTokens[i] == null) continue;
                iIDNew++;
                Image img = new Image(iIDNew,imgTokens[i],pIDNew);
                ImageDAO.getInstance().insert(img);
            }

            session.setAttribute("currentSearch",idin);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
            rd.forward(request, response);

        }




















    }
}