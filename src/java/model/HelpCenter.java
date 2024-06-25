/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HelpCenter {
    private int heID;
    private String questionContent;
    private String answerContent;
    private int topicID;

    public HelpCenter() {
    }

    public HelpCenter(int heID, String questionContent, String answerContent, int topicID) {
        this.heID = heID;
        this.questionContent = questionContent;
        this.answerContent = answerContent;
        this.topicID = topicID;
    }

    public int getHeID() {
        return heID;
    }

    public void setHeID(int heID) {
        this.heID = heID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    @Override
    public String toString() {
        return "HelpCenter{" + "heID=" + heID + ", questionContent=" + questionContent + ", answerContent=" + answerContent + ", topicID=" + topicID + '}';
    }
    
}
