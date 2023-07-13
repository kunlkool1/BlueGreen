<%@page import="entity.Account" %>

<%       
        Account acc = (Account)request.getSession().getAttribute("acc");
        if (acc == null || !acc.isIsAdmin()) {
            response.sendRedirect("home");
        }
 %>

<div class="sidebar">
    <div class="logo">
        <a href="home">
            <img src="./assets/img/logo.png" alt="BlueGreen">
        </a>
    </div>

    <ul class="sidebar-menu">
        <li class="sidebar-item">
            <div>
                <span class="icon">Hi! </span>
                <span class="title">${acc.username}</span>
            </div>
        </li>
        <li class="sidebar-item">
            <a href="dashboard">
                <span class="icon"><i class="fas fa-palette"></i></span>
                <span class="title">Dashboard</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="order">
                <span class="icon"><i class="fas fa-shopping-bag"></i></span>
                <span class="title">Orders</span>
            </a>
        </li>
        <li class="sidebar-item dropdown">
            <div class="dropdown-toggle">
                <span class="icon"><i class="fas fa-box-open"></i></span>
                <span class="title">Products</span>
            </div>

            <ul class="dropdown-list">
                <li><a href="product-management">List Products</a></li>
                <li><a href="add-product">Add Product</a></li>
            </ul>
        </li>
        <li class="sidebar-item">
            <a href="account-management">
                <span class="icon"><i class="fas fa-user-cog"></i></span>
                <span class="title">Accounts</span>
            </a>
        </li>
            
    </ul>
</div>