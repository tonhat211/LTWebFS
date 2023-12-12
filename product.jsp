<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 04/12/2023
  Time: 3:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sản phẩm</title>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--fontawesome-->
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">

    <!--    google font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <!--    css-->

    <!--    header- footer-->
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">

    <!--    content -->
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/slidebar.css">
    <link rel="stylesheet" href="assets/css/product.css">


</head>
<body>
<div>
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
                    <a href="product.html" class="color-f6 menuitem active">Sản Phẩm
                        <!--            <i class="nav-arrow-down ti-angle-down"></i>-->
                    </a>
                    <!--          <ul class="subnav">-->
                    <!--            <li><a href="#" class="color-f6">Bán sản phẩm mới</a></li>-->
                    <!--                            <hr style="width: 70%; margin: auto; background: #000000">-->
                    <!--                            <li><a href="#" class="color-f6">Sản Phẩm cũ</a></li>-->
                    <!--          </ul>-->
                </li>
                <li><a href="news.html" class="color-f6 menuitem">Tin Tức</a></li>
                <li><a href="aboutUs.html" class="color-f6 menuitem">Nhà Phân Phối</a></li>
            </ul>
        </div>

    </div>

    <div class="product__container">
        <div class="prd__slidebar">
            <div class="prd__sliderbar-list">

                <div class="prd__sliderbar-item active">
                    <img src="assets/img/product-img/banner/product-banner.jpg" alt="" class="prd__slidebar-img">
                </div>
                <div class="prd__sliderbar-item ">
                    <img src="assets/img/product-img/banner/product-banner2.jpg" alt="" class="prd__slidebar-img">
                </div>
                <div class="prd__sliderbar-item ">
                    <img src="assets/img/product-img/banner/product-banner3.jpg" alt="" class="prd__slidebar-img">
                </div>

            </div>
            <div class="prd__slidebar-indicator">
                <ul class="prd__slidebar-indicator-list">
                    <li class="prd__slidebar-indicator-item active"><i class="fa-solid fa-circle"></i></li>
                    <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>
                    <li class="prd__slidebar-indicator-item"><i class="fa-solid fa-circle"></i></li>
                </ul>


            </div>
            <div class="move-left">
                <i class="fa-solid fa-circle-chevron-left"></i>
            </div>
            <div class="move-right">
                <i class="fa-solid fa-circle-chevron-right"></i>
            </div>
        </div>

        <div class="prd__subnav">
            <div class="subnav__kind-filter">
                <div class="subnav__kind">
                    <ul class="subnav__kind-list">
                        <li class="subnav__kind-item middle active kindBtn">
                            A
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            B
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            C
                        </li>
                        <li class="subnav__kind-item middle kindBtn">
                            D
                        </li>
                    </ul>
                </div>
                <div class="subnav__filter">
                    <div class="">
                        <i class="fa-solid fa-x cancel-filter"></i>

                    </div>
                    <div class="subnav__filter-item">
                        <p class="subnav__filter-title">Xuất xứ <i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option">
                            <li class="filter-item" filter-value="usa">Mỹ</li>
                            <li class="filter-item" filter-value="rusia">Nga</li>
                        </ul>
                        <span class="filter-chosen">Tất cả</span>
                    </div>
                    <div class="seperate"></div>

                    <div class="subnav__filter-item" style="display: none">
                        <p class="subnav__filter-title">Thời gian ra mắt<i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option">
                            <li class="filter-item" filter-value="current">Gần đây</li>
                            <li class="filter-item" filter-value="past">Đã lâu</li>
                        </ul>
                        <span class="filter-chosen">Tất cả</span>

                    </div>
                    <div class="seperate" style="display: none"></div>
                    <div class="subnav__filter-item">
                        <p class="subnav__filter-title pdr20 ">Giá <i class="fa-solid fa-caret-down"></i></p>
                        <ul class="subnav__filter-option w100">
                            <li class="filter-item" filter-value="high">Cao dần</li>
                            <li class="filter-item" filter-value="low">Thấp dần</li>
                        </ul>
                        <span class="filter-chosen">Tất cả</span>

                    </div>
                </div>
            </div>
            <div class="subnav__brand">
                <div class="grid">
                    <div class="grid__row brand-list">
                        <div class="grid-col-1">
                            <div class="brand-item all-brand">

                                <p class="brand-name">Tất cả</p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">
                                <!--                            Mỹ-->
                                <p class="brand-name">Cardinal Health</p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">
                                <!--                            Mỹ-->

                                <p class="brand-name">Stryker</p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">
                                <!--                            Duc-->

                                <p class="brand-name">Siemens </p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">
                                <!--                            Mỹ-->

                                <p class="brand-name">Beckton Dickinson</p>
                            </div>
                        </div>              <div class="grid-col-1">
                        <div class="brand-item">

                            <p class="brand-name">GE Healthcare</p>
                        </div>
                    </div>
                        <div class="grid-col-1">
                            <div class="brand-item">

                                <p class="brand-name">Fresenius</p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">

                                <p class="brand-name">Philips </p>
                            </div>
                        </div>
                        <div class="grid-col-1">
                            <div class="brand-item">

                                <p class="brand-name">Abbott </p>
                            </div>
                        </div>              <div class="grid-col-1">
                        <div class="brand-item">

                            <p class="brand-name">Johnson </p>
                        </div>
                    </div>
                        <div class="grid-col-1">
                            <div class="brand-item">

                                <p class="brand-name">Medtronic </p>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>

        <div class="prd__product-list">
            <div class="grid">
                <div class="grid__row">

                    <!--                        san pham A-->


                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/may-tro-thinh-geno-2-CIC-1.png" alt="">
                            </div>

                            <div class="product-info">
                                <div class="product-name"><p>Máy trợ thính</p></div>
                                <p class="product-price"><span>1.000.000 </span> VND</p>
                            </div>


                        </div>
                    </div>


                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/phim-x-quang-18x24.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Phim X Quang 18x24 (cm)</p></div>
                                <p class="product-price"><span>700.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/may-pcr.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy luân nhiệt PCR</p></div>
                                <p class="product-price"><span>4.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/ao-chi-ko-co-medic.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Áo chỉ không cổ</p></div>
                                <p class="product-price"><span>1.700.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/den-canh-bao-tia-x.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Đèn cảnh bảo tia X</p></div>
                                <p class="product-price"><span>400.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/tam-chi-can-xa-phong-x-quang.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Tấm chì cản xạ phòng X-Quang</p></div>
                                <p class="product-price"><span>1.035.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/ong-dong-y-te.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Ống đồng khí y tế</p></div>
                                <p class="product-price"><span>1.025.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/bo-dat-noi-khi-quan.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Bộ đặt nội khí quản</p></div>
                                <p class="product-price"><span>1.100.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/may-rung-ho-tro-nieng-rang.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy rung hỗ trợ niềng răng</p></div>
                                <p class="product-price"><span>900.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/bong-y-te-tham-nuoc.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Bông cuộn y tế thấm nước</p></div>
                                <p class="product-price"><span>95.000</span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/kep-gap-di-vat.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Kẹp gắt dị vật</p></div>
                                <p class="product-price"><span>250.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proA">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/A/bo-tieu-phau.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Bộ tiểu phẫu</p></div>
                                <p class="product-price"><span>200.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                        san pham B-->
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proB">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/B/may-cua-xuong.png" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy cưa xương</p></div>
                                <p class="product-price"><span>5.900.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proB">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/B/mo-to-dien-nha-khoa.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Motor điện nha khoa</p></div>
                                <p class="product-price"><span>4.900.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proB">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/B/tay-khoan-nhanh.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Tay khoan nhanh</p></div>
                                <p class="product-price"><span>1.700.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proB">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/B/tu-lanh-am-sau.png" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Tủ lạnh âm sâu</p></div>
                                <p class="product-price"><span>17.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                        san pham C-->
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proC">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/C/may-theo-doi-benh-nhan.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy theo dõi bệnh nhân 3 thông số INFUNIX IP-1020</p></div>
                                <p class="product-price"><span>20.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proC">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/C/may-tro-tho-bmc-g3-viethamedical-bia.webp" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy Trợ Thở BMC G3 B25VT Máy Trợ Thở BiPAP</p></div>
                                <p class="product-price"><span>32.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>

                    <!--                        san pham D-->

                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proD">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/D/may-chup-x-quang-ky-thuat-so-2.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy chụp X quang</p></div>
                                <p class="product-price"><span>17.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proD">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/D/may-ct.jpg" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy CT</p></div>
                                <p class="product-price"><span>200.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>
                    <!--                    1sp-->
                    <div class="grid-col-2 mt20 oneProduct proD">
                        <div class="product-item">
                            <div class="product-img">
                                <img src="./assets/img/product-img/D/may-soc-tim-rescue-1.png" alt="">
                            </div>
                            <div class="product-info">
                                <div class="product-name"><p>Máy khử rung tim</p></div>
                                <p class="product-price"><span>20.000.000 </span> VND</p>

                            </div>

                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
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



<script src="assets/js/product.js">

</script>
<script>
    // showProInKind("B");
</script>
</body>
</html>
