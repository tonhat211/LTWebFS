<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 10/04/2024
  Time: 3:16 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.Employee" %><%--
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
    <link rel="stylesheet" href="assets/css/adminProduct.css">
    <link rel="stylesheet" href="assets/css/adminMenu.css">

</head>
<body>
<%
//    String currentSearch = ((String) session.getAttribute("currentSearch"));
//    if(currentSearch== null || currentSearch.equalsIgnoreCase("")){
//        currentSearch = "";
//    }
    String currentAdminMenu = ((String) session.getAttribute("currentAdminMenu"))==null ? "dashboard" : ((String) session.getAttribute("currentAdminMenu"));
    Employee e = (Employee) session.getAttribute("adminloging");
    if(e== null) {
        e= new Employee();
    }

    String roles = e.getRole();
    if(roles == null) {
        roles ="";
    }

//    ArrayList<ProductUnit> pus = (ArrayList<ProductUnit>) request.getAttribute("productUnitList");

%>
<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <!--        <i class="bi bi-list toggle-sidebar-btn"></i>-->
        <div class="logo">
            <a href="index.jsp">
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
                    <span class="d-none d-md-block dropdown-toggle ps-2"><%=e.getName()%></span>
                </a><!-- End Profile Iamge Icon -->

                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6><%=e.getName()%></h6>
                        <span>Web Designer</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="goto-update-employee-info">
                            <i class="bi bi-person"></i>
                            <span>Cập nhật thông tin</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="log-out">
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
        <li class="nav-item <%=roles.contains("dashboard") ? "" : "hide" %>">
            <a class="nav-link  <%= currentAdminMenu.equalsIgnoreCase("dashboard") ? "" :"collapsed" %> " href="admin-menu-controller?adminMenu=dashboard">
                <!--                <i class="bi bi-grid"></i>-->
                <span>Thống kê</span>
            </a>
        </li><!-- End Dashboard Nav -->
        <li class="nav-item <%=roles.contains("customer") ? "" : "hide" %>">
            <a class="nav-link <%= currentAdminMenu.equalsIgnoreCase("customer") ? "" :"collapsed" %>" href="admin-menu-controller?adminMenu=customer">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý khách hàng</span>
            </a>
        </li>
        <li class="nav-item <%=roles.contains("employee") ? "" : "hide" %>">
            <a class="nav-link <%= currentAdminMenu.equalsIgnoreCase("employee") ? "" :"collapsed" %>" href="admin-menu-controller?adminMenu=employee">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý nhân viên</span>
            </a>
        </li>
        <li class="nav-item <%=roles.contains("product") ? "" : "hide" %>">
            <a class="nav-link <%= currentAdminMenu.equalsIgnoreCase("product") ? "" :"collapsed" %>" href="admin-menu-controller?adminMenu=product">
                <!--        <i class="bi bi-person"></i>-->
                <span>Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="nav-item <%=roles.contains("order") ? "" : "hide" %>">
            <a class="nav-link <%= currentAdminMenu.equalsIgnoreCase("order") ? "" :"collapsed" %>" href="admin-menu-controller?adminMenu=order">
                <!--        <i class="bi bi-person"></i>-->
                <span>Đơn hàng</span>
            </a>
        </li>
        <li class="nav-item <%=roles.contains("log") ? "" : "hide" %>">
            <a class="nav-link <%= currentAdminMenu.equalsIgnoreCase("log") ? "" :"collapsed" %>" href="admin-menu-controller?adminMenu=log&index=1">
                <!--        <i class="bi bi-person"></i>-->
                <span>Log</span>
            </a>
        </li>


    </ul>
</aside><!-- End Sidebar-->





<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<script>
    // getInfo();
    // saveChanged();
    // const inputs = $$('.form-container input');
    // console.log(inputs);
    // for(let i of inputs) {
    //     i.value = "1";
    // }
    // document.querySelector('.btn-add-em-img').addEventListener('click',showAddImg);
    // init();

</script>
</body>
</html>

