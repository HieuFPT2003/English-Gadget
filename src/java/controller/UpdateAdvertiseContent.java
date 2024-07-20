/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AdvertiseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
@WebServlet(name="UpdateAdvertiseContent", urlPatterns={"/UpdateAdvertiseContent"})
public class UpdateAdvertiseContent extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateAdvertiseContent</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAdvertiseContent at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        String adID_raw = request.getParameter("adID");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String isActive_raw = request.getParameter("isActive");
        String adminID_raw = request.getParameter("adminID");
        String adminName = request.getParameter("adminName");
        System.out.println(adID_raw + "-" + title+ "-"+description +imageUrl +isActive_raw +adminID_raw +adminName);
        boolean status=true;
        try {
            int adID = Integer.parseInt(adID_raw);
            int adminID = Integer.parseInt(adminID_raw);
            if(isActive_raw.equals("1")){
                    status = true;
                }else{
                    status = false;
                }
            AdvertiseDAO dao = new AdvertiseDAO();
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            boolean a = dao.updateAds(adID, title, description, imageUrl, status, adminID, created_at);
            
            if (a) {
                System.out.println("Test");
                request.setAttribute("logsu", "Update successfully");
                request.getRequestDispatcher("UpdateAdvertise.jsp").forward(request, response);
            } else {
                System.out.println(" 1");
                request.setAttribute("logfa", "Update failed. Please try again later.");
                request.getRequestDispatcher("UpdateAdvertise.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
