package controller;

import dal.PostDAO;
import dal.UsersDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import model.Users;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet_1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("txt");
        String searchBy = request.getParameter("searchBy");
        PostDAO postDAO = new PostDAO();
        List<Post> posts = null;

        if (searchBy.equalsIgnoreCase("Username")) {
            posts = postDAO.searchAllPostByUsername(keyword);
        } else if (searchBy.equalsIgnoreCase("PostContent")) {
            posts = postDAO.searchPostByText(keyword);
        } else {
            posts = postDAO.searchByBoth(keyword);
        }

        if (posts == null || posts.isEmpty()) {
            request.setAttribute("ms", "Please try again with a different filter or keyword");
            request.getRequestDispatcher("Search.jsp").forward(request, response);
        } else {
            request.setAttribute("listPost", posts);
            request.getRequestDispatcher("MyBlog.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
