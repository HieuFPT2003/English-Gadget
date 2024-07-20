/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ForgetPassDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import javax.mail.SendFailedException;
import model.Sendmail;
import model.Users;

/**
 *
 * @author khanh
 */
@WebServlet(name = "requestPass", urlPatterns = {"/requestPass"})
public class requestPass extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet requestPass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet requestPass at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String email = request.getParameter("email");
        int otp =0;
        //check email co ton tai trong database khong
        ForgetPassDao dao = new ForgetPassDao();
        Users check = dao.CheckMail(email);
        //neu khong in ra log message
        if (check == null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            request.setAttribute("message", " Email addresses do not match!");
            request.getRequestDispatcher("forgetpass.jsp").forward(request, response);
        } else {
            //tao random otp
            Random rand = new Random();
           otp =rand.nextInt(125931);
           String newOtp =String.valueOf(otp);
            //neu co thi send mail 
            Sendmail mail = new Sendmail();
            boolean sendmail = mail.sendEmail(email, newOtp, check.getUsername());
            //neu khong the send mail thi in ra log message va nguoc lai
            if (sendmail == false) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                request.setAttribute("mess", "Could not send Email");
                request.getRequestDispatcher("forgetpass.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);
                request.setAttribute("mess", "send email successfully");
                request.getRequestDispatcher("EnterOtp.jsp").forward(request, response);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
