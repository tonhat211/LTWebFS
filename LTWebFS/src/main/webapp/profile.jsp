<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Quản lý tài khoản</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
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

</head>

<body>

<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <a href="html/index.html" class="logo d-flex align-items-center">
            <!--            <img src="assets/img/logo.png" alt="">-->
            <span class="d-none d-lg-block">Nhóm 63</span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="Tìm kiếm" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <li class="nav-item d-block d-lg-none">
                <a class="nav-link nav-icon search-bar-toggle " href="#">
                    <i class="bi bi-search"></i>
                </a>
            </li><!-- End Search Icon-->

            <!--            <li class="nav-item dropdown">-->

            <!--                <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">-->
            <!--                    <i class="bi bi-bell"></i>-->
            <!--                    <span class="badge bg-primary badge-number">4</span>-->
            <!--                </a>&lt;!&ndash; End Notification Icon &ndash;&gt;-->

            <!--                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">-->
            <!--                    <li class="dropdown-header">-->
            <!--                        You have 4 new notifications-->
            <!--                        <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>-->
            <!--                    </li>-->
            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="notification-item">-->
            <!--                        <i class="bi bi-exclamation-circle text-warning"></i>-->
            <!--                        <div>-->
            <!--                            <h4>Lorem Ipsum</h4>-->
            <!--                            <p>Quae dolorem earum veritatis oditseno</p>-->
            <!--                            <p>30 min. ago</p>-->
            <!--                        </div>-->
            <!--                    </li>-->

            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="notification-item">-->
            <!--                        <i class="bi bi-x-circle text-danger"></i>-->
            <!--                        <div>-->
            <!--                            <h4>Atque rerum nesciunt</h4>-->
            <!--                            <p>Quae dolorem earum veritatis oditseno</p>-->
            <!--                            <p>1 hr. ago</p>-->
            <!--                        </div>-->
            <!--                    </li>-->

            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="notification-item">-->
            <!--                        <i class="bi bi-check-circle text-success"></i>-->
            <!--                        <div>-->
            <!--                            <h4>Sit rerum fuga</h4>-->
            <!--                            <p>Quae dolorem earum veritatis oditseno</p>-->
            <!--                            <p>2 hrs. ago</p>-->
            <!--                        </div>-->
            <!--                    </li>-->

            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="notification-item">-->
            <!--                        <i class="bi bi-info-circle text-primary"></i>-->
            <!--                        <div>-->
            <!--                            <h4>Dicta reprehenderit</h4>-->
            <!--                            <p>Quae dolorem earum veritatis oditseno</p>-->
            <!--                            <p>4 hrs. ago</p>-->
            <!--                        </div>-->
            <!--                    </li>-->

            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->
            <!--                    <li class="dropdown-footer">-->
            <!--                        <a href="#">Show all notifications</a>-->
            <!--                    </li>-->

            <!--                </ul>&lt;!&ndash; End Notification Dropdown Items &ndash;&gt;-->

            <!--            </li>&lt;!&ndash; End Notification Nav &ndash;&gt;-->

            <!--            <li class="nav-item dropdown">-->

            <!--                <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">-->
            <!--                    <i class="bi bi-chat-left-text"></i>-->
            <!--                    <span class="badge bg-success badge-number">3</span>-->
            <!--                </a>&lt;!&ndash; End Messages Icon &ndash;&gt;-->

            <!--                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">-->
            <!--                    <li class="dropdown-header">-->
            <!--                        You have 3 new messages-->
            <!--                        <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>-->
            <!--                    </li>-->
            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="message-item">-->
            <!--                        <a href="#">-->
            <!--                            <img src="assets/img/messages-1.jpg" alt="" class="rounded-circle">-->
            <!--                            <div>-->
            <!--                                <h4>Maria Hudson</h4>-->
            <!--                                <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>-->
            <!--                                <p>4 hrs. ago</p>-->
            <!--                            </div>-->
            <!--                        </a>-->
            <!--                    </li>-->
            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="message-item">-->
            <!--                        <a href="#">-->
            <!--                            <img src="assets/img/messages-2.jpg" alt="" class="rounded-circle">-->
            <!--                            <div>-->
            <!--                                <h4>Anna Nelson</h4>-->
            <!--                                <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>-->
            <!--                                <p>6 hrs. ago</p>-->
            <!--                            </div>-->
            <!--                        </a>-->
            <!--                    </li>-->
            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="message-item">-->
            <!--                        <a href="#">-->
            <!--                            <img src="assets/img/messages-3.jpg" alt="" class="rounded-circle">-->
            <!--                            <div>-->
            <!--                                <h4>David Muldon</h4>-->
            <!--                                <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>-->
            <!--                                <p>8 hrs. ago</p>-->
            <!--                            </div>-->
            <!--                        </a>-->
            <!--                    </li>-->
            <!--                    <li>-->
            <!--                        <hr class="dropdown-divider">-->
            <!--                    </li>-->

            <!--                    <li class="dropdown-footer">-->
            <!--                        <a href="#">Show all messages</a>-->
            <!--                    </li>-->

            <!--                </ul>&lt;!&ndash; End Messages Dropdown Items &ndash;&gt;-->

            <!--            </li>&lt;!&ndash; End Messages Nav &ndash;&gt;-->

            <li class="nav-item dropdown pe-3">
                <%String admin_name = (String) request.getAttribute("admin_name");%>
                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <img src="assets/img/admin/profile-img.jpg" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2"><%=admin_name%></span>
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
                        <a class="dropdown-item d-flex align-items-center" href="html/profile.html">
                            <i class="bi bi-person"></i>
                            <span>Quản lý tài khoản</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <!--                    <li>-->
                    <!--                        <a class="dropdown-item d-flex align-items-center" href="users-profile.html">-->
                    <!--                            <i class="bi bi-gear"></i>-->
                    <!--                            <span>Account Settings</span>-->
                    <!--                        </a>-->
                    <!--                    </li>-->
                    <!--                    <li>-->
                    <!--                        <hr class="dropdown-divider">-->
                    <!--                    </li>-->

                    <!--                    <li>-->
                    <!--                        <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">-->
                    <!--                            <i class="bi bi-question-circle"></i>-->
                    <!--                            <span>Need Help?</span>-->
                    <!--                        </a>-->
                    <!--                    </li>-->
                    <!--                    <li>-->
                    <!--                        <hr class="dropdown-divider">-->
                    <!--                    </li>-->

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
<aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
        <h3 style="text-align: center">[Admin]</h3>
        <li class="nav-item ">
            <a class="nav-link collapsed" href="html/admin_dashboard.html">
                <!--                <i class="bi bi-grid"></i>-->
                <span>Thống kê doanh thu</span>
            </a>
        </li><!-- End Dashboard Nav -->
        <li class="nav-item ">
            <a class="nav-link collapsed" href="html/adminCustomer.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý khách hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminEmployee.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý nhân viên</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminProduct.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminOrder.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý đơn hàng</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminNews.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý tin tức</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminHomes.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý trang chủ</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="html/adminAboutUs.html">
                <!--        <i class="bi bi-person"></i>-->
                <span>Nhà phân phối</span>
            </a>
        </li>

    </ul>
</aside><!-- End Sidebar-->
<main id="main" class="main">

    <div class="pagetitle">

        <h1>Cài đặt</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="html/index.html">Trang chủ</a></li>
                <li class="breadcrumb-item">Người dùng</li>
                <li class="breadcrumb-item active">Quản lý tài khoản</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
        <div class="row">
            <div class="col-xl-4">

                <div class="card">
                    <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
                        <img src="assets/img/admin/profile-img.jpg" alt="Profile" class="rounded-circle">
                        <h2>Kevin Anderson</h2>
                        <h3>Web Designer</h3>
                        <div class="social-links mt-2">
                            <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                            <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                            <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                            <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-xl-8">

                <div class="card">
                    <div class="card-body pt-3">
                        <!-- Bordered Tabs -->
                        <ul class="nav nav-tabs nav-tabs-bordered">

                            <li class="nav-item">
                                <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">
                                    Tổng quan
                                </button>
                            </li>

                            <li class="nav-item">
                                <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">Sửa hồ sơ
                                </button>
                            </li>

                            <li class="nav-item">
                                <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-settings">Cài
                                    đặt
                                </button>
                            </li>

                            <li class="nav-item">
                                <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">
                                    Đổi mật khẩu
                                </button>
                            </li>

                        </ul>
                        <div class="tab-content pt-2">

                            <div class="tab-pane fade show active profile-overview" id="profile-overview">
                                <h5 class="card-title">Tiểu sử</h5>
                                <p class="small fst-italic">Sunt est soluta temporibus accusantium neque nam maiores
                                    cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure
                                    rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at
                                    unde.</p>

                                <h5 class="card-title">Hồ sơ chi tiết</h5>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label ">Tên đầy đủ</div>
                                    <div class="col-lg-9 col-md-8">Kevin Anderson</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Công ty</div>
                                    <div class="col-lg-9 col-md-8">Lueilwitz, Wisoky and Leuschke</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Chức vụ</div>
                                    <div class="col-lg-9 col-md-8">Web Designer</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Quốc gia</div>
                                    <div class="col-lg-9 col-md-8">USA</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Địa chỉ</div>
                                    <div class="col-lg-9 col-md-8">A108 Adam Street, New York, NY 535022</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Số điện thoại</div>
                                    <div class="col-lg-9 col-md-8">(436) 486-3538 x29071</div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-3 col-md-4 label">Email</div>
                                    <div class="col-lg-9 col-md-8">k.anderson@example.com</div>
                                </div>

                            </div>

                            <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                                <!-- Profile Edit Form -->
                                <form>
                                    <div class="row mb-3">
                                        <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">Ảnh đại
                                            diện</label>
                                        <div class="col-md-8 col-lg-9">
                                            <img src="assets/img/admin/profile-img.jpg" alt="Profile">
                                            <div class="pt-2">
                                                <a href="#" class="btn btn-primary btn-sm"
                                                   title="Upload new profile image"><i class="bi bi-upload"></i></a>
                                                <a href="#" class="btn btn-danger btn-sm"
                                                   title="Remove my profile image"><i class="bi bi-trash"></i></a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Tên đầy
                                            đủ</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="fullName" type="text" class="form-control" id="fullName"
                                                   value="Kevin Anderson">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="about" class="col-md-4 col-lg-3 col-form-label">Tiểu sử</label>
                                        <div class="col-md-8 col-lg-9">
                                            <textarea name="about" class="form-control" id="about"
                                                      style="height: 100px">Sunt est soluta temporibus accusantium neque nam maiores cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at unde.</textarea>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="company" class="col-md-4 col-lg-3 col-form-label">Công ty</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="company" type="text" class="form-control" id="company"
                                                   value="Lueilwitz, Wisoky and Leuschke">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Job" class="col-md-4 col-lg-3 col-form-label">Chức vụ</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="job" type="text" class="form-control" id="Job"
                                                   value="Web Designer">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Country" class="col-md-4 col-lg-3 col-form-label">Quốc gia</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="country" type="text" class="form-control" id="Country"
                                                   value="USA">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Address" class="col-md-4 col-lg-3 col-form-label">Địa chỉ</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="address" type="text" class="form-control" id="Address"
                                                   value="A108 Adam Street, New York, NY 535022">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Số điện
                                            thoại</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="phone" type="text" class="form-control" id="Phone"
                                                   value="(436) 486-3538 x29071">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="email" type="email" class="form-control" id="Email"
                                                   value="k.anderson@example.com">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Twitter" class="col-md-4 col-lg-3 col-form-label">Twitter</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="twitter" type="text" class="form-control" id="Twitter"
                                                   value="https://twitter.com/#">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Facebook" class="col-md-4 col-lg-3 col-form-label">Facebook</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="facebook" type="text" class="form-control" id="Facebook"
                                                   value="https://facebook.com/#">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Instagram"
                                               class="col-md-4 col-lg-3 col-form-label">Instagram</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="instagram" type="text" class="form-control" id="Instagram"
                                                   value="https://instagram.com/#">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="Linkedin" class="col-md-4 col-lg-3 col-form-label">Linkedin</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="linkedin" type="text" class="form-control" id="Linkedin"
                                                   value="https://linkedin.com/#">
                                        </div>
                                    </div>

                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                    </div>
                                </form><!-- End Profile Edit Form -->

                            </div>

                            <div class="tab-pane fade pt-3" id="profile-settings">

                                <!-- Settings Form -->
                                <form>

                                    <div class="row mb-3">
                                        <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Thông báo qua
                                            email</label>
                                        <div class="col-md-8 col-lg-9">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="changesMade"
                                                       checked>
                                                <label class="form-check-label" for="changesMade">
                                                    Những thay đổi được thực hiện đối với tài khoản của bạn
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="newProducts"
                                                       checked>
                                                <label class="form-check-label" for="newProducts">
                                                    Thông tin về sản phẩm và dịch vụ mới
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="proOffers">
                                                <label class="form-check-label" for="proOffers">
                                                    Ưu đãi tiếp thị và khuyến mãi
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="securityNotify"
                                                       checked disabled>
                                                <label class="form-check-label" for="securityNotify">
                                                    Cảnh báo bảo mật
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                    </div>
                                </form><!-- End settings Form -->

                            </div>

                            <div class="tab-pane fade pt-3" id="profile-change-password">
                                <!-- Change Password Form -->
                                <form>

                                    <div class="row mb-3">
                                        <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Mật khẩu
                                            hiện tại</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="password" type="password" class="form-control"
                                                   id="currentPassword">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">Mật khẩu
                                            mới</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="newpassword" type="password" class="form-control"
                                                   id="newPassword">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Nhập lại mật
                                            khẩu mới</label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="renewpassword" type="password" class="form-control"
                                                   id="renewPassword">
                                        </div>
                                    </div>

                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                                    </div>
                                </form><!-- End Change Password Form -->

                            </div>

                        </div><!-- End Bordered Tabs -->

                    </div>
                </div>

            </div>
        </div>
    </section>

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer" class="footer">
    <div class="copyright">
        &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
</footer><!-- End Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>

</body>
</html>
