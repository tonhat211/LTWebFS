<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="./assets/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title></title>
    <!--
        - font awesome link
      -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/slide.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">

    <link rel="stylesheet" href="./assets/css/style.css">

    <!--
         bootstrap link
      -->
    <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>


    <style>
        .form-gap {
            padding: 70px;
            margin: auto;
            margin-top: 110px;
        }

        h3, h2 {
            text-align: center;
        }

        .text-center {
            margin-left: 30%;
            width: 42%;
        }

        .form-group {
            margin-bottom: 10px;
        }

        .btn-primary {
            color: #fff;
            background-color: #00A6CF;
            border-color: #00A6CF;
        }
        #footer {
            margin-left: auto !important;
        }
    </style>
</head>

<body>
<%
    String email = (String) session.getAttribute("email");
%>
<div id="main" style="margin-left: auto !important;">
    <%@ include file="header.jsp"%>

    <div class="form-gap">

        <div class="text-center">
            <h3>
                <i class="fa fa-lock fa-4x"></i>
            </h3>
            <h2 class="text-center">Nhập mã OTP</h2>

            <% if (request.getAttribute("message") != null) { %>
            <p style="color: red;"><%= request.getAttribute("message") %></p>
            <% } %>

            <div class="panel-body">

                <form id="" action="ValidateOtp" role="form"
                      autocomplete="off" class="form" method="post">
                    <div class="form-group">
                        <div class="input-group">
											<span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-envelope color-blue"></i></span>
                            <input
                                id="opt" name="otp" placeholder="Nhập mã OTP"
                                class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group">

                        <input name="recover-submit"
                               class="btn btn-lg btn-primary btn-block"
                               value="Đặt lại mật khẩu" type="submit">
                    </div>

                </form>

                <form class="form" action="sendOTP" method="post">
                    <input type="text" placeholder="Nhập mã xác minh" class="code-input" name="email" value="<%=email%>" hidden>
                    <div class="form-group">
                        <input name="recover-submit"
                               class="btn btn-lg btn-primary btn-block"
                               value="Gửi lại mã OTP" type="submit">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<%@ include file="footer.jsp" %>
<script src="assets/js/slidebar.js">
</script>

</body>
</html>