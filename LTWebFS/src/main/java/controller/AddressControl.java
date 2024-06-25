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

@WebServlet("/address-management")
public class AddressControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        User u = (User) session.getAttribute("userloging");

//        int userID = Integer.parseInt(request.getParameter(u.getId()));
        ArrayList<Address> adds = AddressDAO.getInstance().selectByUserID(u.getId());
        String action = request.getParameter("action");
        action = action.toUpperCase();
        switch (action) {
            case "INIT": {
                String html = renderHtml(action,adds);
                response.getWriter().write(html);
                break;
            }
            case "UPDATE": {
                int id = Integer.parseInt(request.getParameter("id"));
                String receiver = request.getParameter("receiver");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                Address a = new Address(id,0,receiver,phone,address);
                AddressDAO.getInstance().update(a);
                ArrayList<Address> newAdds = AddressDAO.getInstance().selectByUserID(u.getId());
                String html = renderHtml("INIT",newAdds);
                response.getWriter().write(html);
                break;
            }
            case "ADD": {
                int userID = u.getId();
                String receiver = request.getParameter("receiver");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                Address a = new Address(0,userID,receiver,phone,address);
                AddressDAO.getInstance().insert(a);
                ArrayList<Address> newAdds = AddressDAO.getInstance().selectByUserID(u.getId());
                String html = renderHtml("INIT",newAdds);
                response.getWriter().write(html);
                break;
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String renderHtml(String action, ArrayList<Address> adds) {
        action = action.toUpperCase();
        String html ="";
        switch (action) {
            case "INIT": {
                html = "   <div class=\"confirm__message\" style=\"margin-bottom: 20px\">\n" +
                        "                        <div class=\"show-flex-row\">\n" +
                        "                            <h3>Sổ địa chỉ</h3>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"show-flex-row\" style=\"justify-content: end\">\n" +
                        "                            <button class=\"btn btn-primary confirm-btn yes-confirm\" type=\"button\" onclick=\"showAddAddress()\">Thêm địa chỉ</button>\n" +
                        "                        </div>\n" +
                        "  </div>\n" +
                        " <div id=\"add-address-container\"></div>\n" +
                        " <div id=\"address-container\">\n";
                for(Address a : adds) {
                    html += "                        <div class=\"address-item\" onclick=\"changeAddress.call(this)\">\n" +
                            "                            <div class=\"show-flex-row\" style=\"justify-content: space-between; align-items: start\">\n" +
                            "                                <div class=\"address-info\">\n" +
                            "                                    <p><span class=\"receiver\">"+ a.getReceiver() +"</span> |   <span class=\"receiver-phone\">"+ a.getPhone()+"4</span></p>\n" +
                            "                                    <p class=\"id\" hidden>"+a.getId()+"</p>\n" +
                            "                                    <p style=\"margin-top: 5px\">Dia chi: <span class=\"address\">"+ a.getAddress()+"</span></p>\n" +
                            "                                </div>\n" +
                            "                                <div>\n" +
                            "                                    <i class=\"fa-solid fa-ellipsis editBtn\" onclick=\"showEditAddress.call(this,event)\"></i>\n" +
//                            "                                    <i class=\"fa-solid fa-ellipsis editBtn\"</i>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"seperate-horizontal-linear\"></div>\n" +
                            "                       </div>\n";


                }
                html +="                  </div>\n";

                break;
            }
        }
        return html;
    }

    public String renderAddressBook(ArrayList<Address> adds) {
        String html ="";
        html = "       <div class=\"modal confirm-stop active\" style=\"z-index: 9\">\n" +
                "            <div class=\"modal__overlay\">\n" +
                "                <div class=\"modal__confirm-content\" onclick=\"event.stopPropagation()\" style=\"top:40px;\">\n" +
                "                    <div class=\"confirm__message\" style=\"margin-bottom: 20px\">\n" +
                "                        <div class=\"show-flex-row\">\n" +
                "                            <h3>Sổ địa chỉ</h3>\n" +
                "                        </div>\n" +
                "                        <div class=\"show-flex-row\" style=\"justify-content: end\">\n" +
                "                            <a class=\"btn btn-primary confirm-btn yes-confirm\" href=\"#\">Thêm địa chỉ</a>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div id=\"address-container\">\n" +
                "                        <div class=\"address-item\">\n" +
                "                            <div class=\"show-flex-row\" style=\"justify-content: space-between; align-items: start\">\n" +
                "                                <div class=\"address-info\">\n" +
                "\n" +
                "                                    <p><span class=\"receiver\">nhat to</span> |   <span class=\"receiver-phone\">12341351534</span></p>\n" +
                "                                    <p class=\"id\" hidden>1</p>\n" +
                "                                    <p style=\"margin-top: 5px\">Dia chi: <span class=\"address\">bao dinh</span></p>\n" +
                "                                </div>\n" +
                "                                <div>\n" +
                "                                    <a href=\"#\" class=\"editBtn\"><i class=\"fa-solid fa-ellipsis\"></i></a>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div class=\"seperate-horizontal-linear\"></div>\n" +
                "                        </div>\n" +
                "                        <div class=\"address-item\">\n" +
                "                            <div class=\"show-flex-row\" style=\"justify-content: space-between; align-items: start\">\n" +
                "                                <div class=\"address-info\">\n" +
                "\n" +
                "                                    <p><span class=\"receiver\">co tot</span> |   <span class=\"receiver-phone\">9216593512</span></p>\n" +
                "                                    <p class=\"id\" hidden>2</p>\n" +
                "                                    <p style=\"margin-top: 5px\">Dia chi: <span class=\"address\">xuan loc</span></p>\n" +
                "                                </div>\n" +
                "                                <div>\n" +
                "                                    <a href=\"#\" class=\"editBtn\"><i class=\"fa-solid fa-ellipsis\"></i></a>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div class=\"seperate-horizontal-linear\"></div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        return html;
    }
}