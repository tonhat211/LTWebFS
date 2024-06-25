package controller;

import database.VerifyCodeDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/ForgotPasswordControl")
public class ForgotPasswordControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ForgotPasswordControl() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
        int otpvalue = 0;
        HttpSession mySession = request.getSession();

        if (email != null && !email.equals("")) {
            Random rand = new Random();
            otpvalue = rand.nextInt(1255650);

            String to = email;

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2003tonhat@gmail.com", "kmus zkvm xqhc ekav");

                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Mã xác minh email web thietbiyte63");
                message.setText("Mã xác minh email của  bạn là: " + otpvalue);
                Transport.send(message);
                System.out.println("message sent successfully");
            }

            catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to send email: " + e.getMessage());
            }



            request.setAttribute("message", "Mã OTP đã được gửi tới địa chỉ email của bạn");
            mySession.setAttribute("otp", otpvalue);
            VerifyCodeDAO.getInstance().insertNewCode(String.valueOf(otpvalue), email);
            mySession.setAttribute("email", email);
            dispatcher = request.getRequestDispatcher("EnterOTP.jsp");
            dispatcher.forward(request, response);
        }
    }

}
