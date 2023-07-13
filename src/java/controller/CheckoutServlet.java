package controller;

import dao.CartDAO;
import dao.OrderDAO;
import entity.Account;
import entity.Cart;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        if(cart==null){
            response.sendRedirect("cart");
        }else{
            Account account = (Account) session.getAttribute("acc");
            if(account!=null){
                try {
                    String firstname = request.getParameter("txtFirstName");
                    String lastname = request.getParameter("txtLastName");
                    String phone = request.getParameter("txtPhone");
                    String address = request.getParameter("txtAddress");
            
                    //luu oder tren database
                    OrderDAO odb = new OrderDAO();
                    odb.addOrder(account.getUsername(), firstname, lastname, phone, address, cart);
                    
                    //xoa cart tren database
                    CartDAO cd = new CartDAO();
                    cd.deleteCart(account);
                    
                    //xoa session cart
                    session.removeAttribute("cart");
                    response.sendRedirect("home");
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
