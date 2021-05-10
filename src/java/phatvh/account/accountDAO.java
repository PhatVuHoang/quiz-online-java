/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class accountDAO implements Serializable {

    public accountDTO checkLogin(String userID, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select email,name,role "
                        + "from account "
                        + "where email = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    accountDTO account = new accountDTO(email, name, role);
                    return account;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void createAccount(String email, String name, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into account(email,name,password,role,status) "
                        + "values(?,?,?,0,1)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, name);
                pst.setString(3, password);
                int row = pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public String getEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select email "
                        + "from account "
                        + "where email = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String mail = rs.getString("email");
                    return mail;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
