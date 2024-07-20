/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PaymentDAO;
import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import model.Payments;
import model.Users;

/**
 *
 * @author khanh
 */
@WebServlet(name = "payment", urlPatterns = {"/payment"})
public class payment extends HttpServlet {

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
            out.println("<title>Servlet payment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet payment at " + request.getContextPath() + "</h1>");
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
       HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID != null) {
            try {
                // Lấy thông tin người dùng từ UsersDAO
                UsersDAO usersDAO = new UsersDAO();
                Users user = usersDAO.getUsersById(userID);
                
                if (user != null) {
                    // Lấy thông tin mã ngẫu nhiên từ request
                    String randomCode = request.getParameter("randomCode");
                  
                    // Tạo đối tượng Payments từ thông tin thu thập được
                    Payments payment = new Payments();
                    
                    payment.setUserId(user.getUserID());
                    payment.setUsername(user.getUsername());
                    payment.setStatus(user.isPremiumID());
                    payment.setContent(randomCode); 
                    payment.setDate(new Date()); 
                    
                    // Lưu thông tin thanh toán vào cơ sở dữ liệu bằng PaymentsDAO
                    PaymentDAO paymentsDAO = new PaymentDAO();
                    paymentsDAO.signup(payment);
                    
                    Payments payments = paymentsDAO.getUsersById(userID);
                    request.setAttribute("payments", payments);
                    
                    System.out.println(payments);
                    // Chuyển hướng đến trang paymentPending.jsp và gửi các thông tin cần thiết
                    request.setAttribute("randomCode", randomCode);
                   
                    
                    request.setAttribute("users", user);
                    request.getRequestDispatcher("paymentPending.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "User not found");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid user ID format");
            }
        } else {
            request.setAttribute("errorMessage", "User ID is required");
        }
        request.getRequestDispatcher("paymentPending.jsp").forward(request, response);
    
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
