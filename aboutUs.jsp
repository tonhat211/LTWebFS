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
    <div id="header">
        <div class="head col rol">
            <div class="logo">
                <a href="index.html">
                    <img src="assets/img/Logo/favicon_icon.png" alt="Logo"
                         style="width: 40px; height: 40px; border-radius: 5px">
                </a>
            </div>
            <div class="search-text">
                <div class="btn-search">
                    <input type="text" name="Search" id="Search" placeholder="Tìm kiếm sản phẩm">
                    <button type="button" class="button-search">
                        <i class="ti-search"></i>
                    </button>
                </div>
            </div>
            <div class="shopping-cart">
                <a href="cart.html">
                    <i class="ti-shopping-cart-full"></i>
                </a>
            </div>
            <div class="user">
                <i class="ti-user"></i>
                <a href="login.html">Đăng nhập</a>
                <i style="color: #f6f6f6">/</i>
                <a href="logup.html">Đăng ký</a>
            </div>
        </div>
        <div id="nav" class="nav rol col">
            <ul class="nav">
                <li><a href="index.html" class="color-f6 menuitem">Trang chủ</a></li>
                <li>
                    <a href="product.html" class="color-f6 menuitem">Sản Phẩm
                        <!--            <i class="nav-arrow-down ti-angle-down"></i>-->
                    </a>
                    <!--          <ul class="subnav">-->
                    <!--            <li><a href="#" class="color-f6">Bán sản phẩm mới</a></li>-->
                    <!--                            <hr style="width: 70%; margin: auto; background: #000000">-->
                    <!--                            <li><a href="#" class="color-f6">Sản Phẩm cũ</a></li>-->
                    <!--          </ul>-->
                </li>
                <li><a href="news.html" class="color-f6 menuitem ">Tin Tức</a></li>
                <li><a href="aboutUs.html" class="color-f6 menuitem active">Nhà Phân Phối</a></li>
            </ul>
        </div>

    </div>
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

    <div id="footer">
        <div class="nameCty pad-10 dis-flex" style="width: 40%;">
            <a href="index.html">
                <img src="assets/img/Logo/favicon_icon.png" alt="" style="height: 60px; width: 60px; margin-bottom: -15px">
                <span style="font-size: 16px; font-weight: 700; position: absolute"> Công ty TMCP TBYT TPHCM </span>
            </a>
            <a class="span-title" href="https://www.google.com/maps/place/Vi%E1%BB%87n+Y+d%C6%B0%E1%BB%A3c+h%E1%BB%8Dc+d%C3%A2n+t%E1%BB%99c+Th%C3%A0nh+ph%E1%BB%91+H%E1%BB%93+Ch%C3%AD+Minh/@10.7977163,106.6688019,17z/data=!4m14!1m7!3m6!1s0x31752953e3eb2ea3:0xd911381946a786c5!2zVmnhu4duIFkgZMaw4bujYyBo4buNYyBkw6JuIHThu5ljIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaA!8m2!3d10.797711!4d106.6713768!16s%2Fg%2F1tdm16vf!3m5!1s0x31752953e3eb2ea3:0xd911381946a786c5!8m2!3d10.797711!4d106.6713768!16s%2Fg%2F1tdm16vf?entry=ttu">
                <i class="ti-location-pin" ></i>
                273 Đ. Nguyễn Văn Trỗi, Phường 10, Phú Nhuận, Thành phố Hồ Chí Minh 70000
            </a>
        </div>
        <div class="list-menu pad-10 dis-flex" style="width: 15%">
            <a href="index.html"> <i class="ti-home"> </i>Trang chủ</a>
            <a href="product.html"><i class="ti-layout-grid2"> </i>Sản Phẩm</a>
            <a href="news.html"><i class="ti-notepad"> </i>Tin Tức</a>
            <a href="aboutUs.html"><i class="ti-bar-chart-alt"> </i>Nhà Phân Phối</a>
        </div>
        <div class="emailCty" style="width: 20%;">
            <div class="email pad-10">
                <a href="https://mail.google.com/mail/u/3/#inbox">tbyt@gmail.com</a>
            </div>
            <div class="icon-socials pad-10">
                <a href="https://www.instagram.com/" title="Instagram" class="social">
                    <i class="ti-instagram"></i>
                </a>
                <a href="https://www.facebook.com/" class="social">
                    <i class="ti-facebook"></i>
                </a>
                <a href="https://www.twitter.com/" class="social">
                    <i class="ti-twitter"></i>
                </a>
                <a href="https://www.google.com/" class="social">
                    <i class="ti-google"></i>
                </a>
            </div>
        </div>
        <div class="branchCty" style="width: 25%">
            <div class="branch-1 branch">
                <h5>Chi nhánh 1</h5>
                <a href="https://www.google.com/maps/place/B%E1%BB%87nh+vi%E1%BB%87n+Y+H%E1%BB%8Dc+C%E1%BB%95+Truy%E1%BB%81n+TP.HCM/@10.785511,106.6162613,13z/data=!4m10!1m2!2m1!1zdmnhu4duIHkgaOG7jWMgY-G7lSB0cnV54buBbg!3m6!1s0x31752fcd97fb81bd:0x7e95d82e21a4d0f5!8m2!3d10.785511!4d106.6883591!15sChx2aeG7h24geSBo4buNYyBj4buVIHRydXnhu4FuWh4iHHZp4buHbiB5IGjhu41jIGPhu5UgdHJ1eeG7gW6SAQhob3NwaXRhbJoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VSb2EyRm1WbTFCUlJBQuABAA!16s%2Fg%2F11j2wj8d0t?entry=ttu">
                    <i class="ti-location-pin">179-187 Đ. Nam Kỳ Khởi Nghĩa, Võ Thị Sáu, Quận 3, TP Hồ Chí Minh, Việt Nam</i>
                </a>
            </div>
            <div class="branch-2 branch">
                <h5>Chi nhánh 2</h5>
                <a href="https://www.google.com/maps/place/Vi%E1%BB%87n+Y+H%E1%BB%8Dc+C%E1%BB%95+Truy%E1%BB%81n+Th%C3%A0nh+Ph%E1%BB%91+H%E1%BB%93+Ch%C3%AD+Minh/@10.8810114,106.6902926,13z/data=!4m10!1m2!2m1!1zdmnhu4duIHkgaOG7jWMgY-G7lSB0cnV54buBbg!3m6!1s0x3174d9d0ee2825b7:0x292b11fd976fedde!8m2!3d10.8810114!4d106.7623904!15sChx2aeG7h24geSBo4buNYyBj4buVIHRydXnhu4FukgEXZWR1Y2F0aW9uYWxfaW5zdGl0dXRpb27gAQA!16s%2Fg%2F11r9qh4vgy?entry=ttu">
                    <i class="ti-location-pin">15/3 Đường số 13, Linh Xuân, Thủ Đức, TP Hồ Chí Minh, Việt Nam</i></a>
            </div>
            <div class="branch-3 branch">
                <h5>Chi nhánh 3</h5>
                <a href="https://www.google.com/maps/place/Ph%C3%B2ng+Chu%E1%BA%A9n+Tr%E1%BB%8B+Y+H%E1%BB%8Dc+C%E1%BB%95+Truy%E1%BB%81n+V%E1%BA%A1n+T%E1%BA%BF+H%C3%B2a/@10.9102109,106.6976411,13z/data=!4m10!1m2!2m1!1zdmnhu4duIHkgaOG7jWMgY-G7lSB0cnV54buBbg!3m6!1s0x3174d8560f30b55d:0x8cabdda404c439d1!8m2!3d10.9102109!4d106.7697389!15sChx2aeG7h24geSBo4buNYyBj4buVIHRydXnhu4FukgEIaG9zcGl0YWzgAQA!16s%2Fg%2F11d_yljybx?entry=ttu">
                    <i class="ti-location-pin"> 325 Nguyễn An Ninh, Dĩ An, Bình Dương, Việt Nam</i>

                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
