package controller.admin;

import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class MngProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        req.setAttribute("proList", dao.getAllProduct());
        req.getRequestDispatcher("mngproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        PrintWriter out = resp.getWriter();

        String txtSearch = req.getParameter("search");
        Map<String, Product> list_all = dao.getAllProductByName(txtSearch);

        int totalSize = list_all.size();
        System.out.println(list_all);
        int itemOfPage = 10;
        //Lấy page hiện tại và truyền lên jsp
        String page = req.getParameter("page");
        int curPage = (page != null) ? Integer.parseInt(page) : 1;

        //Lấy id bắt đầu trang rồi truyền lên jsp
        int start = (curPage - 1) * itemOfPage;

        //Lấy id kết thúc trang rồi truyền lên jsp
        int end = Math.min(start + itemOfPage, totalSize);
        //Lấy tổng số trang dựa trên dữ liệu hiện có rồi truyền lên jsp
        int totalPage = (int) Math.ceil((double) totalSize / itemOfPage);

        String btnPrev = (curPage > 1)?"onclick=\"paging_wrap("+ (curPage - 1) +")\"":"disabled";
        String btnNext = (curPage < totalPage)?"onclick=\"paging_wrap("+ (curPage + 1) +")\"":"disabled";
        
        out.print("<div class=\"col-12 text-center\">\n" +
"                                    <div class=\"paging justify-content-md-end justify-content-center\">\n" +
"                                        <button class=\"paging-btn\" id=\"paging-prev\""+ btnPrev +"><i class=\"fas fa-angle-left\"></i></button>\n" +
"                                        <input type=\"number\" step=\"1\" min=\"1\" class=\"paging-num\" value=\""+ curPage +"\" readonly>\n" +
"                                        <span>/</span>\n" +
"                                        <label class=\"paging-total-num\" for=\"forPageingNum\">"+ totalPage +"</label>\n" +
"                                        <button class=\"paging-btn\" id=\"paging-next\""+ btnNext +"><i class=\"fas fa-angle-right\"></i></button>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"col-12\">\n" +
"                                    <div class=\"table-responsive\">\n" +
"                                        <table class=\"table mb-0\">\n" +
"                                            <thead>\n" +
"                                                <tr>\n" +
"                                                    <th scope=\"col\">Thumbnail</th>\n" +
"                                                    <th scope=\"col\">Name</th>\n" +
"                                                    <th scope=\"col\">Price</th>\n" +
"                                                    <th scope=\"col\">Discount</th>\n" +
"                                                    <th scope=\"col\">Category</th>\n" +
"                                                    <th scope=\"col\">Brand</th>\n" +
"                                                    <th scope=\"col\">Color</th>\n" +
"                                                    <th scope=\"col\">Actions</th>\n" +
"                                                </tr>\n" +
"                                            </thead>\n" +
"                                            <tbody>");
        
        String[] key = list_all.keySet().toArray(new String[0]);
        
        for (int i = start; i < end; i++) {

            out.print("<tr>\n"
                    + "                                            <td><div class=\"thumbnail\" style=\"background-image: url(" + list_all.get(key[i]).getThumbnail() + ");\"></div></td>\n"
                    + "                                            <td class=\"key\">" + list_all.get(key[i]).getName() + "</td>\n"
                    + "                                            <td>" + list_all.get(key[i]).getPrice() + "</td>\n"
                    + "                                            <td>" + list_all.get(key[i]).getDiscount() + "</td>\n"
                    + "                                            <td>" + list_all.get(key[i]).getCategory() + "</td>\n"
                    + "                                            <td>" + list_all.get(key[i]).getBrand() + "</td>\n"
                    + "                                            <td><div class=\"color\" style=\"background-color: " + list_all.get(key[i]).getColor() + "; border: .2px solid rgba(0, 0, 0, 0.5);\"></div></td>\n"
                    + "                                            <td>\n"
                    + "                                                <div class=\"d-flex flex-column gap-3\">\n"
                    + "                                                    <a href=\"update-product?id=" + key[i] + "\" class=\"btn btn-warning btn-lg text-white\" title=\"Update this Product\"><i class=\"fas fa-edit\"></i></a>\n"
                    + "                                                    <a href=\"#\" onclick= \"showMess('" + key[i] + "')\"  class=\"btn btn-danger btn-lg\" title=\"Delete this Product\"><i class=\"fas fa-trash-alt\"></i></a>\n"
                    + "                                                </div>\n"
                    + "                                            </td>\n"
                    + "                                        </tr>");
        }
        
        out.print("</tbody>\n" +
"                                        </table>\n" +
"                                    </div>    \n" +
"                                </div>");

    }

}
