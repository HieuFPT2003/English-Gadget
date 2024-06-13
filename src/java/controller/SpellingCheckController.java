package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import model.ResultCheck;

public class SpellingCheckController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GrammarCheck</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GrammarCheck at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String checkText = request.getParameter("checkText");
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

        //Check
        List<RuleMatch> matches = langTool.check(checkText);

        List<RuleMatch> grammarErrors = new ArrayList<>();

        for (RuleMatch match : matches) {
            if ((match.getRule() instanceof SpellingCheckRule)) {
                grammarErrors.add(match);
            }
        }

        List<ResultCheck> resultCheckList = new ArrayList<>();

        for (int i = grammarErrors.size() - 1; i >= 0; i--) {
            RuleMatch match = grammarErrors.get(i);
            int fromPos = match.getFromPos();
            int endPos = match.getToPos();

            // Get error text.
            String errorText = checkText.substring(fromPos, endPos);
            List<String> listSuggests = match.getSuggestedReplacements();
            String message = match.getMessage();

            resultCheckList.add(new ResultCheck(listSuggests, message, errorText));
        }

        // Conver from java to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResponse = gson.toJson(resultCheckList);

        System.out.println(resultCheckList);
        // Config
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send JSON response
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
