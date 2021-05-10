/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.history;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import phatvh.question.questionDTO;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class historyDAO implements Serializable {

    public void addHistory(String username, String mail, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into history(userName, mail, subject_id) "
                        + "values(?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, mail);
                pst.setString(3, subjectID);
                pst.executeUpdate();
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

    public String idHistory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select max(id) "
                        + "from history ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
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

    public void addHistoryDetail(String historyID, String questionID, String question, String correctAns)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into history_detail(history_id, question_id, question, correct_answer) "
                        + "values(?,?,?,?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, historyID);
                pst.setString(2, questionID);
                pst.setString(3, question);
                pst.setString(4, correctAns);
                pst.executeUpdate();
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

    public void addAns(String userAns, String historyId, String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update history_detail "
                        + "set user_answer = ? "
                        + "where history_id = ? and question_id = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, userAns);
                pst.setString(2, historyId);
                pst.setString(3, questionID);
                pst.executeUpdate();
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

    Map<String, String> historyQuest;

    public int getIdHistory(String question) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select Max(id) "
                        + "from history "
                        + "where question = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, question);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
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
        return 0;
    }

    public String getAnsHistory(String id, String idHistory) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select user_answer "
                        + "from history_detail "
                        + "where question_id = ? and history_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, idHistory);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userAns = rs.getString(1);
                    return userAns;
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

    ArrayList<historyDTO> list;

    public ArrayList<historyDTO> getHistory(String historyID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select question, correct_answer, user_answer, history_id "
                        + "from history_detail "
                        + "where history_id = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, historyID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question = rs.getString(1);
                    String correctAns = rs.getString(2);
                    String userAns = rs.getString(3);
                    String historyId = rs.getString(4);
                    historyDTO dto = new historyDTO(question, correctAns, userAns, historyId);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
                return list;
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

    public void updateScore(String id, float score) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update history "
                        + "set score = ? "
                        + "where id = ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, score);
                pst.setString(2, id);
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

    ArrayList<historyDTO> listHistory;

    public ArrayList<historyDTO> loadHistory(String historyID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select a.question, a.correct_answer, a.user_answer, a.history_id "
                        + "from history_detail a join history b on a.history_id = b.id "
                        + "where b.id = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, historyID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question = rs.getString(1);
                    String correctAns = rs.getString(2);
                    String userAns = rs.getString(3);
                    String histID = rs.getString(4);
                    historyDTO dto = new historyDTO(question, correctAns, userAns, histID);
                    if (listHistory == null) {
                        listHistory = new ArrayList<>();
                    }
                    listHistory.add(dto);
                }
                return listHistory;
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
    ArrayList<historyDTO2> listKey;

    public ArrayList<historyDTO2> getKeyHistory(String username, String subjectID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, quiz_date, score "
                        + "from history "
                        + "where userName = ? and subject_id = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, username);
                pst.setString(2, subjectID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String date = rs.getString(2);
                    float score = rs.getFloat(3);
                    historyDTO2 dto = new historyDTO2(date, score, id);
                    if (listKey == null) {
                        listKey = new ArrayList<>();
                    }
                    listKey.add(dto);
                }
                return listKey;
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

    public int countSubHistory(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(distinct subject_id)\n"
                        + "from history\n"
                        + "where userName = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
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

    public String getIDSub(String username, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select distinct subject_id "
                        + "from history\n"
                        + "where userName = ? "
                        + "order by subject_id "
                        + "offset ? rows "
                        + "fetch next 1 rows only ";
                pst = con.prepareCall(sql);
                pst.setString(1, username);
                pst.setInt(2, index);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String subjectID = rs.getString(1);
                    return subjectID;
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
    ArrayList<String> subid;

    public ArrayList<String> getSubIdHist(String name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select distinct subject_id\n"
                        + "from history\n"
                        + "where userName = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, name);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    if (subid == null) {
                        subid = new ArrayList<>();
                    }
                    subid.add(id);
                }
                return subid;
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

    public ArrayList<historyDTO2> getSearch(String nameSub, String name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<historyDTO2> listSearch = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select a.score, a.quiz_date, a.id "
                        + "from history a join subject b on a.subject_id = b.id "
                        + "where b.nameSubject= ? and userName = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, nameSub);
                pst.setString(2, name);
                rs = pst.executeQuery();
                while (rs.next()) {
                    float score = rs.getFloat(1);
                    String date = rs.getString(2);
                    String id = rs.getString(3);
                    historyDTO2 dto = new historyDTO2(date, score, id);
                    if (listSearch == null) {
                        listSearch = new ArrayList<>();
                    }
                    listSearch.add(dto);
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

        return listSearch;
    }

}
