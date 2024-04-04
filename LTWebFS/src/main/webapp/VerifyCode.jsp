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
    <title>Verify Email</title>
    <link rel="stylesheet" href="assets/css/verifyCode.css">

</head>
<body>
<%
    String email = (String) request.getAttribute("email");
%>
<div class="main">
    <form action="verifyEmail" method="get" class="formm">
        <h1 class="title">Mã xác minh đã được gửi tới email: <%=email%>></h1>
        <div class="show-flex-row">
            <input type="text" placeholder="Nhập mã xác minh" class="code-input" name="codeInput" value="">
            <input type="text" placeholder="Nhập mã xác minh" class="code-input" name="emaill" value="" style="display: none">
            <div>
                <a href="resendCode?email=<%=email%>">Gửi lại mã</a>
            </div>
        </div>
        <div class="show-flex-row">
            <button class="btn" type="submit">Xác minh</button>
        </div>



    </form>
</div>
</body>
</html>
