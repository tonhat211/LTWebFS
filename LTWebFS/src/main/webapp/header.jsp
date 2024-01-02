<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 12/12/2023
  Time: 9:20 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Header</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="assets/css/header_footer.css">
</head>
<body>
<div id="header">
    <div class="head col rol">
        <div class="logo">
            <a href="index.jsp">
                <img src="assets/img/Logo/favicon_icon.png" alt="Logo">
<%--                     style="width: 40px; height: 40px; border-radius: 5px">--%>
                <span class="web-name">Thiết bị y tế 63</span>
            </a>
        </div>

        <div class="search-bar">
            <input type="text" name="Search" id="Search" placeholder="Tìm kiếm sản phẩm">
            <button type="button" class="button-search"><i class="ti-search"></i></button>
        </div>


        <div class="user">
            <div class="shopping-cart">
                <a href="cart.html">
                    <i class="ti-shopping-cart-full"></i>
                </a>
            </div>
            <div class="user-item">
                <i class="ti-user"></i>
                <a href="login.html">Đăng nhập</a>
                <i style="color: #f6f6f6">/</i>
                <a href="logup.html">Đăng ký</a>
            </div>

        </div>
    </div>
    <div id="nav" class="nav rol col">
        <ul class="nav">
            <li><a href="index.jsp" class="color-f6 menuitem active">Trang chủ</a></li>
            <li>
                <a href="product.jsp" class="color-f6 menuitem ">Sản Phẩm
                    <!--            <i class="nav-arrow-down ti-angle-down"></i>-->
                </a>
                <!--          <ul class="subnav">-->
                <!--            <li><a href="#" class="color-f6">Bán sản phẩm mới</a></li>-->
                <!--                            <hr style="width: 70%; margin: auto; background: #000000">-->
                <!--                            <li><a href="#" class="color-f6">Sản Phẩm cũ</a></li>-->
                <!--          </ul>-->
            </li>
            <li><a href="news.jsp" class="color-f6 menuitem ">Tin Tức</a></li>
            <li><a href="aboutUs.jsp" class="color-f6 menuitem">Nhà Phân Phối</a></li>
        </ul>
    </div>

</div>
</body>
</html>
