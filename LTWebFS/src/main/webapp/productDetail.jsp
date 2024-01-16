<%@ page import="model.ProductDetail" %>
<%@ page import="model.Image" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 04/12/2023
  Time: 3:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!--fontawesome-->
  <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">

  <!--    google font-->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

  <!--    css-->

  <!--    header- footer-->
  <link rel="stylesheet" href="assets/css/header_footer.css">
  <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
  <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">

  <!--    content -->
  <link rel="stylesheet" href="assets/css/baseN.css">
  <link rel="stylesheet" href="assets/css/slidebar.css">
  <link rel="stylesheet" href="assets/css/productDetail.css">
  <title>Chi tiết sản phẩm</title>
</head>
<body>
<div>
  <%@ include file="header.jsp" %>

  <%
    ProductDetail productDetail = (ProductDetail) request.getAttribute("productDetail");
  %>
  <div class="product__container">
    <div class="product-item">
      <div class="prd__slidebar">

        <div class="product-img-list">
          <%
            ArrayList<String>  urls = productDetail.getImageUrl();
            for(int i  =  0;i< urls.size();i++){
          %>
                <div class="prd__sliderbar-item <%=  i==0 ? "active"  : ""%> ">
                  <img src="./assets/img/products/<%=urls.get(i)%>" alt="">
                </div>

          <%
            }
          %>
<%--          <div class="prd__sliderbar-item active">--%>
<%--            <img src="./assets/img/product-img/A/may-tro-thinh-geno-2-CIC-1.png" alt="">--%>
<%--          </div>--%>
<%--          <div class="prd__sliderbar-item ">--%>
<%--            <img src="./assets/img/product-img/A/may-tro-thinh-beurer-ha50-1.jpg" alt="">--%>
<%--          </div>--%>
<%--          <div class="prd__sliderbar-item ">--%>
<%--            <img src="./assets/img/product-img/A/may-tro-thinh.jpg" alt="">--%>
<%--          </div>--%>
        </div>
        <div class="prd__slidebar-indicator">
          <ul class="prd__slidebar-indicator-list">
            <%

              for(int i  =  0;i< urls.size();i++){
            %>
            <li class="prd__slidebar-indicator-item <%=  i==0 ? "active"  : ""%>"><i class="fa-solid fa-circle"></i></li>

            <%
              }
            %>
<%--            <li class="prd__slidebar-indicator-item active"><i class="fa-solid fa-circle"></i></li>--%>
<%--            <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>--%>
<%--            <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>--%>
          </ul>


        </div>
        <div class="move-left">
          <i class="fa-solid fa-circle-chevron-left"></i>
        </div>
        <div class="move-right">
          <i class="fa-solid fa-circle-chevron-right"></i>
        </div>
      </div>

      <div class="product-info">
        <div class="product-name">
          <p> <%= productDetail.getName()%> </p>
        </div>
        <div class="product-price">
          <p> <%= productDetail.getPrice()%> <span>VND</span></p>
        </div>
        <div class="product-info-buying">
          <div class="product-info-short">
            <p>Xuất xứ: <span class="product-country"><%= productDetail.getCountry()%></span></p>
            <!--                            <p>Màu: <span class="product-build">xám, be, nâu</span></p>-->
            <p>Hãng sản xuất: <span class="product-warranty"><%= productDetail.getBrand()%></span></p>
            <p>Năm sản xuất: <span class="product-warranty"><%= productDetail.getYearMade()%></span></p>
            <p>Phân loại: <span class="product-warranty"><%= productDetail.getTypes()%></span></p>
            <p>Tình trạng: <span class="product-available"><%= productDetail.getAmount()>0 ? "Còn hàng" : "Hết hàng"%></span></p>
          </div>

          <div class="product-qty-buying">
            <div class="product-qty-control">

              <i class="fa-solid fa-plus qty-control btn-plus-qty"></i>
              <input type="text" class="product-qty-input" value="1" maxlength="2" size="2">
              <i class="fa-solid fa-minus qty-control btn-minus-qty"></i>

            </div>
            <div class="add-to-cart-btn"><p>Thêm vào giỏ hàng</p></div>

          </div>


        </div>
        <!--                <div class="asking">-->
        <!--                    <input type="text" class="asking-input client-phone" maxlength="11">-->
        <!--                    <div class="asking-btn"><p>Nhan tu van</p></div>-->
        <!--                </div>-->
      </div>
    </div>
    <div class="seperate-horizontal">
      <div class="seperate-horizontal-item"></div>
    </div>
    <div class="product-detail">
      <h2>Mô tả</h2>
      <div class="product-detail-content">
        <p><%= productDetail.getName()%><br/>
            <%= productDetail.getDescription()%>
      </div>
    </div>
    <!--        <div class="product-evaluate">-->

    <!--        </div>-->
  </div>

  <%@ include file="footer.jsp" %>
</div>

<script src="assets/js/productDetail.js"></script>

</body>
</html>
