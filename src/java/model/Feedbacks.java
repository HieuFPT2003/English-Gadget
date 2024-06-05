package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Feedbacks {
    private int feedbackID;
    private int userID;
    private String feedbackText;
    private String userImage;

    public Feedbacks() {
    }

    public Feedbacks(int feedbackID, int userID, String feedbackText, String userImage) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.feedbackText = feedbackText;
        this.userImage = userImage;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "Feedbacks{" + "feedbackID=" + feedbackID + ", userID=" + userID + ", feedbackText=" + feedbackText + ", userImage=" + userImage + '}';
    }


    
            
}
