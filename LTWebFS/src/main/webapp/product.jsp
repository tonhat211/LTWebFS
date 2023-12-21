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

    <!--    content -->
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/slidebar.css">
    <link rel="stylesheet" href="assets/css/product.css">


</head>
<body>
<div>
    <%@ include file="header.jsp" %>

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
    <%@ include file="footer.jsp" %>
</div>



<script src="assets/js/product.js">

</script>
<script>
    // showProInKind("B");
</script>
</body>
</html>
