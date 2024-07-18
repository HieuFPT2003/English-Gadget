package controller;

import dal.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;

import java.io.IOException;
import java.util.List;

@WebServlet(name="UserSearchFeedback", urlPatterns={"/usersearchfeedback"})
public class UserSearchFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");

        int limit = 10;
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }
        int offset = (page - 1) * limit;

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbackList = feedbackDAO.searchFeedback(keyword, sortBy, order, offset, limit);
        int totalFeedback = feedbackDAO.countSearchFeedback(keyword);
        int totalPages = (int) Math.ceil((double) totalFeedback / limit);

        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);

        request.getRequestDispatcher("UserSearch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to search user feedback";
    }
}
