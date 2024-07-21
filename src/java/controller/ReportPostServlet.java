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
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import model.Post;
import model.ReportPost;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ReportPostServlet", urlPatterns = {"/ReportPostServlet"})
public class ReportPostServlet extends HttpServlet {

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
        String type = request.getParameter("sort");
        PostDAO postDAO = new PostDAO();
        List<ReportPost> list = postDAO.sortReportPost(type);
        request.setAttribute("listReport", list);
        request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới nếu không tồn tại

        if (session != null && session.getAttribute("role") != null) {
            boolean isAdmin = (boolean) session.getAttribute("role");

            if (isAdmin) {
                PostDAO postDAO = new PostDAO();
                List<ReportPost> list = postDAO.getAllReportedPost();
                request.setAttribute("listReport", list);
                request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
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
        String postIdStr = request.getParameter("postID");
        String userIdStr = request.getParameter("userID");
        String userReportIdStr = request.getParameter("userReportID");
        String userReportName = request.getParameter("userReportName");
        String reportType = request.getParameter("reportType");
        String message = request.getParameter("message");
        Timestamp created_at = new Timestamp(System.currentTimeMillis());
        int postId = Integer.parseInt(postIdStr);
        int userId = Integer.parseInt(userIdStr);
        int userReportId = Integer.parseInt(userReportIdStr);
        PostDAO dao = new PostDAO();
        boolean a = dao.insertUserReportPost(postId, userId, userReportName, reportType, message, userReportId, created_at);
        if (a) {
            request.setAttribute("logsu", "Report Successfully. Hope your opinion will make the blog more suitable for everyone");
            request.getRequestDispatcher("homeBlogUser.jsp").forward(request, response);
        } else {
            request.setAttribute("logfa", "Report Failed. Please try again later.");
            request.getRequestDispatcher("homeBlogUser.jsp").forward(request, response);
        }

//        PrintWriter out = response.getWriter();
//        out.println(postIdStr);
//        out.println(userIdStr);
//        out.println(userReportIdStr);
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
