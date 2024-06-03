package controller;

import dal.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import controller.CheckPlagiarismController;
import java.io.PrintWriter;
import model.Post;

/**
 *
 * @author Q.Hieu
 */
public class MyBlog extends HttpServlet {

    CheckPlagiarismController checkPlagiarism = new CheckPlagiarismController();
    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Object userIDObj = session.getAttribute("userID");
        int currentID = 0;

        if (userIDObj != null && userIDObj instanceof Integer) {
            currentID = (Integer) userIDObj;
        } else {
            response.sendRedirect("login.jsp");
            return;
        }

        if (action != null && action.equals("edit")) {
            int postID = Integer.parseInt(request.getParameter("post"));
            int userID = Integer.parseInt(request.getParameter("id"));

            if (userID != currentID) {
                response.sendRedirect("login.jsp");
                return;
            }
            Post postEdit = postDAO.getPostByPostID(postID);

            request.setAttribute("post", postEdit);
            request.getRequestDispatcher("EditPost.jsp").forward(request, response);
        } else if (action != null && action.equals("delete")) {
            int postID = Integer.parseInt(request.getParameter("post"));
            int userID = Integer.parseInt(request.getParameter("id"));
            
            // Display confirmation dialog
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("var confirmation = confirm('Are you sure you want to delete this post?');");
            out.println("if (confirmation) {");
            out.println("window.location.href = 'myblog?action=confirmDelete&post=" + postID + "&id=" + userID + "';");
            out.println("} else {");
            out.println("window.location.href = 'MyBlog.jsp';");
            out.println("}");
            out.println("</script>");
            out.close();
            return;
        } else if (action != null && action.equals("confirmDelete")) {
            int postID = Integer.parseInt(request.getParameter("post"));
            int userID = Integer.parseInt(request.getParameter("id"));

            if (userID != currentID) {
                response.sendRedirect("login.jsp");
                return;
            }

            postDAO.deletePost(userID, postID);

            // Redirect to the blog page after deletion
            response.sendRedirect("myblog");
            return;
        }

        List<Post> listPosts = postDAO.getPost(currentID);
        request.setAttribute("listPost", listPosts);
        request.getRequestDispatcher("MyBlog.jsp").forward(request, response);
    }

    
    
    // Handle when user post updated
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int postID = Integer.parseInt(request.getParameter("postID"));
        String postText = request.getParameter("postText");

        // Validate
        if (postText == null || postText.trim().isEmpty()) {
            request.setAttribute("post", postDAO.getPostByPostID(postID)); // Restore post data for re-display
            request.setAttribute("errorMessage", "Post text cannot be empty.");
            request.getRequestDispatcher("EditPost.jsp").forward(request, response);
            return;
        }
        boolean check = checkPlagiarism.checkPlagiarism(postText, userID, request, response);

        // return true when not plagiarism
        if (check) {
            boolean resultUpdata = postDAO.updatePost(userID, postID, postText);
            if (resultUpdata) {
                response.sendRedirect("myblog");
            } else {
                request.setAttribute("post", postDAO.getPostByPostID(postID)); // Restore post data for re-display
                request.setAttribute("errorMessage", "Failed to update post. Please try again later.");
                request.getRequestDispatcher("EditPost.jsp").forward(request, response);
            }
        }
    }
}
