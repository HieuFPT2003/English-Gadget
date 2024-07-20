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
import model.Google;
import model.GoogleAccount;
import model.Users;

/**
 *
 * @author khanh
 */
@WebServlet(name = "loginwithgoogle", urlPatterns = {"/loginwithgoogle"})
public class loginwithgoogle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        Google gg = new Google();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
        System.out.println(acc.getId());
        LoginDao dao = new LoginDao();
        //check xem tai khoan nay da ton tai hay chua
        Users c = dao.checkEmailExist(acc.getEmail());
        
        //neu chua ton tai 
        if (c == null) {
            System.out.println(acc);
            Users a = new Users();
            a.setUsername(acc.getName());
            a.setEmail(acc.getEmail());
            a.setAddress("unknown");
            a.setPhone("unknown");
            
            dao.signup(a);
            
          HttpSession session = request.getSession();
            session.setAttribute("email", acc.getEmail());
            session.setAttribute("userID", acc.getId());
            session.setAttribute("usernamegoogle", acc.getName());
            session.setAttribute("premium", 0);
            session.setAttribute("role", 0);
            
            request.getRequestDispatcher("LandingPage.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userID", c.getUserID());
            session.setAttribute("usernamegoogle", acc.getName());
            session.setAttribute("userName", c.getUsername());
            session.setAttribute("premium", c.isPremiumID());
            session.setAttribute("role", c.isRole());
            session.setAttribute("email", c.getEmail());

            request.getRequestDispatcher("LandingPage.jsp").forward(request, response);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
