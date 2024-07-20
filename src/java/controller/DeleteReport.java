/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedbackDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteReport", urlPatterns = {"/delreport"})
public class DeleteReport extends HttpServlet {

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
        String report_raw = request.getParameter("postID");
        try {
            int postID = Integer.parseInt(report_raw);
            PostDAO dao1 = new PostDAO();
            if (dao1.deleteReportPost(postID)) {
                request.setAttribute("logsu", "Delete successfully");
                request.getRequestDispatcher("ReportPostServlet").forward(request, response);
            } else {
                request.setAttribute("logfa", "Fail");
                request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
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

//        PrintWriter out = response.getWriter();
//        String pid_raw = request.getParameter("pid");
//        String uid_raw = request.getParameter("uid");
//        String rid_raw = request.getParameter("rid");
//        int pId = Integer.parseInt(pid_raw);
//        int uId = Integer.parseInt(uid_raw);
//        int rId = Integer.parseInt(rid_raw);
//        out.println(pId);
//        out.println(uId);
//        out.println(rId);
//        PostDAO dao1 = new PostDAO();
//        dao1.deletePost(uId, pId);
//        boolean result = dao1.deleteReportPost(rId) && dao1.deletePost(rId, pId);
//        if (result) {
//            response.sendRedirect("ReportPostServlet");
//
//        } else {
//            request.setAttribute("logfa", "Delete failed");
//            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
//        }
//        if (dao1.deleteReportPost(rpPostId)) {
//            response.sendRedirect("AdminReport.jsp");
//        }else{
//            request.setAttribute("logfa", "Delete failed");
//            request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
//        }
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
