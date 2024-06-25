package controller;

import database.LogDAO;
import model.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/goto-logManagement")
public class gotoLogManagement extends HttpServlet {

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        int index = Integer.parseInt(request.getParameter("index"));
        int limit = 10;
        index = (index -1)*limit;

        ArrayList<Log> logs = LogDAO.getInstance().selectSome(index,limit);
        int pages = LogDAO.getInstance().selectCountTotal();
        int numofPage = pages / limit;
        if(pages % limit != 0) {
            numofPage +=1;
        }
        index = index/limit +1;
        request.setAttribute("index",index);
        request.setAttribute("logs",logs);
        request.setAttribute("limit",limit);
        request.setAttribute("numofPage",numofPage);


        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminLog.jsp");
        rd.forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }
}
