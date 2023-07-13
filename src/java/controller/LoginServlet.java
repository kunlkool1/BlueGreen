package controller;

import dao.ProductDAO;
import dao.AccountDAO;
import dao.CartDAO;
import entity.Account;
import entity.Cart;
import entity.Item;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        req.setAttribute("cateList", dao.getAllCategory());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = req.getParameter("txtUser").toLowerCase();
        String pass = req.getParameter("txtPass");
        AccountDAO dao = new AccountDAO();
        Account account = dao.login(user, pass);
        if (account != null) {
            Cookie cUser = new Cookie("cUser", user);
            Cookie cPass = new Cookie("cPass", pass);

            if (req.getParameter("remem") == null) {
                cUser.setMaxAge(0);
                cPass.setMaxAge(0);
            } else {
                cUser.setMaxAge(60 * 60 * 24 * 30);//30 days
                cPass.setMaxAge(60 * 60 * 24 * 30);

                resp.addCookie(cUser);
                resp.addCookie(cPass);
            }

            session.setAttribute("acc", account);
            CartDAO cDao = new CartDAO();
            
            Cart cart = cDao.getCart(account);
            if (cart == null) {
                cart = new Cart();
            }
            session.setAttribute("cart", cart);
            
            Item item = (Item) session.getAttribute("item");
            if (item != null) {
                cart.addItem(item);
                cDao.addCart(cart, account);
                session.removeAttribute("item");
                resp.sendRedirect("cart");
            } else {
                session.setAttribute("cart", cart);
                resp.sendRedirect("home");
            }
        } else {
            req.setAttribute("error", "<div>\n"
                    + "          <div class=\"alert alert-danger mb-0 mt-3\" role=\"alert\">\n"
                    + "                        Username or Password are incorrect!\n"
                    + "            </div>\n"
                    + "  </div>");
            doGet(req, resp);
        }
    }

}
