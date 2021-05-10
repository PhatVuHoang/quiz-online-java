/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class subjectDAO implements Serializable {

    Map<String, Integer> Subjects;

    public Map<String, Integer> getSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select nameSubject,timeout "
                        + "from subject ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String subject = rs.getString("nameSubject");
                    int timeout = rs.getInt("timeout");
                    if (Subjects == null) {
                        Subjects = new HashMap<>();
                    }
                    Subjects.put(subject, timeout);
                }
                return Subjects;
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

    public String getIdSubject(String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String id = "";
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id "
                        + "from subject "
                        + "where nameSubject = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, subject);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getString("id");
                    return id;
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

    public int countSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(id) "
                        + "from subject ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public int countSubjectSearch(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(DISTINCT a.id) "
                        + "from subject a join question b on a.id = b.subject_id "
                        + "where b.questionContent like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public subjectDTO subjectPaging(int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, nameSubject, timeout "
                        + "from subject "
                        + "order by id ASC "
                        + "offset ? rows "
                        + "fetch next 1 rows only";
                pst = con.prepareStatement(sql);
                pst.setInt(1, index);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    int timeout = rs.getInt(3);
                    subjectDTO dto = new subjectDTO(id, name, timeout);
                    return dto;
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

    public subjectDTO getSubjectSearch(String nameSub) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, nameSubject, timeout "
                        + "from subject "
                        + "where nameSubject = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, nameSub);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    int timeout = rs.getInt(3);
                    subjectDTO dto = new subjectDTO(id, name, timeout);
                    return dto;
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

    public int countSubjectStatus(boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(DISTINCT a.id) \n"
                        + "from subject a join question b on a.id = b.subject_id \n"
                        + "where b.status = ?";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public subjectDTO subjectPagingStatus(int index, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select distinct a.id, nameSubject, timeout "
                        + "from subject a join question b on a.id = b.subject_id "
                        + "where b.status = ? "
                        + "order by a.id ASC "
                        + "offset ? rows "
                        + "fetch next 1 rows only";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setInt(2, index);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    int timeout = rs.getInt(3);
                    subjectDTO dto = new subjectDTO(id, name, timeout);
                    return dto;
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

    public subjectDTO subjectPagingSearch(int index, String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select distinct a.id, nameSubject, timeout "
                        + "from subject a join question b on a.id = b.subject_id "
                        + "where b.questionContent like ? "
                        + "order by a.id ASC "
                        + "offset ? rows "
                        + "fetch next 1 rows only";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setInt(2, index);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    int timeout = rs.getInt(3);
                    subjectDTO dto = new subjectDTO(id, name, timeout);
                    return dto;
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

    public int getTimeOut(String name) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select timeout "
                        + "from subject "
                        + "where nameSubject = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int timeout = rs.getInt(1);
                    return timeout;
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
        return 0;
    }

    public String getNameSub(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select nameSubject\n"
                        + "from subject\n"
                        + "where id = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                if(rs.next()){
                    String nameSub = rs.getString(1);
                    return nameSub;
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
