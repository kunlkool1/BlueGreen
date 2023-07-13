<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | ${p.name}</title>
    <link rel="stylesheet/less" href="./assets/css/product.less">
</head>

<body>
    <%@include file="header.jsp" %>

    <section class="py-md-5 py-3 bg-white">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb justify-content-center">
                <li class="breadcrumb-item"><a href="home">Home</a></li>
                <li class="breadcrumb-item"><a href="store">Store</a></li>
                <li class="breadcrumb-item active" aria-current="page">${p.name}</li>
            </ol>
        </nav>
    </section>

    <div class="content-wrapper">
        <div class="container-lg py-5">
            <section class="detail">
                <div class="wrapper">
                    <div class="row g-4">
                        <div class="col-md-6">
                            <div class="swiper thumbnail mb-4">
                                <div class="swiper-wrapper">
                                    <c:forEach items="${p.listImg}" var="i">
                                        <div class="swiper-slide">
                                            <div class="image" style="background-image: url(${i});"></div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div thumbsSlider="" class="swiper smallImage">
                                <div class="swiper-wrapper">
                                    <c:forEach items="${p.listImg}" var="i">
                                        <div class="swiper-slide">
                                            <div class="image" style="background-image: url(${i});"></div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <form action="add-to-cart?id=${param.id}" method="post">
                                <h2 class="detail-name">${p.name}</h2>
                                <p class="detail-price">
                                    <strong class="price final">${p.price - p.price * p.discount /
                                        100}</strong>
                                    <c:if test="${p.discount != 0}">
                                        <span class="price original">${p.price}</span>
                                    </c:if>
                                </p>
                                <ul class="detail-atr">
                                    <li>
                                        <strong class="me-2">Brand:</strong>
                                        <span>${p.brand}</span>
                                    </li>
                                    <li>
                                        <strong class="me-2">Category:</strong>
                                        <span>${p.category}</span>
                                    </li>
                                    <li class="border-bottom">
                                        <strong class="me-2">Color:</strong>
                                        <span class="detail-color" style="background-color: ${p.color=="
                                            Blue"?"#4fbbf5":p.color};" title="${p.color}">
                                    </li>
                                    <li>
                                        <strong class="me-2">Size:</strong>
                                        <div class="d-flex gap-3 mt-2">
                                            <div class="field-radio">
                                                <input type="radio" name="size" value="S">
                                                <label for="">S</label>
                                            </div>
                                            <div class="field-radio">
                                                <input type="radio" name="size" value="M">
                                                <label for="">M</label>
                                            </div>
                                            <div class="field-radio">
                                                <input type="radio" name="size" value="L">
                                                <label for="">L</label>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <strong class="me-2">Quantity:</strong>
                                        <div class="btn-group detail-quantity">
                                            <button type="button" class="btn-dec">-</button>
                                            <input type="number" step="1" min="1" max="999" value="1"
                                                name="quantity" id="quantity">
                                            <button type="button" class="btn-inc">+</button>
                                        </div>
                                    </li>
                                </ul>
                                <div class="d-flex gap-3">
                                    <button type="button" onclick="addToCart('${param.id}')"
                                        class="button button-secondary text-uppercase"><i
                                            class="fas fa-cart-plus"></i>Add To Cart</button>
                                    <button type="submit" class="button button-main text-uppercase">Buy It
                                        Now</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>

            <section class="description">
                <h1>Description</h1>
                <div class="wrapper" style="text-align: justify;">${p.description}</div>
            </section>

            <section class="py-md-5 py-3">
                <div class="swiper product-list">
                    <div class="swiper-heading">
                        <h2>You May Also Like</h2>
                        <div class="swiper-navigation">
                            <div class="swiper-prev"><i class="fas fa-angle-left"></i></div>
                            <div class="swiper-next"><i class="fas fa-angle-right"></i></div>
                        </div>
                    </div>
                    <div class="swiper-wrapper">
                        <c:forEach items="${related}" var="n">
                            <div class="swiper-slide">
                                <a href="product?id=${n.getKey()}" class="product-card">
                                    <c:if test="${n.getValue().discount != 0}">
                                        <span
                                            class="product-badge badge badge-main rounded-pill">${n.getValue().discount}</span>
                                    </c:if>
                                    <div class="product-thumbnail"
                                        style="background-image: url(${n.getValue().thumbnail});"></div>
                                    <div class="product-body">
                                        <div class="product-brand">${n.getValue().brand}</div>
                                        <h5 class="product-name">${n.getValue().name}</h5>
                                        <div class="product-price">
                                            <div class="price final">${n.getValue().finalPrice()}</div>
                                            <c:if test="${n.getValue().discount != 0}">
                                                <div class="price original">${n.getValue().price}</div>
                                            </c:if>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div id="added" class="toast align-items-center" style="font-size: 16px;" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="true" data-bs-delay="3000" data-bs-animation="true">
            <div class="d-flex">
                <div class="toast-body">
                    <i class="fas fa-check-circle me-2" style="color: #32CD32;"></i>Added to cart
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>
    <script src="./assets/js/product.js"></script>
</body>

</html>