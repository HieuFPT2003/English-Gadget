/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Advertise {

    private int adID;
    private String title;
    private String description;
    private String imageAd;
    private boolean isActive;
    private Date created_at;
    private int userID;
    private String userName;
    private Date sendDate;
    private int userReveiveID;

    public Advertise() {
    }

    public Advertise(int adID, String title, String description, String imageAd, boolean isActive, Date created_at, int userID, Date sendDate, int userReveiveID) {
        this.adID = adID;
        this.title = title;
        this.description = description;
        this.imageAd = imageAd;
        this.isActive = isActive;
        this.created_at = created_at;
        this.userID = userID;
        this.sendDate = sendDate;
        this.userReveiveID = userReveiveID;
    }

    public Advertise(int adID, int userID, String title, String description, String imageAd, boolean isActive, Date created_at) {
        this.userID = userID;
        this.adID = adID;
        this.title = title;
        this.description = description;
        this.imageAd = imageAd;
        this.isActive = isActive;
        this.created_at = created_at;
    }
    public Advertise(int adID, String userName, String title, String description, String imageAd, boolean isActive, Date created_at) {
        this.userName = userName;
        this.adID = adID;
        this.title = title;
        this.description = description;
        this.imageAd = imageAd;
        this.isActive = isActive;
        this.created_at = created_at;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAd() {
        return imageAd;
    }

    public void setImageAd(String imageAd) {
        this.imageAd = imageAd;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public int getUserReveiveID() {
        return userReveiveID;
    }

    public void setUserReveiveID(int userReveiveID) {
        this.userReveiveID = userReveiveID;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Advertise{" + "adID=" + adID + ", title=" + title + ", description=" + description + ", imageAd=" + imageAd + ", isActive=" + isActive + ", created_at=" + created_at + ", userID=" + userID + ", userName=" + userName + ", sendDate=" + sendDate + ", userReveiveID=" + userReveiveID + '}';
    }

}
