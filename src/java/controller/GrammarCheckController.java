  package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.History;
import model.ResultCheck;

import dal.HistoryCheck;

public class GrammarCheckController extends HttpServlet {

    HistoryCheck historyDAO = new HistoryCheck();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            if (!(match.getRule() instanceof SpellingCheckRule)) {
                grammarErrors.add(match);
            }
        }

        List<ResultCheck> resultCheckList = new ArrayList<>();
        StringBuilder correctedTextBuilder = new StringBuilder(checkText);

        System.out.println(correctedTextBuilder);
        for (int i = grammarErrors.size() - 1; i >= 0; i--) {
            RuleMatch match = grammarErrors.get(i);
            int fromPos = match.getFromPos();
            int endPos = match.getToPos();

            // Get error text.
            String errorText = checkText.substring(fromPos, endPos);
            List<String> listSuggests = match.getSuggestedReplacements();
            String message = match.getMessage();

            // If not suggest not change correct word
            String correctText = errorText;

            if (!listSuggests.isEmpty()) {
                correctText = listSuggests.get(0);

                // replace error word to suggest first
                correctedTextBuilder.replace(fromPos, endPos, correctText);
            }

            resultCheckList.add(new ResultCheck(listSuggests, message, errorText, correctedTextBuilder));
        }

        // Save History in DataBase
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("login");
            return;
        }

        // results
        String results = correctedTextBuilder.toString();

        // Kiểm tra nếu results có giá trị thì mới lưu vào cơ sở dữ liệu
        if (results != null && !results.isEmpty()) {
            History newHistory = new History(userID, checkText, results, true);
            historyDAO.saveGrammarCheckHistory(newHistory);
        }

        // Conver from java to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResponse = gson.toJson(resultCheckList);

        // Config
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send JSON response
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
