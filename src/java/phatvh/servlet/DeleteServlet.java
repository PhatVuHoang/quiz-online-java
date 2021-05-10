/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.question.questionDAO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";

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
        String quest = request.getParameter("txtQuest");
        SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
        String updateDate = formatDate.format(new Date());
        HttpSession session = request.getSession();
        String nameSub = (String) session.getAttribute("NAMESUBPAGE");
        String searchValue = request.getParameter("txtSearch");
        String subjectValue = request.getParameter("cbSubjects");
        String statusValue = request.getParameter("cbStatus");
        String indexQuest = request.getParameter("indexPageQuest");
        String index = request.getParameter("indexPage");
        if (index == null) {
            index = "1";
        }
        if (indexQuest == null) {
            indexQuest = "1";
        }
        String url = SEARCH_PAGE;
        try {
            // xóa câu hỏi
            subjectDAO subDAO = new subjectDAO();
            questionDAO questDAO = new questionDAO();
            String idSub = subDAO.getIdSubject(nameSub);
            questDAO.deleteQuestion(quest, idSub, updateDate);

            // load lại trang
            if(searchValue == null) {
                searchValue = (String) session.getAttribute("SEARCHVALUE");
            }
            if(subjectValue == null) {
                subjectValue = (String) session.getAttribute("SUBJECTVALUE");
            }
            if(statusValue == null) {
                statusValue = (String) session.getAttribute("STATUSVALUE");
            }
            if (searchValue != null && !searchValue.equals("")) {
                url = "search?index=" + index;
                session.setAttribute("SEARCHVALUE", searchValue);
            } else if (subjectValue != null && !subjectValue.equals("")) {
                url = "searchSubject?indexQuest=" + indexQuest;
                session.setAttribute("SUBJECTVALUE", subjectValue);
            } else if (statusValue != null && !statusValue.equals("")) {
                url = "searchStatus?index=" + index;
            } else {
                url = "showAll?index=" + index;
            }
        } catch (SQLException e) {
            log("SQL_DeleteServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_DeleteServlet: " + e.getMessage());
        } finally {
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
