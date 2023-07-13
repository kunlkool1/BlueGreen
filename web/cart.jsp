<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
        <title>blueGreen | Cart</title>
        <link rel="stylesheet/less" href="./assets/css/cart.less">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <section class="py-md-5 py-3 bg-white">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb justify-content-center">
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item"><a href="store">Store</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Cart</li>
                </ol>
            </nav>
        </section>

        <div class="content-wrapper">
            <div class="container-lg py-5">
                <div class="table-responsive">
                    <table class="table mb-0">
                        <thead>
                            <tr>
                                <th scope="col">Image</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                                <th scope="col">Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cart.items}" var="i">
                                <tr>
                                    <td><a href="product?id=${i.id}"><div class="thumbnail" style="background-image: url(${i.product.thumbnail});"></div></a></td>
                                    <td><a href="product?id=${i.id}" class="key">${i.product.name}</a></td>
                                    <td><div class="price single-price">${i.product.price}</div></td>
                                    <td>
                                        <div class="btn-group detail-quantity">
                                            <button type="button" class="btn-dec" value="${i.id}" onclick="addProduct(this, 'decrease')">-</button>
                                            <input type="number" step="1" min="1" max="999" value="${i.quantity}" name="quantity" readonly>
                                            <button type="button" class="btn-inc" value="${i.id}" onclick="addProduct(this, 'increase')">+</button>
                                        </div>
                                    </td>
                                    <td><div class="price total-price">${i.total()}</div></td>
                                    <td><button type="button" value="${i.id}" onclick="addProduct(this, 'delete')" class="btn btn-danger btn-lg" title="Delete this Product"><i class="fas fa-trash-alt"></i></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>  

                <form action="checkout" method="post">
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <div class="acc-info">
                                <div class="row g-4 mb-4">
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="firstname">First Name</label>
                                            <input type="text" class="form-control" name="txtFirstName" placeholder="First Name" value="${acc.firstName}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="lastname">Last Name</label>
                                            <input type="text" class="form-control" name="txtLastName" placeholder="Last Name" value="${acc.lastName}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input type="tel" class="form-control" name="txtPhone" placeholder="Phone Number" value="${acc.phone}" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" name="txtEmail" placeholder="Email" value="${acc.email}" readonly required>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="address">Address</label>
                                            <textarea class="form-control" name="txtAddress" id="" cols="30" rows="4" placeholder="Address" required>${acc.address}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
    
                        <div class="col-md-6">
                            <div class="summary">
                                <h2 class="mb-5">Cart Summary</h2>
                                <p class="mb-4">Sub Total<span class="price subtotal">${cart.totalMoney()}</span></p>
                                <p class="mb-4">Shipping Cost<span class="price">20</span></p>
                                <h3 class="mb-5 pt-3 border-top">Grand Total<span class="price grandtotal">${cart.totalMoney() + 20}</span></h3>
                                <button type="submit" class="button button-main text-uppercase w-100">Proceed to Checkout</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="./assets/js/cart.js"></script>
    </body>
</html>
