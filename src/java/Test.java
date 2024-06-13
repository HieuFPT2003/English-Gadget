import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        // Khởi tạo đối tượng JLanguageTool cho tiếng Anh Mỹ
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

        // Đoạn văn cần kiểm tra
        String text = "This is a example sentence with a error.";

        // Kiểm tra tất cả các lỗi
        List<RuleMatch> matches = langTool.check(text);

        // Tách riêng lỗi chính tả và ngữ pháp
        List<RuleMatch> spellingErrors = new ArrayList<>();
        List<RuleMatch> grammarErrors = new ArrayList<>();

        for (RuleMatch match : matches) {
            if (match.getRule() instanceof SpellingCheckRule) {
                spellingErrors.add(match);
            } else {
                grammarErrors.add(match);
            }
        }

        // In ra các lỗi chính tả và vị trí của chúng
        System.out.println("Spelling errors:");
        for (RuleMatch match : spellingErrors) {
            System.out.println("Potential error at characters " +
                match.getFromPos() + "-" + match.getToPos() + ": " +
                match.getMessage());
            System.out.println("Suggested correction(s): " +
                match.getSuggestedReplacements());
        }

        // In ra các lỗi ngữ pháp và vị trí của chúng
        System.out.println("\nGrammar errors:");
        for (RuleMatch match : grammarErrors) {
            System.out.println("Potential error at characters " +
                match.getFromPos() + "-" + match.getToPos() + ": " +
                match.getMessage());
            System.out.println("Suggested correction(s): " +
                match.getSuggestedReplacements());
        }
    }
}
