package controller;

import dal.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import java.io.PrintWriter;
import model.Post;

/**
 *
 * @author Q.Hieu
 */
public class MyBlog extends HttpServlet {

    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Integer currentID = (Integer) session.getAttribute("userID");
        System.out.println(currentID);
        if (currentID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("edit".equals(action)) {
            handleEditRequest(request, response, currentID);
        } else if ("delete".equals(action)) {
            handleDeleteRequest(request, response, currentID);
        } else if ("confirmDelete".equals(action)) {
            handleConfirmDeleteRequest(request, response, currentID);
        } else {
            displayUserPosts(request, response, currentID);
        }
    }

    private void handleEditRequest(HttpServletRequest request, HttpServletResponse response, int currentID)
            throws ServletException, IOException {
        int postID = Integer.parseInt(request.getParameter("post"));
        int userID = Integer.parseInt(request.getParameter("id"));

        if (userID != currentID) {
            response.sendRedirect("login.jsp");
            return;
        }
        Post postEdit = postDAO.getPostByPostID(postID);

        request.setAttribute("post", postEdit);
        request.getRequestDispatcher("EditPost.jsp").forward(request, response);
    }

    private void handleDeleteRequest(HttpServletRequest request, HttpServletResponse response, int currentID)
            throws IOException {
        int postID = Integer.parseInt(request.getParameter("post"));
        int userID = Integer.parseInt(request.getParameter("id"));

        // Display confirmation dialog
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<script type=\"text/javascript\">");
            out.println("var confirmation = confirm('Are you sure you want to delete this post?');");
            out.println("if (confirmation) {");
            out.println("window.location.href = 'myblog?action=confirmDelete&post=" + postID + "&id=" + userID + "';");
            out.println("} else {");
            out.println("window.location.href = 'MyBlog.jsp';");
            out.println("}");
            out.println("</script>");
        }
    }
    private void handleConfirmDeleteRequest(HttpServletRequest request, HttpServletResponse response, int currentID)
            throws IOException {
        int postID = Integer.parseInt(request.getParameter("post"));
        int userID = Integer.parseInt(request.getParameter("id"));

        if (userID != currentID) {
            response.sendRedirect("login.jsp");
            return;
        }

        postDAO.deletePost(postID);

        // Redirect to the blog page after deletion
        response.sendRedirect("myblog");
    }

    private void displayUserPosts(HttpServletRequest request, HttpServletResponse response, int currentID)
            throws ServletException, IOException {
        List<Post> listPosts = postDAO.getPostByUserID(currentID);
        request.setAttribute("listPost", listPosts);
        request.getRequestDispatcher("MyBlog.jsp").forward(request, response);
    }

    // Handle when user post updated
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIdParam = request.getParameter("post");
        String postText = request.getParameter("postText");
        String category = request.getParameter("category");
        
        // Validate parameters
        if (postIdParam == null || postIdParam.trim().isEmpty()) {
        }
        int postID;
        
        try {
            postID = Integer.parseInt(postIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid user ID or post ID format.");
            request.getRequestDispatcher("/myblog").forward(request, response);
            return;
        }

        // Validate post text
        if (postText == null || postText.trim().isEmpty()) {
            request.setAttribute("post", postDAO.getPostByPostID(postID)); // Restore post data for re-display
            request.setAttribute("errorMessage", "Post text cannot be empty.");
            request.getRequestDispatcher("/myblog").forward(request, response);
            return;
        }
        boolean updateSuccess = postDAO.updatePost(postID, category, postText);

        if (updateSuccess) {
            response.sendRedirect("myblog"); // Redirect to blog page after successful update
        } else {
            request.setAttribute("post", postDAO.getPostByPostID(postID)); // Restore post data for re-display
            request.setAttribute("errorMessage", "Failed to update the post. Please try again.");
            request.getRequestDispatcher("/myblog").forward(request, response);
        }
    }
}
