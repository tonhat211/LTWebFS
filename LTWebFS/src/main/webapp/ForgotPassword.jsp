<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="./assets/common/taglib.jsp"%>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Quên mật kẩu</title>

    <!--
        - font awesome link
      -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
<%--    <link--%>
<%--            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'--%>
<%--            rel='stylesheet'>--%>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/slide.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">

    <link rel="stylesheet" href="./assets/css/style.css">
    <link rel="stylesheet" href="./assets/css/adminEmployee.css">
    <link rel="stylesheet" href="./assets/css/updateCustomerInfo.css">

    <!--
         bootstrap link
      -->

    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">


    <style>
        .error {
            color: red;
            background-color: rgba(255, 0, 0, 0.22);
            padding: 7px;
            margin: 4px;
            border-radius: 4px;
            font-size: 10px;
        }
        .forgot {
            background-color: #fff;
            padding: 12px;
            border: 1px solid #dfdfdf
        }

        .padding-bottom-3x {
            padding-bottom: 72px !important
        }

        .card-footer {
            background-color: #fff
        }

        .btn {
            font-size: 13px
        }

        .form-control:focus {
            color: #495057;
            background-color: #fff;
            border-color: #76b7e9;
            outline: 0;
            box-shadow: 0 0 0 0 #28a745;
        }

        .btn1 {
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
        }

        .btn1:hover {
            cursor: pointer;
            opacity: 0.3;
        }

        #footer {
            margin-left: auto !important;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>
<div id="main" style="margin-left: auto !important;">

<div class="container padding-bottom-3x mb-2 mt-5 show-flex-row" style="justify-content: center;">
    <div class="row justify-content-center show-flex-row">
        <div class="col-lg-8 col-md-10" style="margin-top: 110px;">
            <div class="forgot" style="border: none;border-radius: 4px; box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);">
                <h2 style="margin: 10px 0;">Bạn quên mật khẩu?</h2>
                <p>Thay đổi mật khẩu của bạn một cách dễ dàng với 3 bước sau
                    đây.</p>
                <ol class="list-unstyled" style="margin-top: 30px; list-style-type: none;">
                    <li style="margin: 10px 0;"><span class="text-primary text-medium" style="color: #0d6efd">1. </span> Nhập
                        địa chỉ email của bạn vào trường dữ liệu dưới đây.</li>
                    <li style="margin: 10px 0;"><span class="text-primary text-medium" style="color: #0d6efd">2. </span> Chúng
                        tôi sẽ gửi cho bạn mã OTP vào email của bạn để lấy lại mật khẩu</li>
                    <li style="margin: 10px 0;"><span class="text-primary text-medium" style="color: #0d6efd">3. </span> Nhập
                        OTP ở trang tiếp theo</li>
                </ol>
            </div>
            <form class="card mt-4" action="ForgotPasswordControl" method="post" id="forgotPass" style="margin-top: 20px; padding: 10px 0">
                <div class="card-body">
                    <div class="form-group show-flex-col">
                        <label for="email-for-pass" style="margin-bottom: 10px">Nhập địa chỉ email của bạn</label>
                        <input class="form-control" type="text" name="email"
                            id="email-for-pass" required="" style="font-size: 20px">
                        <div class="error" hidden></div>
                    </div>
                </div>
                <div class="card-footer show-flex-row" style="justify-content: space-between">
                    <button class="btn btn-success btn1" type="submit" style="color: white; background-color: #10c010;">Lấy lại mật
                        khẩu</button>
                    <button class="btn btn-danger btn1" type="submit" style="color: white; background-color: #ce0000">
                        <a href="login.jsp" style="text-decoration: none; color: white;">
                            Quay về trang đăng nhập</a>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
<%@ include file="footer.jsp" %>
<script src="assets/js/slidebar.js">

<!--ajax link-->
<link href='' rel='stylesheet'>
<script type='text/javascript'
        src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>


<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
<script type='text/javascript' src=''></script>
<script type='text/javascript' src=''></script>
<script type='text/Javascript'></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const form = document.getElementById('forgotPass');

        form.addEventListener('submit', function (event) {
            event.preventDefault();
            const emailInput = document.getElementById('email-for-pass');
            const email = emailInput.value.trim();
            const errorElement = emailInput.parentElement.querySelector('.error');

            if (!validateEmail(email)) {
                showMessage(errorElement, 'Email không hợp lệ');
                return;
            }

            this.submit();
        });

        function validateEmail(email) {
            const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return emailRegex.test(email);
        }

        function showMessage(element, message) {
            if (element) {
                element.hidden = false;
                element.innerText = message;
            }
        }
    });
</script>
</body>
</html>