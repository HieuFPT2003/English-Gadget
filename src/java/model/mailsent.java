package model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailsent {
    static final String from = "englishgadget.contact@gmail.com";
    static final String password = "mbgd zatg kpho sawf";

    public boolean mailsent(String to, String name) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Upgrade to Premium for $20 a year", "UTF-8");
            
            String content = "<div style='font-family: Arial, sans-serif; line-height: 1.6;'>" +
                "<h1 style='color: #4CAF50;'>Xin chào " + name + "</h1>" +
                "<p style='font-size: 16px;'>Tài khoản của bạn đã được nâng cấp lên Premium.</p>" +
                "<p style='font-size: 16px;'>Chúng tôi hy vọng rằng bạn sẽ có trải nghiệm dịch vụ tốt nhất từ chúng tôi.</p>" +
                "<p style='font-size: 16px;'>Xin chân thành cảm ơn sự hỗ trợ của quý khách.</p>" +
                "<hr style='border: 0; border-top: 1px solid #eee;'/>" +
                "<p style='font-size: 14px; color: #999;'>English Gadget Team</p>" +
                "</div>";
            
            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("Send successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Send error");
            System.out.println(e);
            return false;
        }
    }
}
