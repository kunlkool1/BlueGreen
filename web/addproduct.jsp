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
                <section class="heading justify-content-center">
                    <h1>Add New Product</h1>
                </section>

                <section class="content">
                    <form action="add-product" method="post">
                        <div class="form-group mb-4">
                            <label for="name">Product Name</label>
                            <input type="text" class="form-control" name="txtName" placeholder="Product Name" required>
                        </div>
                        <div class="form-group mb-4">
                            <label for="description">Description</label>
                            <textarea name="txtDescription" class="form-control" rows="4" placeholder="Description" required></textarea>
                        </div>
                        <div class="row g-4 mb-4">
                            <div class="col-md-4 col-12">
                                <label for="category">Category</label>
                                <select name="txtCategory" class="form-control custom-select" required>
                                    <option selected disabled value="">Choose...</option>
                                    <c:forEach items="${cateList}" var="c">
                                        <option value="${c.getValue().name}">${c.getValue().name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-4 col-12">
                                <label for="brand">Brand</label>
                                <select name="txtBrand" class="form-control custom-select" required>
                                    <option selected disabled value="">Choose...</option>
                                    <c:forEach items="${brands}" var="b">
                                        <option value="${b.getValue().name}">${b.getValue().name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-4 col-12">
                                <label for="color">Color</label>
                                <select name="txtColor" class="form-control custom-select select-color" required>
                                    <option selected disabled value="">Choose...</option>
                                    <option value="Black" style="color: black;">Black</option>
                                    <option value="White" style="color: #333;">White</option>
                                    <option value="Blue" style="color: blue;">Blue</option>
                                    <option value="Red" style="color: red;">Red</option>
                                    <option value="Pink" style="color: pink;">Pink</option>
                                    <option value="Gray" style="color: gray;">Gray</option>
                                    <option value="Green" style="color: green;">Green</option>
                                    <option value="Yellow" style="color: gold;">Yellow</option>
                                    <option value="Purple" style="color: purple;">Purple</option>
                                    <option value="Orange" style="color: orangeed;">Orange</option>
                                    <option value="Brown" style="color: brown;">Brown</option>
                                    <option value="Silver" style="color: silver;">Silver</option>
                                </select>
                            </div>

                        </div>
                        <div class="form-group mb-4">
                            <label for="thumbnail">Thumbnail</label>
                            <input type="url" class="form-control" name="txtThumbnail" placeholder="Ex: https://example.com/image.png" required>
                        </div>
                        <div class="form-group mb-4">
                            <label for="images">Images - Each image URL is separated by a carriage return (&#9166;).</label>
                            <textarea inputmode="url" name="txtImg" class="form-control" rows="4" placeholder="Ex: https://example.com/image1.png&#10;https://example.com/image2.png&#10;https://example.com/image3.png" required></textarea>
                        </div>
                        <div class="row g-4 mb-4">
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" step="any" min="0" class="form-control" name="txtPrice" placeholder="Price" required>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="forDiscount">Discount</label>
                                    <input type="number" step="1" min="0" class="form-control" name="txtDiscount" placeholder="Discount" required>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn button btn-success text-uppercase">Add</button>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </main>
    
    <%@include file="script.html" %>
</body>
</html>
