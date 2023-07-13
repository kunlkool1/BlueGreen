package controller;

import dao.AccountDAO;
import dao.ProductDAO;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        req.setAttribute("cateList", dao.getAllCategory());
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("txtUser");
        String email = req.getParameter("txtEmail");
        AccountDAO dao = new AccountDAO();
        
        // Save email on form        
        req.setAttribute("Email", email);
        if(dao.checkExistAccount(user)){
            req.setAttribute("errorUser", "<div>\n"
            + "          <div class=\"alert alert-danger mb-0 mt-3\" role=\"alert\">\n"
            + "                        Username is already exist!\n"
            + "            </div>\n"
            + "  </div>");
            doGet(req, resp);
        }
        else{
            String pass = req.getParameter("txtPass");
            String confirm = req.getParameter("txtCfmPass");
            

            if(pass.equals(confirm)){
                Account account = new Account(user, pass, email, false, "", "", "", "");
                dao.addAccount(account);
                resp.sendRedirect("login");
            }else{
                req.setAttribute("User", user);
                req.setAttribute("errorPass", "<div>\n"
                + "          <div class=\"alert alert-danger mb-0 mt-3\" role=\"alert\">\n"
                + "                        Incorrect password!\n"
                + "            </div>\n"
                + "  </div>");
                doGet(req, resp);
            }
        }
    }
    
}
