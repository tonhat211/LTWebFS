<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 12/12/2023
  Time: 6:58 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Quản lý đơn hàng</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons-->
    <link href="assets/img/Logo/favicon_icon.png" rel="icon">
    <!--        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

    <!-- CSS file -->
    <link rel="stylesheet" href="assets/css/qldh.css">
</head>
<body>
<%@ include file="adminMenu.jsp" %>


<main id="main" class="main" style="">

    <div class="pagetitle">
        <h1>Đơn hàng</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lý đơn hàng</li>
            </ol>
        </nav>
    </div>
    <!-- End Page Title -->
    <div class="grid-col-10">
        <div class="contents">
            <div class="btn-search">
                <div class="search-bar">
                    <form class="search-form d-flex align-items-center" method="POST" action="#">
                        <input type="text" name="query" placeholder="Tìm kiếm mã đơn hàng"
                               title="Enter search keyword">
                        <button type="submit" title="Search" style="position: absolute;
                                                          left: 0;
                                                          background-color: #fff;
                                                          margin-left: -15px; padding: 5px; border-radius: 50%">
                            <i class="bi bi-search" style="padding: 5px"></i></button>
                    </form>
                </div><!-- End Search -->
            </div>
            <div class="content-1 hei-300">
                <div class="container hei-300">
                    <div class="content">
                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/tải%20xuống.jpg" alt="Máy tạo oxi gia đình">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVN67NND88D</a> đã được giao thành công</span>
                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <hr>

                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/laser-dieu-tri.png" alt="Laser điều trị">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVN67NND88D</a> đã được giao thành công</span>
                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <hr>

                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/may-thuy-luc-dieu-tri.png" alt="Máy thủy lực điều trị">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVN097HHD7DJ</a> đã được giao thành công</span>
                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <hr>

                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/may-vi-song.png" alt="Máy vi sóng">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVN89DN66DH8</a> đã được giao thành công</span>

                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <hr>

                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/31_Thiet_bi_y_te_vat_ly_tri_lieu.png"
                                         alt="Thiết bị cần thiết">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVN67NND88D</a> đã được giao thành công</span>
                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <hr>

                        <div class="row-col">
                            <div class="up">
                                <span> Mã đơn hàng <a href="#">JS907H7G6FJJ</a> </span>
                                <hr>
                                <span><a href="#" style="color: red">Hoàn Thành</a></span>
                                <hr>
                                <span>Đơn vị vận chuyển: <a href="">J&T Express</a></span>
                            </div>

                            <div class="left">
                                <div class="img">
                                    <img src="assets/img/admin/laser-dieu-tri.png" alt="Laser điều trị">
                                </div>

                                <div class="contention">
                                    <div class="title">
                                        <span>Kiện hàng có mã vận đơn <a href="#">SPVNI098DJD77DH</a> đã được giao thành công</span>
                                    </div>

                                    <div class="time">
                                        <span>11:13 03-11-2023</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Vendor JS Files -->
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
