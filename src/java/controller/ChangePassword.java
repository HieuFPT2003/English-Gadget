package controller;

import dal.ChangePasswordDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentpass = request.getParameter("current-password");
        String newpass = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirmPassword");
        Integer userId = (Integer) request.getSession().getAttribute("userID");

        if (userId == null) {
            request.setAttribute("messfail", "User is not logged in or session has expired.");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
            return;
        }

        ChangePasswordDao changepass = new ChangePasswordDao();
        Users user = changepass.getUsersById(userId);      
        if (user == null) {
            request.setAttribute("messfail", "User not found.");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
            return;
        }

        if (!user.getPassword().equals(currentpass)) {
            request.setAttribute("messfail", "Current password is incorrect.");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
            return;
        }

        if (!newpass.equals(confirmPassword)) {
            request.setAttribute("messfail", "New password and confirm password do not match.");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(newpass)) {
            request.setAttribute("messfail", "New password must be up to 8 characters long, contain at least one special character, and one number.");
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
            return;
        }

        changepass.updatePasswordUser(userId, newpass);
        request.setAttribute("messSuc", "Password changed successfully.");
        request.getRequestDispatcher("changepass.jsp").forward(request, response);     
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{1,20}$";
        return Pattern.matches(passwordPattern, password);
    }
}
