<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/slide.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <title>Trang chủ</title>
</head>
<body>

<div id="main">
    <%@ include file="header.jsp" %>
    <div id="slider" style="background-color: #dddddd">
        <div class="slideshow-container">
            <div class="mySlides fade">
                <div class="numbertext">1 / 4</div>
                <img src="assets/img/slide-show/Thiet_bi_y_te_vat_ly_tri_lieu.png"
                     style="width:100%; height: 450px">
                <div class="text">Thiết bị y tế</div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">2 / 4</div>
                <img src="assets/img/slide-show/hq720.jpg" style="width:100%; height: 450px;">
                <div class="text">Tin tức covid 19 hiện nay</div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">3 / 4</div>
                <img src="assets/img/slide-show/chung-nhan-iso-13485.jpg" style="width:100%; height: 450px">
                <div class="text">Chứng nhận iso 13485</div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">4 /</div>
                <img src="assets/img/slide-show/khau-trang.jpg" style="width:100%; height: 450px">
                <div class="text">Khẩu trang y tế</div>
            </div>

            <a class="prev" onclick="plusSlides(-1)">❮</a>
            <a class="next" onclick="plusSlides(1)">❯</a>

        </div>
        <br>

        <div style="text-align:center">
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
            <span class="dot" onclick="currentSlide(4)"></span>
        </div>
    </div>
    <div class="JSYDB" style="height: 50px"></div>
    <div id="partners" style="background-color: #dddddd">
        <div class="content">
            <div class="partner-title">
                <span>
                    Đối tác hàng đầu
                </span>
            </div>
            <div class="partner-img">
                <img src="assets/img/Partner/hoanmy.jpg" alt="Hoàn Mỹ">
                <img src="assets/img/Partner/metech.png" alt="Metech">
            </div>
        </div>
    </div>
    <div class="JSYDB" style="height: 50px"></div>
    <div id="aboutUs" style="background-color: #dddddd">
        <div class="content">
            <div class="business-title partner-title">
                <span>Giấy phép kinh doanh</span>
            </div>
            <div class="business-licence partner-img">
                <img src="assets/img/GPKD/gpkd-1.jpeg" alt="Giấy phép kinh doanh ">
                <img src="assets/img/GPKD/gpkd-2.jpg" alt="Giấy phép kinh doanh">
            </div>
        </div>
    </div>
    <div class="JSYDB" style="height: 50px"></div>

</div>
<%@ include file="footer.jsp" %>
<script src="assets/js/slidebar.js">

</script>
</body>
</html>