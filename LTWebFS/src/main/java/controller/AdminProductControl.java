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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/admin-product")
public class AdminProductControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        Employee adminloging = (Employee) session.getAttribute("adminloging");

        String action = request.getParameter("action");
        if(action  !=  null){
            action = action.toUpperCase();
            switch(action){
                case "INIT": {
                    ArrayList<ProductUnit> pus = ProductUnitDAO.getInstance().selectAll();
                    request.setAttribute("productUnitList", pus);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminProduct.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "SEARCH": {
                    String input = request.getParameter("input");
                    ArrayList<ProductUnit> pus = new ArrayList<>();
                    ArrayList<Brand> brandList = new ArrayList<>();
                    ArrayList<String> countryList = new ArrayList<>();
                    
                    pus = ProductUnitDAO.getInstance().selectByKindOrName(input);

                    String html ="";
                    for(int i=0; i<pus.size();i++) {
                        System.out.println(i);
                        html+="  <tr class=\""+ (i%2==0 ?"roww":"") +"\">\n" +
                                "            <th scope=\"row\">"+pus.get(i).getId()+"</th>\n" +
                                "            <td>"+pus.get(i).getName() +"</td>\n" +
                                "            <td>"+pus.get(i).getKind() +"</td>\n" +
                                "            <td>"+pus.get(i).getBrand() +"</td>\n" +
                                "            <td><fmt:formatNumber value=\""+pus.get(i).getPrice() +"\" pattern=\"#,##0.00\"/>\n" +
                                "                VND</td>\n" +
                                "            <td>"+pus.get(i).getAmount() +"</td>\n" +
                                "            <td><a class=\"btn-update-product\" href=\"admin-product?action=prepareUpdate&&id="+pus.get(i).getId()+"\">Cập nhật</a></td>\n" +
                                "        </tr>";
                    }
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }
                case "PREPAREUPDATE": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Brand> brandList = BrandDAO.getInstance().selectAll();
                    ProductUnit pu = ProductUnitDAO.getInstance().selectOneByID(id);
                    ArrayList<Image> imgs = ImageDAO.getInstance().selectByParentID(id);
                    request.setAttribute("productUnit", pu);
                    request.setAttribute("brandList", brandList);
                    request.setAttribute("imgList", imgs);
//                    String status = request.getParameter("status");
//                    if(status!=null) request.setAttribute("status",status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateProduct.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "PREPAREADD": {
                    int id = ProductUnitDAO.getInstance().selectMaxID() +1;
                    int idUnit  = UnitDAO.getInstance().selectTheMaxID() +1;

                    ProductUnit pu = new ProductUnit(id,idUnit);
                    pu.setYearMade(2022);
                    request.setAttribute("productUnit", pu);
                    request.setAttribute("action","add");
                    ArrayList<Brand> brandList = BrandDAO.getInstance().selectAll();

                    pu.setDateImport(java.time.LocalDate.now().toString());
                    request.setAttribute("brandList", brandList);

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/addUpdateProduct.jsp");
                    rd.forward(request, response);
                    break;
                }
                case  "STOP":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,0);
//                    request.setAttribute("id", idin);
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
//                    rd.forward(request, response);
                    System.out.println("stop");
                    String html = "stopped";
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;

                }
                case  "RESALE":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    ProductUnitDAO.getInstance().updateAvailableByProID(idin,1);
//                    request.setAttribute("id", idin);
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
//                    rd.forward(request, response);
                    System.out.println("resale");

                    String html = "resaled";
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);

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

                    ProductUnit pu = ProductUnitDAO.getInstance().selectOneByID(idin);

//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-update-product");
//                    rd.forward(request, response);
//                    break;
                    ArrayList<Brand> brands = BrandDAO.getInstance().selectAll();
                    String message="Cập nhật sản phẩm ID: " + idin + " thành công";
                    ArrayList<Image> newimgs = ImageDAO.getInstance().selectByParentID(idin);
                    String html = renderInfoHtml(message,pu,brands,newimgs);
                    System.out.println("update");
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }
                case "ADD":{
                    System.out.println("add");
                    int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly them
                    String name = request.getParameter("name");
                    String brand = request.getParameter("brand");
                    String[] brandTokens = brand.split("=");
                    String brandName = brandTokens[1];
                    String brandCountry = brandTokens[2];
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

                    String[] dateImportTokens = dateImport.split("-");
                    Datee dateimportDatee = new Datee(Integer.parseInt(dateImportTokens[0]),
                            Integer.parseInt(dateImportTokens[1]),
                            Integer.parseInt(dateImportTokens[2]));

                    Product p = new Product(idin,name,0,1,kind,amount,description);
                    int idUnit = UnitDAO.getInstance().selectTheMaxID() +1;
                    Unit u = new Unit(idUnit,idin,color,size,wattage,price,yearMade,dateimportDatee,1);
                    Brand b =  new Brand(brandName,brandCountry);
                    ArrayList<Image> imgs = new ArrayList<>();
                    if(img==null) imgs = new ArrayList<>();
                    String[] imgTokens = img.split("--");
                    for(int  i=0; i<imgTokens.length;i++) {
                        Image itemp = new Image(imgTokens[i], idin);
                        imgs.add(itemp);
                    }

                    ProductUnitDAO.getInstance().addProductUnit(p,b,u,imgs);

//                    request.setAttribute("id",idin);
//                    request.setAttribute("status","addsuccessful");
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin-product?action=prepareUpdate");
//                    rd.forward(request, response);
//
//                    break;
                    ProductUnit pu = ProductUnitDAO.getInstance().selectOneByID(idin);
                    ArrayList<Brand> brands = BrandDAO.getInstance().selectAll();
                    String message="Thêm sản phẩm mới thành công. ID sản phẩm mới:  " + idin;
                    ArrayList<Image> newimgs = ImageDAO.getInstance().selectByParentID(idin);
                    String html = renderInfoHtml(message,pu,brands,newimgs);
                    System.out.println("add");
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(html);
                    break;
                }
                case  "IMPORT":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    int currentAmount = ProductUnitDAO.getInstance().selectAmount(idin);
                    if(currentAmount==-1) {
                        String html = renderTableHTML(null,amount,"Nhập");
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        int newAmount = amount + currentAmount;

                        ProductUnitDAO.getInstance().updateAmount(idin,newAmount);
                        ProductUnitDAO.getInstance().updateTime(idin,new Date(System.currentTimeMillis()));
                        ProductUnit pu = ProductUnitDAO.getInstance().selectById(idin);

                        //                ghi log
                        String ipAddress = request.getRemoteAddr();
                        Log t = new Log(ipAddress,adminloging.getEmail() + " | importProduct | product ","Nhập kho sản phẩm ID: " + idin ,String.valueOf(currentAmount),String.valueOf(pu.getAmount()),2 );
                        LogDAO.getInstance().insert(t);

                        String html = renderTableHTML(pu,amount,"Nhập");
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                    break;
                }
                case  "EXPORT":{
                    int idin = Integer.parseInt(request.getParameter("id"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    int currentAmount = ProductUnitDAO.getInstance().selectAmount(idin);
                    String reason = request.getParameter("reason");
                    if(currentAmount==-1) {
                        String html = renderTableHTML(null,amount,"Xuất");
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    } else {
                        int newAmount = currentAmount - amount;
                        if(newAmount<0) newAmount=0;

                        ProductUnitDAO.getInstance().updateAmount(idin,newAmount);
                        ProductUnitDAO.getInstance().updateTime(idin,new Date(System.currentTimeMillis()));
                        ProductUnit pu = ProductUnitDAO.getInstance().selectById(idin);

                        //                ghi log
                        String ipAddress = request.getRemoteAddr();
                        Log t = new Log(ipAddress,adminloging.getEmail() + " | exportProduct | product ","Xuất kho sản phẩm ID: " + idin ,String.valueOf(currentAmount),String.valueOf(pu.getAmount()) + " | Nguyên nhân: "+ reason,2 );
                        LogDAO.getInstance().insert(t);

                        String html = renderTableHTML(pu,amount,"Xuất");
                        response.setContentType("text/html");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(html);
                    }
                    break;
                }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String renderTableHTML(ProductUnit p, int amount, String action) {
        String amoutt = String.valueOf(amount);

        String html ="";
        if(p==null) {
            html +="<script> showErrorToast2(\"" + action + " thất bại. Sản phẩm không tồn tại.\"); </script>";

        } else {
            html ="            <tr>\n" +
                    "                <th scope=\"row\">"+p.getId() +"</th>\n" +
                    "                <td>"+ p.getName() +" </td>\n" +
                    "                <td>"+amoutt +"</td>\n" +
                    "                <td>"+ p.getDateImport()+"</td>\n" +
                    "            </tr>";
            html +="<script> showSuccessToast2(\"" + action + " " +  p.getId() + " thành công.\"); </script>";

        }

        return html;
    }

    public String renderInfoHtml(String message,ProductUnit pu, ArrayList<Brand> brandList, ArrayList<Image> imgs) {
        String html="";
        String imgNames = "";
        String imgEs ="";
        String[] imgsTokens = pu.getImg().split("--");
        for(int i=0; i<imgsTokens.length;i++){
            imgNames += "<div class=\"grid-col-2 mtb-5px\">\n" +
                    "      <img src=\"./assets/img/products/"+imgsTokens[i]+"\" alt=\"\" class=\"pro-img-item\">\n" +
                    "</div>";
        }

//        for(Image i : imgs) {
//            imgNames
//        }

        String brands ="";
         for(Brand b : brandList) {
             brands += "<option "+ (b.getId()==pu.getBrandID() ? "selected" : "") +" value=\""+b.getId()+"="+b.getName()+"\">"+ b.getName()+" - " + b.getCountry() +"</option>";

        }

         imgEs="<div class=\"form-group\" id=\"img-container\">\n" +
                 "                            <label class=\"w-20\" for=\"myfile\">Hình ảnh: </label>\n" +
                 "                            <input type=\"file\" id=\"myfile\" name=\"myfile\" accept=\".jpg, .png\" onchange=\"preview()\" multiple >\n" +
                 "                            <div id=\"imgs-container\">";

        for(int i=0;i<imgs.size();i++) {
                                imgEs+="<div class=\"chosen-img\">\n" +
                                        "                                        <i class=\"fa-solid fa-circle-xmark delete-img-btn\" onclick=\"deleteImg(this)\"></i>\n" +
                                        "                                        <img src=\"./assets/img/products/"+ imgs.get(i).getUrl()+"\" alt=\"\" style=\"width: 100px\" >\n" +
                                        "                                        <input class=\"img-name\" name=\"img-name\" value=\""+ imgs.get(i).getUrl()+"\" readonly>\n" +
                                        "                                    </div>";


        }
        imgEs+="  </div>\n" +
                "                        </div>";


        html =  "<script> showSuccessToast(\"" + message + "\"); </script> " +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <h4>"+ (pu.getName()==""?"Thêm sản phẩm":"Cập nhật sản phẩm") +"</h4>\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"grid__row img-showing\">\n" +
//                                       imgs +
                "                            <div class=\"disabled-showing "+ (pu.getAvailable()==0?"active":"") +"\" style=\"right: 0; left: 100px; position: absolute\">\n" +
                "                                <div class=\"disabled-showing-content\">\n" +
                "                                    NGƯNG BÁN\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"stop_reSale "+ (pu.getName()==""?"hide":"") +"\" >\n" +
                "                            <div class=\"btn btn-stop-pro "+ (pu.getAvailable()==1?"active":"") +"\" onclick=\"showModalStop()\">Dừng bán</div>\n" +
                "                            <div class=\"btn btn-resale-pro "+ (pu.getAvailable()==0?"active":"") +"\" onclick=\"showModalResale()\">Bán lại</div>\n" +
                "\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label class=\"w-20\" for=\"id\">ID</label>\n" +
                "                            <input type=\"text\" size=\"10\" class=\"form-control w-80\" id=\"id\" name=\"id\" aria-describedby=\"emailHelp\" placeholder=\"ID\" value=\""+pu.getId()+"\" readonly>\n" +
                "                            <!--                       <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your email with anyone else.</small>-->\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label class=\"w-20\" for=\"name\">Tên: </label>\n" +
                "                            <input type=\"text\" class=\"form-control w-80\" id=\"name\" name=\"name\" aria-describedby=\"\" placeholder=\"Enter name\" value=\""+pu.getName() +"\">\n" +
                "                            <!--                        <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your email with anyone else.</small>-->\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-80\">\n" +
                "                        <label class=\"w-20\" for=\"brand\">Thương hiệu: </label>\n" +
                "                        <select class=\"form-select\" aria-label=\"Default select example\" id=\"brand\" name=\"brand\">\n" +
                                            brands +
                "\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label class=\"w-20\" for=\"kind\">Lĩnh vực: </label>\n" +
                "                            <input type=\"text\" class=\"form-control w-80\" id=\"kind\" name=\"kind\" paria-describedby=\"\" placeholder=\"Enter kind\" value=\""+pu.getKind() +"\">\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label class=\"w-20\" for=\"price\">Đơn giá: </label>\n" +
                "                            <input type=\"text\" class=\"form-control w-80\" id=\"price\" name=\"price\" paria-describedby=\"\" placeholder=\"Enter kind\" value=\""+pu.getPrice() +"\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <label  for=\"color\">Màu sắc: </label>\n" +
                "                            <input type=\"text\" size=\"10\" class=\"form-control\" id=\"color\" name=\"color\" aria-describedby=\"\" placeholder=\"Enter color\" value=\""+pu.getColor() +"\">\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <label for=\"size\">Kích cỡ: </label>\n" +
                "                            <input type=\"text\" class=\"form-control\" id=\"size\" name=\"size\" aria-describedby=\"\" placeholder=\"Enter size\" value=\""+pu.getSize() +"\">\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group\">\n" +
                "                            <label for=\"wattage\">Công suất (W): </label>\n" +
                "                            <input type=\"text\" class=\"form-control\" id=\"wattage\" name=\"wattage\" aria-describedby=\"\" placeholder=\"Enter wattage\" value=\""+pu.getWattage() +"\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group w-50\" style=\"display: none;\">\n" +
                "                        <label  class=\"w-20\" for=\"amount\">Tồn kho: </label>\n" +
                "                        <input type=\"number\" class=\"form-control w-80\" id=\"amount\" name=\"amount\" aria-describedby=\"\" placeholder=\"Enter amount\" value=\""+pu.getAmount() +"\">\n" +
                "                    </div>\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label  class=\"w-20\" for=\"yearmade\">Năm sản xuất: </label>\n" +
                "                            <input type=\"number\" size=\"10\" class=\"form-control w-80\" id=\"yearmade\" name=\"yearmade\"  aria-describedby=\"\" placeholder=\"Enter year made\" value=\""+pu.getYearMade() +"\">\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group w-50\">\n" +
                "                            <label  class=\"w-20\"  for=\"dateimport\">Ngày nhập: </label>\n" +
                "                            <input type=\"date\" class=\"form-control w-80\" id=\"dateimport\" name=\"dateimport\"  aria-describedby=\"\" placeholder=\"Enter date import\" value=\""+pu.getDateImport() +"\">\n" +
                "                        </div>\n" +
                "\n" +
                "                    </div>\n" +
//                "                    <div class=\"form-group\">\n" +
//                "                        <label class=\"w-20\" for=\"img\">Hình ảnh: </label>\n" +
//                "                        <input type=\"text\" class=\"form-control\" id=\"img\" name=\"img\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\""+pu.getImg() +"\">\n" +
//                "                    </div>\n" +
                imgEs +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"w-20\" for=\"description\" class=\"input-title\">Mô tả</label>\n" +
                "                        <textarea  type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" rows=\"6\" >"+pu.getDes() +"\n" +
                "                            </textarea>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\" style=\"display: none\">\n" +
                "                        <label class=\"w-20\" for=\"action\" class=\"input-title\">action</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" id=\"action\" name=\"action\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\""+ (pu.getName().equals("")?"add":"update") +"\">\n" +
                "\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\" style=\"display: none\">\n" +
                "                        <label class=\"w-20\" for=\"status\" class=\"input-title\">status</label>\n" +
                "                        <input type=\"text\" class=\"form-control\" id=\"status\" name=\"status\" aria-describedby=\"\" placeholder=\"Enter img url\" value=\"1\">\n" +
                "\n" +
                "                    </div>\n" +
                "\n" +
                "                    <div class=\"show-flex-row\">\n" +
                "                        <div class=\"ad_func-container\">\n" +
                "                            <div><a class=\"btn btn-third\" href=\"admin-menu-controller?adminMenu=product\">Hủy</a></div>\n" +
                "                        </div>\n" +
                "                        <div class=\"ad_func-container\">\n" +
                "                            <button class=\"btn btn-primary\" type=\"submit\">"+ (pu.getName().equals("")?"Thêm":"Lưu") +"</button>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!--                    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>-->\n";
        return html;
    }
}