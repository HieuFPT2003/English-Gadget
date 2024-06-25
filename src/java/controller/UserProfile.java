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
        } else {
            request.setAttribute("errorMessage", "User ID is required");
        }
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int age = Integer.parseInt(request.getParameter("age"));

            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);

            if (user != null) {
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setAge(age);
                usersDAO.update(user);
            }
            response.sendRedirect("userprofile?userID=" + userID);
        } catch (NumberFormatException e) {
            response.sendRedirect("userupdate?userID=" + request.getParameter("userID"));
        }
    }

    @Override
    public String getServletInfo() {
        return "User profile servlet";
    }
}
