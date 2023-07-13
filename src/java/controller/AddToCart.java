package controller;

import dao.CartDAO;
import dao.ProductDAO;
import entity.Account;
import entity.Cart;
import entity.Item;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Add to cart
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("acc");
        ProductDAO dao = new ProductDAO();
        
        String id = req.getParameter("id");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Product pr = dao.getProductById(id);
        Item item = new Item(id, pr, quantity);
        
        if (account != null) {           // User is logged in
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
            }

            cart.addItem(item);
            CartDAO c = new CartDAO();
            c.addCart(cart, account); // Add cart to database
            
            resp.sendRedirect("cart");

        } else {            //User is not logged in
            session.setAttribute("item", item);
            resp.sendRedirect("login");
        }
    }

}
