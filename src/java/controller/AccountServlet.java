package controller;

import dao.AccountDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        OrderDAO oDao = new OrderDAO();
        Account acc = (Account)req.getSession().getAttribute("acc");
        req.setAttribute("orderList", oDao.getOrdersByAccount(acc.getUsername()));
        req.setAttribute("cateList", dao.getAllCategory());
        req.getRequestDispatcher("my-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();

        Account account = (Account) session.getAttribute("acc");
        AccountDAO dao = new AccountDAO();

        String userName = account.getUsername();

        if (action.equals("updateInfo")) {
            String firstName = req.getParameter("txtFirstName");
            String lastName = req.getParameter("txtLastName");
            String phone = req.getParameter("txtPhone");
            String address = req.getParameter("txtAddress");
            String email = req.getParameter("txtEmail");

            dao.updateAccount(userName, firstName, lastName, phone, address);

            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setPhone(phone);
            account.setAddress(address);

            out.print("<div class=\"row g-4 mb-4\">\n" +
"                                    <div class=\"col-sm-6 col-12\">\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label for=\"firstname\">First Name</label>\n" +
"                                            <input type=\"text\" class=\"form-control\" id=\"txtFirstName\" placeholder=\"First Name\" value=\""+ firstName +"\">\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-6 col-12\">\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label for=\"lastname\">Last Name</label>\n" +
"                                            <input type=\"text\" class=\"form-control\" id=\"txtLastName\" placeholder=\"Last Name\" value=\""+ lastName +"\">\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-6 col-12\">\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label for=\"phone\">Phone</label>\n" +
"                                            <input type=\"tel\" class=\"form-control\" id=\"txtPhone\" placeholder=\"Phone Number\" value=\""+ phone +"\">\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-6 col-12\">\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label for=\"email\">Email</label>\n" +
"                                            <input type=\"email\" class=\"form-control\" id=\"txtEmail\" placeholder=\"Email\" value=\""+ email +"\" readonly>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12\">\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label for=\"address\">Address</label>\n" +
"                                            <textarea class=\"form-control\" id=\"txtAddress\" id=\"\" cols=\"30\" rows=\"4\" placeholder=\"Address\">"+ address +"</textarea>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
                    "<div class=\"col-12\">\n"
                            + "          <div class=\"alert alert-success mb-0\" role=\"alert\">\n"
                            + "                        Your info update successful!\n"
                            + "            </div>\n"
                            + "  </div>"+
"                                    <div class=\"col-12\">\n" +
"                                        <button type=\"button\" onclick=\"changePass()\" class=\"button button-secondary w-100\">Update</button>\n" +
"                                    </div>\n" +
"                                </div>");
        }

        if (action.equals("changePass")) {

            String curPass = "";
            String newPass = "";
            String cfmPass = "";

            curPass = req.getParameter("txtCurPass");
            newPass = req.getParameter("txtNewPass");
            cfmPass = req.getParameter("txtCfmPass");

            Account check = dao.login(userName, curPass);

            if (check == null) {
                out.print("<div class=\"alert alert-danger mb-0\" role=\"alert\">\n"
                        + "                        Password is incorrect!\n"
                        + "  </div>");
            } else {
                if (!newPass.equals(cfmPass)) {
                    out.print("<div class=\"alert alert-danger mb-0\" role=\"alert\">\n"
                            + "                        New password and confirm password do not match!\n"
                            + "  </div>");
                } else {
                    dao.updatePass(userName, newPass);
                    account.setPassword(newPass);
                    Cookie[] cookies = req.getCookies();
                    if (cookies != null) {
                        for (Cookie cook : cookies) {
                            if (cook.getName().equals("cPass")) {
                                if (cook.getValue() != null) {
                                    cook.setValue(newPass);
                                    resp.addCookie(cook);
                                }
                            }
                        }
                    }
                    out.print("<div class=\"alert alert-success mb-0\" role=\"alert\">\n"
                            + "                        Password update successful!\n"
                            + "  </div>");
                }
            }

        }
    }

}
