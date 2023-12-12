<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 08/12/2023
  Time: 11:50 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <!--        <i class="bi bi-list toggle-sidebar-btn"></i>-->
        <div class="logo">
            <a href="index.html">
                <img src="assets/img/Logo/favicon_icon.png" alt="Logo"
                     style="width: 40px; height: 40px; border-radius: 5px">
            </a>
        </div>

    </div><!-- End Logo -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <li class="nav-item d-block d-lg-none">
                <a class="nav-link nav-icon search-bar-toggle " href="#">
                    <i class="bi bi-search"></i>
                </a>
            </li><!-- End Search Icon-->
            <li class="nav-item dropdown pe-3">

                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <img src="assets/img/profile/profile-img.jpg" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>
                </a><!-- End Profile Iamge Icon -->

                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6>Kevin Anderson</h6>
                        <span>Web Designer</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="profile.html">
                            <i class="bi bi-person"></i>
                            <span>Cài đặt</span>
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
            <a class="nav-link collapsed" href="admin_dashboard.html">
                <!--                <i class="bi bi-grid"></i>-->
                <span>Thống kê doanh thu</span>
            </a>
        </li><!-- End Dashboard Nav -->
        <li class="nav-item ">
            <a class="nav-link collapsed" href="adminCustomer.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý khách hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminEmployee.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý nhân viên</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link " href="adminProduct.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminOrder.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý đơn hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminNews.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý tin tức</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminHomes.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý trang chủ</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="adminAboutUs.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Nhà phân phối</span>
            </a>
        </li>

    </ul>
</aside><!-- End Sidebar-->

<main id="main" class="main">

    <div class="pagetitle">
        <h1>Quản lí sản phẩm</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lí sản phẩm</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <!--    <section class="section dashboard">-->
    <!--        <h1>Content</h1>-->
    <!--    </section>-->
    <div class="ad-content">
        <!--                        <div class="temp"> <p>noi dung</p></div> &lt;!&ndash;Xoa dong nay truoc khi code&ndash;&gt;-->

        <div class="ad-content-item">
            <div class="ad_header">
                <div class="ad_find-container">
                    <input type="text" placeholder="Nhập id hoặc tên" class="ad_find-input">
                    <div class="ad_find-btn"><i class="fa-solid fa-magnifying-glass"></i></div>
                </div>
                <!--                            <div class="ad_func-container">-->
                <!--                                <div class="ad_func-add-btn ad_func-btn">Thêm</div>-->
                <!--                            </div>-->
            </div>
            <div class="ad_container">
                <div class="pro-showing">
                    <div class="pro-img-cont">
                        <div class="grid__row">
                            <div class="grid-col-2 mtb-5px">
                                <img src="assets/img/product-img/A/may-tro-thinh.jpg" alt="" class="pro-img-item">
                            </div>
                            <div class="grid-col-2 mtb-5px">
                                <img src="assets/img/product-img/A/may-tro-thinh.jpg" alt="" class="pro-img-item">
                            </div>
                            <div class="grid-col-2 mtb-5px">
                                <img src="assets/img/product-img/A/may-tro-thinh.jpg" alt="" class="pro-img-item">
                            </div>
                            <div class="grid-col-2 mtb-5px">
                                <img src="assets/img/product-img/A/may-tro-thinh.jpg" alt="" class="pro-img-item">
                            </div>
                        </div>
                    </div>
                    <div class="seperate-horizontal-90-ad mtb-5px"></div>
                    <div class="pro-info">
                        <p>ID sản phẩm: <span class="pro_id">#pro9</span></p>
                        <p>Tên: <span class="pro_name">Máy trợ thính châu âu</span></p>
                        <p>Phân loại: <span class="pro_kind">A</span></p>
                        <p>Giá: <span class="pro_price">1500000 </span>VND</p>
                        <p>Số lượng hiện có: <span class="pro_number">1500 </span> (sản phẩm)</p>
                        <p>Năm sản xuất: <span class="pro_year">2023</span></p>
                        <p>Ngày nhập : <span class="pro_date-in">22.10.2023</span></p>
                        <p>Xuất xứ: <span class="pro_from">Đức</span></p>
                        <p>Hãng sản xuất: <span class="pro_brand">Apple</span></p>
                        <p>Mô tả: <span class="pro_des">là model thiết bị mới được sản xuất và lắp ráp trực tiếp tại châu Âu, đảm bảo chất
lượng của sp
</span></p>
                    </div>
                    <div class="disabled-showing t70 l140">NGƯNG BÁN</div>
                </div>

                <div class="customer-control">
                    <div class="ad_btn-control btn-up-pro">Cập nhật thông tin</div>
                    <div class="ad_btn-control btn-stop-pro">Dừng bán</div>
                    <div class="ad_btn-control btn-resale-pro">Bán lại</div>
                </div>
            </div>
        </div>
        <div class="confirm-popup confirm-stop">
            <div class="modal__overlay">
                <div class="modal__confirm-content" onclick="event.stopPropagation()">
                    <div class="confirm__message">
                        <p>Bạn chắc chắn muốn dừng bán sản phẩm này?</p>
                    </div>
                    <div class="confirm__control">
                        <div class="confirm-btn yes-confirm">Dừng</div>
                        <div class="confirm-btn no-confirm">Hủy</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="confirm-popup confirm-resale">
            <div class="modal__overlay">
                <div class="modal__confirm-content" onclick="event.stopPropagation()">
                    <div class="confirm__message">
                        <p>Bạn chắc chắn muốn bán lại bán sản phẩm này?</p>
                    </div>
                    <div class="confirm__control">
                        <div class="confirm-btn yes-resale-confirm">Bán lại</div>
                        <div class="confirm-btn no-resale-confirm">Hủy</div>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <div class="ad-content mt10">
        <div class="confirm-popup add-img-box">
            <div class="modal__overlay">
                <div class="modal__confirm-content" onclick="event.stopPropagation()">
                    <div class="confirm__message">
                        <div class="input-wrap">
                            <label for="pro-img" class="input-title">Nhập đường dẫn tới hình ảnh.</label>
                            <input type="text" class="input-item" name="pro-img" id="pro-img">
                        </div>
                    </div>
                    <div class="confirm__control">
                        <div class="confirm-btn add-img-btn">Thêm</div>
                        <div class="confirm-btn cancel-add-img-btn">Hủy</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ad-content-item">
            <div class="ad_header">
                <div class="ad_func-container">
                    <div class="ad_func-add-btn ad_func-btn active">Thêm</div>
                    <div class="ad_func-save-btn ad_func-btn">Lưu</div>
                </div>

            </div>
            <div class="ad_container">
                <div class="form-container">
                    <form action="">
                        <div class="show-flex-row">
                            <div class="input-wrap">
                                <label for="pro-name" class="input-title">Tên sản phẩm</label>
                                <input type="text" class="input-item" name="pro-name" id="pro-name">
                                <span class="input-error"></span>
                            </div>
                            <div class="input-wrap">
                                <label class="input-title">Ảnh</label>

                                <i class="fa-solid fa-plus ad_btn-control btn-add-em-img"></i>
                            </div>

                        </div>
                        <div class="input-wrap">
                            <label for="pro-kind" class="input-title">Phân loại</label>
                            <input type="text" class="input-item" name="pro-kind" id="pro-kind">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label for="pro-price" class="input-title">Giá (VND)</label>
                            <input type="text" class="input-item" name="pro-price" id="pro-price">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label for="pro-number" class="input-title">Số lượng hiện có</label>
                            <input type="text" class="input-item" name="pro-number" id="pro-number">
                            <span class="input-error"></span>
                        </div>
                        <div class="show-flex-row">

                            <div class="input-wrap">
                                <label for="pro-from" class="input-title">Xuất xứ</label>
                                <input type="text" class="input-item" name="pro-from" id="pro-from">
                                <span class="input-error"></span>
                            </div>
                            <div class="input-wrap">
                                <label for="pro-brand" class="input-title">Hãng sản xuất</label>
                                <input type="text" class="input-item" name="pro-brand" id="pro-brand">
                                <span class="input-error"></span>
                            </div>

                        </div>

                        <div class="show-flex-row">
                            <div class="input-wrap">
                                <label for="pro-year" class="input-title">Năm sản xuất</label>
                                <input type="text" class="input-item" name="pro-year" id="pro-year">
                                <span class="input-error"></span>
                            </div>
                            <div class="input-wrap">
                                <label for="pro-date-in" class="input-title">Ngày nhập </label>
                                <input type="text" class="input-item" name="pro-date-in" id="pro-date-in">
                                <span class="input-error"></span>
                            </div>

                        </div>
                        <div class="input-wrap">
                            <label for="pro-des" class="input-title">Mô tả</label>
                            <textarea  type="text" class="input-item" name="pro-des" id="pro-des" rows="6">
                                            </textarea>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<script src="assets/js/adPro.js"></script>
<script>
    // getInfo();
    // saveChanged();
    // const inputs = $$('.form-container input');
    // console.log(inputs);
    // for(let i of inputs) {
    //     i.value = "1";
    // }
    init();

</script>
</body>
</html>