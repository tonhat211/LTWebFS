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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/adminCustomer.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/adminProduct.css">
    <link rel="stylesheet" href="assets/css/toast.css">
    <link rel="stylesheet" href="assets/css/login.css">


    <script src="assets/js/toast.js"></script>
    <script src="assets/js/adPro.js">

    </script>

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
        <div class="ad_header" style="width: 100%; flex-direction: column" id="action-product">
            <div class="show-flex-row" style="width: 100%; align-items: center">
                <form class="ad_find-container" action="admin-search-product" method="get" id="searchForm">
                    <input type="text" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="input" value="<%=adminCurrentSearchProduct%>">
                    <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div>
                    <a class="ad_btn-control" id="export-product-btn">Xuất kho</a>

                </div>
                <div>
                    <a class="ad_btn-control" id="import-product-btn">Nhập kho</a>
                </div>
                <div>
                    <a class="ad_btn-control btn-up-pro" href="admin-product?action=prepareAdd">Thêm sản phẩm</a>

                </div>

            </div>


            <div style="width: 100%" id="product-action">

            </div>
        </div>

    </div>

<%--    <%--%>
<%--        for(ProductSuperDetail p : psds) {--%>
<%--    %>--%>

    <table class="table" id="info-table" style="width: 100vw;">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Tên</th>
            <th scope="col">Lĩnh vực</th>
            <th scope="col">Thương hiệu</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">Tồn kho</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody id="info-table-body">
            <%
                for(int i=0; i<pus.size();i++) {
            %>
        <tr class="<%= i%2==0 ? "roww" : ""%>">
            <th scope="row"><%=pus.get(i).getId()%></th>
            <td><%=pus.get(i).getName() %></td>
            <td><%=pus.get(i).getKind() %></td>
            <td><%=pus.get(i).getBrand() %></td>
            <td><fmt:formatNumber value="<%=pus.get(i).getPrice() %>" pattern="#,##0.00"/>
                VND</td>
            <td><%=pus.get(i).getAmount() %></td>
            <td><a class="btn-update-product" href="admin-product?action=prepareUpdate&&id=<%=pus.get(i).getId()%>">Cập nhật</a></td>
        </tr>
            <%
                }
            %>

                    </tbody>
                </table>

        <div class="ad-content-item">


        </div>
    </div>

    <script>

        document.querySelector("#searchForm").addEventListener('submit',function (event){
            event.preventDefault();
            var data = new FormData(this);
            var input = data.get("input");
            var action ="search";
            searchProduct(action, input);

        });

        function searchProduct(action, input) {
            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "GET",
                data: {action: action, input: input},
                success: function(data) {
                    console.log(data);
                    $("#info-table-body").html(data);

                }
            });
        }
    </script>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<script>



</script>

<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<%--<script src="assets/js/adPro.js"></script>--%>
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
