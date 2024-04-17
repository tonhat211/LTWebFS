<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Lịch sử mua hàng</title>
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
    Customer cus = (Customer) request.getAttribute("customer");
    if(cus==null) {
        cus = new Customer();
    }

    ArrayList<Order> os = (ArrayList<Order>) request.getAttribute("orderList");
    if(os==null) {
        os = new ArrayList<>();
    }





%>
<div class="ad-content mt10">
    <div class="ad-content-item">
        <div class="ad_container">
            <a href="goto-customer-admin" class="backto-AdminProduct">Quay lại trang quản lí khách hàng</a>
            <div class="customer-info">
                <div class="show-flex-row">
                    <h4>Khách hàng</h4>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Họ và tên</th>
                        <th scope="col">Liên hệ</th>
                        <th scope="col">Ngày đăng kí</th>
                        <th scope="col">Tổng chi tiêu</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><%= cus.getName()%></td>
                        <td><%=cus.getEmail() %> <br> <%=cus.getPhone()%> <br> <%=cus.getAddress() %> </br></td>
                        <td><%=cus.getDateIn() %></td>
                        <td><%=cus.getTotalSpend() %></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="history-buying customer-info">
                <div class="show-flex-row">
                    <h4>Lịch sử  mua hàng</h4>
                </div>
                <table class="table">

                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tổng tiền (VND)</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Thời gian tạo</th>
                        <th scope="col">Tình trạng</th>
                        <th scope="col">Chi tiết đơn hàng</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                        for(int i=0; i<os.size();i++) {
                    %>
<%--                    <%= i%2==0 ? "roww" : ""%>--%>
                    <tr class=" <%= i%2==0 ? "roww" : ""%>">
                        <td><%=os.get(i).getId()%></td>
                        <td><%=os.get(i).getTotalPrice()%></td>
                        <td><%=os.get(i).getDateSet()%></td>
                        <td><%=os.get(i).getTimeSet()%></td>
                        <td><%=(os.get(i).getIsCompleted()==1?"Đã hoàn thành":"Chưa hoàn thành")%></td>
                        <td><a href="goto-order-detail?ordID=<%=os.get(i).getId()%>">Chi tiết đơn hàng</a></td>

                    </tr>
                    <%
                        }
                    %>

                    </tbody>
                </table>


                    <% if(os.isEmpty()){

                    %>
                    <h4>Chưa có đơn hàng nào</h4>
                    <%
                        }
                    %>

            </div>
        </div>
    </div>
</div>

</body>
</html>
