/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ContactDAO;
import dal.LoginDao;
import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EmailContactServlet", urlPatterns = {"/EmailContactServlet"})
public class EmailContactServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        LoginDao dao = new LoginDao();
//        HttpSession session = request.getSession();
//        session.setAttribute("acc", );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //        processRequest(request, response);
        ContactDAO ctDAO = new ContactDAO();
        Timestamp create_at = new Timestamp(System.currentTimeMillis());
        boolean status = false;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        boolean a = ctDAO.insertContact(name, email, subject, message, status, create_at);
        
        if (sendEmail(email, name, subject, message)) {
            request.setAttribute("logsu","Submit successfully. Thank you for providing your support information.");
            request.getRequestDispatcher("Contact.jsp").forward(request, response);
        } else {
            request.setAttribute("logfa","Submit failed. Please check the information again");
            request.getRequestDispatcher("Contact.jsp").forward(request, response);
        }

    }

    public boolean sendEmail(String to, String name, String subject, String content) {
        final String from = "englishgadget.contact@gmail.com";
        final String password = "mbgd zatg kpho sawf";

        // Khai báo thuộc tính PROPERTIES
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Tạo Session
        Session session = Session.getInstance(props, auth);

        // Gửi email
        // Tạo tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            // Người gửi
            msg.setFrom(from);
            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // Tiêu đề Email
            msg.setSubject(subject);
            // Quy định ngày gửi
            msg.setSentDate(new Date());
            // Quy định email nhận phản hồi
            msg.setReplyTo(InternetAddress.parse(from, false));
            // Nội dung
            String htmlContent = "<!DOCTYPE html>\r\n"
                    + "<html>\r\n"
                    + "<body>\r\n"
                    + "<h2> Dear " + name + "," + "</h2>\r\n"
                    + "<p>We have received your feedback regarding the issue with "
                    + "[" + subject + "]" + ". Your detailed explanation [" + content + "] "
                    + "has been very helpful. Our development team is currently reviewing your feedback and "
                    + "will respond to you at the earliest possible time. We appreciate your patience and"
                    + " understanding as we work to resolve this matter.</p>\r\n"
                    + "<h5>Best regards!</h5>\r\n"
                    + "\r\n"
                    + "<h5>English Gadget Development Team</h5>\r\n"
                    + "<h5>Contact: englishgadget.contact@gmail.com</h5>"
                    + "</body>\r\n"
                    + "</html>";
            msg.setContent(htmlContent, "text/html; charset=UTF-8");

            // Gửi mail
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;

        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

}
