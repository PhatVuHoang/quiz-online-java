/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class questionDAO implements Serializable {

    public void addQuestion(String question, String op1, String op2, String op3, String op4, String ans, String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean status = true;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into question(questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer,status,subject_id) "
                        + "values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, question);
                pst.setString(2, op1);
                pst.setString(3, op2);
                pst.setString(4, op3);
                pst.setString(5, op4);
                pst.setString(6, ans);
                pst.setBoolean(7, status);
                pst.setString(8, subject);
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

    public void updateQuestion(String id, String question, String op1, String op2, String op3, String op4, String ans, String subject, boolean statusUpdate, String updateDate)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update question "
                        + "set questionContent = ?, answer_1 = ?, answer_2 = ?, answer_3 = ?, answer_4 = ?, correct_answer = ?, status = ?, subject_id = ?, update_date = ? "
                        + "where id = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, question);
                pst.setString(2, op1);
                pst.setString(3, op2);
                pst.setString(4, op3);
                pst.setString(5, op4);
                pst.setString(6, ans);
                pst.setBoolean(7, statusUpdate);
                pst.setString(8, subject);
                pst.setString(9, updateDate);
                pst.setString(10, id);
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

    public void deleteQuestion(String quest, String subjectID, String updateDate)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        boolean status = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update question "
                        + "set status = ?, update_date = ? "
                        + "where questionContent = ? and subject_id = ? ";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setString(2, updateDate);
                pst.setString(3, quest);
                pst.setString(4, subjectID);
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

//    SHOW PAGE
    private ArrayList<questionDTO> questionsShow;

    public ArrayList<questionDTO> getQuestion(String subjectIDQuest) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer, status, subject_id "
                        + "from question "
                        + "where subject_id = ? "
                        + "order by questionContent ASC ";
                pst = con.prepareStatement(sql);
                pst.setString(1, subjectIDQuest);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String quest = rs.getString("questionContent");
                    String op1 = rs.getString("answer_1");
                    String op2 = rs.getString("answer_2");
                    String op3 = rs.getString("answer_3");
                    String op4 = rs.getString("answer_4");
                    String ans = rs.getString("correct_answer");
                    boolean status = rs.getBoolean("status");
                    String subjectID = rs.getString("subject_id");
                    if (questionsShow == null) {
                        questionsShow = new ArrayList<>();
                    }
                    questionDTO dto = new questionDTO(id, quest, op1, op2, op3, op4, ans, subjectID, status);
                    questionsShow.add(dto);
                }
                return questionsShow;
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

    private ArrayList<questionDTO> questions;

    public ArrayList<questionDTO> getQuestionSearch(String searchValue, String subjectIDQuest) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, questionContent, answer_1, answer_2, answer_3, answer_4, correct_answer, status, subject_id "
                        + "from question "
                        + "where questionContent like ? and subject_id = ? "
                        + "order by questionContent ASC ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, subjectIDQuest);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String quest = rs.getString("questionContent");
                    String op1 = rs.getString("answer_1");
                    String op2 = rs.getString("answer_2");
                    String op3 = rs.getString("answer_3");
                    String op4 = rs.getString("answer_4");
                    String ans = rs.getString("correct_answer");
                    boolean status = rs.getBoolean("status");
                    String subjectID = rs.getString("subject_id");
                    if (questions == null) {
                        questions = new ArrayList<>();
                    }
                    questionDTO dto = new questionDTO(id, quest, op1, op2, op3, op4, ans, subjectID, status);
                    questions.add(dto);
                }
                return questions;
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

    public int countQuestSearch(String searchValue, String subID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(id) "
                        + "from question "
                        + "where questionContent like ? and subject_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, subID);
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

    public int countQuest(String subID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select count(id) "
                        + "from question "
                        + "where subject_id = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, subID);
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

    private ArrayList<questionDTO> quests;

    public ArrayList<questionDTO> getQuestSubject(String subjectID, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id, questionContent,answer_1,answer_2,answer_3,answer_4,correct_answer,status "
                        + "from question "
                        + "where subject_id = ? "
                        + "order by questionContent ASC "
                        + "offset ? rows "
                        + "fetch next 20 rows only ";
                pst = con.prepareStatement(sql);
                pst.setString(1, subjectID);
                pst.setInt(2, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String quest = rs.getString(2);
                    String op1 = rs.getString(3);
                    String op2 = rs.getString(4);
                    String op3 = rs.getString(5);
                    String op4 = rs.getString(6);
                    String ans = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    questionDTO question = new questionDTO(id, quest, op1, op2, op3, op4, ans, subjectID, status);
                    if (quests == null) {
                        quests = new ArrayList();
                    }
                    quests.add(question);
                }
                return quests;
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

    private ArrayList<questionDTO> questsStatus;

    public ArrayList<questionDTO> getQuestStatus(boolean status, String subID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id,questionContent,answer_1,answer_2,answer_3,answer_4,correct_answer,status,subject_id "
                        + "from question "
                        + "where status = ? and subject_id = ? "
                        + "order by questionContent ASC ";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setString(2, subID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String quest = rs.getString(2);
                    String op1 = rs.getString(3);
                    String op2 = rs.getString(4);
                    String op3 = rs.getString(5);
                    String op4 = rs.getString(6);
                    String ans = rs.getString(7);
                    boolean status_quest = rs.getBoolean(8);
                    String subjectID = rs.getString(9);
                    questionDTO question = new questionDTO(id, quest, op1, op2, op3, op4, ans, subID, status);
                    if (questsStatus == null) {
                        questsStatus = new ArrayList();
                    }
                    questsStatus.add(question);
                }
                return questsStatus;
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

    // take quiz
    ArrayList<questionDTO> listID;

    public ArrayList<questionDTO> getQuestRand(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select top 20 id,questionContent,answer_1,answer_2,answer_3,answer_4,correct_answer,status,subject_id "
                        + "from question "
                        + "where subject_id = ? "
                        + "order by newid() ";
                pst = con.prepareStatement(sql);
                pst.setString(1, subjectID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String questtion = rs.getString(2);
                    String op1 = rs.getString(3);
                    String op2 = rs.getString(4);
                    String op3 = rs.getString(5);
                    String op4 = rs.getString(6);
                    String ans = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    String subID = rs.getString(9);
                    questionDTO dto = new questionDTO(id, questtion, op1, op2, op3, op4, ans, subjectID, status);
                    if (listID == null) {
                        listID = new ArrayList<>();
                    }
                    listID.add(dto);
                }
                return listID;
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
