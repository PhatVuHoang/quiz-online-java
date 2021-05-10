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
@WebServlet(name = "ShowAllUpdateServlet", urlPatterns = {"/ShowAllUpdateServlet"})
public class ShowAllUpdateServlet extends HttpServlet {
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
        String index = request.getParameter("index");
        String url = UPDATE_PAGE;
        if (index == null || index.equals("")) {
            index = "1";
        }
        try {
            HttpSession session = request.getSession();
            session.setAttribute("SEARCHVALUE", "");
            session.setAttribute("SUBJECTVALUE", "");
            session.setAttribute("STATUSVALUE", "");
            session.setAttribute("SHOWALL", "show");
            subjectDAO subDAO = new subjectDAO();
            questionDAO questDAO = new questionDAO();
            int pageCurrent = Integer.parseInt(index) - 1;
            int numPage = subDAO.countSubject();
            session.setAttribute("NUMPAGE", numPage);
            String nameSub = subDAO.subjectPaging(pageCurrent).getNameSubject();
            session.setAttribute("NAMESUBPAGE", nameSub);

            String idSub = subDAO.getIdSubject(nameSub);
            ArrayList<questionDTO> listQuest = questDAO.getQuestion(idSub);
            session.setAttribute("LISTQUEST", listQuest);
        } catch (SQLException e) {
            log("SQL_ShowAllServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_ShowAllServlet: " + e.getMessage());
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
