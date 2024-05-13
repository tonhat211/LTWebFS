package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ValidateOtp")
public class ValidOTPControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String otpString = request.getParameter("otp");
        if (otpString == null || otpString.isEmpty()) {
            // Xử lý trường hợp không có giá trị OTP được gửi lên từ form
            response.sendRedirect(request.getContextPath() + "/EnterOTP.jsp");
            return;
        }

        int value;
        try {
            value = Integer.parseInt(otpString);
        } catch (NumberFormatException e) {
            // Xử lý trường hợp giá trị OTP không hợp lệ
            response.sendRedirect(request.getContextPath() + "/EnterOTP.jsp?error=invalid_otp");
            return;
        }

        HttpSession session = request.getSession();
        Integer otp = (Integer) session.getAttribute("otp");

        if (otp == null || value != otp) {
            // Xử lý trường hợp OTP không khớp
            request.setAttribute("message", "Wrong OTP");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/EnterOTP.jsp");
            dispatcher.forward(request, response);
        } else {
            // Xử lý trường hợp OTP khớp
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ResetPassword.jsp");
            dispatcher.forward(request, response);
        }
    }

}
