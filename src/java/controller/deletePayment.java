    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
     */
    package controller;

    import dal.PaymentDAO;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import java.util.List;
    import model.Payments;

    /**
     *
     * @author khanh
     */
    @WebServlet(name = "deletePayment", urlPatterns = {"/deletePayment"})
    public class deletePayment extends HttpServlet {

        /**
         * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
         * methods.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
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
                // Xử lý ngoại lệ nếu có lỗi và điều hướng tới trang thông báo lỗi

                request.getRequestDispatcher("AdminListOfPre.jsp").forward(request, response);
            }
        }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

    }
