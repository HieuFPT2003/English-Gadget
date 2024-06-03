package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int postID;
    private int userID;
    private String customerName;
    private String postText;
    private Date datePosted;
    private int likeCount;
    private int dislikeCount;
    private boolean Edited = false;

    // Constructor
    
    public Post() {
    }

    public Post(int postID, int userID, String customerName, String postText, Date datePosted, int likeCount, int dislikeCount) {
        this.postID = postID;
        this.userID = userID;
        this.customerName = customerName;
        this.postText = postText;
        this.datePosted = datePosted;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public Post(int postID, int userID, String customerName, String postText, Date datePosted, int likeCount, int dislikeCount, boolean Editeds) {
        this.postID = postID;
        this.userID = userID;
        this.customerName = customerName;
        this.postText = postText;
        this.datePosted = datePosted;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.Edited = Editeds;
    }

    public boolean isEdited() {
        return Edited;
    }

    public void setEdited(boolean Edited) {
        this.Edited = Edited;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getFormattedDatePosted() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(datePosted);
    }

    @Override
    public String toString() {
        return "Post{" + "postID=" + postID + ", userID=" + userID + ", customerName=" + customerName + ", postText=" + postText + ", datePosted=" + datePosted + ", likeCount=" + likeCount + ", dislikeCount=" + dislikeCount + ", Edited=" + Edited + '}';
    }


}
