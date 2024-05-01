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
                case  "STOP":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,0);
                    request.setAttribute("id", idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);
                    break;

                }
                case  "RESALE":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,1);
                    request.setAttribute("id", idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);

                    break;

                }
                case "UPDATE":{
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
                    String name = request.getParameter("name");
//                    String country = request.getParameter("country");
                    String brand = request.getParameter("brand");
                    String[] brandTokens = brand.split("=");
                    int brandID = Integer.parseInt(brandTokens[0]);

                    String kind = request.getParameter("kind");
                    String color = request.getParameter("color");
                    String size = request.getParameter("size");
                    float wattage = Float.parseFloat(request.getParameter("wattage"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    int yearMade = Integer.parseInt(request.getParameter("yearmade"));
                    String dateImport = request.getParameter("dateimport");
                    String img = request.getParameter("img");
                    String description = request.getParameter("description");
//                    System.out.println(dateImport);

                    String[] dateImportTokens = dateImport.split("-");
                    Datee dateimportDatee = new Datee(Integer.parseInt(dateImportTokens[0]),
                            Integer.parseInt(dateImportTokens[1]),
                                    Integer.parseInt(dateImportTokens[2]));
//                    '16-2022-4'
//                    Datee dateimportDatee = new Datee(01,01,2022);
                    Product p = new Product(idin,name,0,0,kind,amount,description);
                    Unit u = new Unit(0,idin,color,size,wattage,price,yearMade,dateimportDatee,1);
                    Brand b =  new Brand(brandID);

                    ArrayList<Image> imgs = new ArrayList<>();
                    String[] imgTokens = img.split("--");
                    for(int  i=0; i<imgTokens.length;i++) {
                        Image itemp = new Image(imgTokens[i], idin);
                        imgs.add(itemp);
                    }

                    ProductUnitDAO.getInstance().updateProductUnit(p,b,u,imgs);


                    request.setAttribute("id",idin);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
                    rd.forward(request, response);
                    break;
                }
                case "ADD":{
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
                    String name = request.getParameter("name");
                    String country = request.getParameter("country");
                    String brand = request.getParameter("brand");
                    String kind = request.getParameter("kind");
                    String color = request.getParameter("color");
                    String size = request.getParameter("size");
                    float wattage = Float.parseFloat(request.getParameter("wattage"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    int yearMade = Integer.parseInt(request.getParameter("yearmade"));
                    String dateImport = request.getParameter("dateimport");
                    String img = request.getParameter("img");
                    String description = request.getParameter("description");
//                    System.out.println(dateImport);

                    String[] dateImportTokens = dateImport.split("-");
                    Datee dateimportDatee = new Datee(Integer.parseInt(dateImportTokens[0]),
                            Integer.parseInt(dateImportTokens[1]),
                            Integer.parseInt(dateImportTokens[2]));
//                    '16-2022-4'
//                    Datee dateimportDatee = new Datee(01,01,2022);
                    Product p = new Product(idin,name,0,1,kind,amount,description);
                    int idUnit = UnitDAO.getInstance().selectTheMaxID() +1;
                    Unit u = new Unit(idUnit,idin,color,size,wattage,price,yearMade,dateimportDatee,1);
                    Brand b =  new Brand(brand, country);
                    Image i = new Image(img,idin);



                    ProductUnitDAO.getInstance().addProductUnit(p,b,u,i);
                    int status= Integer.parseInt(request.getParameter("status"));

                    request.setAttribute("id",idin);
                    request.setAttribute("status",status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-add-product");
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