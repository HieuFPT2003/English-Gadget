package controller;

import dal.UsersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;
import model.mailsent;

@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIDStr = request.getParameter("userID");
        if (userIDStr != null) {
            int userID = Integer.parseInt(userIDStr);
            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("update.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String newPassword = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int age = Integer.parseInt(request.getParameter("age"));
            boolean premiumID = request.getParameter("premiumID") != null && request.getParameter("premiumID").equals("1");

            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);
            
            if (user != null) {
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                user.setAge(age);
                user.setPremiumID(premiumID);
                if (newPassword != null && !newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                  
                    
                }
                usersDAO.update(user);
                 
                
            }
            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            response.sendRedirect("update?userID=" + request.getParameter("userID"));
        }
    }
}
