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

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchQAServlet", urlPatterns = {"/searchqa"})

public class SearchQAServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String keyword = request.getParameter("txt");
        HelpCenterDAO dao = new HelpCenterDAO();
        List<HelpCenter> listQA = dao.searchAns(keyword);
        PrintWriter out = response.getWriter();
        String ms = "";

        if (listQA == null || listQA.isEmpty()) {
            ms = "Sorry, no results found. Please try again with a different keyword.";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);
        } else {
            request.setAttribute("listQaA", listQA);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);
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
//        processRequest(request, response);
        HelpCenterDAO dao1 = new HelpCenterDAO();

        String selectedTopic = request.getParameter("topic");
        if (selectedTopic.equalsIgnoreCase("Account")) {
            List<HelpCenter> listHe = dao1.searchQAByiD(1);
            request.setAttribute("listQaA", listHe);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);

        }else if(selectedTopic.equalsIgnoreCase("Grammar Checking")){
            List<HelpCenter> listHe = dao1.searchQAByiD(2);
            request.setAttribute("listQaA", listHe);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);
        }
        else if(selectedTopic.equalsIgnoreCase("Post")){
            List<HelpCenter> listHe = dao1.searchQAByiD(3);
            request.setAttribute("listQaA", listHe);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);
        }else{
            List<HelpCenter> listHe = dao1.searchQAByiD(4);
            request.setAttribute("listQaA", listHe);
            request.getRequestDispatcher("HelpCenter.jsp").forward(request, response);
        }

    }


}
