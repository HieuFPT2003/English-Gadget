package model;

import java.util.List;

public class ResultCheck {

    private List<String> listSuggests;
    private String message;
    private String errorText;
    private StringBuilder correctText;

    public ResultCheck(List<String> listSuggests, String message, String errorText, StringBuilder correctText) {
        this.listSuggests = listSuggests;
        this.message = message;
        this.errorText = errorText;
        this.correctText = correctText;
    }

    public ResultCheck(List<String> listSuggests, String message, String errorText) {
        this.listSuggests = listSuggests;
        this.message = message;
        this.errorText = errorText;
    }
    
    

    public List<String> getListSuggests() {
        return listSuggests;
    }

    public void setListSuggests(List<String> listSuggests) {
        this.listSuggests = listSuggests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public StringBuilder getCorrectText() {
        return correctText;
    }

    public void setCorrectText(StringBuilder correctText) {
        this.correctText = correctText;
    }

    @Override
    public String toString() {
        return "ResultCheck{" + "listSuggests=" + listSuggests + ", message=" + message + ", errorText=" + errorText + ", correctText=" + correctText + '}';
    }

}
