package controller;

import database.DecartDAO;
import database.LogDAO;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/log-out")
public class LogoutController extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session  =request.getSession();
        String email="";
        String ipAddress = request.getRemoteAddr();
        Log log= new Log();





        User u = (User) session.getAttribute("userloging");
         if(u!=null) {
             email = u.getEmail();
             String currentMenu = ((String) session.getAttribute("currentMenu"));
             log = new Log(ipAddress,email + " | " + currentMenu,"Đã đăng xuất khỏi website." ,email ,"trống",1 );

         } else {
             Employee e = (Employee) session.getAttribute("adminloging");
             email = e.getEmail();
             String currentAdminMenu = (String) session.getAttribute("currentAdminMenu");
             log = new Log(ipAddress,email + " | " + currentAdminMenu,"Đã đăng xuất khỏi hệ thống admin." ,email,"trống",1 );

         }

        session.removeAttribute("userloging");
        session.removeAttribute("adminloging");

//        xu ly ghi log
        LogDAO.getInstance().insert(log);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/menucontrol?menu=product&kind=A");
            rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doGet(req,resp);
    }
}