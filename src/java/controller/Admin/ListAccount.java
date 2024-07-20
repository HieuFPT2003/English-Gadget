package controller.Admin;

import dal.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListServlet", urlPatterns = {"/list"})
public class ListAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the current session, don't create a new one if it doesn't exist

        // Validate session and username
        if (session != null) {
            Object usernameObj = session.getAttribute("username");
            if (usernameObj != null) {
                String username = (String) usernameObj;

                UsersDAO usersDAO = new UsersDAO();

                int limit = 2; // Number of users per page
                int page = 1;  // Current page

                // Get the page parameter from the request
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    try {
                        page = Integer.parseInt(pageParam);
                    } catch (NumberFormatException e) {
                        page = 1; // Default to the first page if the page parameter is not a number
                    }
                }

                int offset = (page - 1) * limit; // Calculate the offset for the SQL query

                try {
                    // Get the paginated list of users
                    List<Users> usersList = usersDAO.getPaginatedUsers(offset, limit);
                    int totalUsers = usersDAO.getTotalUserCount();
                    int totalPages = (int) Math.ceil((double) totalUsers / limit);

                    // Set the request attributes
                    request.setAttribute("usersList", usersList);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("currentPage", page);

                    // Check for success message and set it as a request attribute if present
                    String message = request.getParameter("message");
                    if (message != null) {
                        request.setAttribute("message", message);
                    }

                    // Forward the request to the JSP for display
                    request.getRequestDispatcher("list.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            } else {
                // If username is not in session, redirect to login page
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            // If no session, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
