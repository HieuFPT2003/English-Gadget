package controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.UsersDAO;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID_raw = request.getParameter("userID");
        
        try {
            int userID = Integer.parseInt(userID_raw);
            UsersDAO dao = new UsersDAO();
            dao.delete(userID);
            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid user ID format: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); 
            request.setAttribute("errorMessage", "Error deleting user: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
