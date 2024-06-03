package controller;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import model.SimilarPost;

public class CheckPlagiarismController extends HttpServlet {

    PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckPlagiarismController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckPlagiarismController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    public boolean checkPlagiarism(String contentText,int UserID ,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Post> listPosts = postDAO.getAllPostExceptUserID(UserID);

        List<SimilarPost> similarPostAuthor = checkPlagiarism(contentText, listPosts);

        
        if (!similarPostAuthor.isEmpty()) {
            request.setAttribute("similarPosts", similarPostAuthor);
            request.getRequestDispatcher("ErrorCreate.jsp").forward(request, response);
            return false;
        } 
        
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
