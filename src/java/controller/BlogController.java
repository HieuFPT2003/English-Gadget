/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Post;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Post;

/**
 *
 * @author Q.Hieu
 */
public class BlogController extends HttpServlet {

    PostDAO postDAO = new PostDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
           List<Post> listPosts = postDAO.getAllPost();
            req.setAttribute("listPost", listPosts);
            req.getRequestDispatcher("homeBlogUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String contentText = request.getParameter("contentpost");
    
        // Validate contentText
        if (contentText == null || contentText.trim().isEmpty()) {
            // Show error message using JavaScript alert
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Content cannot be empty.');");
            out.println("window.location.href='Test.jsp';");
            out.println("</script>");
            return; // Stop further execution
        }

        boolean createPost = postDAO.insertUserPost(1, contentText);
        if (createPost) {
            response.sendRedirect("BlogController"); 
        } else {
            // Show error message using JavaScript alert
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Failed to create post. Please try again later.');");
            out.println("window.location.href='BlogController';");
            out.println("</script>");
        }   
        
        
    }

}