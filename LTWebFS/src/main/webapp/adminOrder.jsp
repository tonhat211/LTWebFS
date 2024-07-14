<%@ page import="model.Order" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
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
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
    <link rel="stylesheet" href="assets/css/orderManagement.css">

</head>
<body>
<%
    Map<Order,String> orders = (Map<Order, String>) request.getAttribute("orders");
    if(orders == null) orders = new HashMap<>();
%>

<%@ include file="adminMenu.jsp" %>

<main id="main" class="main">
    <%--thong bao--%>
    <div id="toast">

    </div>

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

    <div class="ad-content" style="padding: 10px">
        <div class="ad_container">
            <div class="ad-content-item">
                <div class="ad_header">

                    <div class="show-flex-row" style="width: 100%; align-items: center">
                        <form class="ad_find-container" action="update-order" method="get" id="searchOrderForm">
                            <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="input" value="">
                            <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="action" value="search" hidden readonly>
                            <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </form>
                        <div>
                            <a class="ad_btn-control btn-up-pro" href="ordermenu?adminMenu=all">Quản lý đơn hàng</a>
                        </div>

                    </div>

                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" class="w-5" style="width: 5%;"> ID</th>
                        <th scope="col" style="width: 10%;">Thời gian</th>
                        <th scope="col" style="width: 45%;">Danh sách sản phẩm</th>
                        <th scope="col" style="width: 15%;">Tổng tiền (VND)</th>
                        <th scope="col" style="width: 15%;">Tình trạng</th>
                    </tr>
                    </thead>
                    <tbody id="ordersTable" class="order-info-container">
                    <%
                        int i=0;
                        for (Map.Entry<Order, String> item : orders.entrySet()) {
                            i++;
//                    System.out.println(item.getKey() + " - " + item.getValue());

                    %>
                        <tr class="<%= (i%2==0) ? "roww": ""%>">
                            <td><%=item.getKey().getId()%></td>
                            <td><%=item.getKey().getDateSet()%> <br/> <%=item.getKey().getTimeSet()%></td>
                            <td><%=item.getValue()%></td>
                            <td><%=ProductUnit.formatPrice(item.getKey().getTotalPrice())%></td>
                            <td class="<%=item.getKey().getColorByStatus()%>"><%=item.getKey().getStatusBefore()%></td>
                        </tr>
                    <%
                        }
                    %>

                    </tbody>
                </table>
            </div>

        </div>


    </div>
    <div class="confirm-popup confirm-stop">
        <div class="modal__overlay" onclick="unshowDetail()">
            <div class="modal__emplpoyee-detail" onclick="event.stopPropagation()">
                <%--                <div class="confirm__message">--%>
                <%--                    <div class="info-container">--%>
                <%--                        <div class="info-container__title">--%>
                <%--                            <img class="w-50" src="./assets/img/employee/3035.jpg" alt="">--%>
                <%--                        </div>--%>
                <%--                        <div class="info-container__content">--%>
                <%--                            <h6 style="font-size: 30px">To minh nhat</h6>--%>
                <%--                            <p>Chuc vu: <span>Truong phong</span></p>--%>
                <%--                            <p>Bo phan: <span>phong it</span></p>--%>

                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                    <div class="info-container">--%>
                <%--                        <div class="info-container__title">--%>
                <%--                            <p>Thong tin lien he:</p>--%>
                <%--                        </div>--%>

                <%--                        <div class="info-container__content">--%>
                <%--                            <p>email: <span>3614861@gmail.com</span></p>--%>
                <%--                            <p>sdt: <span>109253718</span></p>--%>
                <%--                            <p>Chi nhan laafm viec: <span>isdfviuwqhfl waefgiuerhwg</span></p>--%>

                <%--                        </div>--%>
                <%--                    </div>--%>

                <%--                    <div class="info-container">--%>
                <%--                        <div class="info-container__title">--%>
                <%--                            <p>Thong tin lam viec:</p>--%>
                <%--                        </div>--%>
                <%--                        <div class="info-container__content">--%>
                <%--                            <p>ngayf vao lam: <span>1/1/2022</span></p>--%>
                <%--                            <p>ngayf ra: <span>null</span></p>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>

                <%--                    <div class="info-container">--%>
                <%--                        <div class="info-container__title">--%>
                <%--                            <p>Thong tin ca nhan:</p>--%>
                <%--                        </div>--%>
                <%--                        <div class="info-container__content">--%>
                <%--                            <p>gioi tinh: <span>nam</span></p>--%>
                <%--                            <p>ngay sinh: <span>04-10-2003</span></p>--%>
                <%--                            <p>dia chi thuong chu: <span>isdfviuwqhfl waefgiuerhwg</span></p>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

            </div>
        </div>
    </div>

        <script>
            function searchOrder(action, input) {
                console.log('goi update order');
                $.ajax({
                    url: "/LTWebFS/update-order",
                    method: "GET",
                    data: {action: action, input: input},
                    success: function(data) {
                        $("#ordersTable").html(data);

                    }
                });
            }



            $(document).ready(function() {
                document.querySelector("#searchOrderForm").addEventListener('submit',function (event) {
                    event.preventDefault();
                    var formData = new FormData(this);
                    var action = formData.get("action");
                    var  input = formData.get("input");
                    if(input === "") {
                        input = -1;
                    }

                    searchOrder(action, input);
                })

            });
        </script>





</main><!-- End #main -->


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
