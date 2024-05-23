/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Q.Hieu
 */
public class Post {
    private int postID;
    private int userID;
    private String postText;
    private Date datePosted;
    private int likeCount;
    private int dislikeCount;
    private List<Integer> likeUserIDs;
    private List<Integer> dislikeUserIDs;

    // Parameterized constructor
    public Post(int postID, int userID, String postText, Date datePosted, int likeCount, int dislikeCount) {
        this.postID = postID;
        this.userID = userID;
        this.postText = postText;
        this.datePosted = datePosted;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    // Getters and setters
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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public List<Integer> getLikeUserIDs() {
        return likeUserIDs;
    }

    public void setLikeUserIDs(List<Integer> likeUserIDs) {
        this.likeUserIDs = likeUserIDs;
    }

    public List<Integer> getDislikeUserIDs() {
        return dislikeUserIDs;
    }

    public void setDislikeUserIDs(List<Integer> dislikeUserIDs) {
        this.dislikeUserIDs = dislikeUserIDs;
    }

    // toString method
    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", userID=" + userID +
                ", postText='" + postText + '\'' +
                ", datePosted=" + datePosted +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                ", likeUserIDs=" + likeUserIDs +
                ", dislikeUserIDs=" + dislikeUserIDs +
                '}';
    }
}