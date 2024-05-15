package controller;

import database.LogDAO;
import database.UserDAO;
import database.VerifyCodeDAO;
import model.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ValidateOtp")
public class validOTPControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String otpInput = request.getParameter("otp");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        boolean isVerified = VerifyCodeDAO.getInstance().isVerifyOk(otpInput, email);

        if (isVerified == true) {
            int status = UserDAO.getInstance().checkAvailable(email);
            UserDAO.getInstance().availableUser(email);

            request.setAttribute("status", "verifySuccessful");
            request.setAttribute("email", email);

            Log log = new Log(request.getRemoteAddr(), email + " | verify_code ",
                    "Xác minh thành công.", email + " | " + status, email + " | 1", 1);
            LogDAO.getInstance().insert(log);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ResetPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "Mã OTP không chính xác hoặc đã hết hạn. Vui lòng nhập lại.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/EnterOTP.jsp");
            dispatcher.forward(request, response);
        }
    }
}
