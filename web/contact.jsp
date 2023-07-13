<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp" %>
    <title>blueGreen | Contact to Us</title>
    <link rel="stylesheet/less" href="./assets/css/home.less">
</head>
<body>
    <%@include file="header.jsp" %>

    <section class="py-md-5 py-3 bg-white">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb justify-content-center">
                <li class="breadcrumb-item"><a href="home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Contact</li>
            </ol>
        </nav>
    </section>
    
    <div class="content-wrapper">
        <div class="container-lg py-5">
            <section class="map mb-5">
                <iframe  class="w-100 d-block" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.8458210929657!2d108.2580489256276!3d15.969425971090406!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCAoRlBUIHVuaXZlcnNpdHkp!5e0!3m2!1sen!2s!4v1678117830336!5m2!1sen!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </section>

            <section class="contact mg-5">
                <div class="row g-4">
                    <div class="col-md-6 order-md-1 order-2">
                        <div class="contact-form p-4">
                            <h1>Contact</h1>
                            <div class="field mb-4">
                                <input type="text" class="field-input" required>
                                <label for="Name" class="field-label">Name</label>
                            </div>
                            <div class="field mb-4">
                                <input type="email" class="field-input" required>
                                <label for="Email" class="field-label">Email *</label>
                            </div>
                            <div class="field mb-4">
                                <input type="tel" class="field-input" required>
                                <label for="Phone" class="field-label">Phone Number</label>
                            </div>
                            <div class="field mb-4">
                                <textarea class="field-textarea" cols="30" rows="3" required></textarea>
                                <label for="Comment" class="field-label">Comment</label>
                            </div>
                            <button class="button button-secondary">Send</button>
                        </div>
                    </div>
                    
                    <div class="col-md-6 order-md-2 order-1">
                        <div class="our-info p-4">
                            <h1>Get In Touch With Us</h1>
                            <p><i class="fa fa-map-marker-alt me-2"></i>FPT Complex, Hoa Hai, Ngu Hanh Son, Da Nang</p>
                            <p><i class="fa fa-phone me-2"></i>123-456-7890</p>
                            <p><i class="fa fa-envelope me-2"></i>bluegreen43@hotmail.com</p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    
    <%@include file="footer.jsp" %>
</body>
</html>
