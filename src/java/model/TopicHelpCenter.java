/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class TopicHelpCenter {
    private int topicID;
    private String topicName;
    private String topicDetail;
    private String topicPics;

    public TopicHelpCenter() {
    }

    public TopicHelpCenter(int topicID, String topicName, String topicDetail, String topicPics) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.topicDetail = topicDetail;
        this.topicPics = topicPics;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDetail() {
        return topicDetail;
    }

    public void setTopicDetail(String topicDetail) {
        this.topicDetail = topicDetail;
    }

    public String getTopicPics() {
        return topicPics;
    }

    public void setTopicPics(String topicPics) {
        this.topicPics = topicPics;
    }

    @Override
    public String toString() {
        return "TopicHelpCenter{" + "topicID=" + topicID + ", topicName=" + topicName + ", topicDetail=" + topicDetail + ", topicPics=" + topicPics + '}';
    }
    
    
}
