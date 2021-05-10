/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "AddQuestServlet", urlPatterns = {"/AddQuestServlet"})
public class AddQuestServlet extends HttpServlet {

    private final String ADD_PAGE = "add.jsp";

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

        String url = ADD_PAGE;

        String subject = request.getParameter("cbSubject");
        String quest = request.getParameter("txtQuestion");
        String option_1 = request.getParameter("txtOption1");
        String option_2 = request.getParameter("txtOption2");
        String option_3 = request.getParameter("txtOption3");
        String option_4 = request.getParameter("txtOption4");
        String ans = request.getParameter("txtAnswer");
        ArrayList<String> listOption = new ArrayList<>();
        listOption.add(option_1);
        listOption.add(option_2);
        listOption.add(option_3);
        listOption.add(option_4);
        try {
            HttpSession session = request.getSession();
            boolean foundErr = false;
            questionErrDTO error = new questionErrDTO();
            if (subject.equals("--Subject--")) {
                foundErr = true;
                error.setSubjectEmpty("please select subject");
            }
            if (quest.isEmpty()) {
                foundErr = true;
                error.setQuestionEmpty("question is empty");
            }
            if (option_1.isEmpty()) {
                foundErr = true;
                error.setOp1Empty("option 1 is empty");
            } else {
                for (int i = 0; i < listOption.size(); i++) {
                    for (int j = i + 1; j < listOption.size(); j++) {
                        if (listOption.get(i).equals(listOption.get(j))) {
                            foundErr = true;
                            error.setOpDuplicate("Options duplicate!");
                            break;
                        }
                    }
                }
            }
            if (option_2.isEmpty()) {
                foundErr = true;
                error.setOp2Empty("option 2 is empty");
            } else {
                for (int i = 0; i < listOption.size(); i++) {
                    for (int j = i + 1; j < listOption.size(); j++) {
                        if (listOption.get(i).equals(listOption.get(j))) {
                            foundErr = true;
                            error.setOpDuplicate("Options duplicate!");
                            break;
                        }
                    }
                }
            }
            if (option_3.isEmpty()) {
                foundErr = true;
                error.setOp3Empty("option 3 is empty");
            } else {
                for (int i = 0; i < listOption.size(); i++) {
                    for (int j = i + 1; j < listOption.size(); j++) {
                        if (listOption.get(i).equals(listOption.get(j))) {
                            foundErr = true;
                            error.setOpDuplicate("Options duplicate!");
                            break;
                        }
                    }
                }
            }
            if (option_4.isEmpty()) {
                foundErr = true;
                error.setOp4Empty("option 4 is empty");
            } else {
                for (int i = 0; i < listOption.size(); i++) {
                    for (int j = i + 1; j < listOption.size(); j++) {
                        if (listOption.get(i).equals(listOption.get(j))) {
                            foundErr = true;
                            error.setOpDuplicate("Options duplicate!");
                            break;
                        }
                    }
                }
            }
            if (ans.isEmpty()) {
                foundErr = true;
                error.setAnsEmpty("answer is empty");
            } else {
                boolean flag = true;
                for (int i = 0; i < listOption.size(); i++) {
                    if (ans.equals(listOption.get(i))) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    foundErr = true;
                    error.setAnsNotMatch("Answer must match 1 of the options");
                }
            }
            if (foundErr) {
                request.setAttribute("ADDERROR", error);
                request.setAttribute("QUEST", quest);
                request.setAttribute("OP1", option_1);
                request.setAttribute("OP2", option_2);
                request.setAttribute("OP3", option_3);
                request.setAttribute("OP4", option_4);
                request.setAttribute("ANS", ans);
            } else {
                questionDAO questDAO = new questionDAO();
                subjectDAO subDAO = new subjectDAO();
                String idSubject = subDAO.getIdSubject(subject);
                questDAO.addQuestion(quest, option_1, option_2, option_3, option_4, ans, idSubject);
            }
        } catch (SQLException e) {
            log("SQL_AddQuestServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_AddQuestServlet: " + e.getMessage());
        } finally {
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
