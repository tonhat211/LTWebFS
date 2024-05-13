<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 08/12/2023
  Time: 11:50 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Admin - Quản lý sản phẩm</title>


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
    String currentSearch = ((String) session.getAttribute("currentSearch"));
    if(currentSearch== null || currentSearch.equalsIgnoreCase("")){
        currentSearch = "";
    }

    String adminCurrentSearchProduct = ((String) session.getAttribute("adminCurrentSearchProduct"))==null ? "" : ((String) session.getAttribute("adminCurrentSearchProduct"));


    ArrayList<ProductUnit> pus = (ArrayList<ProductUnit>) request.getAttribute("productUnitList");

%>
<!-- ======= Header ======= -->
<%@ include file="adminMenu.jsp" %>


<main id="main" class="main">

    <div class="pagetitle">
        <h1>Quản lí sản phẩm</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lí sản phẩm</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <!--    <section class="section dashboard">-->
    <!--        <h1>Content</h1>-->
    <!--    </section>-->
    <div class="ad-content">
        <div class="ad_header" style="width: 100%">
            <div class="show-flex-row" style="width: 100%; align-items: center">
                <form class="ad_find-container" action="admin-search-product" method="get">
                    <input type="text" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="search" value="<%=adminCurrentSearchProduct%>">
                    <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div>
                    <a class="ad_btn-control btn-up-pro" href="goto-add-product?status=0">Thêm sản phẩm</a>
                </div>

            </div>


            <!--                            <div class="ad_func-container">-->
            <!--                                <div class="ad_func-add-btn ad_func-btn">Thêm</div>-->
            <!--                            </div>-->
        </div>
    </div>

<%--    <%--%>
<%--        for(ProductSuperDetail p : psds) {--%>
<%--    %>--%>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Tên</th>
            <th scope="col">Lĩnh vực</th>
            <th scope="col">Thương hiệu</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">Tồn kho</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
            <%
                for(int i=0; i<pus.size();i++) {
            %>
        <tr class="<%= i%2==0 ? "roww" : ""%>">
            <th scope="row"><%=pus.get(i).getId()%></th>
            <td><%=pus.get(i).getName() %></td>
            <td><%=pus.get(i).getKind() %></td>
            <td><%=pus.get(i).getBrand() %></td>
            <td><%=pus.get(i).getPrice() %></td>
            <td><%=pus.get(i).getAmount() %></td>
            <td><a class="btn-update-product" href="go-to-update-product?id=<%=pus.get(i).getId()%>">Cập nhật</a></td>
        </tr>
            <%
                }
            %>

                    </tbody>
                </table>
            <%--    <div class="ad-content">--%>
<%--        <!--                        <div class="temp"> <p>noi dung</p></div> &lt;!&ndash;Xoa dong nay truoc khi code&ndash;&gt;-->--%>

<%--        <div class="ad-content-item">--%>


<%--            <div class="ad_container">--%>
<%--                <div class="pro-showing">--%>
<%--                    <div class="pro-img-cont">--%>
<%--                        <div class="grid__row">--%>
<%--                            <%--%>
<%--                                String[] imgurlTokens = p.getImgsUrl().split("==");--%>
<%--                                for(int i=0; i<imgurlTokens.length; i++){--%>
<%--                            %>--%>
<%--                                    <div class="grid-col-2 mtb-5px">--%>
<%--                                        <img src="assets/img/products/<%=imgurlTokens[i]%>" alt="" class="pro-img-item">--%>
<%--                                    </div>--%>


<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="seperate-horizontal-90-ad mtb-5px"></div>--%>
<%--                    <div class="pro-info">--%>
<%--                        <p>ID sản phẩm: <span class="pro_id"><%=p.getName()%></span></p>--%>
<%--                        <p>Tên: <span class="pro_name"><%=p.getId()%></span></p>--%>
<%--                        <p>Xuất xứ: <span class="pro_country"><%=p.getCountry()%></span></p>--%>
<%--                        <p>Hãng sản xuất: <span class="pro_brand"><%=p.getBrand()%></span></p>--%>
<%--                        <p>Nhóm: <span class="pro_kind"><%=p.getKind()%></span></p>--%>
<%--                        <p>Lĩnh vực: <span class="pro_area"><%=p.getArea()%></span></p>--%>
<%--                        <p>Giá: <span class="pro_price"><%=p.getPrice()%></span>VND</p>--%>
<%--                        <p>Phân loại: <span class="pro_type"><%=p.getType()%></span></p>--%>
<%--                        <p>Số lượng hiện có: <span class="pro_amount"><%=p.getAmount()%></span> (sản phẩm)</p>--%>
<%--                        <p>Năm sản xuất: <span class="pro_yearMade"><%=p.getYearMade()%></span></p>--%>
<%--                        <p>Ngày nhập: <span class="pro_dateImport"><%=p.getDateImport()%></span></p>--%>
<%--                        <p>Imei:<span class="pro_imei"><%=p.getImei()%></span></p>--%>

<%--                        <p>Mô tả: <span class="pro_des"><%=p.getDes()%></span></p>--%>
<%--                    </div>--%>
<%--                    <div class="disabled-showing t70 l140 <%=p.getAvailable()==0 ? "active" : ""%>">NGƯNG BÁN</div>--%>
<%--                </div>--%>

<%--                <div class="customer-control">--%>
<%--                    <div>--%>
<%--                        <a class="ad_btn-control btn-up-pro" href="addUpdate-product?action=update&id=<%=p.getId()%>">Cập nhật thông tin</a>--%>
<%--                    </div>--%>
<%--                    <div class="ad_btn-control btn-stop-pro <%=p.getAvailable()==1 ? "active" : ""%>">Dừng bán</div>--%>
<%--                    <div class="ad_btn-control btn-resale-pro <%=p.getAvailable()==0 ? "active" : ""%>">Bán lại</div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--&lt;%&ndash;            <script> init();</script>&ndash;%&gt;--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>

<%--        </div>--%>
<%--        <div class="confirm-popup confirm-stop">--%>
<%--            <div class="modal__overlay">--%>
<%--                <div class="modal__confirm-content" onclick="event.stopPropagation()">--%>
<%--                    <div class="confirm__message">--%>
<%--                        <p>Bạn chắc chắn muốn dừng bán sản phẩm này?</p>--%>
<%--                    </div>--%>
<%--                    <div class="confirm__control">--%>
<%--                        <div ><a class="confirm-btn yes-confirm" href="stopresale-product?action=stop&id=<%=p.getId()%>">Dừng</a></div>--%>
<%--                        <div class="confirm-btn no-confirm">Hủy</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="confirm-popup confirm-resale">--%>
<%--            <div class="modal__overlay">--%>
<%--                <div class="modal__confirm-content" onclick="event.stopPropagation()">--%>
<%--                    <div class="confirm__message">--%>
<%--                        <p>Bạn chắc chắn muốn bán lại bán sản phẩm này?</p>--%>
<%--                    </div>--%>
<%--                    <div class="confirm__control">--%>
<%--                        <div ><a  class="confirm-btn yes-resale-confirm" href="stopresale-product?action=resale&id=<%=p.getId()%>">Bán lại</a></div>--%>
<%--                        <div class="confirm-btn no-resale-confirm">Hủy</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>


<%--    </div>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--    <div class="confirm-popup add-img-box">--%>
<%--            <div class="modal__overlay">--%>
<%--                <div class="modal__confirm-content" onclick="event.stopPropagation()">--%>
<%--                    <div class="confirm__message">--%>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="pro-img" class="input-title">Nhập đường dẫn tới hình ảnh.</label>--%>
<%--                            <input type="text" class="input-item" name="pro-img" id="pro-img">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="confirm__control">--%>
<%--                        <div class="confirm-btn add-img-btn">Thêm</div>--%>
<%--                        <div class="confirm-btn cancel-add-img-btn">Hủy</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="ad-content-item">

<%--            <div class="ad_container">--%>
<%--                <div class="form-container">--%>
<%--                    <form action="add-product" method="get">--%>
<%--                        <div class="ad_func-container">--%>
<%--                            <button class="ad_func-add-btn ad_func-btn active" type="submit">Thêm</button>--%>
<%--                            <button class="ad_func-save-btn ad_func-btn">Lưu</button>--%>
<%--                        </div>--%>
<%--                        <div class="show-flex-row">--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-name" class="input-title">Tên sản phẩm</label>--%>
<%--                                <input type="text" class="input-item" name="pro-name" id="pro-name" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label class="input-title">Ảnh</label>--%>

<%--                                <i class="fa-solid fa-plus ad_btn-control btn-add-em-img"></i>--%>
<%--                            </div>--%>


<%--                        </div>--%>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="pro-imgs" class="input-title">Đường dẫn của ảnh</label>--%>
<%--                            <input type="text" class="input-item" name="pro-imgs" id="pro-imgs" required>--%>
<%--                            <span class="input-error"></span>--%>
<%--                        </div>--%>
<%--                        <div class="show-flex-row">--%>

<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-country" class="input-title">Xuất xứ</label>--%>
<%--                                <input type="text" class="input-item" name="pro-country" id="pro-country">--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-brand" class="input-title">Hãng sản xuất</label>--%>
<%--                                <input type="text" class="input-item" name="pro-brand" id="pro-brand" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>

<%--                        </div>--%>
<%--                        <div class="show-flex-row">--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-kind" class="input-title">Nhóm</label>--%>
<%--                                <input type="text" class="input-item" name="pro-kind" id="pro-kind" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-area" class="input-title">Lĩnh vực</label>--%>
<%--                                <input type="text" class="input-item" name="pro-area" id="pro-area" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>

<%--                        </div>--%>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="pro-type" class="input-title">Phân loại</label>--%>
<%--                            <input type="text" class="input-item" name="pro-type" id="pro-type">--%>
<%--                            <span class="input-error"></span>--%>
<%--                        </div>--%>
<%--                        <div class="show-flex-row">--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-price" class="input-title">Giá (VND)</label>--%>
<%--                                <input type="text" class="input-item" name="pro-price" id="pro-price" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-amount" class="input-title">Số lượng hiện có</label>--%>
<%--                                <input type="text" class="input-item" name="pro-amount" id="pro-amount" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                        </div>--%>



<%--                        <div class="show-flex-row">--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-yearMade" class="input-title">Năm sản xuất</label>--%>
<%--                                <input type="text" class="input-item" name="pro-yearMade" id="pro-yearMade" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>
<%--                            <div class="input-wrap">--%>
<%--                                <label for="pro-dateImport" class="input-title">Ngày nhập </label>--%>
<%--                                <input type="date" class="input-item" name="pro-dateImport" id="pro-dateImport" required>--%>
<%--                                <span class="input-error"></span>--%>
<%--                            </div>--%>

<%--                        </div>--%>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="pro-des" class="input-title">Mô tả</label>--%>
<%--                            <textarea  type="text" class="input-item" name="pro-des" id="pro-des" rows="6" required>--%>
<%--                                            </textarea>--%>

<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<script src="assets/js/adPro.js"></script>
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
</body>
</html>
