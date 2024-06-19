package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Feedback;

@WebServlet(name="FeedBackAdd", urlPatterns={"/feedbackadd"})
public class FeedBackAdd extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false means do not create new session if not exist
        
        if (session != null && session.getAttribute("userID") != null) {

            String feedbackTopic = request.getParameter("feedbackTopic");
            String feedbackText = request.getParameter("feedbackText");
            int userID = (int) session.getAttribute("userID"); // Get userID from session
            
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback f = new Feedback(0, userID, feedbackTopic, feedbackText, null); 
            feedbackDAO.insert(f);
            
            response.sendRedirect("thankfeedback.jsp"); 
        } else {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
