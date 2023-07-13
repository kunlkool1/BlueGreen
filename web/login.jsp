<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="entity.Account" %>
<%       
        if (request.getSession().getAttribute("acc") != null) {
            response.sendRedirect("home");
        }
 %>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
    <title>Login | blueGreen</title>
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
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="account-form">
                        <h1 class="text-center">Login</h1>
                        <form action="login" method="post">
                            <div class="field">
                                <input type="text" class="field-input" name="txtUser" required>
                                <label for="forUsername" class="field-label">Username</label>
                            </div>
                            <div class="field">
                                <input type="password" class="field-input" name="txtPass" required>
                                <label for="forPassword" class="field-label">Password</label>
                            </div>
                            ${error}
                            <div class="field">
                                <input type="checkbox" name="remem" value="yes" class="me-2">
                                <label for="">Remember me</label>
                            </div>
                            <div class="forgot-password text-end mb-4">
                                <a href="#">Forgot password?</a>
                            </div>
                            <button type="submit" class="button button-secondary">Login</button>
                        </form>
                        <p class="mt-5 text-center text-primary">Don't have an account? <a href="register">Register</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
