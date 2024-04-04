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
<%
    String user = (String) session.getAttribute("userName");
    if(user== null || user.equalsIgnoreCase("")){
        user = "";
    }

%>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <div class="logo">
            <a href="index.jsp">
                <img src="assets/img/Logo/favicon_icon.png" alt="Logo"
                     style="width: 40px; height: 40px; border-radius: 5px">
            </a>
        </div>
        <!--    <i class="bi bi-list toggle-sidebar-btn"></i>-->
    </div><!-- End Logo -->

    <!--  <div class="search-bar">-->
    <!--    <form class="search-form d-flex align-items-center" method="POST" action="#">-->
    <!--      <input type="text" name="query" placeholder="Tìm kiếm" title="Enter search keyword">-->
    <!--      <button type="submit" title="Search"><i class="bi bi-search"></i></button>-->
    <!--    </form>-->
    <!--  </div>&lt;!&ndash; End Search Bar &ndash;&gt;-->

    <!--  <div class="search-bar">-->
    <!--    <form class="search-form d-flex align-items-center" method="POST" action="#">-->
    <!--      <input type="text" name="query" placeholder="Tìm kiếm" title="Enter search keyword">-->
    <!--      <button type="submit" title="Search"><i class="bi bi-search"></i></button>-->
    <!--    </form>-->
    <!--  </div>&lt;!&ndash; End Search Bar &ndash;&gt;-->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <!--      <li class="nav-item d-block d-lg-none">-->
            <!--        <a class="nav-link nav-icon search-bar-toggle " href="#">-->
            <!--          <i class="bi bi-search"></i>-->
            <!--        </a>-->
            <!--      </li>&lt;!&ndash; End Search Icon&ndash;&gt;-->

            <li class="nav-item dropdown pe-3">

                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#"
                   data-bs-toggle="dropdown">
                    <img src="assets/img/admin/profile-img.jpg" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2"><%=user%></span>
                </a><!-- End Profile Iamge Icon -->

                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6><%=user%></h6>
                        <span>Web Designer</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="html/profile.html">
                            <i class="bi bi-person"></i>
                            <span>Quản lý tài khoản</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <i class="bi bi-box-arrow-right"></i>
                            <span>Đăng xuất</span>
                        </a>
                    </li>

                </ul><!-- End Profile Dropdown Items -->
            </li><!-- End Profile Nav -->

        </ul>
    </nav><!-- End Icons Navigation -->

</header>
<!-- End Header -->

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
        <h3 style="text-align: center">[Admin]</h3>
        <li class="nav-item ">
            <a class="nav-link collapsed" href="admin_dashboard.jsp">
                <!--                <i class="bi bi-grid"></i>-->
                <span>Thống kê doanh thu</span>
            </a>
        </li><!-- End Dashboard Nav -->
        <li class="nav-item ">
            <a class="nav-link collapsed" href="adminCustomer.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý khách hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminEmployee.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý nhân viên</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminProduct.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link " href="adminOrder.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý đơn hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminNews.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý tin tức</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminHomes.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý trang chủ</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminAboutUs.jsp">
                <!--        <i class="bi bi-person"></i>-->
                <span>Nhà phân phối</span>
            </a>
        </li>

    </ul>
</aside><!-- End Sidebar-->

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
