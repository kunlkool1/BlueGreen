package controller.admin;

import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MngAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        req.setAttribute("accList", dao.getAllAccount());
        req.getRequestDispatcher("mngaccount.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        AccountDAO dao = new AccountDAO();

        
        if (action.equals("add")) {
            String username = req.getParameter("txtUser");
            String password = req.getParameter("txtPass");
            boolean isAdmin = req.getParameter("checkAdmin") == null ? false : true;
            String email = req.getParameter("txtEmail");
            
            if (!dao.checkExistAccount(username)) {
                Account acc = new Account(username, password, email, isAdmin, "", "", "", "");
                dao.addAccount(acc);
            } else {
                req.setAttribute("error", "<div class=\"field\">\n"
                        + "          <div class=\"alert alert-danger mb-0\" role=\"alert\">\n"
                        + "                        Username is already exist!\n"
                        + "            </div>\n"
                        + "  </div>");
                req.setAttribute("open", "open");
            }
            
        }
        
        if (action.equals("delete")) {
            String username = req.getParameter("user");
            System.out.println(username);
            dao.deleteAccount(username);
        }
        
        doGet(req, resp);
    }
    
}
