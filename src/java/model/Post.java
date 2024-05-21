package model;
import java.util.Date;

public class Post {
    private int UserID;
    private String Text;
    private Date date;

    public Post(int UserID, String Text) {
        this.UserID = UserID;
        this.Text = Text;
    }

    public Post(int UserID, String Text, Date date) {
        this.UserID = UserID;
        this.Text = Text;
        this.date = date;
    }
    
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
}
