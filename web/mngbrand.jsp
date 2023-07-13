<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | Brand Management</title>
    <link rel="stylesheet/less" href="./assets/css/admin.less">
</head>
<body>
    <%@include file="sidebar.jsp" %>

    <main>
        <form action="brand-management" method="post">
            <div class="container py-4">
                <div class="admin-wrapper">
                    <section class="heading">
                        <h1>Brand Management</h1>
                        <button type="button" class="btn btn-success btn-lg modal-toggle" title="Add New Brand">
                            <i class="fas fa-plus"></i>
                            <span class="d-none d-md-inline">Add New Brand</span>
                        </button>
                    </section>

                    <section class="content" style="background-color: #ededed;">
                        <div class="container-fluid my-4">
                            <div class="row g-4">
                                <c:forEach items="${brandList}" var="b">
                                    <div class="col-md-3 col-sm-4 col-6">
                                        <div class="brand-card">
                                            <div class="brand-img" style="background-image: url(${b.getValue().image});"></div>
                                            <h2 class="brand-title text-center mb-4">${b.getValue().name}</h2>
                                            <div class="brand-action text-center">
                                                <button name="action" value="update" class="btn btn-warning btn-lg text-white" title="Update this Brand"><i class="fas fa-edit"></i></button>
                                                <button name="action" value="delete" class="btn btn-danger btn-lg" title="Delete this Brand"><i class="fas fa-trash-alt"></i></button>
                                            </div>
                                        </div>
                                    </div>    
                                </c:forEach>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </form>
    </main>

    <div class="modal-overlay">
        <div class="modal-container">
            <form action="brand-management" method="post">
                <div class="modal-header">
                    <h2>Add brand</h2>
                    <span class="btn btn-close btn-close-white btn-lg modal-toggle"></span>
                </div>
                <div class="modal-body">
                    <div class="field">
                        <input type="txt" class="field-input" name="txtName" required>
                        <label for="forName" class="field-label">Brand Name</label>
                    </div>
                    <div class="field">
                        <input onblur="displayImg(this)" type="url" class="field-input" name="txtImg" required>
                        <label for="forUsername" class="field-label">Image URL</label>
                    </div>
                    <div class="brand-img mt-4 border" style="background-image: url(https://propertywiselaunceston.com.au/wp-content/themes/property-wise/images/no-image.png); background-color: #f2f2f2;"></div>
                </div>
                <div class="modal-footer">
                    <button name="action" value="add" class="btn btn-success button text-uppercase" title="Add">
                        <i class="fas fa-plus"></i>
                        <span class="d-none d-md-inline">Add</span>
                    </button>
                </div>
            </form>
        </div>
    </div>

    <%@include file="script.html" %>
    <script>
        function displayImg(url) {
            var img = url.value;
            var display = document.querySelector('.modal-body .brand-img');
            checkImgUrlExists(img).then(result => {
                if (result) {
                    display.style.backgroundImage = "url("+ img +")";
                    display.style.backgroundColor = "white";
                } else {
                    console.log("Fail")
                    display.style.backgroundImage = "url(https://propertywiselaunceston.com.au/wp-content/themes/property-wise/images/no-image.png)";
                    display.style.backgroundColor = "#f2f2f2";
                }
            });
        }
    </script>
</body>
</html>
