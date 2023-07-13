package controller;

import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class StoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        req.setAttribute("cateList", dao.getAllCategory());
        String[] cate = req.getParameterValues("category");
        req.setAttribute("proList", dao.getProductByFilter("", "", null, cate, null, ""));
        req.setAttribute("brands", dao.getAllBrand());
        req.getRequestDispatcher("store.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        PrintWriter out = resp.getWriter();

        String[] brands = req.getParameterValues("brands");
        String[] colors = req.getParameterValues("colors");
        String[] categories = req.getParameterValues("cates");
        String search = req.getParameter("search");
        String min = req.getParameter("min");
        String max = req.getParameter("max");
        
        Map<String, Product> proList = dao.getProductByFilter(min, max, brands, categories, colors, search);
                
        int totalSize = proList.size();
        int itemOfPage = 12;

        //Lấy tổng số trang dựa trên dữ liệu hiện có rồi truyền lên jsp
        int totalPage = (int) Math.ceil((double) totalSize / itemOfPage);

        //Lấy page hiện tại và truyền lên jsp
        String page = req.getParameter("page");
        int curPage = (page != null) ? Integer.parseInt(page) : 1;

        //Lấy id bắt đầu trang rồi truyền lên jsp
        int start = (curPage - 1) * itemOfPage;

        //Lấy id kết thúc trang rồi truyền lên jsp
        int end = Math.min(curPage * itemOfPage, totalSize);

        String[] key = proList.keySet().toArray(new String[0]);
        
        String btnPrev = (curPage > 1)?"onclick=\"filter("+ (curPage - 1) +")\"":"disabled";
        String btnNext = (curPage < totalPage)?"onclick=\"filter("+ (curPage + 1) +")\"":"disabled";

        out.print("<div class=\"col-12\">\n" +
"                                <div class=\"sort\">\n" +
"                                    <div class=\"row gy-4 justify-content-between\">\n" +
"                                        <div class=\"col-md-5 col-12\">\n" +
"                                            <div class=\"sort-by\">\n" +
"                                                <label for=\"\" class=\"text-primary\">Sort By</label>\n" +
"                                                <div class=\"field mb-0\">\n" +
"                                                    <input type=\"text\" class=\"field-select\" value=\"Price\" readonly>\n" +
"                                                    <ul class=\"field-option\">\n" +
"                                                        <li class=\"field-option-item\">Price: Low to High</li>\n" +
"                                                        <li class=\"field-option-item\">Price: High to Low</li>\n" +
"                                                    </ul>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"                                        <div class=\"col-md-5 col-12\">\n" +
"                                            <div class=\"paging\">\n" +
"                                                <button type=\"button\" class=\"paging-btn\" id=\"paging-prev\" "+ btnPrev +"><i class=\"fas fa-angle-left\"></i></button>\n" +
"                                                <input type=\"number\" step=\"1\" min=\"1\" class=\"paging-num\" value=\""+ curPage +"\" name=\"page\" readonly>\n" +
"                                                <span>/ "+ totalPage +"</span>\n" +
"                                                <button type=\"button\" class=\"paging-btn\" id=\"paging-prev\""+ btnNext +"\"><i class=\"fas fa-angle-right\"></i></button>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </div>");
                
        for (int i = start; i < end; i++) {
            Product pro = proList.get(key[i]);
            
            String badge = "";
            String price = "";
            
            if (pro.getDiscount() == 0) {
                badge = "";
                price = "";
            } else {
                badge = "<span class=\"product-badge badge badge-main rounded-pill\">" + pro.getDiscount() + "</span>\n";
                price = "<div class=\"price original\">"+pro.getPrice()+"</div>\n";
            }

            double finalPrice = pro.getPrice() - pro.getPrice() * pro.getDiscount() / 100;
                        
            out.print("<div class=\"col-lg-3 col-md-4\" >\n" +
"                                    <a href=\"product?id="+ key[i] +"\" class=\"product-card\">\n" +
badge +
"                                        <div class=\"product-thumbnail\" style=\"background-image: url("+ pro.getThumbnail() +")\"></div>\n" +
"                                        <div class=\"product-body\">\n" +
"                                            <div class=\"product-brand\">"+ pro.getBrand() +"</div>\n" +
"                                            <h5 class=\"product-name\">" +pro.getName() +" - <span>"+ pro.getColor() +"</span></h5>\n" +
"                                            <div class=\"product-price\">\n" +
"                                                <div class=\"price final\">"+ finalPrice +"</div>\n" +
price +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </a>\n" +
"                                </div>");
        }

    }

}
    