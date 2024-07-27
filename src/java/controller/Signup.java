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
@WebServlet(name = "Signup", urlPatterns = {"/signup"})
public class Signup extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Signup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Signup at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
     String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String ageStr = request.getParameter("age");

    if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
        request.setAttribute("mess", "The fields can't be empty");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    if (username.length() <= 6) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("age", ageStr);
        request.setAttribute("mess", "Username must be longer than 6 characters");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    int age = 0;
    try {
        age = Integer.parseInt(ageStr);
    } catch (NumberFormatException e) {
        request.setAttribute("mess", "Invalid age");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    if (age <= 10) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("age", ageStr);
        request.setAttribute("mess", "Age must be greater than 10");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
        return;
    }

    LoginDao dao = new LoginDao();
    Users existingUser = dao.checkAccountExist(username);
    Users emailExist = dao.checkEmailExist(email);

    if (existingUser != null) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("age", ageStr);
        request.setAttribute("mess", "Username already exists");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else if (emailExist != null) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("age", ageStr);
        request.setAttribute("mess", "Email already exists");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else {
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setAge(age);

        dao.signup(newUser);

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("age", ageStr);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
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
