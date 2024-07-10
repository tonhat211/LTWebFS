<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
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
        User user = (User) session.getAttribute("userloging");

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
                <a href="cart">
                    <i class="ti-shopping-cart-full"></i>
                </a>
            </div>
            <div class="user-item">
                <%
                    if(user!=null){
                %>
                    <div class="sub-menu-container" style="display: inline-block">
                        <p class="user-name" style="display: inline-block; margin-bottom: 0"><%=user.getName()%></p>
                        <ul class="sub-menu">
                            <li><a href="update-info?action=init&&object=customer">Cập nhật tài khoản</a></li>
                            <li><a href="cus-order?action=init">Đơn hàng</a></li>
                        </ul>
                    </div>

                    <i style="color: #f6f6f6; margin-right: 20px; margin-left: 20px;">|</i>
                    <a href="log-out">Đăng xuất</a>
                <%
                    }  else  {
                %>
                    <i class="ti-user"></i>
                    <a href="login.jsp?status=0">Đăng nhập</a>
                    <i style="color: #f6f6f6">/</i>
                    <a href="signup.jsp">Đăng ký</a>
                <%
                    }
                %>
            </div>

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
