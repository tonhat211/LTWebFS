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
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/aboutUs/aboutUs.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <title>Nhà Phân Phối</title>
</head>
<body>
<div id="main">
    <%@ include file="header.jsp" %>
    <div class="FJSSD" style="height: 150px; background-color: #dddddd"></div>
    <div id="name" style="background-color: #dddddd">
        <div class="content dis-flex-aboutUs">
            <div class="title">
                <span>THÔNG TIN</span>
            </div>
            <div class="container">
                <div class="img">
                    <img src="assets/img/Logo/favicon_icon.png" alt="Ảnh logo công ty" style="width: 100px"; height=100px">
                </div>
                <div class="contention">
                    <div class="title-name">
                    <span>
                        Cty TMCP TBYT TPHCM
                    </span>
                    </div>
                    <br>
                    <div class="address">
                        <a href=""><i class="ti-location-pin">273 Đ. Nguyễn Văn Trỗi, Phường 10, Phú Nhuận, Thành phố Hồ Chí Minh 70000</i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="content content-business">
            <div class="title">
                    <span>
                        GIẤY PHÉP
                    </span>
            </div>
            <div class="img">
                <img src="assets/img/GPKD/gpkd-2.jpg" alt="Ảnh giấy chứng nhận">
            </div>
        </div>
        <div class="content">
            <div class="contact title">
                <span>LIÊN HỆ</span>
            </div>
            <div class="contact">
                <input type="text" name="text" id="Text" placeholder="Tiêu đề">
                <input type="text" name="contact" id="contact" placeholder="Nội dung">
                <button class="btn">
                    Gửi
                </button>
            </div>
        </div>
    </div>
    <div class="FDSD" style="height: 50px; background-color: #dddddd"></div>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
