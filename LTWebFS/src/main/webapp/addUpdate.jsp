<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Header</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--  <link href="assets/img/favicon.png" rel="icon">-->
    <!--  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/adminCustomer.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/adminProduct.css">
</head>
<body>
<%
    ProductUnit pu = (ProductUnit) request.getAttribute("productUnit");
    if(pu==null){
        pu = new ProductUnit();
    }

%>
<div class="ad-content mt10">
    <div class="confirm-popup add-img-box">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <div class="input-wrap">
                        <label for="pro-img" class="input-title">Nhập đường dẫn tới hình ảnh.</label>
                        <input type="text" class="input-item" name="pro-img" id="pro-img">
                    </div>
                </div>
                <div class="confirm__control">
                    <div class="confirm-btn add-img-btn">Thêm</div>
                    <div class="confirm-btn cancel-add-img-btn">Hủy</div>
                </div>
            </div>
        </div>
    </div>
    <div class="ad-content-item">
        <!--            <div class="ad_header">-->
        <!--                <div class="ad_func-container">-->
        <!--                    <div class="ad_func-add-btn ad_func-btn active">Thêm</div>-->
        <!--                    <div class="ad_func-save-btn ad_func-btn">Lưu</div>-->
        <!--                </div>-->

        <!--            </div>-->
        <div class="ad_container">
            <div class="form-container">
                <form action="addUpdate-product" method="get">
                    <div class="show-flex-row">
                        <div class="ad_func-container">
                            <button class="ad_func-save-btn ad_func-btn active" type="submit">Lưu</button>
                        </div>
                        <div class="ad_func-container">
                            <div><a class="ad_func-btn active" href="adminProduct.jsp">Hủy</a></div>
                        </div>


                    </div>


                    <div class="show-flex-row">
                        <div class="input-wrap" style="display: none">
                            <label for="pro-id" class="input-title">ID</label>
                            <input type="text" class="input-item" name="pro-id" id="pro-id" value="<%=pu.getId()%>">
                            <span class="input-error"></span>
                        </div>
<%--                        <div class="input-wrap" style="display: none">--%>
<%--                            <label for="pro-imei" class="input-title">imei</label>--%>
<%--                            <input type="text" class="input-item" name="pro-imei" id="pro-imei" value="<%=pu.()%>">--%>
<%--                            <span class="input-error"></span>--%>
<%--                        </div>--%>
                        <div class="input-wrap">
                            <label for="pro-name" class="input-title">Tên sản phẩm</label>
                            <input type="text" class="input-item" name="pro-name" id="pro-name" value="<%=pu.getName()%>">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label class="input-title">Ảnh</label>

                            <i class="fa-solid fa-plus ad_btn-control btn-add-em-img"></i>
                        </div>

                    </div>
                    <div class="input-wrap">
                        <label for="pro-imgs" class="input-title">Đường dẫn của ảnh</label>
                        <input type="text" class="input-item" name="pro-imgs" id="pro-imgs"  value="<%=pu.getImg()%>">
                        <span class="input-error"></span>
                    </div>
                    <div class="show-flex-row">

                        <div class="input-wrap">
                            <label for="pro-country" class="input-title">Xuất xứ</label>
                            <input type="text" class="input-item" name="pro-country" id="pro-country"  value="<%=pu.getMadeIn()%>">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label for="pro-brand" class="input-title">Hãng sản xuất</label>
                            <input type="text" class="input-item" name="pro-brand" id="pro-brand"  value="<%=pu.getBrand()%>">
                            <span class="input-error"></span>
                        </div>

                    </div>
                    <div class="show-flex-row">
                        <div class="input-wrap">
                            <label for="pro-kind" class="input-title">Nhóm</label>
                            <input type="text" class="input-item" name="pro-kind" id="pro-kind" value="<%=pu.getKind()%>">
                            <span class="input-error"></span>
                        </div>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="pro-area" class="input-title">Lĩnh vực</label>--%>
<%--                            <input type="text" class="input-item" name="pro-area" id="pro-area" value="<%=pu.getArea()%>">--%>
<%--                            <span class="input-error"></span>--%>
<%--                        </div>--%>

                    </div>
                    <div class="input-wrap">
                        <label for="pro-type" class="input-title">Phân loại</label>
                        <input type="text" class="input-item" name="pro-type" id="pro-type" value="<%=pu.getPhanloai()%>">
                        <span class="input-error"></span>
                    </div>
                    <div class="show-flex-row">
                        <div class="input-wrap">
                            <label for="pro-price" class="input-title">Giá (VND)</label>
                            <input type="number" class="input-item" name="pro-price" id="pro-price"  value="<%=pu.getPrice()%>">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label for="pro-amount" class="input-title">Số lượng hiện có</label>
                            <input type="number" class="input-item" name="pro-amount" id="pro-amount" value="<%=pu.getAmount()%>">
                            <span class="input-error"></span>
                        </div>
                    </div>



                    <div class="show-flex-row">
                        <div class="input-wrap">
                            <label for="pro-yearMade" class="input-title">Năm sản xuất</label>
                            <input type="number" class="input-item" name="pro-yearMade" min="1" max="10000000" id="pro-yearMade" value="<%=pu.getYearMade()%>">
                            <span class="input-error"></span>
                        </div>
                        <div class="input-wrap">
                            <label for="pro-dateImport" class="input-title">Ngày nhập </label>
                            <input type="date" class="input-item" name="pro-dateImport" id="pro-dateImport" value="<%=pu.getDateImport()%>">
                            <span class="input-error"></span>
                        </div>

                    </div>
                    <div class="input-wrap">
                        <label for="pro-des" class="input-title">Mô tả</label>
                        <textarea  type="text" class="input-item" name="pro-des" id="pro-des" rows="6" ><%=pu.getDes()%>
                            </textarea>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/addUpdate.js"></script>
</body>
</html>
