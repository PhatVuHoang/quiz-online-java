/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.subject;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class subjectDTO implements Serializable{
    private String id;
    private String nameSubject;
    private int timeout;

    public subjectDTO() {
    }

    public subjectDTO(String id, String nameSubject, int timeout) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.timeout = timeout;
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
     * @return the nameSubject
     */
    public String getNameSubject() {
        return nameSubject;
    }

    /**
     * @param nameSubject the nameSubject to set
     */
    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    
    
}
