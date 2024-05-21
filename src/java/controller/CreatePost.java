
package controller;

import dal.PostDAO;
import model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "CreatePost", urlPatterns = {"/CreatePost"})
public class CreatePost extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
          
        }catch(Exception e){
            
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String ContentText = request.getParameter("contentpost");
            PostDAO createPost = new PostDAO();
            boolean post =  createPost.inseartPost(1, ContentText);
            if(post){
                response.sendRedirect("postAll.jsp");
            }else{
                response.sendRedirect("post.jsp");
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDAO createPost = new PostDAO();
        List<Post> posts = createPost.getAllPost();
        if(posts.size() < 0){
            System.out.println("Fail");
            resp.sendRedirect("post.jsp");
        }else{
            System.out.println("Success");
            resp.sendRedirect("postAll.jsp");
        }  
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
