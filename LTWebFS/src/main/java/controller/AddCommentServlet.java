package controller;

import database.CommentDAO;
import model.Comment;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    private CommentDAO commentsDao = new CommentDAO();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String content = request.getParameter("comment");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setProductId(productId);
        comment.setUserId(userId);
        comment.setUsername(userName);


        // Chuyển đổi java.util.Date thành java.time.LocalDateTime
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        comment.setDate(currentDateTime);

        commentsDao.addComment(comment);

        String html = renderHtml(comment);
        response.getWriter().write(html);

//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/product?action=detail&&id="+productId);
//        rd.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public String renderHtml(Comment comment) {
        String html="";
        html =
                "<div class=\"comment-item\">\n" +
                        "                  <p><strong>"+comment.getUsername() +"</strong> - "+comment.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))+"</p>\n" +
                        "                  <p>"+comment.getContent() +"</p>\n" +
                        "              </div>";

        return html;
    }
}
