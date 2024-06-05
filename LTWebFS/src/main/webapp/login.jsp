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

    <style>
        .password-container{
            position: relative;
        }

        .password-container .form-control {
            padding-right: 40px; /* Đảm bảo đủ chỗ cho icon */
        }

        .password-container .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            z-index: 2;
        }

        .forgot {
            text-align: right;
            margin-top: -10px;
            margin-bottom: 10px;
        }

        .social {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 15px;
        }

        .social a {
            color: white;
            border-radius: 50%;
            padding: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 50px;
            height: 50px;
            text-align: center;
            text-decoration: none;
        }

        .social a.facebook {
            background-color: #3b5998;
        }

        .social a.google {
            background-color: #db4437;
        }

        .social i {
            font-size: 20px;
        }

        .social-label {
            display: flex;
            align-items: center;
            text-align: center;
            width: 100%;
            margin: 20px 0;
        }

        .social-label::before,
        .social-label::after {
            content: "";
            flex: 1;
            border-bottom: 1px solid #ccc;
            margin: 0 10px;
        }

    </style>

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
<%--                <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" value="<%=email%>" placeholder="Nhập email">--%>
                <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" value="2003tonhat@gmail.com" placeholder="Nhập email">
                <div class="error" <%=status.equalsIgnoreCase("notUser") ? "" : "hidden"%>>Tài khoản không tồn tại</div>

            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Mật khẩu</label>
                <div class="password-container">
<%--                    <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Nhập mật khẩu">--%>
                    <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Nhập mật khẩu" value="1234">
                    <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>
                <div class="error" <%= status.equalsIgnoreCase("loginFailed") ? "" : "hidden" %> >Sai mật khẩu</div>
            </div>

                <div  class="forgot">
                    <a href="ForgotPassword.jsp">Quên mật khẩu? </a>

            </div>
            <div style="display: flex; justify-content: center">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
        </form>

            <div class="social-label">
                <span>Hoặc đăng nhập bằng</span>
            </div>

            <div class="social">
                <div><a href="https://www.facebook.com/v19.0/dialog/oauth?fields=id,name,email&client_id=1443587996285926&redirect_uri=http://localhost:8080/LTWebFS/loginFB"
                        class="facebook"><i class="fa-brands fa-facebook"></i></a></div>
                <div><a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid

&redirect_uri=http://localhost:8080/LTWebFS/loginGG

&response_type=code

&client_id=922388877466-mefk0ck070diu19f1dmqhpsvefcm08n7.apps.googleusercontent.com&approval_prompt=force" class="google"><i class="fa-brands fa-google-plus-g"></i></a></div>

            </div>

            <div style="margin-top: 15px;text-align: center">

                <a href="signup.jsp">Chưa có tài khoản? Đăng ký</a>
            </div>


    </div>

</div>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const togglePassword = document.querySelector('.toggle-password');
        const passwordInput = document.querySelector('#exampleInputPassword1');

        togglePassword.addEventListener('click', function (e) {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);

            this.classList.toggle('fa-eye-slash');
            this.classList.toggle('fa-eye');
        });
    });



</script>
</body>
</html>
