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
import phatvh.account.accountErrDTO;
import phatvh.subject.subjectDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private final String SIGN_UP_PAGE = "signup.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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

        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");

        String url = SIGN_UP_PAGE;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String pass = DatatypeConverter.printHexBinary(hash);

            boolean foundErr = false;
            accountErrDTO error = new accountErrDTO();
            accountDAO dao = new accountDAO();

            if (email.isEmpty()) {
                foundErr = true;
                error.setEmailEmpty("Email is empty");
            } else if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                foundErr = true;
                error.setEmailFormat("Email invalid");
            } else if (email.equals(dao.getEmail(email))) {
                foundErr = true;
                error.setEmailIsExisted("Email is existed");
            }

            if (name.isEmpty()) {
                foundErr = true;
                error.setNameEmpty("Name is empty");
            } else if (name.trim().length() > 50) {
                foundErr = true;
                error.setNameFormat("Name only have 50 characters");
            }

            if (password.isEmpty()) {
                foundErr = true;
                error.setPasswordEmpty("Password is empty");
            } else if (password.trim().length() < 5 || password.trim().length() > 20) {
                foundErr = true;
                error.setPasswordFormat("Password must be have 8 - 20 characters");
            }

            if (foundErr) {
                request.setAttribute("ERRORS", error);
            } else {
                url = LOGIN_PAGE;
                dao.createAccount(email, name, pass);
                HttpSession session = request.getSession();
                session.setAttribute("FULLNAME", name);
                subjectDAO subject = new subjectDAO();
                Map<String, Integer> subjects = subject.getSubject();
                session.setAttribute("SUBJECTS", subjects);
            }
        } catch (SQLException e) {
            log("SQL_SignupServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("Naming_SignupServlet: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("NoSuchAlgorithmException_SignupServlet: " + e.getMessage());
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
