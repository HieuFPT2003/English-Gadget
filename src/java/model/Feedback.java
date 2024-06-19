package model;

import java.util.Date;

public class Feedback {
    private int feedbackID;
    private int userID;
    private String feedbackTopic;
    private String feedbackText;
    private Date createdAt;

    public Feedback() {
    }

    public Feedback(int feedbackID, int userID, String feedbackTopic, String feedbackText, Date createdAt) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.feedbackTopic = feedbackTopic;
        this.feedbackText = feedbackText;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
