package controller.Admin;

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
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("role") != null) {
            boolean isAdmin = (boolean) session.getAttribute("role");

            if (isAdmin) {
                FeedbackDAO feedbackDAO = new FeedbackDAO();

                int limit = 10; // Số lượng feedback mỗi trang
                int page = 1;   // Trang hiện tại

                // Lấy tham số trang từ request
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    page = Integer.parseInt(pageParam);
                }

                int offset = (page - 1) * limit; // Tính toán offset cho truy vấn SQL

                try {
                    // Lấy danh sách feedback theo phân trang
                    List<Feedback> feedbackList = feedbackDAO.getPaginatedFeedback(offset, limit);
                    int totalFeedback = feedbackDAO.getTotalFeedback(); 
                    int totalPages = (int) Math.ceil((double) totalFeedback / limit);

                    // Thiết lập các thuộc tính cho request
                    request.setAttribute("feedbackList", feedbackList);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", page);

                    // Kiểm tra và thiết lập thông báo thành công nếu có
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        request.setAttribute("message", message);
                    }

                    // Chuyển tiếp đến JSP để hiển thị
                    request.getRequestDispatcher("AdminFeedback.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
