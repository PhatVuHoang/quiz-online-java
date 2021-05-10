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
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.question.questionDAO;
import phatvh.question.questionErrDTO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String UPDATE_PAGE = "update.jsp";

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
        String id = request.getParameter("txtID");
        String quest = request.getParameter("txtQuest");
        String op1 = request.getParameter("txtOp1");
        String op2 = request.getParameter("txtOp2");
        String op3 = request.getParameter("txtOp3");
        String op4 = request.getParameter("txtOp4");
        String ans = request.getParameter("txtAns");
        String status = request.getParameter("cbStatusUpdate");
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        String updateDate = formatDate.format(new Date());
        HttpSession session = request.getSession();
        String nameSub = (String) session.getAttribute("NAMESUBPAGE");
        String searchValue = request.getParameter("txtSearch");
        String subjectValue = request.getParameter("cbSubjects");
        String statusValue = request.getParameter("cbStatus");
        String indexQuest = request.getParameter("indexPageQuest");
        String index = request.getParameter("indexPage");
        if (index == null || index.equals("")) {
            index = "1";
        }
        if (indexQuest == null || indexQuest.equals("")) {
            indexQuest = "1";
        }
        boolean statusUpdate = false;
        if (status.equals("activate")) {
            statusUpdate = true;
        }
        String url = UPDATE_PAGE;
        try {
            //Check đáp án bị trùng và ans không trùng với đáp án
            boolean foundErr = false;
            questionErrDTO error = new questionErrDTO();
            ArrayList<String> options = new ArrayList<>();
            options.add(op1);
            options.add(op2);
            options.add(op3);
            options.add(op4);
            for (int i = 0; i < options.size(); i++) {
                for (int j = i + 1; j < options.size(); j++) {
                    if (options.get(i).equals(options.get(j))) {
                        foundErr = true;
                        error.setOpDuplicate("Options duplicate!");
                    }
                }
            }
            boolean flag = true;
            for (int i = 0; i < options.size(); i++) {
                if (ans.equals(options.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                foundErr = true;
                error.setAnsNotMatch("Answer must match 1 of the options");
            }
            session.setAttribute("IDQUEST", id);
            if (foundErr) {
                session.setAttribute("ADDERROR", error);
            } else {
                // Update câu hỏi
                error.setAnsNotMatch("");
                error.setOpDuplicate("");
                session.setAttribute("ADDERROR", error);
                subjectDAO subDAO = new subjectDAO();
                questionDAO questDAO = new questionDAO();
                String idSub = subDAO.getIdSubject(nameSub);
                questDAO.updateQuestion(id, quest, op1, op2, op3, op4, ans, idSub, statusUpdate, updateDate);

                // load lại trang
                if (searchValue == null) {
                    searchValue = (String) session.getAttribute("SEARCHVALUE");
                }
                if (subjectValue == null) {
                    subjectValue = (String) session.getAttribute("SUBJECTVALUE");
                }
                if (statusValue == null) {
                    statusValue = (String) session.getAttribute("STATUSVALUE");
                }
                if (searchValue != null && !searchValue.equals("")) {
                    url = "searchUpdate?index=" + index;
                    session.setAttribute("SEARCHVALUE", searchValue);
                } else if (subjectValue != null && !subjectValue.equals("")) {
                    url = "searchSubjectUpdate?indexQuest=" + indexQuest;
                    session.setAttribute("SUBJECTVALUE", subjectValue);
                } else if (statusValue != null && !statusValue.equals("")) {
                    url = "searchStatusUpdate?index=" + index;
                    session.setAttribute("STATUSVALUE", statusValue);
                } else {
                    url = "showAllUpdate?index=" + index;
                }
            }
        } catch (SQLException e) {
            log("SQL_DeleteServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_DeleteServlet: " + e.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
              response.sendRedirect(url);
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
