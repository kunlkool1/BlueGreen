<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | Accessories for Gamers</title>
    <link rel="stylesheet/less" href="./assets/css/home.less">
</head>
<body>
    <!-- Header -->
    <%@include file="header.jsp" %>
    
    <!-- Banner -->
    <section class="banner py-md-5 py-3">
        <div class="container-lg">
            <div class="row g-3">
                <div class="col-md-8 col-12">
                    <div class="swiper banner-list">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <div class="banner-img" style="background-image: url(https://stogagame.com/wp-content/uploads/2022/01/%E5%A4%A7%E9%BB%84%E8%9C%82banner-11.jpg);"></div>
                            </div>
                            <div class="swiper-slide">
                                <div class="banner-img" style="background-image: url(https://zidli.vn/wp-content/uploads/2022/07/Banner-ZIDLI-ZH29Flywing-ZHA10Flywing-FH6U-FH18U-scaled.jpg);"></div>
                            </div>
                            <div class="swiper-slide">
                                <div class="banner-img" style="background-image: url(https://vcdn.tikicdn.com/ts/tmp/f1/fc/22/4722504e43c49137364bdf7f2c240b0e.jpg);"></div>
                            </div>
                            <div class="swiper-slide">
                                <div class="banner-img" style="background-image: url(https://eu.aimcontrollers.com/wp-content/uploads/2020/03/banner-menu-ps4-1.jpg);"></div>
                            </div>
                            <div class="swiper-slide">
                                <div class="banner-img" style="background-image: url(https://storage-asset.msi.com/event/reseller/2020/gnp/GK20/msi-gk20-main-banner.jpg);"></div>
                            </div>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>

                <div class="col-md-4 col-12">
                    <div class="row h-100 flex-md-column flex-row justify-content-between">
                        <div class="col-6 col-md-12">
                            <div class="banner-img" style="background-image: url(https://images.samsung.com/is/image/samsung/p5/hk_en/offer/s8offer_gearvr/VR-Banner-Facebook.jpg);"></div>
                        </div>
                        <div class="col-6 col-md-12">
                            <div class="banner-img" style="background-image: url(https://i.pinimg.com/originals/96/71/0d/96710dce4c217f77104720f99cdc2604.png);"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div class="content-wrapper">
        <div class="container-lg">

            <!-- Feature -->
            <section class="feature py-md-5 py-3">
                <div class="row g-3">
                    <div class="col-lg-3 col-sm-6 col-12">
                        <div class="feature-item">
                            <div class="feature-icon">
                                <i class="fas fa-shipping-fast"></i>
                            </div>
                            <div class="feature-content">
                                <h3>Free Shipping</h3>
                                <p>When order over 2M₫</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-12">
                        <div class="feature-item">
                            <div class="feature-icon">
                                <i class="fas fa-headset"></i>
                            </div>
                            <div class="feature-content">
                                <h3>Support 24/7</h3>
                                <p>Get support all day</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-12">
                        <div class="feature-item">
                            <div class="feature-icon">
                                <i class="fas fa-sync"></i>
                            </div>
                            <div class="feature-content">
                                <h3>Refund</h3>
                                <p>Within 3 days!</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 col-12">
                        <div class="feature-item">
                            <div class="feature-icon">
                                <i class="fas fa-credit-card"></i>
                            </div>
                            <div class="feature-content">
                                <h3>Secure Payments</h3>
                                <p>100% Protected</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
    
            <!-- Category -->
            <%@include file="category.jsp" %>
    
            <!-- New Release -->
            <section class="offer py-md-5 py-3">
                <div class="swiper product-list">
                    <div class="swiper-heading">
                        <h2>New Release</h2>
                        <div class="swiper-navigation">
                            <div class="swiper-prev"><i class="fas fa-angle-left"></i></div>
                            <div class="swiper-next"><i class="fas fa-angle-right"></i></div>
                        </div>
                    </div>
                    <div class="swiper-wrapper">
                        <c:forEach items="${newRelease}" var="n">
                            <div class="swiper-slide">
                                <a href="product?id=${n.getKey()}" class="product-card">
                                    <c:if test="${n.getValue().discount != 0}">
                                        <span class="product-badge badge badge-main rounded-pill">${n.getValue().discount}</span>
                                    </c:if>
                                    <div class="product-thumbnail" style="background-image: url(${n.getValue().thumbnail});"></div>
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
    
            <!-- Special Offers -->
            <section class="offer py-md-5 py-3">
                <div class="swiper product-list">
                    <div class="swiper-heading">
                        <h2>Special Offers</h2>
                        <div class="swiper-navigation">
                            <div class="swiper-prev"><i class="fas fa-angle-left"></i></div>
                            <div class="swiper-next"><i class="fas fa-angle-right"></i></div>
                        </div>
                    </div>
                    <div class="swiper-wrapper">
                        <c:forEach items="${special}" var="s">
                            <div class="swiper-slide">
                                <a href="product?id=${s.getKey()}" class="product-card">
                                    <span class="product-badge badge badge-main rounded-pill">${s.getValue().discount}</span>
                                    <div class="product-thumbnail" style="background-image: url(${s.getValue().thumbnail});"></div>
                                    <div class="product-body">
                                        <div class="product-brand">${s.getValue().brand}</div>
                                        <h5 class="product-name">${s.getValue().name}</h5>
                                        <div class="product-price">
                                            <div class="price final">${s.getValue().finalPrice()}</div>
                                            <div class="price original">${s.getValue().price}</div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
    
            <!-- Brands -->
            <section class="brand py-md-5 py-3">
                <div class="swiper brand-list">
                    <div class="swiper-wrapper">
                        <c:forEach items="${brands}" var="b">
                            <div class="swiper-slide">
                                <div class="swiper-img" style="background-image: url(${b.getValue().image});"></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </div>
    </div>
    
    <!-- Footer -->
    <%@include file="footer.jsp" %>
    <script src="./assets/js/home.js"></script>
</body>
</html>
