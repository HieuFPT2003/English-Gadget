package controller;

import dal.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Users;

@WebServlet(name="AdminSortAccount", urlPatterns={"/sort"})
public class AdminSortAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sortBy = request.getParameter("sortBy");
        String keyword = request.getParameter("keyword");
        int limit = 3; // Number of users per page
        int page = 1;   // Current page

        String limitParam = request.getParameter("limit");
        if (limitParam != null && !limitParam.isEmpty()) {
            limit = Integer.parseInt(limitParam);
        }

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        int offset = (page - 1) * limit;

        UsersDAO usersDAO = new UsersDAO();
        List<Users> users;
        if (sortBy == null || sortBy.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/list.jsp");
            return;
        }

        users = usersDAO.sortUsers(sortBy, offset, limit);
        int totalUsers = usersDAO.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / limit);

        request.setAttribute("users", users);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("limit", limit);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("AdminSortAccount.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
