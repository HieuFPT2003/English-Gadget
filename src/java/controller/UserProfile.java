package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet(name = "UserProfile", urlPatterns = {"/userprofile"})
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID_raw = request.getParameter("userID");
        if (userID_raw != null) {
            try {
                int userID = Integer.parseInt(userID_raw);
                UsersDAO usersDAO = new UsersDAO();
                Users user = usersDAO.getUsersById(userID);
                if (user != null) {
                    request.setAttribute("user", user);
                } else {
                    request.setAttribute("errorMessage", "User not found");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid user ID format");
            }
        }
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "User profile servlet";
    }
}
