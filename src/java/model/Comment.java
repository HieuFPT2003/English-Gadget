/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author nookh
 */
public class Comment {
    private int commentID;
    private int userID;
    private int postID;
    private String commentText;
    private Date dateCommented;

    public Comment() {
    }

    public Comment(int commentID, int userID, int postID, String commentText, Date dateCommented) {
        this.commentID = commentID;
        this.userID = userID;
        this.postID = postID;
        this.commentText = commentText;
        this.dateCommented = dateCommented;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(Date dateCommented) {
        this.dateCommented = dateCommented;
    }
    
    
}
