/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AdvertiseDAO;
import dal.ContactDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.Advertise;
import model.Contact;

/**
 *
 * @author Admin
 */
@WebServlet(name="UpdateContact", urlPatterns={"/UpdateContact"})
public class UpdateContact extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String ctID_raw = request.getParameter("ctID");
//        try {
//            int ctID = Integer.parseInt(ctID_raw);
//            ContactDAO contactDAO = new ContactDAO();
//            Contact contact = contactDAO.getContactByID(ctID);
//            request.setAttribute("contact", contact);
//            request.getRequestDispatcher("UpdateContact.jsp").forward(request, response);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String ctID_raw = request.getParameter("ctID");
        Timestamp create_at = new Timestamp(System.currentTimeMillis());
        boolean status = false;
        ContactDAO ctDAO = new ContactDAO();
        
        try {
            
            int ctID = Integer.parseInt(ctID_raw);
            boolean a = ctDAO.updateContact(ctID, true, create_at);
            response.sendRedirect("ContactServlet");
        } catch (Exception e) {
            
        }

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
