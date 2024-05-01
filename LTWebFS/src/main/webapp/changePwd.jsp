<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Cập nhật thông tin</title>
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
    <link rel="stylesheet" href="assets/css/updateCustomerInfo.css">
    <link rel="stylesheet" href="assets/css/changePwd.css">

</head>
<body>
<%
    User u = (User) request.getAttribute("customer");
    if(u==null) {
        u = new User();
    }

    String object = (String) request.getAttribute("object");
    if(object== null){
        object="customer";
    }
    String status = (String) request.getAttribute("status");
%>

<%
    if(status == null) {
        status = "";
    }
%>




<div class="ad-content mt10">
    <div class="form-container">

        <form action="change-pwd" method="post" id="pwdForm">
            <div class="show-flex-row" style="justify-content: center">
                <h4>Đổi mật khẩu</h4>

            </div>
            <div class="form-group w-100" style="padding: 0px;">
                <div class="show-flex-col w-100">
                    <div class="show-flex-row w-100">
                        <label class="w-50" for="currentPwd">Mật khẩu hiện tại</label>
                        <input type="password" size="10" class="form-control w-50" id="currentPwd" name="currentPwd" aria-describedby="" placeholder="Nhập mật khẩu hiện tại" required>
                        <input type="text" size="10" class="form-control w-50" id="object" name="object" aria-describedby="" placeholder="Nhập mật khẩu hiện tại" value="<%=object%>" hidden>
                    </div>
                    <div class="error active" <%=status.equalsIgnoreCase("incorrectPwd") ? "" : "hidden"%>>Sai mật khẩu</div>

                </div>
            </div>
            <div class="form-group w-100" style="padding: 0px;">
                <div class="show-flex-col w-100">
                    <div class="show-flex-row w-100">
                        <label class="w-50" for="newPwd">Mật khẩu mới</label>
                        <input type="password" size="10" class="form-control w-50" id="newPwd" name="newPwd" aria-describedby="" placeholder="Nhập mật khẩu mới" required>
                    </div>
                    <div class="error active" <%=status.equalsIgnoreCase("notTheSamePwd") ? "" : "hidden"%>>Mật khẩu không trùng khớp</div>


                </div>

            </div>
            <div class="form-group w-100" style="padding: 0px;">
                <div class="show-flex-col w-100">
                    <div class="show-flex-row w-100">
                        <label class="w-50" for="renewPwd">Nhập lại mật khẩu mới</label>
                        <input type="password" size="10" class="form-control w-50" id="renewPwd" name="renewPwd" aria-describedby="" placeholder="Nhập lại mật khẩu mới" required>

                    </div>
                    <div class="error active" <%=status.equalsIgnoreCase("notTheSamePwd") ? "" : "hidden"%>>Mật khẩu không trùng khớp</div>
                </div>

            </div>

            <div class="show-flex-row" style="justify-content: center; margin-top: 40px">
                <div class="show-flex-row w-50">
                    <a class="btn btn-third confirm-btn no-confirm"  href="goto-update-customer-info">Hủy</a>
                    <button class="btn btn-primary confirm-btn yes-confirm" type="submit">Lưu</button>
                </div>
            </div>



        </form>
    </div>


</div>





</body>
</html>
