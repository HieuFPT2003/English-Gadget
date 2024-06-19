package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.util.Date;
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
            int userID = Integer.parseInt(userIDStr);
            UsersDAO usersDAO = new UsersDAO();
            Users user = usersDAO.getUsersById(userID);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("userprofile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("1");
            
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
            
            System.out.println(usersDAO);
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
                usersDAO.getUsersById(userID);
            }
            response.sendRedirect("userprofile");
        } catch (NumberFormatException e) {
            response.sendRedirect("updateprofile?userID=" + request.getParameter("userID"));
        }
    }
}
