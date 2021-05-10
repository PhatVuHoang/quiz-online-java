/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.account;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class accountErrDTO implements Serializable{
    private String emailIsExisted;
    private String emailFormat;
    private String emailEmpty;
    private String nameFormat;
    private String nameEmpty;
    private String passwordFormat;
    private String passwordEmpty;

    public accountErrDTO() {
    }

    public accountErrDTO(String emailIsExisted, String emailFormat, String emailEmpty, String nameFormat, String nameEmpty, String passwordFormat, String passwordEmpty) {
        this.emailIsExisted = emailIsExisted;
        this.emailFormat = emailFormat;
        this.emailEmpty = emailEmpty;
        this.nameFormat = nameFormat;
        this.nameEmpty = nameEmpty;
        this.passwordFormat = passwordFormat;
        this.passwordEmpty = passwordEmpty;
    }

    /**
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    /**
     * @return the emailFormat
     */
    public String getEmailFormat() {
        return emailFormat;
    }

    /**
     * @param emailFormat the emailFormat to set
     */
    public void setEmailFormat(String emailFormat) {
        this.emailFormat = emailFormat;
    }

    /**
     * @return the emailEmpty
     */
    public String getEmailEmpty() {
        return emailEmpty;
    }

    /**
     * @param emailEmpty the emailEmpty to set
     */
    public void setEmailEmpty(String emailEmpty) {
        this.emailEmpty = emailEmpty;
    }

    /**
     * @return the nameFormat
     */
    public String getNameFormat() {
        return nameFormat;
    }

    /**
     * @param nameFormat the nameFormat to set
     */
    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    /**
     * @return the nameEmpty
     */
    public String getNameEmpty() {
        return nameEmpty;
    }

    /**
     * @param nameEmpty the nameEmpty to set
     */
    public void setNameEmpty(String nameEmpty) {
        this.nameEmpty = nameEmpty;
    }

    /**
     * @return the passwordFormat
     */
    public String getPasswordFormat() {
        return passwordFormat;
    }

    /**
     * @param passwordFormat the passwordFormat to set
     */
    public void setPasswordFormat(String passwordFormat) {
        this.passwordFormat = passwordFormat;
    }

    /**
     * @return the passwordEmpty
     */
    public String getPasswordEmpty() {
        return passwordEmpty;
    }

    /**
     * @param passwordEmpty the passwordEmpty to set
     */
    public void setPasswordEmpty(String passwordEmpty) {
        this.passwordEmpty = passwordEmpty;
    }
    
    
}
