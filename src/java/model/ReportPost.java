/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ReportPost {
    private int reportID;
    private int postID;
    private int userID;
    private int userReportID;
    private String username;
    private String userReportName;
    private String postText;
    private String reportType;
    private String message;
    private Date create_at;

    public ReportPost() {
    }

    public ReportPost(int reportID, int postID, int userID, int userReportID, String username, String userReportName, String reportType, String message, Date create_at) {
        this.reportID = reportID;
        this.postID = postID;
        this.userID = userID;
        this.userReportID = userReportID;
        this.username = username;
        this.userReportName = userReportName;
        this.reportType = reportType;
        this.message = message;
        this.create_at = create_at;
    }
    
    public ReportPost(int reportID, int postID, String username, String userReportName, String postText, String reportType, String message, Date create_at) {
        this.reportID = reportID;
        this.postID = postID;
        this.username = username;
        this.userReportName = userReportName;
        this.reportType = reportType;
        this.message = message;
        this.create_at = create_at;
        this.postText = postText;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserReportID() {
        return userReportID;
    }

    public void setUserReportID(int userReportID) {
        this.userReportID = userReportID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserReportName() {
        return userReportName;
    }

    public void setUserReportName(String userReportName) {
        this.userReportName = userReportName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "ReportPost{" + "reportID=" + reportID + ", postID=" + postID + ", userID=" + userID + ", userReportID=" + userReportID + ", username=" + username + ", userReportName=" + userReportName + ", postText=" + postText + ", reportType=" + reportType + ", message=" + message + ", create_at=" + create_at + '}';
    }
    

   
    

    
}