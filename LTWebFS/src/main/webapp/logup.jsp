<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/css_login/login.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
</head>
<body>
<div id="main-container">
    <h1>Đăng ký</h1>
    <form action="logup" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email <span style="color: #ED1212">*</span></label>

            <input type="email" class="form-control form-control-lg" id="email"
                   placeholder="Nhập email (ít nhất 8 kí tự)">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu <span style="color: #ED1212">*</span></label>
            <input type="password" class="form-control form-control-lg" id="password" placeholder="Nhập mật khẩu">
        </div>
        <div class="mb-3">
            <label for="birthday" class="form-label">Ngày sinh <span style="color: #ED1212">*</span></label>
            <input type="date" class="form-control form-control-lg" id="birthday" name="birthday">
        </div>
        <div class="d-grid gap-2">
            <button id="submitLogin" class="btn my-3" type="submit"><b>Đăng ký</b></button>
        </div>
        <p class="already">Đã có tài khoản ? <a class="log" href="login.html">Đăng nhập</a>
        </p>
    </form>
</div>
</body>
</html>
