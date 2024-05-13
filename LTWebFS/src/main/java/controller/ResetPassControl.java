package controller;

import database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/newPassword")
public class ResetPassControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy thông tin từ form đổi mật khẩu
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confPassword");

        // Lấy email từ session
        String userEmail = (String) session.getAttribute("email");

        // Kiểm tra nếu mật khẩu mới và xác nhận mật khẩu không khớp
        if (!newPassword.equals(confirmPassword))
            {
            request.setAttribute("message", "Mật khẩu và xác nhận mật khẩu không khớp.");
            request.getRequestDispatcher("/ResetPassword.jsp").forward(request, response);
            return; // Dừng luồng xử lý, không tiếp tục thực hiện đổi mật khẩu
        }

        // Thực hiện đổi mật khẩu
        UserDAO usersDAO = UserDAO.getInstance();
        boolean passwordChanged = usersDAO.changePassword(newPassword, userEmail);

        // Xử lý kết quả và chuyển hướng
        if (passwordChanged) {
            // Nếu đổi mật khẩu thành công
            request.setAttribute("status", "resetSuccess");
            request.getRequestDispatcher("/logup.jsp").forward(request, response);
        } else {
            // Nếu đổi mật khẩu không thành công
            request.setAttribute("status", "resetFailed");
            request.getRequestDispatcher("/ResetPassword.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
