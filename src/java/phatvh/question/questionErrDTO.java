/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.question;

/**
 *
 * @author ASUS
 */
public class questionErrDTO {
    private String subjectEmpty;
    private String questionEmpty;
    private String op1Empty;
    private String op2Empty;
    private String op3Empty;
    private String op4Empty;
    private String ansEmpty;
    private String ansNotMatch;
    private String opDuplicate;

    public questionErrDTO() {
    }

    public questionErrDTO(String subjectEmpty, String questionEmpty, String op1Empty, String op2Empty, String op3Empty, String op4Empty, String ansEmpty, String ansNotMatch, String opDuplicate) {
        this.subjectEmpty = subjectEmpty;
        this.questionEmpty = questionEmpty;
        this.op1Empty = op1Empty;
        this.op2Empty = op2Empty;
        this.op3Empty = op3Empty;
        this.op4Empty = op4Empty;
        this.ansEmpty = ansEmpty;
        this.ansNotMatch = ansNotMatch;
        this.opDuplicate = opDuplicate;
    }

    /**
     * @return the subjectEmpty
     */
    public String getSubjectEmpty() {
        return subjectEmpty;
    }

    /**
     * @param subjectEmpty the subjectEmpty to set
     */
    public void setSubjectEmpty(String subjectEmpty) {
        this.subjectEmpty = subjectEmpty;
    }

    /**
     * @return the questionEmpty
     */
    public String getQuestionEmpty() {
        return questionEmpty;
    }

    /**
     * @param questionEmpty the questionEmpty to set
     */
    public void setQuestionEmpty(String questionEmpty) {
        this.questionEmpty = questionEmpty;
    }

    /**
     * @return the op1Empty
     */
    public String getOp1Empty() {
        return op1Empty;
    }

    /**
     * @param op1Empty the op1Empty to set
     */
    public void setOp1Empty(String op1Empty) {
        this.op1Empty = op1Empty;
    }

    /**
     * @return the op2Empty
     */
    public String getOp2Empty() {
        return op2Empty;
    }

    /**
     * @param op2Empty the op2Empty to set
     */
    public void setOp2Empty(String op2Empty) {
        this.op2Empty = op2Empty;
    }

    /**
     * @return the op3Empty
     */
    public String getOp3Empty() {
        return op3Empty;
    }

    /**
     * @param op3Empty the op3Empty to set
     */
    public void setOp3Empty(String op3Empty) {
        this.op3Empty = op3Empty;
    }

    /**
     * @return the op4Empty
     */
    public String getOp4Empty() {
        return op4Empty;
    }

    /**
     * @param op4Empty the op4Empty to set
     */
    public void setOp4Empty(String op4Empty) {
        this.op4Empty = op4Empty;
    }

    /**
     * @return the ansEmpty
     */
    public String getAnsEmpty() {
        return ansEmpty;
    }

    /**
     * @param ansEmpty the ansEmpty to set
     */
    public void setAnsEmpty(String ansEmpty) {
        this.ansEmpty = ansEmpty;
    }

    /**
     * @return the ansNotMatch
     */
    public String getAnsNotMatch() {
        return ansNotMatch;
    }

    /**
     * @param ansNotMatch the ansNotMatch to set
     */
    public void setAnsNotMatch(String ansNotMatch) {
        this.ansNotMatch = ansNotMatch;
    }

    /**
     * @return the opDuplicate
     */
    public String getOpDuplicate() {
        return opDuplicate;
    }

    /**
     * @param opDuplicate the opDuplicate to set
     */
    public void setOpDuplicate(String opDuplicate) {
        this.opDuplicate = opDuplicate;
    }
    
    
    
}
