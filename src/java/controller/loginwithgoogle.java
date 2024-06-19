/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDao;
import java.io.IOException;
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
        System.out.println(acc.getEmail());

        LoginDao dao = new LoginDao();

        //check xem tai khoan nay da ton tai hay chua
        Users c = dao.checkAccountExist(acc.getEmail());

        //neu chua ton tai 
        if (c == null) {
            System.out.println(acc);
            Users a = new Users();
            a.setUsername(acc.getName());
            a.setEmail(acc.getEmail());
            dao.signup(a);
            
            HttpSession session = request.getSession();
            session.setAttribute("userID", a.getUserID());
            session.setAttribute("UserName", a.getUsername());
            session.setAttribute("premium", a.isPremiumID());
            session.setAttribute("role", a.isRole());
            
            request.getRequestDispatcher("LandingPage.jsp").forward(request, response);
        } else {
            
            System.out.println("1");
            HttpSession session = request.getSession();
            session.setAttribute("userID", c.getUserID());
            session.setAttribute("UserName", c.getUsername());
            session.setAttribute("premium", c.isPremiumID());
            session.setAttribute("role", c.isRole());
            request.getRequestDispatcher("LandingPage.jsp").forward(request, response);
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

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
