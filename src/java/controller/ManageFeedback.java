package controller;

import dal.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Feedback;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageFeedback", urlPatterns = {"/managefeedback"})
public class ManageFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the current session, don't create a new one if it doesn't exist

        if (session != null && session.getAttribute("role") != null) {
            boolean isAdmin = (boolean) session.getAttribute("role");

            if (isAdmin) {
                FeedbackDAO feedbackDAO = new FeedbackDAO();

                int limit = 10; // Number of feedback items per page
                int page = 1;   // Current page

                // Get the page parameter from the request
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    page = Integer.parseInt(pageParam);
                }

                int offset = (page - 1) * limit; // Calculate the offset for SQL query

                try {
                    // Get the paginated feedback list
                    List<Feedback> feedbackList = feedbackDAO.getPaginatedFeedback(offset, limit);
                    int totalFeedback = feedbackDAO.getTotalFeedback(); 
                    int totalPages = (int) Math.ceil((double) totalFeedback / limit);

                    // Set the attributes for the request
                    request.setAttribute("feedbackList", feedbackList);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", page);

                    // Check and set success message if any
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        request.setAttribute("message", message);
                    }

                    // Forward to JSP for display
                    request.getRequestDispatcher("AdminFeedback.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            } else {
                // If not admin, redirect to login page
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            // If no session or role in session, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
