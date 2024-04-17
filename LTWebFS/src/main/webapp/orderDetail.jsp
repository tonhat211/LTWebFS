<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Chi tiết đơn hàng</title>
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
    <link rel="stylesheet" href="assets/css/addUpdate.css">
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/historyBuying.css">
    <link rel="stylesheet" href="assets/css/adminProduct.css">
</head>
<body>
<%
    Order o = (Order) request.getAttribute("order");
    if(o==null) {
        o = new Order();
    }

    ArrayList<DeOrder> dos = (ArrayList<DeOrder>) request.getAttribute("dos");
    if(dos==null) {
        dos = new ArrayList<>();
    }





%>
<div class="ad-content mt10">
    <div class="ad-content-item">
        <div class="ad_container">
            <a href="goto-history-buying?cusID=<%=o.getCusID()%>" class="backto-AdminProduct">Quay lại</a>
            <div class="customer-info">
                <div class="show-flex-row">
                    <h4>Đơn hàng</h4>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tổng tiền</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Thời gian tạo</th>
                        <th scope="col">Tình trạng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><%= o.getId()%></td>
                        <td><%= o.getTotalPrice()%></td>
                        <td><%= o.getDateSet()%></td>
                        <td><%=o.getTimeSet()%></td>
                        <td><%= o.getIsCompleted()==1 ? "Đã hoàn thành" : "Chưa hoàn thành"%></td>


                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="history-buying customer-info">
                <div class="show-flex-row">
                    <h4>Chi tiết đơn hàng</h4>
                </div>
                <table class="table">

                    <thead>
                    <tr>
                        <th scope="col">ID đơn hàng</th>
                        <th scope="col">ID sản phẩm</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Số lượng</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        for(int i=0; i<dos.size();i++) {
                    %>
<%--                    <%= i%2==0 ? "roww" : ""%>--%>
                    <tr class=" <%= i%2==0 ? "roww" : ""%>">
                        <td><%=dos.get(i).getOrdID()%></td>
                        <td><%=dos.get(i).getProID()%></td>
                        <td><%=dos.get(i).getpName()%></td>
                        <td><%=dos.get(i).getPriceUnit()%></td>
                        <td><%=dos.get(i).getQty()%></td>

                    </tr>
                    <%
                        }
                    %>


                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>

</body>
</html>
