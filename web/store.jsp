<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
        <title>blueGreen | Accessories for Gamers</title>
        <link rel="stylesheet/less" href="./assets/css/store.less">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <section class="py-md-5 py-3 bg-white">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb justify-content-center">
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Store</li>
                </ol>
            </nav>
        </section>

        <div class="content-wrapper">
            <div class="container-lg pb-5">
                <%@include file="category.jsp" %>

                <div class="row g-4">
                    <div class="col-lg-3">
                        <div class="filter">
                            <h2><i class="fas fa-filter me-2"></i>Filter By</h2>
                            <div class="filter-price">
                                <div class="row align-items-center">
                                    <div class="col-lg-12 col-2">
                                        <h3 class="mb-lg-3 mb-0">Price</h3>
                                    </div>

                                    <div class="col-lg-12 col-10">
                                        <div class="d-flex align-items-center justify-content-center column-gap-3">
                                            <div class="field mb-0">
                                                <input oninput="filter(1)" type="number" step="any" min="0" class="field-input" id="filter-min" name="txtMinPrice" required>
                                                <label for="forMinPrice" class="field-label">$ From</label>
                                            </div>
                                            <div>-</div>
                                            <div class="field mb-0">
                                                <input oninput="filter(1)" type="number" step="any" min="0" class="field-input" id="filter-max" name="txtMaxPrice" required>
                                                <label for="forMaxPrice" class="field-label">$ To</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="filter-brand">
                                <h3 class="mb-3">Brand</h3>
                                <div class="row g-3">
                                    <c:forEach items="${brands}" var="b">
                                        <div class="col-lg-4 col-sm-2 col-4">
                                            <input type="checkbox" value="${b.getValue().name}" class="field-checkbox-img" style="background-image: url(${b.getValue().image});">
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>

                            <div class="filter-cate">
                                <h3 class="mb-3">Category</h3>
                                <div class="row g-3">
                                    <c:forEach items="${cateList}" var="c">
                                        <div class="col-lg-4 col-sm-2 col-4">
                                            <input type="checkbox" value="${c.getValue().name}" class="field-checkbox-img" ${c.getValue().name == param.category?"checked":""} style="background-image: url(${c.getValue().imageUrl}); background-size: contain; padding-bottom: 100%;">
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>

                            <div class="filter-color">
                                <h3 class="mb-3">Color</h3>
                                <div class="color-wrapper d-flex flex-wrap gap-3 align-items-center">
                                    <input type="checkbox" class="field-checkbox-color" title="Black" value="Black" style="background-color: black;">
                                    <input type="checkbox" class="field-checkbox-color" title="White" value="White" style="background-color: white; border: .5px solid #333;">
                                    <input type="checkbox" class="field-checkbox-color" title="Blue" value="Blue" style="background-color: #4fbbf5;">
                                    <input type="checkbox" class="field-checkbox-color" title="Red" value="Red" style="background-color: red;">
                                    <input type="checkbox" class="field-checkbox-color" title="Pink" value="Pink" style="background-color: pink;">
                                    <input type="checkbox" class="field-checkbox-color" title="Gray" value="Gray" style="background-color: gray">
                                    <input type="checkbox" class="field-checkbox-color" title="Green" value="Green" style="background-color: green">
                                    <input type="checkbox" class="field-checkbox-color" title="Yellow" value="Yellow" style="background-color: gold">
                                    <input type="checkbox" class="field-checkbox-color" title="Purple" value="Purple" style="background-color: purple">
                                    <input type="checkbox" class="field-checkbox-color" title="Orange" value="Orange" style="background-color: orangered">
                                    <input type="checkbox" class="field-checkbox-color" title="Brown" value="Brown" style="background-color: brown">
                                    <input type="checkbox" class="field-checkbox-color" title="Silver" value="Silver" style="background-color: silver">
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9">
                        <div class="row g-4 product-display">
                            <div class="col-12">
                                <div class="sort">
                                    <div class="row gy-4 justify-content-between">
                                        <div class="col-md-5 col-12">
                                            <div class="sort-by">
                                                <label for="" class="text-primary">Sort By</label>
                                                <div class="field mb-0">
                                                    <input type="text" class="field-select" value="Price" readonly>
                                                    <ul class="field-option">
                                                        <li class="field-option-item">Price: Low to High</li>
                                                        <li class="field-option-item">Price: High to Low</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-5 col-12">
                                            <div class="paging">
                                                <button type="button" class="paging-btn" id="paging-prev" disabled><i class="fas fa-angle-left"></i></button>
                                                <input type="number" step="1" min="1" class="paging-num" value="1" name="page" readonly>
                                                <span>/ <fmt:formatNumber pattern="#" value="${Math.ceil(proList.size() / 12)}"/></span>
                                                <button type="button" class="paging-btn" id="paging-prev" onclick="filter(2)"><i class="fas fa-angle-right"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:forEach items="${proList}" var="p" begin="0" end="11">
                                <div class="col-lg-3 col-md-4" >
                                    <a href="product?id=${p.getKey()}" class="product-card">
                                        <c:if test="${p.getValue().discount != 0}">
                                            <span class="product-badge badge badge-main rounded-pill">${p.getValue().discount}</span>
                                        </c:if>
                                        <div class="product-thumbnail" style="background-image: url(${p.getValue().thumbnail})"></div>
                                        <div class="product-body">
                                            <div class="product-brand">${p.getValue().brand}</div>
                                            <h5 class="product-name">${p.getValue().name} - <span>${p.getValue().color}</span></h5>
                                            <div class="product-price">
                                                <div class="price final">${p.getValue().price - p.getValue().price * p.getValue().discount / 100}</div>
                                                <c:if test="${p.getValue().discount != 0}">
                                                    <div class="price original">${p.getValue().price}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="./assets/js/store.js"></script>
    </body>
</html>