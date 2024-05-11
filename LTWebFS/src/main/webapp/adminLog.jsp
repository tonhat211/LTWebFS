<%@ page import="model.*" %><%--
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

    <title>Quản lý Log</title>
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
    <link rel="stylesheet" href="assets/css/adminLog.css">


</head>
<body>
<%

    String adminCurrentSearch= (String) request.getAttribute("currentSearch");
        if(adminCurrentSearch== null || adminCurrentSearch.equalsIgnoreCase("")){
            adminCurrentSearch = "";
    }
    ArrayList<Customer> customerList = (ArrayList<Customer>) request.getAttribute("customerList");

    ArrayList<Log> logs = (ArrayList<Log>) request.getAttribute("logs");
    if(logs == null) {
        logs = new ArrayList<>();
    }
    int index = (int) request.getAttribute("index");
    int limit = (int) request.getAttribute("limit");
    int numofPage = (int) request.getAttribute("numofPage");


%>

<!-- ======= Header ======= -->
<%@ include file="adminMenu.jsp" %>


<main id="main" class="main">

    <div class="pagetitle">
        <h1>Quản lý Log</h1>
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
    <div class="ad-content" style="padding: 10px 4px">

        <div class="ad-content-item" style="padding: 0">
            <div class="container">
                <a href="log-control?action=alert">Dọn dẹp log alert</a>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 10%;">Mức độ</th>
                        <th scope="col" style="width: 20%;">Địa chỉ IP </br> Thông tin </br> Thời gian</th>
                        <th scope="col" style="width: 20%;">Hành động</th>
                        <th scope="col" style="width: 25%;">Giá trị trước</th>
                        <th scope="col" style="width: 25%;">Giá trị sau</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(Log t : logs) {
                    %>
                    <tr>
                        <%
                            String levelString="";
                            switch (t.getLevel()){
                                case 1: {
                                    levelString = "ALERT";
                                    break;
                                }
                                case 2: {
                                    levelString = "WARNING";
                                    break;
                                }
                                case 3: {
                                    levelString = "DANGER";
                                    break;
                                }

                            }
                        %>

                        <td class="<%=levelString%>"><%=levelString%></td>
                        <td><%=t.getIp()%> </br> <%=t.getInfo()%> </br> <%=t.getTime()%></td>
                        <td><%=t.getAction()%></td>
                        <td><%=t.getPreValue()%></td>
                        <td><%=t.getAfterValue()%></td>
                    </tr>

                    <%
                        }
                    %>

                    </tbody>
                </table>
                <div class="show-flex-row" style="justify-content: center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <div class="grid__row">
                                <%
                                    for(int i =0; i<numofPage;i++) {
                                %>
                                <div class="grid-col-1-padding-0"  style="width: 40px; height: 40px;">
                                    <li class="page-item"><a class="page-link <%=index%> <%=i%> <%=(i+1) == index ? "active" : ""%>" href="goto-logManagement?index=<%=i+1%>"><%=i+1%></a></li>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>


    </div>


</main><!-- End #main -->


<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>


<!-- Template Main JS File -->
<script src="assets/js/jquery.js"></script>
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
