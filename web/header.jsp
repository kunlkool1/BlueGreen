<header class="upper">
    <div class="container-lg">
        <div class="row align-items-center">
            <div class="col-md-3 col-6">
                <div class="logo">
                    <a href="home"><img src="./assets/img/logo.png" alt="Logo" title="BlueGreen"></a>
                </div>
            </div>

            <div class="col-md-5 col-12 order-md-2 order-3 mt-2 mt-md-0">
                <div class="search">
                    <div class="search-group">
                        <input type="text" name="search" id="search-bar" class="input-field" placeholder="Search Product here...">
                        <button class="button button-main" type="submit" title="Search"><i class="fas fa-search"></i></button>
                    </div>
                </div>
            </div>

            <div class="col-md-3 offset-0 offset-md-1 col-6 order-md-3 order-2">
                <div class="user">
                    <c:if test="${acc == null}">
                        <a href="login" class="account" title="Log In">
                            <i class="far fa-user"></i>
                            <span class="d-none d-sm-block">Log In</span>
                        </a>
                    </c:if>
                    <c:if test="${acc != null}">
                        <a href="myaccount" class="account" title="${acc.username}">
                            <i class="far fa-user"></i>
                            <span class="d-none d-sm-block text-uppercase">${acc.username}</span>
                        </a>
                    </c:if>
                    <a href="cart" class="cart" title="Cart">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="badge badge-main">${cart.items.size()}</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>

<header class="bottom">
    <div class="container-lg">
        <nav class="dropdown">
            <nav class="nav-mobile d-flex d-md-none justify-content-between align-items-center">
                <h3 class="text-uppercase">Menu</h3>
                <button class="button button-primary dropdown-toggle">
                    <i class="fas fa-bars"></i>
                </button>
            </nav>
            <ul class="menu flex-column flex-md-row dropdown-list d-md-flex">
                <li class="menu-item dropdown">
                    <div class="dropdown-toggle">
                        <i class="fas fa-bars d-none d-md-inline-block"></i>
                        <span>Shop Categories</span>
                    </div>
                    <ul class="dropdown-list position-relative position-sm-absolute">
                        <c:forEach items="${cateList}" var="c">
                            <li><a href="store?category=${c.getValue().name}">${c.getValue().name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="menu-item"><a href="home">Home</a></li>
                <li class="menu-item"><a href="store">Our Store</a></li>
                <li class="menu-item"><a href="contact">Contact</a></li>
                <c:if test="${acc.isAdmin}">
                    <li class="menu-item"><a href="dashboard">Admin</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>
