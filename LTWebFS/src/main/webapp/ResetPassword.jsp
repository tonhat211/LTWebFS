<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="./assets/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <title>Đặt lại mật khẩu</title>

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!-- CSS -->
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/slide.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="./assets/css/style.css">

    <!-- Bootstrap -->
    <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet'>

    <!-- Custom CSS -->
    <style>
        .custom-control-label::before {
            background-color: #dee2e6;
            border: #dee2e6
        }

        hr {
            margin-top: 0rem;
            margin-bottom: 0rem;
        }

        #footer {
            margin-left: auto !important;
        }
    </style>
</head>

<body>

<div id="main" style="margin-left: auto !important;">
    <%@ include file="header.jsp"%>

    <div>
        <div class="container">
            <div class="row justify-content-center" style="margin-top: 110px;">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5">
                    <div class="container bg-white rounded mt-2 mb-2 px-0">
                        <div class="row justify-content-center align-items-center pt-3">
                            <h1><strong>Đặt lại mật khẩu</strong></h1>
                        </div>
                        <div class="pt-3 pb-3">
                            <% if (request.getAttribute("status") != null) { %>
                            <% if (request.getAttribute("status").equals("resetSuccess")) { %>
                            <p class='text-success ml-1'>Mật khẩu đã được đặt lại thành công!</p>
                            <% } else if (request.getAttribute("status").equals("resetFailed")) { %>
                            <p class='text-danger ml-1'>Đặt lại mật khẩu không thành công. Vui lòng thử lại.</p>
                            <% } %>
                            <% } %>


                            <% if (request.getAttribute("message") != null) { %>
                            <p class='text-danger ml-1'><%= request.getAttribute("message") %></p>
                            <% } %>

                            <div class="panel-body">
                                <form class="form-horizontal" action="newPassword" method="POST">
                                    <div class="form-group row justify-content-center px-3">
                                        <div class="col-9 px-0">
                                            <input type="password" name="password"
                                                   placeholder="Nhập mật khẩu mới"
                                                   class="form-control border-info placeicon">
                                        </div>
                                    </div>
                                    <div class="form-group row justify-content-center px-3">
                                        <div class="col-9 px-0">
                                            <input type="password" name="confPassword"
                                                   placeholder="Xác nhận mật khẩu mới"
                                                   class="form-control border-info placeicon">
                                        </div>
                                    </div>
                                    <div class="form-group row justify-content-center">
                                        <div class="col-3 px-3 mt-3">
                                            <input type="submit" value="Đặt lại"
                                                   class="btn btn-block btn-info">
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="mx-0 px-0 bg-light">
                                <div class="px-4 pt-5">
                                    <hr>
                                </div>
                                <div class="pt-2">
                                    <div class="row justify-content-center">
                                        <h5>Không có tài khoản?<span><a href="logup.jsp"
                                                                        class="text-danger"> Đăng ký ngay!</a></span>
                                        </h5>
                                    </div>
                                    <div class="row justify-content-center align-items-center pt-4 pb-5">
                                        <div class="col-4"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
<script src="assets/js/slidebar.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

</body>
</html>
