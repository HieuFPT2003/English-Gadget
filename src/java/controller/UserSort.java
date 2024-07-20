package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.util.List;
import model.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="UserSort", urlPatterns={"/usersort"})
public class UserSort extends HttpServlet {

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

        String sortBy = request.getParameter("sortBy");
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "created_at"; // Default sort by created_at
        }

        String order = request.getParameter("order");
        if (order == null || order.isEmpty()) {
            order = "DESC"; // Default order DESC
        }

        try {
            List<Feedback> feedbackList;
            int totalFeedback;
            int totalPages;

            // Fetch sorted feedback entries with pagination
            feedbackList = feedbackDAO.sortFeedback(sortBy, order, offset, limit);
            totalFeedback = feedbackDAO.getTotalFeedback();
            
            totalPages = (int) Math.ceil((double) totalFeedback / limit);

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("order", order);

            request.getRequestDispatcher("UserSort.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Servlet to sort feedback by specified column";
    }
}
