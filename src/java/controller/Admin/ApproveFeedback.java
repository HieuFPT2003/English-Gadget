package controller.Admin;

import dal.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ApproveFeedback", urlPatterns={"/approvefeedback"})
public class ApproveFeedback extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String feedbackIDStr = request.getParameter("feedbackID");
        if (feedbackIDStr != null) {
            int feedbackID = Integer.parseInt(feedbackIDStr);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.approveFeedback(feedbackID);
        }
        response.sendRedirect("managefeedback");
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

    @Override
    public String getServletInfo() {
        return "Servlet to approve feedback";
    }
}
