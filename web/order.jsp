<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
        <title>blueGreen | Order Management</title>
        <link rel="stylesheet/less" href="./assets/css/admin.less">
    </head>
    <body>
        <%@include file="sidebar.jsp" %>
        <main>
            <div class="container-lg py-4">
                <div class="admin-wrapper">
                    <section class="heading">
                        <h1>Order Management</h1>
                    </section>

                    <section class="content">
                        <div class="container-fluid my-4">
                            <div class="order-filter">
                                <div class="form-group mb-4">
                                    <input type="date" oninput="filter(this)" class="form-control" id="search-date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="<%= new Date()%>"/>" placeholder="Time" required>
                                </div>
                            </div>

                            <div class="accordion" id="accordionOrder">
                                <c:forEach items="${orderList}" var="o" varStatus="c">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header fs-4 d-flex justify-content-between align-items-center" id="order${c.count}">
                                            <span>Time: <fmt:formatDate value="${o.getValue().date}" pattern="dd/MM/yyyy HH:mm:ss"/></span>
                                            <div class="form-group">
                                                <select class="form-control custom-select" onchange="modifyOrder('${o.getKey()}', this)">
                                                    <option value="" disabled>Status</option>
                                                    <option value="Processing" ${o.getValue().status == "Processing"?"selected":""}>Processing</option>
                                                    <option value="Delivering" ${o.getValue().status == "Delivering"?"selected":""}>Delivering</option>
                                                    <option value="Done" ${o.getValue().status == "Done"?"selected":""}>Done</option>
                                                    <option value="Canceled" ${o.getValue().status == "Canceled"?"selected":""}>Canceled</option>
                                                    <option value="Refunded" ${o.getValue().status == "Refunded"?"selected":""}>Refunded</option>
                                                </select>
                                            </div>
                                            <div class="group-btn">
                                                <button class="accordion-button collapsed fs-4 d-flex justify-content-between" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${c.count}" aria-expanded="true" aria-controls="collapse${c.count}">
                                                    <i class="fas fa-stream me-2"></i>
                                                </button>
                                                <button class="btn btn-danger btn-lg" onclick="deleteOrder('${o.getKey()}')"><i class="fas fa-trash-alt"></i></button>
                                            </div>
                                        </h2>
                                        <div id="collapse${c.count}" class="accordion-collapse collapse" aria-labelledby="order${c.count}" data-bs-parent="#accordionOrder">
                                            <div class="accordion-body">
                                                <div class="table-responsive">
                                                    <table class="table mb-0">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Image</th>
                                                                <th scope="col">Name</th>
                                                                <th scope="col">Price</th>
                                                                <th scope="col">Quantity</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${o.getValue().cart.items}" var="i">
                                                                <tr>
                                                                    <td><a href="product?id=${i.id}"><div class="thumbnail" style="background-image: url(${i.product.thumbnail});"></div></a></td>
                                                                    <td><a href="product?id=${i.id}" class="key">${i.product.name}</a></td>
                                                                    <td><div class="price single-price">${i.product.finalPrice()}</div></td>
                                                                    <td>${i.quantity}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="order-info">
                                                    <div class="customer">
                                                        <div class="name"><strong>Name: </strong>${o.getValue().firstName} ${o.getValue().lastName}</div>
                                                        <div class="phone"><strong>Phone: </strong>${o.getValue().phone}</div>
                                                        <div class="email"><strong>Address: </strong>${o.getValue().address}</div>
                                                    </div>

                                                    <div class="order-price">
                                                        <strong>Total: <span class="price">${o.getValue().cart.totalMoney()}</span></strong>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </main>

        <form action="account-management" method="post">

            <div class="modal-overlay ${open}">
                <div class="modal-container">
                    <div class="modal-header">
                        <h2>Create Account</h2>
                        <span class="btn btn-close btn-close-white btn-lg modal-toggle"></span>
                    </div>
                    <div class="modal-body">
                        <div class="field">
                            <input type="text" class="field-input" name="txtUser" required>
                            <label for="forUsername" class="field-label">Username</label>
                        </div>
                        ${error}
                        <div class="field">
                            <input type="password" class="field-input" name="txtPass" required>
                            <label for="forPassword" class="field-label">Password</label>
                        </div>
                        <div class="field">
                            <input type="checkbox" class="field-checkbox" name="checkAdmin">
                            <label for="forIsAdmin">Is Admin</label>
                        </div>
                        <div class="field">
                            <input type="email" class="field-input" name="txtEmail" value="" required>
                            <label for="forEmail" class="field-label">Email</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button name="action" value="add" class="btn btn-success button text-uppercase" title="Add">
                            <i class="fas fa-user-plus"></i>
                            <span class="d-none d-md-inline">Add</span>
                        </button>
                    </div>
                </div>
            </div>
        </form>
        <%@include file="script.html" %>
        <script src="./assets/js/order.js"></script>
    </body>
</html>
