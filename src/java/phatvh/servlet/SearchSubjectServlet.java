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
import phatvh.question.questionDTO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchSubjectServlet", urlPatterns = {"/SearchSubjectServlet"})
public class SearchSubjectServlet extends HttpServlet {

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
        String searchValue = request.getParameter("cbSubjects");
        
        String url = SEARCH_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute("STATUSVALUE", "");
        session.setAttribute("SEARCHVALUE", "");
        session.setAttribute("SHOWALL", "");
        String indexQuest = request.getParameter("indexQuest");
        if (indexQuest == null || indexQuest.equals("")) {
            indexQuest = "1";
        }
        if(searchValue == null) {
            searchValue = (String) session.getAttribute("SUBJECTVALUE");
        } else {
            session.setAttribute("SUBJECTVALUE", searchValue);
        }
        
        try {
            subjectDAO subDAO = new subjectDAO();
            questionDAO questDAO = new questionDAO();
            session.setAttribute("NAMESUBPAGE", searchValue);

            String idSub = subDAO.getIdSubject(searchValue);
            int count = questDAO.countQuest(idSub);
            int numPageQuest = count / 20;
            if (count % 20 != 0) {
                numPageQuest++;
            }
            session.setAttribute("NUMPAGEQUEST", numPageQuest);
            int pageQuestCurrent = (Integer.parseInt(indexQuest) - 1) * 20;
            ArrayList<questionDTO> listQuest = questDAO.getQuestSubject(idSub, pageQuestCurrent);
            session.setAttribute("LISTQUEST", listQuest);

        } catch(SQLException e){
            log("SQL_SearchSubject: " + e.getMessage());
        } catch(NamingException e){
            log("Naming_SearchSubject: " + e.getMessage());
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
