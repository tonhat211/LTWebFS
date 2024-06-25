<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 08/12/2023
  Time: 11:50 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./assets/common/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Admin - Quản lý sản phẩm</title>


    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--  <link href="assets/img/favicon.png" rel="icon">-->
    <!--  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/adminCustomer.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/adminProduct.css">
</head>
<body>
<%
    String currentSearch = ((String) session.getAttribute("currentSearch"));
    if(currentSearch== null || currentSearch.equalsIgnoreCase("")){
        currentSearch = "";
    }

    String adminCurrentSearchProduct = ((String) session.getAttribute("adminCurrentSearchProduct"))==null ? "" : ((String) session.getAttribute("adminCurrentSearchProduct"));


    ArrayList<ProductUnit> pus = (ArrayList<ProductUnit>) request.getAttribute("productUnitList");

%>
<!-- ======= Header ======= -->
<%@ include file="adminMenu.jsp" %>


<main id="main" class="main">

    <div class="pagetitle">
        <h1>Quản lí sản phẩm</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lí sản phẩm</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <!--    <section class="section dashboard">-->
    <!--        <h1>Content</h1>-->
    <!--    </section>-->
    <div class="ad-content">
        <div class="ad_header" style="width: 100%; flex-direction: column">
            <div class="show-flex-row" style="width: 100%; align-items: center">
                <form class="ad_find-container" action="admin-search-product" method="get">
                    <input type="text" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="search" value="<%=adminCurrentSearchProduct%>">
                    <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div>
                    <a class="ad_btn-control btn-up-pro" href="goto-add-product?status=0">Thêm sản phẩm</a>
                </div>
                <div>
                    <a class="ad_btn-control" href="goto-add-product?status=0">Thêm sản phẩm</a>
                </div>
                <div>
                    <a class="ad_btn-control btn-up-pro" href="goto-add-product?status=0">Thêm sản phẩm</a>
                </div>
            </div>
            <div style="width: 100%" id="product-action">
                <div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                <div style="width: 100%">

                    <form action="" style="width: 70%; margin: 0 auto">
                        <h4 style="text-align: center">Nhap kho</h4>
                        <div class="show-flex-row">
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="id">ID: </label>
                                <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Enter name" value="">
                                <div class="error">Vui lòng đăng nhập để sử dụng giỏ hàng</div>

                            </div>

                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="amount">So luong: </label>
                                <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Enter name" value="">
                            </div>
                        </div>
                        <div class="form-group w-30" style="margin: 10px 10px">
                            <label class="w-20" for="reason">Nguyen nhan: </label>

                            <textarea  type="text" class="form-control" name="reason" id="reason" rows="6"></textarea>

                        </div>

                        <div class="show-flex-row" style="justify-content: end">
                            <button class="btn btn-primary" type="submit" style="margin: 10px 10px;">Nhap</button>
                        </div>

                    </form>
                </div>
            </div>



        </div>

    </div>

    <table class="table" style="width: 100vw;" id="info-table">
        <thead>
            <tr>
                <th scope="col" style="width: 10%;">ID</th>
                <th scope="col" style="width: 60%;">Tên</th>
                <th scope="col" style="width: 10%;">So luong nhap</th>
                <th scope="col" style="width: 20%">Thoi gian</th>
            </tr>
        </thead>
        <tbody >
            <tr class="roww">
                <th scope="row">12</th>
                <td>may loc </td>
                <td>100</td>
                <td>8213</td>
            </tr>


        </tbody>
    </table>

    <div class="ad-content-item">

    </div>
    </div>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<script src="assets/js/adPro.js"></script>
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
</body>
</html>
