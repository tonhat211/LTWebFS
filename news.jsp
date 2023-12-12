<%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 12/12/2023
  Time: 6:51 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/news.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <title>Tin Tức</title>
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
                    <a href="product.html" class="color-f6 menuitem ">Sản Phẩm
                        <!--            <i class="nav-arrow-down ti-angle-down"></i>-->
                    </a>
                    <!--          <ul class="subnav">-->
                    <!--            <li><a href="#" class="color-f6">Bán sản phẩm mới</a></li>-->
                    <!--                            <hr style="width: 70%; margin: auto; background: #000000">-->
                    <!--                            <li><a href="#" class="color-f6">Sản Phẩm cũ</a></li>-->
                    <!--          </ul>-->
                </li>
                <li><a href="news.html" class="color-f6 active menuitem">Tin Tức</a></li>
                <li><a href="aboutUs.html" class="color-f6 menuitem">Nhà Phân Phối</a></li>
            </ul>
        </div>

    </div>
    <div class="HSJS" style="margin-top: 150px"></div>
    <div id="content">
        <div class="container">
            <div class="thumb column">
                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-suckhoe.vnecdn.net/2023/07/06/2-2921-1688531474-1688604627-5079-1688604655.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=ZJQKBauGr7l1borx6i8_4Q"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/het-canh-benh-nhan-chay-khap-noi-chup-chieu-kham-benh-4625430.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Hết cảnh bệnh nhân 'chạy khắp nơi chụp chiếu' khám bệnh</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/het-canh-benh-nhan-chay-khap-noi-chup-chieu-kham-benh-4625430.html">
                                <span>Bệnh viện Bạch Mai, Việt Đức, Chợ Rẫy đã mua sắm và sửa chữa được máy móc, trang thiết bị y tế, bệnh nhân khám và điều trị tại chỗ, không phải di chuyển đến cơ sở khác. </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-suckhoe.vnecdn.net/2023/08/25/1-1692959962-3272-1692960042.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=SAlAFJo2reBDaECaxgtpxQ"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/benh-vien-co-tien-nhung-khong-mua-duoc-thiet-bi-mong-muon-4646087.html"
                               class="hov-a-title">
                                <h2 class="h2-text">'Bệnh viện có tiền nhưng không mua được thiết bị mong muốn'</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/benh-vien-co-tien-nhung-khong-mua-duoc-thiet-bi-mong-muon-4646087.html">
                                <span>Các bệnh viện công tại Hà Nội hiện đủ tài chính nhưng gặp khó trong mua sắm thiết bị y tế do gặp các vướng mắc về giá.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-kinhdoanh.vnecdn.net/2023/05/31/trienlam-1685520312-8869-1685520409.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=tNuozpw42asmvSC1QsODJQ"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/trien-lam-trang-thiet-bi-y-te-quoc-te-k-med-expo-vietnam-2023-4611813.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Triển lãm Trang thiết bị Y tế quốc tế K-Med Expo Vietnam 2023</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/trien-lam-trang-thiet-bi-y-te-quoc-te-k-med-expo-vietnam-2023-4611813.html">
                                <span>Triển lãm dự kiến diễn ra từ 15 - 17/6, với sự tham gia của 150 đơn vị là công ty thiết bị y tế Hàn Quốc, doanh nghiệp nước ngoài.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-vnexpress.vnecdn.net/2022/10/27/3-1666845803.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=axxGXsU7BgCvhuGHVlbRew"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/nhung-cong-nghe-gioi-thieu-tai-techmart-y-te-4528680.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Những công nghệ giới thiệu tại Techmart Y tế
                                </h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/nhung-cong-nghe-gioi-thieu-tai-techmart-y-te-4528680.html">
                                <span>TP HCM - Hơn 200 công nghệ, thiết bị được giới thiệu tại chợ công nghệ và thiết bị ngành y tế (Techmart) giúp kết nối cung cầu, chuyển giao công nghệ. </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-suckhoe.vnecdn.net/2022/02/17/may-do-spo2-voi-benh-nhan-covi-9421-3876-1645107386.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=0xthQr7GyV9Fe_rJlfTvoA"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/luu-y-khi-chon-may-do-spo2-4428855.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Lưu ý khi chọn máy đo SpO2</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/luu-y-khi-chon-may-do-spo2-4428855.html">
                                <span>Người mua cần chọn máy đo SpO2 có thương hiệu, tem, phiếu bảo hành tại các cửa hàng thiết bị y tế chuyên dụng hoặc đại lý phân phối.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-vnexpress.vnecdn.net/2022/01/17/rosa-jpeg-8821-1636547788-jpeg-6832-1933-1642435535.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=oeF3NYd0sJF4NUJMk4tviQ"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/chieu-nang-gia-robot-phau-thuat-gap-5-lan-trong-vu-an-o-benh-vien-bach-mai-4417485.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Chiêu nâng giá robot phẫu thuật gấp 5 lần trong vụ án ở Bệnh viện Bạch Mai</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/chieu-nang-gia-robot-phau-thuat-gap-5-lan-trong-vu-an-o-benh-vien-bach-mai-4417485.html">
                                <span>Cựu giám đốc Bệnh viện Bạch Mai Nguyễn Quốc Anh bị cáo buộc nhận 400 triệu đồng và 10.000 USD, "tạo điều kiện" cho Công ty BMS nâng khống giá robot phẫu thuật gấp 5 lần.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-vnexpress.vnecdn.net/2021/11/08/settop1-1636340791-6783-1636340799.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=bGUONT0FfA3-4tP-iRRsWA"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/giam-doc-benh-vien-thu-duc-bi-bat-4382591.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Giám đốc Bệnh viện Thủ Đức bị bắt</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/giam-doc-benh-vien-thu-duc-bi-bat-4382591.html">
                                <span>TP HCMÔng Nguyễn Minh Quân, 48 tuổi, Giám đốc Bệnh viện TP Thủ Đức bị cáo buộc thông đồng với doanh nghiệp để mua thiết bị vật tư y tế, gây thiệt hại cho Nhà nước. </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-suckhoe.vnecdn.net/2021/10/29/le-trao-ta-ng-ma-y-chu-p-x-qua-4417-2720-1635497824.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=Nfklj4u9vVe9FzPURVAjhQ"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/benh-vien-tri-covid-19-duoc-tang-thiet-bi-y-te-cong-nghe-cao-4378829.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Bệnh viện trị Covid-19 được tặng thiết bị y tế công nghệ cao</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/benh-vien-tri-covid-19-duoc-tang-thiet-bi-y-te-cong-nghe-cao-4378829.html">
                                <span>Bệnh viện điều trị người bệnh Covid-19 (Hà Nội) được tặng máy X-quang di động kỹ thuật số, Bệnh viện Trung ương Huế nhận hệ thống máy gây mê kèm thở.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-kinhdoanh.vnecdn.net/2021/09/02/khautrang3Mnhai-1630565965-1786-1630566197.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=JIHoDYhwL8OINx9RriPNTw"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/hon-20-800-khau-trang-3m-nghi-hang-gia-4350196.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Hơn 20.800 khẩu trang 3M nghi hàng giả</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/hon-20-800-khau-trang-3m-nghi-hang-gia-4350196.html" >
                                <span>Trong 400.000 sản phẩm, thiết bị y tế phòng dịch bị thu giữ do không rõ nguồn gốc, 20.880 khẩu trang y tế 3M có dấu hiệu giả mạo nhãn hiệu.  </span>
                            </a>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="content">
                    <div class="row-col ">
                        <div class="img">
                            <img
                                    src="https://i1-kinhdoanh.vnecdn.net/2021/08/18/image001-1629278176-7902-1629284158.jpg?w=500&h=300&q=100&dpr=2&fit=crop&s=h7YBwekJU0WKMdpN9Im5gA"
                                    alt="ảnh minh họa">
                        </div>
                        <div class="title">
                            <a href="https://vnexpress.net/phuong-trang-tang-gan-6-000-thiet-bi-y-te-cho-tp-hcm-4342614.html"
                               class="hov-a-title">
                                <h2 class="h4-text">Phương Trang tặng gần 6.000 thiết bị y tế cho TP HCM</h2>
                            </a>
                        </div>
                        <div class="contention">
                            <a href="https://vnexpress.net/phuong-trang-tang-gan-6-000-thiet-bi-y-te-cho-tp-hcm-4342614.html">
                                <span>Tập đoàn tặng 20 máy trợ thở, 1.390 máy tạo oxy, 4.400 máy đo nồng độ oxy SpO2 cho các bệnh viện dã chiến, trung tâm y tế, vào ngày 17/8.  </span>
                            </a>
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
</body>
</html>
