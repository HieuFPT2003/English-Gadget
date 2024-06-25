package controller;


import dal.FeedbackDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;

@WebServlet(name="SearchFeedback", urlPatterns={"/searchfeedback"})
public class SearchFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedback = feedbackDAO.searchFeedback(keyword);
        request.setAttribute("feedback", feedback); 
        request.getRequestDispatcher("AdminFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

