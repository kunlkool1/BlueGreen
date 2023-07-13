package controller.admin;

import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProductServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        String id = req.getParameter("id");
        req.setAttribute("cateList", dao.getAllCategory());
        req.setAttribute("brandList", dao.getAllBrand());
        Product product = dao.getProductById(id);
        req.setAttribute("proInfo", product);
        req.getRequestDispatcher("updateproduct.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nameInput = req.getParameter("txtName");
        String desInput = req.getParameter("txtDescription");
        String cateInput = req.getParameter("txtCategory");
        String brandInput = req.getParameter("txtBrand");
        String colorInput = req.getParameter("txtColor");
        String thumnailInput = req.getParameter("txtThumbnail");
        String ListImgInput = req.getParameter("txtImg");
        String priceInput = req.getParameter("txtPrice");
        String discountInput = req.getParameter("txtDiscount");
        
        double price = Double.parseDouble(priceInput);
        int discount = Integer.parseInt(discountInput);
        ProductDAO dao = new ProductDAO();
        dao.updateProduct(id, nameInput, desInput, cateInput, brandInput, colorInput, thumnailInput, ListImgInput, price, discount);
        resp.sendRedirect("product-management");

    }
}
