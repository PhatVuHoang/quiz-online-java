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
import phatvh.subject.subjectDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        String searchValue = request.getParameter("txtSearch");
        String index = request.getParameter("index");
        HttpSession session = request.getSession();
        session.setAttribute("SUBJECTVALUE", "");
        session.setAttribute("STATUSVALUE", "");
        session.setAttribute("SHOWALL", "");
        if (index == null || index.equals("")) {
            index = "1";
        }
        if (searchValue == null) {
            searchValue = (String) session.getAttribute("SEARCHVALUE");
        } else {
            session.setAttribute("SEARCHVALUE", searchValue);
        }
        String url = SEARCH_PAGE;
        try {
            subjectDAO subDAO = new subjectDAO();
            questionDAO questDAO = new questionDAO();
            if (searchValue != null && !searchValue.equals("")) {
                int pageCurrent = Integer.parseInt(index) - 1;
                int numPage = subDAO.countSubjectSearch(searchValue);
                session.setAttribute("NUMPAGE", numPage);
                subjectDTO subject = subDAO.subjectPagingSearch(pageCurrent, searchValue);
                ArrayList<questionDTO> listQuest = new ArrayList();
                if (subject != null) {
                    String nameSub = subject.getNameSubject();
                    session.setAttribute("NAMESUBPAGE", nameSub);
                    String idSub = subDAO.getIdSubject(nameSub);
                    listQuest = questDAO.getQuestionSearch(searchValue, idSub);
                }
                session.setAttribute("LISTQUEST", listQuest);
            }
        } catch (SQLException e) {
            log("SQL_SearchServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_SearchServlet: " + e.getMessage());
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
