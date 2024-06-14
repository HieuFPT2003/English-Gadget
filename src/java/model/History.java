package model;

import java.util.Date;


public class History {
    private int checkID;
    private int userID;
    private String text;
    private String result;
    private java.util.Date checkDate;
    private boolean type;

    public History(int checkID, int userID, String text, String result, Date checkDate, boolean type) {
        this.checkID = checkID;
        this.userID = userID;
        this.text = text;
        this.result = result;
        this.checkDate = checkDate;
        this.type = type;
    }

    // For 
    public History(int userID, String text, String result, boolean type) {
        this.userID = userID;
        this.text = text;
        this.result = result;
        this.type = type;
    }

    // For spelling
    public History(int userID, String text, boolean type) {
        this.userID = userID;
        this.text = text;
        this.type = type;
    }
    
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
   
    
    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public String toString() {
        return "History{" + "checkID=" + checkID + ", userID=" + userID + ", text=" + text + ", result=" + result + ", checkDate=" + checkDate + '}';
    }
}
