<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
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
    <%
        String currentMenu = ((String) session.getAttribute("currentMenu"))==null ? "index" : ((String) session.getAttribute("currentMenu"));
        String recentSearch = (String) session.getAttribute("currentSearch");

    %>

    <div class="head col rol">
        <div class="logo">
            <a href="index.jsp">
                <img src="assets/img/Logo/favicon_icon.png" alt="Logo">
                <%--                     style="width: 40px; height: 40px; border-radius: 5px">--%>
                <span class="web-name">Thiết bị y tế 63</span>
            </a>
        </div>

        <form class="search-bar" action="searchProduct" method="get">
            <input type="text" name="Search" id="Search" value= "<%= recentSearch!=null  ? recentSearch : ""%>" placeholder="Tìm kiếm sản phẩm">
            <button type="submit" class="button-search"><i class="ti-search"></i></button>
        </form>


        <div class="user">
            <div class="shopping-cart">
                <a href="cart.jsp">
                    <i class="ti-shopping-cart-full"></i>
                </a>
            </div>
            <div class="user-item">
                <i class="ti-user"></i>
                <a href="login.jsp">Đăng nhập</a>
                <i style="color: #f6f6f6">/</i>
                <a href="logup.jsp">Đăng ký</a>
            </div>
<%--            <li class="nav-item dropdown pe-3">--%>

<%--                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">--%>
<%--                    <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">--%>
<%--                    <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>--%>
<%--                </a><!-- End Profile Iamge Icon -->--%>

<%--                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">--%>
<%--                    <li class="dropdown-header">--%>
<%--                        <h6>Kevin Anderson</h6>--%>
<%--                        <span>Web Designer</span>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <hr class="dropdown-divider">--%>
<%--                    </li>--%>

<%--                    <li>--%>
<%--                        <a class="dropdown-item d-flex align-items-center" href="profile.html">--%>
<%--                            <i class="bi bi-person"></i>--%>
<%--                            <span>Quản lý tài khoản</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <hr class="dropdown-divider">--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a class="dropdown-item d-flex align-items-center" href="#">--%>
<%--                            <i class="bi bi-box-arrow-right"></i>--%>
<%--                            <span>Đăng xuất</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                </ul><!-- End Profile Dropdown Items -->--%>
<%--            </li><!-- End Profile Nav -->--%>
        </div>
    </div>
    <div id="nav" class="nav rol col">
        <ul class="nav">
<%--            <li><a href="menucontrol?menu=index" class="color-f6 menuitem <%= currentMenu.equalsIgnoreCase("index") ? "active" : ""%>">Trang chủ</a></li>--%>
            <li><a href="menucontrol?menu=index" class="color-f6 menuitem <%=currentMenu.equalsIgnoreCase("index") ? "active" :""%>" >Trang chủ</a></li>
            <li>
                <a href="menucontrol?menu=product&kind=A" class="color-f6 menuitem  <%=currentMenu.equalsIgnoreCase("product") ? "active" :""%>">Sản Phẩm
                    <!--            <i class="nav-arrow-down ti-angle-down"></i>-->
                </a>
                <!--          <ul class="subnav">-->
                <!--            <li><a href="#" class="color-f6">Bán sản phẩm mới</a></li>-->
                <!--                            <hr style="width: 70%; margin: auto; background: #000000">-->
                <!--                            <li><a href="#" class="color-f6">Sản Phẩm cũ</a></li>-->
                <!--          </ul>-->
            </li>
            <li><a href="menucontrol?menu=news" class="color-f6 menuitem <%= currentMenu.equalsIgnoreCase("news") ? "active" :"" %> ">Tin Tức</a></li>
            <li><a href="menucontrol?menu=aboutus" class="color-f6 menuitem <%=currentMenu.equalsIgnoreCase("aboutus") ? "active" :""%>">Nhà Phân Phối</a></li>
        </ul>
    </div>

</div>
</body>
</html>
