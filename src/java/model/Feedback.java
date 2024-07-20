package model;

import java.util.Date;

public class Feedback {
    private int feedbackID;
    private int userID;
    private String feedbackTopic;
    private String feedbackText;
    private Date createdAt;
    private String username;
    private int rating;
    private int status;
    private int role;

    public Feedback() {
    }

    public Feedback(int userID, String feedbackTopic, String feedbackText,int rating) {
        this.userID = userID;
        this.feedbackTopic = feedbackTopic;
        this.feedbackText = feedbackText;
        this.rating = rating;
    }

    public Feedback(int feedbackID, int userID, String feedbackTopic, String feedbackText, Date createdAt, String username, int rating, int status, int role) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.feedbackTopic = feedbackTopic;
        this.feedbackText = feedbackText;
        this.createdAt = createdAt;
        this.username = username;
        this.rating = rating;
        this.status = status;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    

}