<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
        <title>blueGreen | Product Management</title>
        <link rel="stylesheet/less" href="./assets/css/admin.less">
    </head>
    <body>
        <%@include file="sidebar.jsp" %>

        <main>
            <div class="container py-4">
                <div class="admin-wrapper">
                    <section class="heading">
                        <h1>Product Management</h1>
                        <a href="add-product" class="btn btn-success btn-lg" title="Add New Product">
                            <i class="fas fa-plus-circle"></i>
                            <span class="d-none d-md-inline">Add New Product</span>
                        </a>
                    </section>

                    <section class="content">
                        <div class="container-fluid my-4">
                            <div class="field mb-4">
                                <input oninput="paging_wrap(1)" type="text" id="search-bar" class="field-input" required>
                                <label for="" class="field-label">Search something</label>
                            </div>
                            <div class="row g-4 product-container">
                                <div class="col-12 text-center">
                                    <div class="paging justify-content-md-end justify-content-center">
                                        <button class="paging-btn" id="paging-prev" disabled><i class="fas fa-angle-left"></i></button>
                                        <input type="number" step="1" min="1" class="paging-num" value="1" readonly>
                                        <span>/</span>
                                        <label class="paging-total-num" for="forPageingNum"><fmt:formatNumber pattern="#" value="${Math.ceil(proList.size() / 10)}"/></label>
                                        <button class="paging-btn" id="paging-next" onclick="paging_wrap(2)"><i class="fas fa-angle-right"></i></button>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="table-responsive">
                                        <table class="table mb-0">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Thumbnail</th>
                                                    <th scope="col">Name</th>
                                                    <th scope="col">Price</th>
                                                    <th scope="col">Discount</th>
                                                    <th scope="col">Category</th>
                                                    <th scope="col">Brand</th>
                                                    <th scope="col">Color</th>
                                                    <th scope="col">Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${proList}" var="p" begin="0" end="9">
                                                    <tr>
                                                        <td><div class="thumbnail" style="background-image: url(${p.getValue().thumbnail});"></div></td>
                                                        <td class="key">${p.getValue().name}</td>
                                                        <td>${p.getValue().price}</td>
                                                        <td>${p.getValue().discount}</td>
                                                        <td>${p.getValue().category}</td>
                                                        <td>${p.getValue().brand}</td>
                                                        <td><div class="color" style="background-color: ${p.getValue().color == "Blue"?"#4fbbf5":p.getValue().color}; border: .2px solid rgba(0, 0, 0, 0.5);"></div></td>
                                                        <td>
                                                            <div class="d-flex flex-column gap-3">
                                                                <a href="update-product?id=${p.getKey()}" class="btn btn-warning btn-lg text-white" title="Update this Product"><i class="fas fa-edit"></i></a>
                                                                <a href="#" onclick= "showMess('${p.getKey()}')"  class="btn btn-danger btn-lg" title="Delete this Product"><i class="fas fa-trash-alt"></i></a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </main>
        <script>
            function showMess(id) {
                var op = confirm('Are you sure to delete this Product id = ' + id);
                if (op === true) {
                    window.location.href = 'delete-product?id='+id;
                }
            }
        </script>
        <%@include file="script.html" %>
        <script src="./assets/js/mngProduct.js"></script>
    </body>

</html>