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

@WebServlet(name = "SearchFeedback", urlPatterns = {"/searchfeedback"})
public class SearchFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();

        int limit = 10; 
        int page = 1;   
        
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }
        
        int offset = (page - 1) * limit;

        try {
            String keyword = request.getParameter("keyword");
            String sortBy = request.getParameter("sortBy");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            String rating = request.getParameter("rating");

            List<Feedback> feedbackList;
            int totalFeedback;
            int totalPages;

            // Fetch feedback entries with pagination and filters
            feedbackList = feedbackDAO.getFilteredFeedback(keyword, status, rating, sortBy, order, offset, limit);
            totalFeedback = feedbackDAO.getTotalFilteredFeedback(keyword, status, rating); 
            totalPages = (int) Math.ceil((double) totalFeedback / limit);

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("keyword", keyword);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("order", order);
            request.setAttribute("status", status);
            request.setAttribute("rating", rating);

            // Check for success message
            String message = (String) request.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
            }

            request.getRequestDispatcher("AdminSearch.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
