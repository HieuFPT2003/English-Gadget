/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdvertiseDAO;
import dal.ContactDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Advertise;
import model.Contact;
import model.ReportPost;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/ContactServlet"})
public class ContactServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới nếu không tồn tại

        if (session != null && session.getAttribute("role") != null) {
            boolean isAdmin = (boolean) session.getAttribute("role");

            if (isAdmin) {
                ContactDAO dao = new ContactDAO();
                List<Contact> contact = dao.getAllContact();
                request.setAttribute("contactList", contact);
                request.getRequestDispatcher("AdminContact.jsp").forward(request, response);

            } else {
                // Nếu không phải admin, chuyển hướng về trang login
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            // Nếu không có session hoặc không có role trong session, chuyển hướng về trang login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
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
        String filter = request.getParameter("filter");
        ContactDAO contact = new ContactDAO();
        List<Contact> list = contact.filterContactByType(filter);
        request.setAttribute("contactList", list);
        request.getRequestDispatcher("AdminContact.jsp").forward(request, response);

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
