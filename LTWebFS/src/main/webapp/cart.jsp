<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/cart.css">
    <link rel="stylesheet" href="./assets/fonts/fontawesome-free-6.4.0-web/css/all.min.css">
</head>
<body>
<div id="main-container">
    <h2>Giỏ hàng (2 sản phẩm)</h2>
    <div class="left">
        <div class="items row">
            <div class="col-4">
                <input class="form-check-input checkBox" type="checkbox" value="">
                <img class="image img-fluid ps-3" src="https://thietbiytevp.com/products/OMRON%20HEM%207156T.jpg">
            </div>
            <div class="col-8">
                <p class="title">Máy đo huyết áp tự động OMRON HEM-7156T</p>
                <p class="price">1,349,000đ</p>
                <p class="discount">1,550,000đ</p><span class="percent"> | -23%</span>
                <div class="clearfix"></div>
                <div class="volume">
                    <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                        <button type="button" class="btn btn-outline-light sub">-</button>
                        <input class="quantity" type="text" value="1">
                        <button type="button" class="btn btn-outline-light add">+</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="items row">
            <div class="col-4">
                <input class="form-check-input checkBox" type="checkbox" value="">
                <img class="image img-fluid ps-3" src="https://thietbiytevp.com/products/May-do-duong-huyet-Accu-Chek-Guide-va-que-thu-400x400.jpg">
            </div>
            <div class="col-8">
                <p class="title">Máy đo đường huyết Accu Chek Guide</p>
                <p class="price">1,499,000đ</p>
                <p class="discount">1,900,000đ</p><span class="percent"> | -53%</span>
                <div class="clearfix"></div>
                <div class="volume">
                    <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                        <button type="button" class="btn btn-outline-light sub">-</button>
                        <input class="quantity" type="text" value="1">
                        <button type="button" class="btn btn-outline-light add">+</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="right">
        <!--        <div class="khuyenMai">-->
        <!--            <p>Khuyến mãi</p>-->
        <!--            <select class="form-select" aria-label="Default select example">-->
        <!--                <option selected>Open this select menu</option>-->
        <!--                <option value="1">One</option>-->
        <!--                <option value="2">Two</option>-->
        <!--                <option value="3">Three</option>-->
        <!--            </select>-->
        <!--        </div>-->
        <div id="thanhTien" class="mt-3">
            <div class="part1">
                <p>Tạm tính</p>
                <p class="totalTemp">1,848,000đ</p>
            </div>
            <div class="part2">
                <p>Thành tiền</p>
                <p class="total">1,848,000đ</p>
                <p class="vat">(Đã bao gồm VAT nếu có)</p>
            </div>
        </div>
        <div class="d-grid gap-2">
            <button id="pay" class="btn" type="submit"><b>Tiến hành đặt cọc</b></button>
        </div>
        <p class="note">Quý khách vui lòng đặt cọc trước</p>
    </div>
    <div class="clearfix"></div>
</div>
<script src="assets/js/jquery.js"></script>
<script src="assets/js/cart.js"></script>
</body>
</html>
