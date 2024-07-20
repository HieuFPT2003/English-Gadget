    package controller;

    import dal.PaymentDAO;
    import java.io.IOException;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import java.util.List;
    import model.Payments;

    @WebServlet(name = "deletePayment", urlPatterns = {"/deletePayment"})
    public class deletePayment extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try {
                // Lấy tham số content từ request để xóa thanh toán dựa trên nội dung
                String content = request.getParameter("content");
                System.out.println(content);
                PaymentDAO paymentDAO = new PaymentDAO();
                // Xóa các thanh toán dựa trên nội dung
                paymentDAO.deletePaymentByContent(content);
                List<Payments> payments = paymentDAO.getAll();
                request.setAttribute("payments", payments);
                // Điều hướng quay lại danh sách tài khoản premium
                request.getRequestDispatcher("AdminListOfPre.jsp").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error");
                request.getRequestDispatcher("AdminListOfPre.jsp").forward(request, response);
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
            processRequest(request, response);
        }

        @Override
        public String getServletInfo() {
            return "Short description";
        }

    }
