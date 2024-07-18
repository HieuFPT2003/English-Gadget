package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Feedback;

@WebServlet(name="UserFeedback", urlPatterns={"/userfeedback"})
public class UserFeedback extends HttpServlet {

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
            List<Feedback> feedbackList;
            int totalFeedback;
            int totalPages;

            // Fetch only approved feedback entries with pagination
            feedbackList = feedbackDAO.getApprovedFeedback(offset, limit);
            totalFeedback = feedbackDAO.getTotalFeedback(); // Total number of feedback entries
            totalPages = (int) Math.ceil((double) totalFeedback / limit);

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("limit", limit);

            request.getRequestDispatcher("Userfeedback.jsp").forward(request, response);
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
        return "Servlet to handle user feedback display";
    }
}
