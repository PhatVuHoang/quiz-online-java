/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.account.accountDTO;
import phatvh.history.historyDAO;
import phatvh.question.questionDAO;
import phatvh.question.questionDTO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

    private final String TAKE_QUIZ = "takequiz.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = TAKE_QUIZ;

        HttpSession session = request.getSession();
        String subject = request.getParameter("cbSubject");
        if (subject == null) {
            subject = (String) session.getAttribute("SUBJECT");
        } else {
            session.setAttribute("SUBJECT", subject);
        }
        try {
            subjectDAO subDAO = new subjectDAO();
            questionDAO questDAO = new questionDAO();
            historyDAO history = new historyDAO();
            accountDTO account = (accountDTO) session.getAttribute("ACCOUNT");
            String idSubject = subDAO.getIdSubject(subject);
            ArrayList<questionDTO> listQuestion = (ArrayList<questionDTO>) session.getAttribute("LISTQUEST");
            if (listQuestion == null) {
                listQuestion = questDAO.getQuestRand(idSubject);
            }
            String check = (String) session.getAttribute("HISTORY");
            if (check == null) {
                history.addHistory(account.getName(), account.getEmail(), idSubject);
                String idHistory = history.idHistory();
                for (questionDTO dto : listQuestion) {
                    history.addHistoryDetail(idHistory, dto.getId(), dto.getQuestion(), dto.getAns());
                }
                session.setAttribute("HISTORY", "added");
                session.setAttribute("IDHISTORY", idHistory);
                session.setAttribute("USERANS", "");
            }
            int quest = 1;
            if (request.getParameter("index") == null) {
                quest = 1;
            } else {
                quest = Integer.parseInt(request.getParameter("index"));
            }
            String idQuest = listQuestion.get(quest - 1).getId();
            String idHistory = (String) session.getAttribute("IDHISTORY");
            String ans = history.getAnsHistory(idQuest, idHistory);
            if (ans != null) {
                session.setAttribute("USERANS", ans);
            } else {
                session.setAttribute("USERANS", "");
            }
            
            
            //time out
            String timeNow = (String) session.getAttribute("BEGINQUIZ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            if (timeNow == null) {
                timeNow = dateFormat.format(cal.getTime());
                session.setAttribute("BEGINQUIZ", timeNow);
            }
            String timeEnd = (String) session.getAttribute("ENDQUIZ");
            int timeout = subDAO.getTimeOut(subject);
            if (timeEnd == null) {
                cal.add(Calendar.MINUTE, timeout);
                timeEnd = dateFormat.format(cal.getTime());
                session.setAttribute("ENDQUIZ", timeEnd);
            }
            Date end = dateFormat.parse(timeEnd);
            Date now = new Date();

            long time = end.getTime() - now.getTime();
            long second = (time / 1000 % 60) + 1;
            long min = time / (60 * 1000) % 60;
            long hour = time / (60 * 60 * 1000);

            session.setAttribute("HOUR", hour);
            session.setAttribute("MINUTE", min);
            session.setAttribute("SECOND", second);
            questionDTO questionShow = listQuestion.get(quest - 1);
            session.setAttribute("QUESTDISPLAY", questionShow);
            session.setAttribute("LISTQUEST", listQuestion);
            session.setAttribute("SUBJECT", subject);
            session.setAttribute("INDEX", quest);
        } catch (SQLException e) {
            log("SQL_QuizServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_QuizServlet: " + e.getMessage());
        } catch (Exception e){
            
        } 
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
