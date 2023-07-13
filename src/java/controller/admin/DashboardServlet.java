package controller.admin;

import dao.AccountDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO pdao = new ProductDAO();
        int countProduct = pdao.countProduct();
        req.setAttribute("countProduct", countProduct);
        
        OrderDAO odao = new OrderDAO();
        int countOrder = odao.countOrder();
        req.setAttribute("countOrder", countOrder);
        
        AccountDAO adao = new AccountDAO();
        int countCustomer = adao.countCustomer();
        req.setAttribute("countCustomer", countCustomer);
        
        double income = odao.getAllMoney();
        req.setAttribute("income", income);
        
        String chartMoney = odao.getValue12Month();
        req.setAttribute("chartMoney", chartMoney);
        
        String chartOrder = odao.getOrder12Month();
        req.setAttribute("chartOrder", chartOrder);
        
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}