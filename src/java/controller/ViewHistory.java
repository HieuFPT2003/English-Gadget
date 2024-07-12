/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import dal.HistoryCheck;

/**
 *
 * @author Q.Hieu
 */
@WebServlet(name = "ViewHistory", urlPatterns = {"/ViewHistory"})
public class ViewHistory extends HttpServlet {
    
    HistoryCheck historyCheck = new HistoryCheck();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewHistory</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewHistory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        JSONArray historyArray = historyCheck.getHistory(userID);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Gửi JSON phản hồi lại cho client
        PrintWriter out = response.getWriter();
        out.print(historyArray.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
