package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet(name = "UserUpdate", urlPatterns = {"/userupdate"})
public class UserUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIDStr = request.getParameter("userID");
        if (userIDStr != null) {
            try {
                int userID = Integer.parseInt(userIDStr);
                UsersDAO usersDAO = new UsersDAO();
                Users user = usersDAO.getUsersById(userID);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("userprofile");
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

            response.sendRedirect("userprofile?userID=" + userID); // Ensure redirect includes userID
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("userupdate?userID=" + request.getParameter("userID"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("userprofile?error=true");
        }
    }
}
