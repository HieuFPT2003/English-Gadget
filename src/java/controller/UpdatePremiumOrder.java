/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;
import model.mailsent;

/**
 *
 * @author khanh
 */
@WebServlet(name = "UpdatePremiumOrder", urlPatterns = {"/updateOrder"})
public class UpdatePremiumOrder extends HttpServlet {

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
            out.println("<title>Servlet UpdatePremiumOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePremiumOrder at " + request.getContextPath() + "</h1>");
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
         String userIDStr = request.getParameter("userID");
        if (userIDStr != null) {
            int userID = Integer.parseInt(userIDStr);
            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("updatePremiumOrder.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("list");
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
       try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String newPassword = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int age = Integer.parseInt(request.getParameter("age"));
            boolean premiumID = request.getParameter("premiumID") != null && request.getParameter("premiumID").equals("1");

            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);
            
            if (user != null) {
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setAge(age);
                user.setPremiumID(premiumID);
                if (newPassword != null && !newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                  
                    
                }
                usersDAO.update(user);
                mailsent mail = new mailsent();
                mail.mailsent(email, username);
                
            }
            response.sendRedirect("ListOfPremium");
        } catch (NumberFormatException e) {
            response.sendRedirect("updateOrder?userID=" + request.getParameter("userID"));
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
