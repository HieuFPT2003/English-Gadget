/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ReportPost;
import org.languagetool.language.SouthAfricanEnglish;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchReport", urlPatterns = {"/searchReport"})
public class SearchReport extends HttpServlet {

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
//        processRequest(request, response);
        String filter = request.getParameter("filter");
        PostDAO postDAO = new PostDAO();
        List<ReportPost> listSearched = postDAO.filterReportPostByType(filter);
        if (listSearched != null && !listSearched.isEmpty()) {
            request.setAttribute("listReport", listSearched);
            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
        } else {
            request.setAttribute("logfa", "Don't have this key");
            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String message = request.getParameter("message");
        PostDAO postDAO = new PostDAO();
        List<ReportPost> listSearched = postDAO.searchReportPostsByMessage(message);
        if (listSearched != null && !listSearched.isEmpty()) {
            request.setAttribute("listReport", listSearched);
            
            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
        } else {
            
            request.setAttribute("logfa", "Don't have this key");
            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
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
