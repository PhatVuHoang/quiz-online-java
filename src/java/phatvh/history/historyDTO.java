/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.history;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class historyDTO implements Serializable{
    private String question;
    private String correctAns;
    private String userAns;
    private String historyId;

    public historyDTO() {
    }

    public historyDTO(String question, String correctAns, String userAns, String historyId) {
        this.question = question;
        this.correctAns = correctAns;
        this.userAns = userAns;
        this.historyId = historyId;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the correctAns
     */
    public String getCorrectAns() {
        return correctAns;
    }

    /**
     * @param correctAns the correctAns to set
     */
    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    /**
     * @return the userAns
     */
    public String getUserAns() {
        return userAns;
    }

    /**
     * @param userAns the userAns to set
     */
    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }

    /**
     * @return the historyId
     */
    public String getHistoryId() {
        return historyId;
    }

    /**
     * @param historyId the historyId to set
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    
    
}
