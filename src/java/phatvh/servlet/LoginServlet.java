/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import phatvh.account.accountDAO;
import phatvh.account.accountDTO;
import phatvh.question.questionDAO;
import phatvh.question.questionDTO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    private final String MAIN_PAGE = "main.jsp";

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
        String username = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = LOGIN_PAGE;
        try {
//          Đổi qua hex
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String pass = DatatypeConverter.printHexBinary(hash);
            HttpSession session = request.getSession();
            accountDAO dao = new accountDAO();
            accountDTO account = dao.checkLogin(username, pass);
            session.setAttribute("ACCOUNT", account);
            if (account != null) {
                session.setAttribute("FULLNAME", account.getName());
                String role;
                if (account.isRole()) {
                    role = "admin";
                    url = SEARCH_PAGE;
                    session.setAttribute("PERMISSION", role);
                    // phan trang cho lan dau tien
                    int index = 1;
                    int pageCurrent = index - 1;
                    session.setAttribute("SEARCHVALUE", "");
                    session.setAttribute("SUBJECTVALUE", "");
                    session.setAttribute("STATUSVALUE", "");
                    subjectDAO subDAO = new subjectDAO();
                    int numPage = subDAO.countSubject();
                    session.setAttribute("NUMPAGE", numPage);
                    String nameSub = subDAO.subjectPaging(pageCurrent).getNameSubject();
                    session.setAttribute("NAMESUBPAGE", nameSub);

                    String idSub = subDAO.getIdSubject(nameSub);
                    questionDAO questDAO = new questionDAO();
                    ArrayList<questionDTO> listQuest = questDAO.getQuestion(idSub);
                    session.setAttribute("LISTQUEST", listQuest);
                    session.setAttribute("SHOWALL", "show");
                } else {
                    role = "";
                    session.setAttribute("PERMISSION", role);
                    url = MAIN_PAGE;
                }
            } else {
                request.setAttribute("INVALIDLOGIN", "Email or password is invalid");
            }
            subjectDAO subject = new subjectDAO();
            Map<String, Integer> subjects = subject.getSubject();
            session.setAttribute("SUBJECTS", subjects);
        } catch (SQLException e) {
            log("SQL_LoginServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_LoginServlet: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("NoSuchAlgorithmException_LoginServlet: " + e.getMessage());
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
