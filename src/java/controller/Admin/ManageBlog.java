/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Admin;

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

/**
 *
 * @author Q.Hieu
 */
public class ManageBlog extends HttpServlet {

    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("role");

        if (isAdmin != null && isAdmin) {
            try (PrintWriter out = response.getWriter()) {
                List<Post> listPosts = postDAO.getAllPost();
                Collections.reverse(listPosts); // Reverse the order if needed
                request.setAttribute("listPost", listPosts);
                request.getRequestDispatcher("AdminBlog.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
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
        String postIDString = request.getParameter("postID");
        String check = request.getParameter("action");

        List<Post> listPosts = postDAO.getAllPost();

        if (postIDString != null && check.equals("check")) {
            try {
                int postID = Integer.parseInt(postIDString);
                Post postCheck = postDAO.getPostByPostID(postID);
                String content = postCheck.getPostText();
                List<SimilarPost> similarPostAuthor = checkPlagiarism(content, listPosts);

                if (!similarPostAuthor.isEmpty()) {
                    request.setAttribute("similarPosts", similarPostAuthor);
                    request.getRequestDispatcher("ErrorCreate.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid postID format: " + postIDString);
            }
        } else {
            response.getWriter().println("postID parameter is missing");
        }

    }

    private List checkPlagiarism(String postText, List<Post> allPosts) {
        double threshold = 0.4; // tương đồng 40%
        List<SimilarPost> similarPostAuthor = new ArrayList<>();

        for (Post existingPost : allPosts) {
            // Tính toán độ tương đồng giữa bài đăng mới và bài đăng có sẵn trong cơ sở dữ liệu
            // https://vi.wikipedia.org/wiki/Kho%E1%BA%A3ng_c%C3%A1ch_Levenshtein       

            double similarity = postDAO.getSimilarity(postText, existingPost.getPostText());

            if (similarity > threshold) {
                similarPostAuthor.add(new SimilarPost(existingPost, similarity));
            }
        }

        return similarPostAuthor;
    }

}
