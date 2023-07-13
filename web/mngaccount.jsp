<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
        <title>blueGreen | Account Management</title>
        <link rel="stylesheet/less" href="./assets/css/admin.less">
    </head>
    <body>
        <%@include file="sidebar.jsp" %>
        <main>
            <div class="container py-4">
                <div class="admin-wrapper">
                    <section class="heading">
                        <h1>Account Management</h1>
                        <button type="button" class="btn btn-success btn-lg modal-toggle" title="Add New Account">
                            <i class="fas fa-user-plus"></i>
                            <span class="d-none d-md-inline">Add New Account</span>
                        </button>
                    </section>

                    <section class="content">
                        <div class="container-fluid my-4">
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th scope="col">No.</th>
                                            <th scope="col">Username</th>
                                            <th scope="col">Is Admin</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${accList}" var="a" varStatus="c">
                                            <form action="account-management" method="post">
                                                <tr>
                                                    <td>${c.count}</td>
                                                    <td><input class="input-invisible" type="text" value="${a.getValue().username}" readonly name="user"></td>
                                                    <td>
                                                        <c:if test="${a.getValue().isAdmin}">
                                                            <i style="color: #32CD32; font-size: 2rem;" class="fas fa-check-circle"></i>
                                                        </c:if>
                                                        <c:if test="${!a.getValue().isAdmin}">
                                                            <i style="color: red; font-size: 2rem;" class="fas fa-times-circle"></i>
                                                        </c:if>
                                                    </td>
                                                    <td>${a.getValue().email}</td>
                                                    <td><button name="action" value="delete" class="btn btn-danger btn-lg" title="Delete this Product"><i class="fas fa-trash-alt"></i></button></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </main>

        <form action="account-management" method="post">

            <div class="modal-overlay ${open}">
                <div class="modal-container">
                    <div class="modal-header">
                        <h2>Create Account</h2>
                        <span class="btn btn-close btn-close-white btn-lg modal-toggle"></span>
                    </div>
                    <div class="modal-body">
                        <div class="field">
                            <input type="text" class="field-input" name="txtUser" required>
                            <label for="forUsername" class="field-label">Username</label>
                        </div>
                        ${error}
                        <div class="field">
                            <input type="password" class="field-input" name="txtPass" required>
                            <label for="forPassword" class="field-label">Password</label>
                        </div>
                        <div class="field">
                            <input type="checkbox" class="field-checkbox" name="checkAdmin">
                            <label for="forIsAdmin">Is Admin</label>
                        </div>
                        <div class="field">
                            <input type="email" class="field-input" name="txtEmail" value="" required>
                            <label for="forEmail" class="field-label">Email</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button name="action" value="add" class="btn btn-success button text-uppercase" title="Add">
                            <i class="fas fa-user-plus"></i>
                            <span class="d-none d-md-inline">Add</span>
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <%@include file="script.html" %>
    </body>
</html>
