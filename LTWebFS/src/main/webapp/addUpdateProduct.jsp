<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.Brand" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Thêm/Cập nhật sản phẩm</title>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/addUpdate.css">
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/toast.css">

    <script src="assets/js/toast.js"></script>
    <script src="assets/js/addUpdateProduct.js"></script>
</head>
<body>
<%
    ProductUnit pu = (ProductUnit) request.getAttribute("productUnit");
    if(pu==null) {
        pu = new ProductUnit();
    }

    ArrayList<Brand> brandList = (ArrayList<Brand>) request.getAttribute("brandList");
    if(brandList==null){
        brandList = new ArrayList<>();
    }

    String status = (String) request.getAttribute("status");
    if(status==null) {
        status="";
    }
%>
<div class="ad-content mt10">
    <div id="toast">

    </div>

    <%=(status.equalsIgnoreCase("addsuccessful")?"<script> showSuccessToast(\"Thêm thành công sản phẩm ID: "+ pu.getId() +"\"); </script>":"")%>
    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p>Bạn chắc chắn muốn dừng bán sản phẩm này?</p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn yes-confirm" href="addUpdate-product?action=stop&id=<%=pu.getId()%>">Dừng</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal confirm-resale">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p>Mở bán lại sản phẩm?</p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn yes-resale" href="addUpdate-product?action=resale&id=<%=pu.getId()%>">Bán lại</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>
        </div>
    </div>
    <div class="ad-content-item">

        <div class="ad_container">
            <a href="goto-product-admin" class="backto-AdminProduct">Quay lại trang quản lí sản phẩm</a>

            <div class="form-container">
                <form action="addUpdate-product" method="get" id="updateForm">
                    <div class="show-flex-row">
                        <h4><%= (pu.getName()==""?"Thêm sản phẩm":"Cập nhật sản phẩm") %></h4>
                    </div>
                    <div class="show-flex-row">
                        <div class="grid__row img-showing">
                        <%
                            String[] imgsTokens = pu.getImg().split("--");
                            for(int i=0; i<imgsTokens.length;i++){
                        %>
                            <div class="grid-col-2 mtb-5px">
                                <img src="./assets/img/products/<%=imgsTokens[i]%>" alt="" class="pro-img-item">
                            </div>

                        <%

                            }

                        %>
                            <div class="disabled-showing <%= (pu.getAvailable()==0?"active":"") %>" style="right: 0; left: 100px; position: absolute;">
                                <div class="disabled-showing-content">
                                    NGƯNG BÁN
                                </div>
                            </div>
                        </div>
                        <div class="stop_reSale <%= (pu.getName()==""?"hide":"") %>" >
                            <div class="btn btn-stop-pro <%= (pu.getAvailable()==1?"active":"") %>" onclick="showModalStop()">Dừng bán</div>
                            <div class="btn btn-resale-pro <%= (pu.getAvailable()==0?"active":"") %>"  onclick="showModalResale()">Bán lại</div>

                        </div>
                    </div>
                    <div id="info-form-content">
                        <div class="show-flex-row">

                            <div class="form-group w-50">
                                <label class="w-20" for="id">ID</label>
                                <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=pu.getId()%>" readonly>
                                <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                            </div>
                            <div class="form-group w-50">
                                <label class="w-20" for="name">Tên: </label>
                                <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Enter name" value="<%=pu.getName() %>">
                                <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                            </div>
                        </div>
                        <div class="form-group w-80">
                            <label class="w-20" for="brand">Thương hiệu: </label>
                            <select class="form-select" aria-label="Default select example" id="brand" name="brand">
                                <% for(Brand b : brandList) {
                                %>
                                <option <%=b.getId()==pu.getBrandID() ? "selected" : "" %> value="<%=b.getId()+"="+b.getName()+"="+b.getCountry()%>"><%=b.getName()+" - " + b.getCountry() %></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>
                        <div class="show-flex-row">
                            <div class="form-group w-50">
                                <label class="w-20" for="kind">Lĩnh vực: </label>
                                <input type="text" class="form-control w-80" id="kind" name="kind" paria-describedby="" placeholder="Enter kind" value="<%=pu.getKind() %>">
                            </div>
                            <div class="form-group w-50">
                                <label class="w-20" for="price">Đơn giá: </label>
                                <input type="text" class="form-control w-80" id="price" name="price" paria-describedby="" placeholder="Enter kind" value="<%=pu.getPrice() %>">
                            </div>
                        </div>

                        <div class="show-flex-row">
                            <div class="form-group">
                                <label  for="color">Màu sắc: </label>
                                <input type="text" size="10" class="form-control" id="color" name="color" aria-describedby="" placeholder="Enter color" value="<%=pu.getColor() %>">
                            </div>
                            <div class="form-group">
                                <label for="size">Kích cỡ: </label>
                                <input type="text" class="form-control" id="size" name="size" aria-describedby="" placeholder="Enter size" value="<%=pu.getSize() %>">
                            </div>
                            <div class="form-group">
                                <label for="wattage">Công suất (W): </label>
                                <input type="text" class="form-control" id="wattage" name="wattage" aria-describedby="" placeholder="Enter wattage" value="<%=pu.getWattage() %>">
                            </div>
                        </div>
                        <div class="form-group w-50" style="display: none;">
                            <label  class="w-20" for="amount">Tồn kho: </label>
                            <input type="number" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Enter amount" value="<%=pu.getAmount() %>">
                        </div>
                        <div class="show-flex-row">
                            <div class="form-group w-50">
                                <label  class="w-20" for="yearmade">Năm sản xuất: </label>
                                <input type="number" size="10" class="form-control w-80" id="yearmade" name="yearmade"  aria-describedby="" placeholder="Enter year made" value="<%=pu.getYearMade() %>">
                            </div>
                            <div class="form-group w-50">
                                <label  class="w-20"  for="dateimport">Ngày nhập: </label>
                                <input type="date" class="form-control w-80" id="dateimport" name="dateimport"  aria-describedby="" placeholder="Enter date import" value="<%=pu.getDateImport() %>">
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="w-20" for="img">Hình ảnh: </label>
                            <input type="text" class="form-control" id="img" name="img" aria-describedby="" placeholder="Enter img url" value="<%=pu.getImg() %>">
                        </div>
                        <div class="form-group">
                            <label class="w-20" for="description" class="input-title">Mô tả</label>
                            <textarea  type="text" class="form-control" name="description" id="description" rows="6" ><%=pu.getDes() %>
                            </textarea>
                        </div>
                        <div class="form-group" style="display: none">
                            <label class="w-20" for="action" class="input-title">action</label>
                            <input type="text" class="form-control" id="action" name="action" aria-describedby="" placeholder="Enter img url" value="<%= (pu.getName()==""?"add":"update") %>">

                        </div>
                        <div class="form-group" style="display: none">
                            <label class="w-20" for="status" class="input-title">status</label>
                            <input type="text" class="form-control" id="status" name="status" aria-describedby="" placeholder="Enter img url" value="1">

                        </div>

                        <div class="show-flex-row">
                            <div class="ad_func-container">
                                <div><a class="btn btn-third" href="goto-product-admin">Hủy</a></div>
                            </div>
                            <div class="ad_func-container">
                                <button class="btn btn-primary" type="submit"><%= (pu.getName()==""?"Thêm":"Lưu") %></button>
                            </div>
                        </div>
                    </div>

                    <!--                    <button type="submit" class="btn btn-primary">Submit</button>-->
                </form>

            </div>
        </div>
    </div>
</div>
<script>






</script>
<script src="assets/js/addUpdate.js"></script>
</body>
</html>
