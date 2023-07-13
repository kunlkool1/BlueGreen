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
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class AdjustmentCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("acc");
        Cart cart = (Cart) session.getAttribute("cart");
        
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        
        ProductDAO dao = new ProductDAO();
        CartDAO c = new CartDAO();
        
        PrintWriter out = resp.getWriter();

        if (action.equals("increase")) {
            if (account != null) {           // User is logged in

                if (cart == null) {
                    cart = new Cart();
                }

                Product pr = dao.getProductById(id);
                Item item = new Item(id, pr, 1);
                cart.addItem(item);
                c.addCart(cart, account); // Add cart to database

            }
        }
        
        if (action.equals("decrease")) {
            Item item = cart.getItemById(id);
            int quan = item.getQuantity();
            if (quan > 1) {
                item.setQuantity(quan - 1);
            } else {
                cart.deleteItem(id);
            }
            c.addCart(cart, account);
        }
        
        if (action.equals("delete")) {
            cart.deleteItem(id);
            c.addCart(cart, account);
        }
        
        for (Item i : cart.getItems()) {
            out.print("<tr>\n"
                    + "                                    <td><div class=\"thumbnail\" style=\"background-image: url(" + i.getProduct().getThumbnail() + ");\"></div></td>\n"
                    + "                                    <td class=\"key\">" + i.getProduct().getName() + "</td>\n"
                    + "                                    <td><div class=\"price single-price\">" + i.getProduct().getPrice() + "</div></td>\n"
                    + "                                    <td>\n"
                    + "                                        <div class=\"btn-group detail-quantity\">\n"
                    + "                                            <button type=\"button\" class=\"btn-dec\" value=\""+ i.getId() +"\" onclick=\"addProduct(this, 'decrease')\">-</button>\n"
                    + "                                            <input type=\"number\" step=\"1\" min=\"1\" max=\"999\" value=\"" + i.getQuantity() + "\" name=\"quantity\" readonly>\n"
                    + "                                            <button type=\"button\" class=\"btn-inc\" value=\""+ i.getId() +"\" onclick=\"addProduct(this, 'increase')\">+</button>\n"
                    + "                                        </div>\n"
                    + "                                    </td>\n"
                    + "                                    <td><div class=\"price total-price\">" + i.total() + "</div></td>\n"
                    + "                                    <td><button type=\"button\" value=\""+ i.getId() +"\" onclick=\"addProduct(this, 'delete')\" class=\"btn btn-danger btn-lg\" title=\"Delete this Product\"><i class=\"fas fa-trash-alt\"></i></button></td>\n"
                    + "                                </tr>");
        }
    }
    
}
