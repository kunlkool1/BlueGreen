<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
    <title>Register | blueGreen</title>
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
                        <h1 class="text-center">Create an account</h1>
                        <form action="register" method="post">
                            <div class="field">
                                <input type="text" class="field-input" name="txtUser" value="${User}" required>
                                <label for="forUsername" class="field-label">Username</label>
                            </div>
                            ${errorUser}
                            <div class="field">
                                <input type="email" class="field-input" name="txtEmail" value="${Email}" required>
                                <label for="forEmail" class="field-label">Email</label>
                            </div>
                            <div class="field">
                                <input type="password" class="field-input" name="txtPass" required>
                                <label for="forPassword" class="field-label">Password</label>
                            </div>
                            <div class="field">
                                <input type="password" class="field-input" name="txtCfmPass" required>
                                <label for="forCfmPassword" class="field-label">Confirm Password</label>
                            </div>
                            ${errorPass}
                            <button type="submit" class="button button-secondary">Sign Up</button>
                        </form>
                        <p class="mt-5 text-center text-primary">Have already an account? <a href="login">Login here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
