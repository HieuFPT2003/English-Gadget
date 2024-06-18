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
    private String feedbackTopic;
    private String feedbackText;

    public Feedbacks() {
    }

    public Feedbacks(int feedbackID, int userID, String feedbackTopic, String feedbackText) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.feedbackTopic = feedbackTopic;
        this.feedbackText = feedbackText;
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

    public String getFeedbackTopic() {
        return feedbackTopic;
    }

    public void setFeedbackTopic(String feedbackTopic) {
        this.feedbackTopic = feedbackTopic;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    @Override
    public String toString() {
        return "Feedbacks{" + "feedbackID=" + feedbackID + ", userID=" + userID + ", feedbackTopic=" + feedbackTopic + ", feedbackText=" + feedbackText + '}';
    }

   
}
    

   
