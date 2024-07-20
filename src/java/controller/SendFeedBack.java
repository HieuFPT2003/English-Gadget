package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Feedback;

@WebServlet(name = "SendFeedBack", urlPatterns = {"/sendfeedback"})
public class SendFeedBack extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userID") != null) {
            // Retrieve form parameters
            String feedbackTopic = request.getParameter("feedbackTopic");
            String feedbackText = request.getParameter("feedbackText");
            int rating = Integer.parseInt(request.getParameter("rating"));
            int userID = (int) session.getAttribute("userID");

            // Create a Feedback object
            Feedback feedback = new Feedback(userID, feedbackTopic, feedbackText, rating);

            // Save the feedback to the database
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.insert(feedback);

            // Redirect to thankfeedback.jsp
            response.sendRedirect("thankfeedback.jsp");
        } else {
            // Redirect to login.jsp if session or userID is invalid
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for sending user feedback";
    }
}
