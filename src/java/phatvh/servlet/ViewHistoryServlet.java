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
import phatvh.account.accountDTO;
import phatvh.history.historyDAO;
import phatvh.history.historyDTO2;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ViewHistoryServlet", urlPatterns = {"/ViewHistoryServlet"})
public class ViewHistoryServlet extends HttpServlet {

    private final String HISTORY_PAGE = "history.jsp";

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
        String url = HISTORY_PAGE;
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        try {
            HttpSession session = request.getSession();
            accountDTO account = (accountDTO) session.getAttribute("ACCOUNT");
            historyDAO hisDAO = new historyDAO();
            int countSub = hisDAO.countSubHistory(account.getName());
            session.setAttribute("HISTORYPAGE", countSub);
            int page = Integer.parseInt(index) - 1;
            String idSub = hisDAO.getIDSub(account.getName(), page);
            subjectDAO subDAO = new subjectDAO();
            String nameSub = subDAO.getNameSub(idSub);
            session.setAttribute("NAMESUBHISTORY", nameSub);
            //load
            ArrayList<historyDTO2> listKey = hisDAO.getKeyHistory(account.getName(), idSub);
            ArrayList<String> listIdSub = hisDAO.getSubIdHist(account.getName());
            ArrayList<String> listSub  = new ArrayList<>();
            for (String string : listIdSub) {
                String namesub = subDAO.getNameSub(string);
                listSub.add(namesub);
            }
            session.setAttribute("LISTSUB", listSub);
            session.setAttribute("LISTHIST", listKey);
        } catch (SQLException e) {
            log("SQL_ViewHistory: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_ViewHistory: " + e.getMessage());
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
