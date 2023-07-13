package controller.admin;

import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        req.setAttribute("cateList", dao.getAllCategory());
        req.setAttribute("brands", dao.getAllBrand());
        req.getRequestDispatcher("addproduct.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameInput = req.getParameter("txtName");
        String desInput = req.getParameter("txtDescription");
        String cateInput = req.getParameter("txtCategory");
        String brandInput = req.getParameter("txtBrand");
        String colorInput = req.getParameter("txtColor");
        String thumnailInput = req.getParameter("txtThumbnail");
        String listImgInput = req.getParameter("txtImg");
        String priceInput = req.getParameter("txtPrice");
        String discoutInput = req.getParameter("txtDiscount");
        ProductDAO dao = new ProductDAO();
        double price = Double.parseDouble(priceInput);
        int discount = Integer.parseInt(discoutInput);


        dao.addNewProduct(nameInput, desInput, cateInput, brandInput, colorInput, thumnailInput, listImgInput, price, discount);
        resp.sendRedirect("product-management");
    }
    
}
