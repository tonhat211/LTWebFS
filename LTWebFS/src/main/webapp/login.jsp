<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
</head>
<body>
<%
    String status = (String) request.getAttribute("status");
    if(status==null){
        status ="";
    }

    String email = (String) request.getAttribute("email");
    if(email==null){
        email ="";
    }
%>

<div class="ad-content mt10">
    <div class="form-container" style="margin-top: 100px">
        <div class="title"><h1>Đăng nhập</h1></div>

        <form action="check-login" method="post">
            <div class="successful" <%=status.equalsIgnoreCase("verifySuccessful") ? "" : "hidden"%>>Xác minh tài khoản thành công, vui lòng đăng nhập lại</div>
            <div class="error" <%=status.equalsIgnoreCase("loginForUsingCart") ? "" : "hidden"%>>Vui lòng đăng nhập để sử dụng giỏ hàng</div>

            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email</label>
                <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" value="<%=email%>" placeholder="Nhập email">
                <div class="error" <%=status.equalsIgnoreCase("notUser") ? "" : "hidden"%>>Tài khoản không tồn tại</div>

            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Nhập mật khẩu">
                <div class="error"<%=status.equalsIgnoreCase("loginFailed") ? "" : "hidden"%> >Sai mật khẩu</div>

            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <div style="display: flex; justify-content: center">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
            <div style="margin-top: 40px; text-align: center">
                <a href="signup.jsp">Chưa có tài khoản? Đăng ký</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>
