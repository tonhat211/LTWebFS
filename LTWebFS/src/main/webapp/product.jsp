<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Brand" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <!--    google font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--    content -->
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/slidebar.css">
    <link rel="stylesheet" href="assets/css/product.css">


</head>
<body>
<div>
    <%@ include file="header.jsp" %>

    <%
        ArrayList<ProductUnit>  pus = (ArrayList<ProductUnit>) request.getAttribute("productUnitList");
        ArrayList<Brand>  brands = (ArrayList<Brand>) request.getAttribute("topBrandList");
        ArrayList<String>  countries = (ArrayList<String>) request.getAttribute("countryList");

        String kind = (String) session.getAttribute("kind");
        if(kind==null) kind= "A";
        System.out.println("kind in jsp: " + kind);

    %>

    <script>
        $(document).ready(function() {
            const kinds = document.querySelectorAll('.subnav-kind-link');
            for(let i=0;i<kinds.length;i++) {
                if((kinds[i].innerText=="<%=kind%>")) {
                    kinds[i].classList.add("active");
                }
            }


        });

    </script>


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
                            <a href="product?action=query&&kind=A" class="subnav-kind-link">A</a>
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            <a href="product?action=query&&kind=B" class="subnav-kind-link">B</a>
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            <a href="product?action=query&&kind=C" class="subnav-kind-link">C</a>
                        </li>

                    </ul>
                </div>
                <div class="subnav__filter">
                    <div class="">
                        <a href="product?action=query&&kind=<%=kind%>" ><i class="fa-solid fa-x cancel-filter"></i></a>
                    </div>
                    <div class="subnav__filter-item country-filter">
                        <p class="subnav__filter-title">Xuất xứ <i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option country-list">
                            <li class="filter-item country-item">Tất cả</li>
                            <%
                                for(String c : countries){
                            %>
                            <li class="filter-item country-item"><%=c%></li>
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
                    <div class="subnav__filter-item price-filter">
                        <p class="subnav__filter-title pdr20">Giá<i class="fa-solid fa-caret-down" style="margin-left: 4px;"></i></p>
                        <ul class="subnav__filter-option w100">
                            <li class="filter-item price-item">Mặc định</li>
                            <li class="filter-item price-item">Cao dần</li>
                            <li class="filter-item price-item">Thấp dần</li>
                        </ul>
                        <span class="filter-chosen">Mặc định</span>

                    </div>
                </div>
            </div>
            <div class="subnav__brand">
                <div class="grid">
                    <div class="grid__row brand-list">
                        <script>

                        </script>
                        <div class="grid-col-1">
                            <div class="brand-item all-brand">
                                <input type="text" class="brand-id" value="-2" hidden>
                                <input type="text" class="brand-country" value="none" hidden>
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
                                    <input type="text" class="brand-id" value="<%=br.getId()%>" hidden>
                                    <input type="text" class="brand-country" value="<%=br.getCountry()%>" hidden>
                                    <p class="brand-name"> <%=brandName%> </p>
                                </div>
                            </div>


                           <%
                        }
                        %>

                        <div class="grid-col-1">
                            <div class="brand-item other-brand btn-primary" style="background-color: #C6CBFB">
                                <input type="text" class="brand-id" value="-1" hidden>
                                <input type="text" class="brand-country" value="none" hidden>
                                <p class="brand-name">Khác</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="prd__product-list">
            <div class="grid">
                <div class="grid__row product-list">

                    <!--                        san pham A-->


                    <!--                    1sp-->
                    <%
                        for(ProductUnit p : pus){%>
                    <div class="grid-col-2 mt20 oneProduct">
                        <div class="product-item">
                            <a href="product?action=detail&&id=<%=p.getId()%>" class="product-item-link">
                                <div class="product-img">
                                    <%String imgurl = p.getImg();%>
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
        <div class="show-flex-row" style="justify-content: end">
            <input type="text" id="current-arrange" value="0" hidden>
            <input type="text" id="current-country" value="Tất cả" hidden>
            <input type="text" id="current-brandID" value="-2" hidden>
            <a href="#" class="d-flex align-items-center justify-content-center more-product" style="font-size: 24px">
                <i class="bi bi-arrow-down-square-fill" style="color:#00B3DA;font-size: 30px"></i></a>
        </div>

    </div>
    <%@ include file="footer.jsp" %>
</div>

<script>

    const brandList = document.querySelector(".brand-list");
    const countryList = document.querySelector(".country-list");

    function queryProduct(urlin) {
        $.ajax({
            type: "GET",
            url: urlin,
            success: function(data) {
                $('.product-list').html(data);
                var newUrl = urlin;
                history.pushState({ path: newUrl }, '', newUrl);
            },
            error: function(error) {
                console.error("Error during querying: ", error);
            }
        });
    }

    const moreProBtn = document.querySelector(".more-product");
    moreProBtn.addEventListener('click', function(event) {
        event.preventDefault();
        let index = document.querySelectorAll(".product-item").length;
        let country= document.querySelector("#current-country").value;
        let brandID= document.querySelector("#current-brandID").value;
        let arrange= document.querySelector("#current-arrange").value;
        console.log("index jsp: " +index);

        moreProduct(country, brandID, arrange, index);
    });

    function moreProduct(country, brandID, arrange, index) {
        $.ajax({
            url: "/LTWebFS/product?action=more",
            type: "GET",
            data: {country: country, brandID: brandID, arrange: arrange, index: index},
            success: function(data) {
                $('.product-list').append(data);
            }
        });
    }

    const brands = document.querySelectorAll(".brand-item");
    for(let i=0; i<brands.length;i++) {
        brands[i].addEventListener('click',function () {
            for(let j=0;j<brands.length;j++) {
                brands[j].classList.remove("active");
            }

            let id = this.querySelector('.brand-id').value;
            document.querySelector("#current-brandID").value =id;
            if(id!=-2) {
                brands[i].classList.add("active");
            }
            let arrange= document.querySelector("#current-arrange").value;
            document.querySelector("#current-brandID").value = id;

            chooseByBrand(id, arrange);
        });
    }

    function chooseByBrand(brandID, arrange) {
        $.ajax({
            url: "/LTWebFS/product?action=brand",
            type: "GET",
            data : {brandID : brandID, arrange: arrange},
            success: function (data) {
                $('.product-list').html(data);
            }
        });
    }

    const countries = document.querySelectorAll(".country-item");
    for(let i=0; i<countries.length;i++) {
        countries[i].addEventListener('click',function () {
            document.querySelector("#current-country").value =countries[i].innerText;
            for(let j=0;j<brands.length;j++) {
                brands[j].classList.remove("active");
            }
            for(let j=0; j<brands.length;j++) {
                if(brands[j].querySelector('.brand-country').value == countries[i].innerText) {
                    brands[j].classList.add("active");
                }

            }
            let arrange= document.querySelector("#current-arrange").value;
            chooseByCountry(countries[i].innerText,arrange);

        });
    }


    function chooseByCountry(country, arrange) {
        $.ajax({
            url: "/LTWebFS/product?action=country",
            type: "GET",
            data: {country: country, arrange: arrange},
            success: function (data) {
                $('.product-list').html(data);
            }
        });
    }

//     sap xep theo gia
    const prices = document.querySelectorAll(".price-item");
    for(let i=0;i<prices.length;i++) {
        prices[i].addEventListener('click',function (){
            const brands = document.querySelectorAll(".brand-item");
            const countries = document.querySelectorAll(".country-item");
            let brandID=-2;
            let country ="Tất cả";

            let arrangeContent = prices[i].innerText;
            let arrange;

            if (arrangeContent=="Cao dần") arrange=1;
            else if(arrangeContent=="Thấp dần") arrange=-1;
            else arrange=0;

            for(let i=0; i<brands.length;i++) {
                if(brands[i].classList.contains("active")) {
                    brandID= brands[i].querySelector(".brand-id").value;
                    break;
                }
            }

            for(let i=0; i<countries.length;i++) {
                if(countries[i].classList.contains("active")) {
                    country= countries[i].innerText;
                    break;
                }
            }
            document.querySelector("#current-arrange").value = arrange;
            arrangeByPrice(arrange, brandID, country);

        });
    }

    function arrangeByPrice(arrange, brandID, country) {
        $.ajax({
            url: "/LTWebFS/product?action=arrange",
            type:"GET",
            data: {arrange: arrange, brandID: brandID, country: country},
            success: function(data) {
                $('.product-list').html(data);
            }
        });
    }


</script>



<script src="assets/js/product.js">

</script>

</body>
</html>
