package controller.admin;

import dao.OrderDAO;
import entity.Item;
import entity.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MngOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO dao = new OrderDAO();

        Calendar calendar = Calendar.getInstance();

        // Đặt thời gian về 0 giờ
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        req.setAttribute("orderList", dao.getOrdersByDate(calendar.getTime()));
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        OrderDAO dao = new OrderDAO();

        if (action.equals("update")) {
            String id = req.getParameter("orderId");
            String status = req.getParameter("status");
            dao.updateStatus(id, status);
        }
        
        if (action.equals("filter") || action.equals("delete")) {
            PrintWriter out = resp.getWriter();
            if (action.equals("delete")) {
                String id = req.getParameter("orderId");
                dao.deleteOrder(id);
            }
            
            try {
                String date = req.getParameter("date");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = format.parse(date);
                
                Map<String, Order> map = dao.getOrdersByDate(startDate);
                
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                int count = 0;
                for (Map.Entry<String, Order> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Order o = entry.getValue();
                    count++;
                    out.print("<div class=\"accordion-item\">\n" +
"                                        <h2 class=\"accordion-header fs-4 d-flex justify-content-between align-items-center\" id=\"order${c.count}\">\n" +
"                                            <span>Time: "+ formater.format(o.getDate()) +"</span>\n" +
"                                            <div class=\"form-group\">\n" +
"                                                <select class=\"form-control custom-select\" onchange=\"modifyOrder('"+ key  +"', this)\">\n" +
"                                                    <option value=\"\" disabled>Status</option>\n" +
"                                                    <option value=\"Processing\" "+ (o.getStatus().equals("Processing")?"selected":"") +">Processing</option>\n" +
"                                                    <option value=\"Delivering\" "+ (o.getStatus().equals("Delivering")?"selected":"") +">Delivering</option>\n" +
"                                                    <option value=\"Done\" "+ (o.getStatus().equals("Done")?"selected":"") +">Done</option>\n" +
"                                                    <option value=\"Canceled\" "+ (o.getStatus().equals("Canceled")?"selected":"") +">Canceled</option>\n" +
"                                                    <option value=\"Refunded\" "+ (o.getStatus().equals("Refunded")?"selected":"") +">Refunded</option>\n" +
"                                                </select>\n" +
"                                            </div>\n" +
"                                            <div class=\"group-btn\">\n" +
"                                                <button class=\"accordion-button collapsed fs-4 d-flex justify-content-between\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapse"+ count +"\" aria-expanded=\"true\" aria-controls=\"collapse"+ count +"\">\n" +
"                                                    <i class=\"fas fa-stream me-2\"></i>\n" +
"                                                </button>\n" +
"                                                <button class=\"btn btn-danger btn-lg\" onclick=\"deleteOrder('"+ key +"')\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
"                                            </div>\n" +
"                                        </h2>\n" +
"                                        <div id=\"collapse"+ count +"\" class=\"accordion-collapse collapse\" aria-labelledby=\"order"+ count +"\" data-bs-parent=\"#accordionOrder\">\n" +
"                                            <div class=\"accordion-body\">\n" +
"                                                <div class=\"table-responsive\">\n" +
"                                                    <table class=\"table mb-0\">\n" +
"                                                        <thead>\n" +
"                                                            <tr>\n" +
"                                                                <th scope=\"col\">Image</th>\n" +
"                                                                <th scope=\"col\">Name</th>\n" +
"                                                                <th scope=\"col\">Price</th>\n" +
"                                                                <th scope=\"col\">Quantity</th>\n" +
"                                                            </tr>\n" +
"                                                        </thead>\n" +
"                                                        <tbody>\n");
                    
                    for (Item i : o.getCart().getItems()) {
                        out.print("<tr>\n" +
"                                                                    <td><a href=\"product?id="+ i.getId() +"\"><div class=\"thumbnail\" style=\"background-image: url("+ i.getProduct().getThumbnail() +");\"></div></a></td>\n" +
"                                                                    <td><a href=\"product?id="+ i.getId() +"\" class=\"key\">"+ i.getProduct().getName()+"</a></td>\n" +
"                                                                    <td><div class=\"price single-price\">"+ i.getProduct().finalPrice() +"</div></td>\n" +
"                                                                    <td>"+ i.getQuantity() +"</td>\n" +
"                                                                </tr>");
                    }
                    
                    out.print("                                                        </tbody>\n" +
"                                                    </table>\n" +
"                                                </div>\n" +
"                                                <div class=\"order-info\">\n" +
"                                                    <div class=\"customer\">\n" +
"                                                        <div class=\"name\"><strong>Name: </strong>"+ o.getFirstName() + " " + o.getLastName() +"</div>\n" +
"                                                        <div class=\"phone\"><strong>Phone: </strong>"+ o.getPhone() +"</div>\n" +
"                                                        <div class=\"email\"><strong>Address: </strong>"+ o.getAddress()+"</div>\n" +
"                                                    </div>\n" +
"\n" +
"                                                    <div class=\"order-price\">\n" +
"                                                        <strong>Total: <span class=\"price\">"+ o.getCart().totalMoney() +"</span></strong>\n" +
"                                                    </div>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div>");
                }
            } catch (ParseException ex) {
                Logger.getLogger(MngOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
