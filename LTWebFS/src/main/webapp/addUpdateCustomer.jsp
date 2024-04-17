<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Thêm/Cập nhật khách hàng</title>
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
</head>
<body>
<%
    User u = (User) request.getAttribute("customer");
    if(u==null) {
        u = new User();
    }
%>
<div class="ad-content mt10">
    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p>Bạn chắc chắn muốn khóa tài khoản này?</p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn yes-confirm" href="addUpdate-customer?action=lock&id=<%=u.getId()%>">Khóa</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>
        </div>
    </div>
    <div class="ad-content-item">

        <div class="ad_container">
            <a href="goto-customer-admin" class="backto-AdminProduct">Quay lại trang quản lí khách hàng</a>

            <div class="form-container">
                <form action="addUpdate-customer" method="get">
                    <div class="show-flex-row">
                        <h4><%= (u.getName()==""?"Thêm khách hàng mới":"Cập nhật thông tin khách hàng") %></h4>

                    </div>
                    <div class="show-flex-row">
                        <div class="grid__row img-showing">
                            <div class="disabled-showing <%= (u.getAvailable()==0?"active":"") %>" style="<%= (u.getName()==""?"position: relative;  left: 0":"") %>" >
                                <div class="disabled-showing-content">
                                    <%= (u.getName()==""?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA") %>
                                </div>
                            </div>
                        </div>
                        <div class="stop_reSale <%= (u.getName()==""?"hide":"") %>" >
                            <div class="btn btn-stop-pro <%= (u.getAvailable()==1?"active":"") %>">Khóa tài khoản</div>
                            <div class="btn btn-resale-pro <%= (u.getAvailable()==0?"active":"") %>"><a class="no-a"
                                    href="addUpdate-customer?action=unlock&id=<%=u.getId()%>">Mở khóa tài khoản</a></div>

                        </div>
                    </div>


                    <div class="form-group w-50">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=u.getId()%>" readonly>
                        <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="name">Name: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập tên" value="<%=u.getName() %>">
                        <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>

                    <div class="form-group w-80">
                        <label class="w-20" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-80" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=u.getEmail() %>">
                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-80" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=u.getPhone() %>">
                    </div>
                    <div class="form-group w-100">
                        <label class="w-20" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=u.getAddress() %>">
                    </div>

                    <div class="show-flex-row">
                        <%String info = u.getInfo();
                            String sex = "";
                            String birthday="";
                            if(info!=null){
                                String infoTokens[] = info.split("=");

                                if(infoTokens.length <2){
                                    sex = "trống";
                                    birthday="trống";
                                }
                                else {
                                    sex = infoTokens[0];
                                    birthday = infoTokens[1];
                                    String[] bdTokens = birthday.split("-");
                                    if((bdTokens[1].length()<2))
                                        bdTokens[1]="0" + bdTokens[1];
                                    if((bdTokens[2]).length()<2)
                                        bdTokens[2]="0" + bdTokens[2];

                                    birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];

                                }
                            }


                        %>
                        <div class="form-group w-50">
                            <label class="w-20" for="sex">Giới tính: </label>
                            <input type="text" class="form-control w-80" id="sex" name="sex" paria-describedby="" placeholder="Enter kind" value="<%=sex%>">
                        </div>
                        <div class="form-group w-50">
                            <label  class="w-20"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Enter date import" value="<%=birthday%>">
                        </div>
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-20"  for="datein">Ngày tham gia: </label>
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày tham gia" value="<%= u.getDateIn().getDateInMonthDayYearSql() %>">
                    </div>

                    <div class="form-group" style="display: none">
                        <label class="w-20" for="action" class="input-title">action</label>
                        <input type="text" class="form-control" id="action" name="action" aria-describedby="" placeholder="Enter img url" value="<%= (u.getName()==""?"add":"update") %>">
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="w-20" for="status" class="input-title">status</label>
                        <input type="text" class="form-control" id="status" name="status" aria-describedby="" placeholder="Enter img url" value="1">
                    </div>

                    <div class="show-flex-row">
                        <div class="ad_func-container">
                            <div><a class="btn btn-third" href="goto-customer-admin">Hủy</a></div>
                        </div>
                        <div class="ad_func-container">
                            <button class="btn btn-primary" type="submit"><%= (u.getName()==""?"Thêm":"Lưu") %></button>
                        </div>
                    </div>
                    <!--                    <button type="submit" class="btn btn-primary">Submit</button>-->
                </form>

            </div>
        </div>
    </div>
</div>
<script src="assets/js/addUpdate.js"></script>
</body>
</html>