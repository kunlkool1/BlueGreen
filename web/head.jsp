<%@page import="dao.AccountDAO" %>
<%@page import="dao.CartDAO" %>
<%@page import="entity.Account" %>


<%
    Cookie[] cookies = request.getCookies();  
        String user = "", pass = "";
        if (cookies != null) {
            for(Cookie cook: cookies){
                String uName_check = cook.getName();
                if(uName_check.equals("cUser")){
                    if(cook.getValue() != null){
                        user = cook.getValue();
                    }
                }
                if(uName_check.equals("cPass")){
                    if(cook.getValue() != null){
                        pass = cook.getValue();
                    }
                }
            }
            if (!user.isEmpty() && !pass.isEmpty()) {
                AccountDAO accDao = new AccountDAO();
                CartDAO cartDao = new CartDAO();
                Account acc = accDao.login(user, pass);
                
                request.getSession().setAttribute("acc", acc);
                request.getSession().setAttribute("cart", cartDao.getCart(acc));
            }
        }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Favicon -->
<link rel="shortcut icon" type="image/png" href="./assets/img/favicon.png"/>

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

<!-- Swiper -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>