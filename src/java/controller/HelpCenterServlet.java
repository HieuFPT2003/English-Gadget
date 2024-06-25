/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.HelpCenterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.HelpCenter;
import model.TopicHelpCenter;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HelpCenterServlet", urlPatterns = {"/help"})
public class HelpCenterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HelpCenterDAO dao = new HelpCenterDAO();
        List<TopicHelpCenter> list = dao.getAllTopic();
        request.setAttribute("listTopic", list);

        HelpCenterDAO dao1 = new HelpCenterDAO();
        List<HelpCenter> list1 = dao1.getAllQaA();
        request.setAttribute("listQaA", list1);
        request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);

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
        //lay du lieu

    }

}
