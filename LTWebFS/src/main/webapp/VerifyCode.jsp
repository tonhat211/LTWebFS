<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 12/12/2023
  Time: 6:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <title>Xác minh email</title>
    <link rel="stylesheet" href="assets/css/verifyCode.css">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

</head>
<body>
<%
    String email = (String) request.getAttribute("email");
    String status = (String) request.getAttribute("status");
    if(status==null){
        status="";
    }
%>
<div class="main">
    <div class="container">

        <form action="verifyEmail" method="get" class="formm">
            <div class="error" <%=status.equalsIgnoreCase("unavailableAccount") ? "" : "hidden"%>>Tài khoản chưa được xác minh</div>
            <div class="error" <%=status.equalsIgnoreCase("lockedTemporarilyAccount") ? "" : "hidden"%>>Tài khoản đã bị tạm khóa do đăng nhập sai 5 lần liên tiếp, vui lòng xác minh để có thể đăng nhập</div>
            <h4 class="title">Mã xác minh đã được gửi tới email: <%=email%></h4>
            <p style="text-align: center">Mã xác minh sẽ hết hạn trong vòng 5 phút</p>
            <div class="show-flex-row">
                <input type="text" placeholder="Nhập mã xác minh" class="code-input" name="codeInput" value="" required>
                <input type="text" placeholder="Nhập mã xác minh" class="code-input" name="email" value="<%=email%>" hidden>

            </div>
            <div class="error" <%=status.equalsIgnoreCase("verifyFailed") ? "" : "hidden"%>>Mã xác minh sai</div>

            <div class="show-flex-row">
                <div>
                    <a href="resendCode?email=<%=email%>">Gửi lại mã</a>
                </div>
                <div>
                    <button class="btn" type="submit">Xác minh</button>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>
