package model.JavaMail;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class EmailService implements IJavaMail {
    @Override
    public boolean send(String to, String subject, String messageContent) {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.SSL_PORT);

        // get Session
        Session session = javax.mail.Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()  {
                return new javax.mail.PasswordAuthentication(EmailProperty.APP_EMAIL,EmailProperty.APP_PASSWORD);
            }

        });







        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            ((MimeMessage) message).setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageContent);

            // send message
            Transport.send(message);
            return true;
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String to ="21130463@st.hcmuaf.edu.vn";
        String subject="Đăng ký nhận thư thành công";
        String message = "Cảm ơn bạn đã đăng ký nhận thư";
        IJavaMail emailService = new EmailService();
        emailService.send(to,subject,message);
    }
}


