<%@ page import="model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 08/12/2023
  Time: 11:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Admin - Quản lý khách hàng</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--  <link href="assets/img/favicon.png" rel="icon">-->
    <!--  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

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

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/adminCustomer.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
</head>
<body>
<%

    String adminCurrentSearch= (String) request.getAttribute("currentSearch");
        if(adminCurrentSearch== null || adminCurrentSearch.equalsIgnoreCase("")){
            adminCurrentSearch = "";
    }
    ArrayList<Customer> customerList = (ArrayList<Customer>) request.getAttribute("customerList");

%>

<!-- ======= Header ======= -->
<%@ include file="adminMenu.jsp" %>


<main id="main" class="main">

    <div class="pagetitle">
        <h1>Quản lí khách hàng</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lí khách hàng</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <!--    <section class="section dashboard">-->
    <!--        <h1>Content</h1>-->
    <!--    </section>-->
    <div class="ad-content">

        <div class="ad-content-item">
            <div class="ad_header">

                <div class="show-flex-row" style="width: 100%; align-items: center">
                    <form class="ad_find-container" action="admin-search" method="get">
                        <input type="text" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="search" value="<%=adminCurrentSearch%>">
                        <input type="text" style="display: none" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="object" value="customer" readonly>
                        <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </form>
                    <div>
                        <a class="ad_btn-control btn-up-pro" href="goto-add-customer?status=0">Thêm khách hàng</a>
                    </div>

                </div>

            </div>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Họ và tên</th>
                    <th scope="col">Liên hệ</th>
<%--                    <th scope="col">Phone</th>--%>
<%--                    <th scope="col">Address</th>--%>
                    <th scope="col">Ngày đăng kí</th>
                    <th scope="col">Tổng chi tiêu (VND)</th>
                    <th scope="col">Lịch sử mua hàng</th>
                    <th scope="col">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(int i=0; i<customerList.size();i++) {
                %>
                <tr class="<%= i%2==0 ? "roww" : ""%>">
<%--                    <th scope="row"><%=pus.get(i).getId()%></th>--%>
                    <td><%=customerList.get(i).getName() %></td>
                    <td><%=customerList.get(i).getEmail() %> <br> <%=customerList.get(i).getPhone()%> <br> <%=customerList.get(i).getAddress() %> </br></td>

<%--                    <td><%=cuss.get(i).getAddress() %></td>--%>
                    <td><%=customerList.get(i).getDateIn() %></td>
                    <td><%=customerList.get(i).getTotalSpend() %></td>
                    <td><a href="goto-history-buying?cusID=<%=customerList.get(i).getId()%>">Lịch sử mua hàng</a></td>


                    <td><a class="btn-update-product" href="goto-update-customer?id=<%=customerList.get(i).getId()%>">Cập nhật</a></td>
                </tr>
                <%
                    }
                %>

                </tbody>
            </table>
        </div>


    </div>


</main><!-- End #main -->


<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>


<!-- Template Main JS File -->
<script src="assets/js/jquery.js"></script>
<script src="assets/js/main.js"></script>
<script src="assets/js/adCus.js"></script>
<script>
    // getInfo();
    // saveChanged();
</script>
</body>
</html>
