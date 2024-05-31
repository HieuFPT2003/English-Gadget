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
public class SubscriptionPlans {
    private int planID;
    private String planName;
    private double planPrice;
    private int durationDays;
    private Date createdAt;

    public SubscriptionPlans() {
    }

    public SubscriptionPlans(int planID, String planName, double planPrice, int durationDays, Date createdAt) {
        this.planID = planID;
        this.planName = planName;
        this.planPrice = planPrice;
        this.durationDays = durationDays;
        this.createdAt = createdAt;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(double planPrice) {
        this.planPrice = planPrice;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
