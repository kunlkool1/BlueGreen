<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | ${acc.username}</title>
    <link rel="stylesheet/less" href="./assets/css/account.less">
</head>
<body>
    <%@include file="header.jsp" %>

    <section class="py-md-5 py-3 bg-white">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb justify-content-center">
                <li class="breadcrumb-item"><a href="home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Account</li>
            </ol>
        </nav>
    </section>    
    
    <div class="content-wrapper">
        <div class="container-lg py-5">
            <div class="row g-5">
                <div class="col-md-3">
                    <ul class="tablist">
                        <li class="tab active" tabindex="0">Account</li>
                        <li class="tab" tabindex="1">Orders</li>
                        <li class="tab" tabindex="2">Change Password</li>
                        <li class="tab" tabindex="0"><a href="logout"><i class="fas fa-sign-out-alt me-2"></i>Logout</a></li>
                    </ul>
                </div>

                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade show active" tab-pane="0">
                            <h2>Account</h2>
                            <form>
                                <div class="row g-4 mb-4">
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="firstname">First Name</label>
                                            <input type="text" class="form-control" id="txtFirstName" placeholder="First Name" value="${acc.firstName}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="lastname">Last Name</label>
                                            <input type="text" class="form-control" id="txtLastName" placeholder="Last Name" value="${acc.lastName}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input type="tel" class="form-control" id="txtPhone" placeholder="Phone Number" value="${acc.phone}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="txtEmail" placeholder="Email" value="${acc.email}" readonly>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="address">Address</label>
                                            <textarea class="form-control" id="txtAddress" id="" cols="30" rows="4" placeholder="Address">${acc.address}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <button type="button" onclick="changeInfo()" class="button button-secondary w-100">Update</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="tab-pane fade" tab-pane="1">
                            <h2>Orders</h2>
                            <div class="accordion" id="accordionExample">
                                <c:forEach items="${orderList}" var="o" varStatus="c">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="order${c.count}">
                                            <button class="accordion-button collapsed fs-4 d-flex justify-content-between" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${c.count}" aria-expanded="true" aria-controls="collapse${c.count}">
                                                <span>Time: <fmt:formatDate value="${o.getValue().date}" pattern="dd/MM/yyyy HH:mm:ss"/></span>
                                                <span>Status: ${o.getValue().status}</span>
                                            </button>
                                        </h2>
                                        <div id="collapse${c.count}" class="accordion-collapse collapse" aria-labelledby="order${c.count}" data-bs-parent="#accordionExample">
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

                        <div class="tab-pane fade" tab-pane="2">
                            <h2>Change Password</h2>
                            <form>
                                <div class="row g-4 mb-4">
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="current-password">Current Password</label>
                                            <input type="password" class="form-control" id="txtCurPass" placeholder="Current Password" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="new-password">New Password</label>
                                            <input type="password" class="form-control" id="txtNewPass" placeholder="New Password" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="confirm-password">Confirm Password</label>
                                            <input type="password" class="form-control" id="txtCfmPass" placeholder="Confirm Password" required>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                    </div>
                                    <div class="col-12">
                                        <button type="button" onclick="changePass()" class="button button-secondary w-100">Save Changes</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <%@include file="footer.jsp" %>
    <script src="./assets/js/my-account.js"></script>
</body>
</html>
