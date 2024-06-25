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

@WebServlet(name="SendFeedBack", urlPatterns={"/sendfeedback"})
public class SendFeedBack extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        
        if (session != null && session.getAttribute("userID") != null) {

            String feedbackTopic = request.getParameter("feedbackTopic");
            String feedbackText = request.getParameter("feedbackText");
            int userID = (int) session.getAttribute("userID");
            
            System.out.println("Feed:" + feedbackTopic);
            System.out.println("Text"+feedbackText);
            System.out.println("UserID:" + userID);
            
            
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback f = new Feedback( userID, feedbackTopic, feedbackText); 
            feedbackDAO.insert(f);
            
            response.sendRedirect("thankfeedback.jsp"); 
        } else {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "Servlet for sending user feedback";
    }
}
