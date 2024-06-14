/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Post;
import model.SimilarPost;
import controller.CheckPlagiarismController;

/**
 *
 * @author Q.Hieu
 */
public class BlogController extends HttpServlet {

    CheckPlagiarismController checkPlagiarism = new CheckPlagiarismController();
    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        Integer userID = authenticateUser(req);

        if (userID != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userID", userID);

            try (PrintWriter out = resp.getWriter()) {
                List<Post> listPosts = postDAO.getAllPost();
                Collections.reverse(listPosts);
                req.setAttribute("listPost", listPosts);
                req.getRequestDispatcher("homeBlogUser.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("login.jsp");
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
        HttpSession session = request.getSession();
        Object userIDObj = session.getAttribute("userID");
        int currentID = 0;

        if (userIDObj != null && userIDObj instanceof Integer) {
            currentID = (Integer) userIDObj;
        } else {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String contentText = request.getParameter("contentpost");
        boolean check = checkPlagiarism.checkPlagiarism(contentText, currentID, request, response);

        
        // return true when not plagiarism
        if (check) {
            boolean createPost = postDAO.insertUserPost(currentID, contentText);
            if (createPost) {
                response.sendRedirect("blog");
            } else {
                // Show error message using JavaScript alert
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed to create post. Please try again later.');");
                    out.println("window.location.href='BlogController';");
                    out.println("</script>");
                }
            }
        }
    }

    // Gia dinh session tra ve userID = 1
    private Integer authenticateUser(HttpServletRequest req) {

        return 1;
    }

}
