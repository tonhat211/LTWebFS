package controller;

import database.UserDAO;
import model.Datee;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update-customer-info")
public class UpdateCustomerInfoControl extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();

        int idin = Integer.parseInt(request.getParameter("id"));
//                  xu ly cap nhat
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String info = sex + "=" + birthday;
        String address = request.getParameter("address");
        String datein = request.getParameter("datein");
        String[] dateInTokens = datein.split("-");
        Datee dateinDatee = new Datee(Integer.parseInt(dateInTokens[0]),
                Integer.parseInt(dateInTokens[1]),
                Integer.parseInt(dateInTokens[2]));
//                    '16-2022-4'
//                    Datee dateimportDatee = new Datee(01,01,2022);


        User u = new User(idin,name,email,phone,address,info,dateinDatee);


        UserDAO.getInstance().updateCustomer(u);
        session.setAttribute("userloging",u);


        request.setAttribute("status", "updateInfoSuccessful");
        request.setAttribute("id",idin);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/goto-update-customer-info");
        rd.forward(request, response);
    }
}