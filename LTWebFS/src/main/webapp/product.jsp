<%@ page import="java.util.ArrayList" %>
<%@ page import="database.BrandDAO" %>
<%@ page import="model.Brand" %>
<%@ page import="model.Product" %>
<%@ page import="controller.ControllerProduct" %>
<%@ page import="model.ProductHeader" %>
<%@ page import="model.ProductUnit" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 04/12/2023
  Time: 3:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./assets/common/taglib.jsp"%>
<html>
<head>
<%--    <% String title = request.getParameter("kind");%>--%>
    <title>Sản phẩm</title>
<%--    <title><%=title%></title>--%>


    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--fontawesome-->
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">

    <!--    google font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <!--    content -->
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/slidebar.css">
    <link rel="stylesheet" href="assets/css/product.css">


</head>
<body>
<div>
    <%@ include file="header.jsp" %>

    <%
        String currentKind = (String) request.getAttribute("currentKind");
        ArrayList<ProductUnit>  pus = (ArrayList<ProductUnit>) request.getAttribute("productUnitList");
        ArrayList<Brand>  brands = (ArrayList<Brand>) request.getAttribute("brandList");
        ArrayList<String>  countries = (ArrayList<String>) request.getAttribute("countryList");

    %>

    <script>
        <%--console.log(<%= currentKind.equalsIgnoreCase("A") %>);--%>
        <%--console.log("<%= currentKind.equalsIgnoreCase("A") ? "a" : "B"%>");--%>
        <%--console.log(<%= currentKind%>);--%>
    </script>




<%--<%ControllerProduct qly = new ControllerProduct("A");%>--%>

    <div class="product__container">
        <div class="prd__slidebar">
            <div class="prd__sliderbar-list">

                <div class="prd__sliderbar-item active">
                    <img src="assets/img/product-img/banner/product-banner.jpg" alt="" class="prd__slidebar-img">
                </div>
                <div class="prd__sliderbar-item ">
                    <img src="assets/img/product-img/banner/product-banner2.jpg" alt="" class="prd__slidebar-img">
                </div>
                <div class="prd__sliderbar-item ">
                    <img src="assets/img/product-img/banner/product-banner3.jpg" alt="" class="prd__slidebar-img">
                </div>

            </div>
            <div class="prd__slidebar-indicator">
                <ul class="prd__slidebar-indicator-list">
                    <li class="prd__slidebar-indicator-item active"><i class="fa-solid fa-circle"></i></li>
                    <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>
                    <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>
                </ul>


            </div>
            <div class="move-left">
                <i class="fa-solid fa-circle-chevron-left"></i>
            </div>
            <div class="move-right">
                <i class="fa-solid fa-circle-chevron-right"></i>
            </div>
        </div>

        <div class="prd__subnav">
            <div class="subnav__kind-filter">
                <div class="subnav__kind">
                    <ul class="subnav__kind-list">

                        <li class="subnav__kind-item middle kindBtn">
                            <a href="product?kind=A" class="subnav-kind-link <%= currentKind.equalsIgnoreCase("A") ? "active" : ""%>">A</a>
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            <a href="product?kind=B" class="subnav-kind-link <%= currentKind.equalsIgnoreCase("B") ? "active" : ""%>">B</a>
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            <a href="product?kind=C" class="subnav-kind-link <%= currentKind.equalsIgnoreCase("C") ? "active" : ""%>" >C</a>
                        </li>

                    </ul>
                </div>
                <div class="subnav__filter">
                    <div class="">
                        <i class="fa-solid fa-x cancel-filter"></i>

                    </div>
                    <div class="subnav__filter-item">
                        <p class="subnav__filter-title">Xuất xứ <i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option">
                            <%
                                for(String c : countries){

                            %>
                            <li class="filter-item" filter-value="usa"><%=c%></li>



                            <%
                                }
                            %>

                        </ul>
                        <span class="filter-chosen">Tất cả</span>
                    </div>
                    <div class="seperate"></div>

                    <div class="subnav__filter-item" style="display: none">
                        <p class="subnav__filter-title">Thời gian ra mắt<i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option">
                            <li class="filter-item" filter-value="current">Gần đây</li>
                            <li class="filter-item" filter-value="past">Đã lâu</li>
                        </ul>
                        <span class="filter-chosen">Tất cả</span>

                    </div>
                    <div class="seperate" style="display: none"></div>
                    <div class="subnav__filter-item">
                        <p class="subnav__filter-title pdr20 ">Giá<i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option w100">
                            <li class="filter-item" filter-value="high">Mặc định</li>
                            <li class="filter-item" filter-value="high">Cao dần</li>
                            <li class="filter-item" filter-value="low">Thấp dần</li>
                        </ul>
                        <span class="filter-chosen">Tất cả</span>

                    </div>
                </div>
            </div>
            <div class="subnav__brand">
                <div class="grid">
                    <div class="grid__row brand-list">
                        <div class="grid-col-1">
                            <div class="brand-item all-brand">

                                <p class="brand-name">Tất cả</p>
                            </div>
                        </div>

                        <%
                        String brandName="";
                        for(Brand br : brands){
                            brandName = br.getName();
                            %>
                            <div class="grid-col-1">
                                <div class="brand-item">
                                    <p class="brand-name"> <%=brandName%> </p>
                                </div>
                            </div>


                           <%
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>

        <div class="prd__product-list">
            <div class="grid">
                <div class="grid__row">

                    <!--                        san pham A-->


                    <!--                    1sp-->
                    <%
                        for(ProductUnit p : pus){%>
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <a href="productDetail?id=<%=p.getId()%>" class="product-item-link">
                                <div class="product-img">
                                    <%String imgurl  =  "./assets/img/products/" + p.getImg();%>
                                    <img src= <%=imgurl%>  alt="">
                                </div>

                                <div class="product-info">
                                    <div class="product-name"><p><%=p.getName()%></p></div>
                                    <p class="product-price">
                                        <fmt:formatNumber value="<%=p.getPrice()%>" pattern="#,##0.00"/>
                                        VND
                                    </p>

                                </div>
                            </a>



                        </div>
                    </div>
                    <%  }   %>




                </div>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>



<script src="assets/js/product.js">

</script>
<script>
    function sortProducts(order) {
        $.ajax({
            type: "GET",
            url: "ProductServlet",
            data: { order: order },
            dataType: "json",
            success: function(response) {
                displayProducts(response);
            },
            error: function(error) {
                console.error("Error during sorting: ", error);
            }
        });
    }

    // Hàm hiển thị sản phẩm
    function displayProducts(products) {
        var productList = $("#productList");
        productList.empty();

        $.each(products, function(index, product) {
            var productDiv = $("<div>").addClass("product").text(product.name + " - Price: $" + product.price);
            productList.append(productDiv);
        });
    }

    // Gọi hàm ban đầu để hiển thị sản phẩm
    // sortProducts('asc');
</script>
</body>
</html>
