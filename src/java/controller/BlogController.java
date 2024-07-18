package controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import model.Post;

public class BlogController extends HttpServlet {

    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID != null && userID > 0) {
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
        Integer currentID = (Integer) session.getAttribute("userID");

        if (currentID == null || !(currentID instanceof Integer)) {
            response.sendRedirect("login.jsp");
            return;
        }

        String contentText = request.getParameter("contentpost");
        String category = request.getParameter("category");

        // Create a new post with status set to 0 (pending approval)
        Post newPost = new Post();
        newPost.setUserID(currentID);
        newPost.setPostText(contentText);
        newPost.setDatePosted(new Date());
        newPost.setCategory(category);
        newPost.setStatus(false); // status false means pending approval

        // Insert the post into the database
        boolean success = postDAO.insertPost(newPost);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (success) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Hãy đợi quản trị viên duyệt bài viết của bạn.');");
                out.println("window.location.href = 'blog';"); // Redirect to your JSP page
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Có lỗi xảy ra. Vui lòng thử lại.');");
                out.println("window.location.href = 'blog';"); // Redirect to your JSP page
                out.println("</script>");
            }
        }
    }
}
