/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.question;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class questionDTO implements Serializable{
    private String id;
    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String ans;
    private String subjectID;
    private boolean status;

    public questionDTO() {
    }

    public questionDTO(String id, String question, String op1, String op2, String op3, String op4, String ans, String subjectID, boolean status) {
        this.id = id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.ans = ans;
        this.subjectID = subjectID;
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the op1
     */
    public String getOp1() {
        return op1;
    }

    /**
     * @param op1 the op1 to set
     */
    public void setOp1(String op1) {
        this.op1 = op1;
    }

    /**
     * @return the op2
     */
    public String getOp2() {
        return op2;
    }

    /**
     * @param op2 the op2 to set
     */
    public void setOp2(String op2) {
        this.op2 = op2;
    }

    /**
     * @return the op3
     */
    public String getOp3() {
        return op3;
    }

    /**
     * @param op3 the op3 to set
     */
    public void setOp3(String op3) {
        this.op3 = op3;
    }

    /**
     * @return the op4
     */
    public String getOp4() {
        return op4;
    }

    /**
     * @param op4 the op4 to set
     */
    public void setOp4(String op4) {
        this.op4 = op4;
    }

    /**
     * @return the ans
     */
    public String getAns() {
        return ans;
    }

    /**
     * @param ans the ans to set
     */
    public void setAns(String ans) {
        this.ans = ans;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

        
}
