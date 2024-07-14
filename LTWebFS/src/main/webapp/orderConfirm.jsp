<%@ page import="java.util.Map" %>
<%@ page import="model.Order" %>
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

    <title>Quản lý đơn hàng - Xác nhận</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons-->
    <link href="assets/img/Logo/favicon_icon.png" rel="icon">
    <!--        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

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
<%--    <link rel="stylesheet" href="assets/css/qldh.css">--%>
    <link rel="stylesheet" href="assets/css/orderManagement.css">
    <link rel="stylesheet" href="assets/css/toast.css">
    <link rel="stylesheet" href="assets/css/table.css">

    <script src="assets/js/toast.js"></script>

</head>
<body>
<%
    Map<Order,String> orders = (Map<Order, String>) request.getAttribute("orders");
    if(orders == null) orders = new HashMap<>();
%>


<%@ include file="adminOrderMenu.jsp" %>


<main id="main" class="main" style="margin-left: 180px">
    <div class="ad-content" style="flex-direction: column">
        <h2 style="text-align: center">Xác nhận đơn hàng</h2>

        <div class="ad_header" style="width: 100%;">
            <div class="show-flex-row" style="width: 100%; align-items: center; justify-content: space-between;">
                <form class="ad_find-container" action="update-order" method="post" id="searchOrderForm" style="display: none">
                    <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="id">
                    <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="action" value="search" hidden readonly>
                    <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="searchStatus" value="0" hidden readonly>
<%--                    <button class="ad_find-btn" id="searchOrderBtn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>--%>
                </form>
                <div>
                    <form class="ad_find-container" action="update-order" id="updateOrderForm" method="post" style="width: 200px;">
                        <input type="text" placeholder="Nhập ID đơn hàng" class="ad_find-input" name="id" id="input_orderID">
                        <input type="text" placeholder="Nhập id don hang" class="ad_find-input" name="action" value="confirm" hidden readonly>
                    </form>
                </div>

            </div>
        </div>
        <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
            <div id="toast-2" style="margin: auto 0">
<%--                <div class="toastt-2 toast-2--success">--%>
<%--                    <p class="toast__msg" style="color: white">thanh cong</p>--%>
<%--                </div>--%>
            </div>
        </div>

<%--        <script> showSuccessToast2("hi");</script>--%>

        <table class="table" style="width: 100%;">
            <thead  style="width: 100%;">
            <tr>
                <th scope="col" class="w-5" style="width: 5%;"> ID</th>
                <th scope="col" style="width: 10%;">Thời gian</th>
                <th scope="col" style="width: 45%;">Danh sách sản phẩm</th>
                <th scope="col" style="width: 15%;">Tổng tiền (VND)</th>
                <th scope="col" style="width: 15%;">Tình trạng</th>
                <th scope="col" style="width: 10%;">Cập nhật</th>

            </tr>
            </thead>
            <tbody class="order-info-container">
                <%=(orders.isEmpty()?"<p>Không có đơn hàng có thể xác nhận</p>":"")
                %>
<%--            <script>showErrorToast2("Xac nhan 12 khong thanh cong. Da hoan thanh");</script>--%>
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
                <td><%=item.getKey().getStatusBefore()%></td>
                <td><button class="btn_confirm_order confirm" onclick="updateOrder('confirm',<%=item.getKey().getId()%>);">Xác nhận</button> </td>
<%--                <td><button  class="btn_confirm_order" onclick="showErrorToast2('hi');">Xac nhan</button> </td>--%>
            </tr>
            <%
                }
            %>


            </tbody>
        </table>

    </div>
</main>

<script>
    function updateOrder(action, id) {
        console.log('goi update order');
        $.ajax({
            url: "/LTWebFS/update-order",
            method: "POST",
            data: {action: action, id: id, method: "hand"},
            success: function(data) {
                console.log(data);
                $(".order-info-container").html(data);

            }
        });
    }

    function searchOrder(action, id, searchStatus) {
        console.log('goi update order');
        console.log(action);
        console.log(id);
        console.log(searchStatus);

        $.ajax({
            url: "/LTWebFS/update-order",
            method: "POST",
            data: {action: action, id: id,searchStatus: searchStatus},
            success: function(data) {
                console.log(data);
                $(".order-info-container").html(data);

            }
        });
    }



    $(document).ready(function() {
        document.querySelector("#searchOrderForm").addEventListener('submit',function (e){
            e.preventDefault();
            var formdata = new FormData(this);
            var id = formdata.get("id");
            var action = formdata.get("action");
            var searchStatus = formdata.get("searchStatus");
            if(id === "" ) {
                return;
            }
            console.log(id);
            console.log(action);
            console.log(searchStatus);
            searchOrder(action,id,searchStatus);

        });

        document.querySelector("#input_orderID").addEventListener('keydown', function (e){
            if(e.key === 'Enter') {
                console.log("ener");
                e.preventDefault();
                    var formdata = new FormData(document.querySelector("#updateOrderForm"));
                    var id = formdata.get("id");
                    var action = formdata.get("action");
                    if(id === "" ) {
                        return;
                    }
                    console.log(id);
                    console.log(action);
                    updateOrder(action,id);
            }
        })


    });
</script>



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
