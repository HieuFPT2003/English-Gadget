package controller;

import dal.AdvertiseDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.ReportPost;

@WebServlet(name = "AddAdvertiseControl", urlPatterns = {"/AddAdvertiseControl"})
public class AddAdvertiseControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddAdvertiseControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAdvertiseControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher("AddAdvertise.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới nếu không tồn tại

        if (session != null && session.getAttribute("role") != null) {
            boolean isAdmin = (boolean) session.getAttribute("role");
            boolean status = true;
            if (isAdmin) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String status_raw = request.getParameter("isActive");
                String adminID_raw = request.getParameter("adminID");
                String imageUrl = request.getParameter("imageUrl");
                if(status_raw.equals("1")){
                    status = true;
                }else{
                    status = false;
                }
                
                PrintWriter out = response.getWriter();
                out.println(title);
                out.println(description);
                out.print(status);
                out.print(status_raw);

                try {
                    int adminID = Integer.parseInt(adminID_raw);

                    // Log đường dẫn ảnh URL
                    out.println("Image URL: " + imageUrl);
                    System.out.println(session.getId());
                    AdvertiseDAO daoAds = new AdvertiseDAO();
                    Timestamp created_at = new Timestamp(System.currentTimeMillis());
                    boolean a = daoAds.insertAdvertise(title, description, imageUrl, status, adminID, created_at);
                    if (a) {
                        request.setAttribute("logsu", "Add successfully");
                        request.getRequestDispatcher("AddAdvertise.jsp").forward(request, response);
                    } else {
                        request.setAttribute("logfa", "Add failed. Please try again later.");
                        request.getRequestDispatcher("AddAdvertise.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace(out);
                    out.println("Error: " + e.getMessage());
                }
            } else {
                // Nếu không phải admin, chuyển hướng về trang login
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            // Nếu không có session hoặc không có role trong session, chuyển hướng về trang login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
