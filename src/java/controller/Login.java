/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author khanh
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            // Handle empty username or password
            request.setAttribute("mess", "Username or password cannot be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        LoginDao dao = new LoginDao();
        Users user = dao.login(username, password);

        if (user == null) {
            // Handle invalid login
            request.setAttribute("mess", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Successful login, store user in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on user role
            if (user.isRole()) { // Assuming isRole() checks if user is admin (role = 1)
                response.sendRedirect("AdminLandingPage.jsp");
            } else {
                response.sendRedirect("LandingPage.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
