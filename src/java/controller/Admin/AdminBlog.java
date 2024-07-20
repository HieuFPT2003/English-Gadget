package controller.Admin;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "AdminBlog", urlPatterns = {"/AdminBlog"})
public class AdminBlog extends HttpServlet {

    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("role");
        if (isAdmin != null && isAdmin) {
            try (PrintWriter out = response.getWriter()) {
                List<Post> listPosts = postDAO.getAllPostsForAdmin();
                Collections.reverse(listPosts);
                request.setAttribute("listPost", listPosts);
                request.getRequestDispatcher("AdminManageBlog.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
                } else {
                    response.sendRedirect("ErrorCreate.jsp");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid postID format: " + postIDString);
            }
        } else if (postIDString != null && check.equals("accept")) {
            int postID = Integer.parseInt(postIDString);
            if (postDAO.updatePostStatus(postID, 1)) {
                processRequest(request, response);
            };
        } else if (postIDString != null && check.equals("hidden")) {
            int postID = Integer.parseInt(postIDString);
            if (postDAO.updatePostStatus(postID, 0)) {
                response.sendRedirect("ManageBlog");
            }
        } else if (postIDString != null && check.equals("delete")) {
            int postID = Integer.parseInt(postIDString);
            if (postDAO.deletePost(postID)) {
                processRequest(request, response);
            };
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
