package model;

import java.util.Date;

public class Payments {
    private int paymentid;
    private int userId;
    private String username;
    private String content;
    private Date date;
    private boolean status;
    public Payments(){}

    public Payments(int paymentid, int userId, String username, String content, Date date, boolean status) {
        this.paymentid = paymentid;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public Payments(int userId, String username, String content, Date date, boolean status) {
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.date = date;
        this.status = status;
    }
    

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    

}
