<%--<%@ page import="model.Employee" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: TO NHAT--%>
<%--  Date: 08/12/2023--%>
<%--  Time: 11:48 CH--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta content="width=device-width, initial-scale=1.0" name="viewport">--%>

<%--    <title>Admin - Quản lý nhân viên</title>--%>
<%--    <meta content="" name="description">--%>
<%--    <meta content="" name="keywords">--%>
<%--    <!-- Favicons -->--%>
<%--    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">--%>
<%--    <!-- Google Fonts -->--%>
<%--    <link href="https://fonts.gstatic.com" rel="preconnect">--%>
<%--    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">--%>
<%--    <!-- Vendor CSS Files -->--%>
<%--    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">--%>
<%--    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">--%>
<%--    <!--fontawesome-->--%>
<%--    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">--%>
<%--    <!-- Template Main CSS File -->--%>
<%--    <link href="assets/css/style.css" rel="stylesheet">--%>
<%--    <link rel="stylesheet" href="assets/css/adminCustomer.css">--%>
<%--    <link rel="stylesheet" href="assets/css/baseN.css">--%>
<%--    <link rel="stylesheet" href="assets/css/adminN.css">--%>
<%--    <link rel="stylesheet" href="assets/css/adminEmployee.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<%--%>

<%--    Employee e = (Employee) request.getAttribute("employee");--%>
<%--%>--%>
<%--<%@ include file="adminMenu.jsp" %>--%>


<%--<main id="main" class="main">--%>

<%--    <div class="pagetitle">--%>
<%--        <h1>Quản lí nhân viên</h1>--%>
<%--        <nav>--%>
<%--            <ol class="breadcrumb">--%>
<%--                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>--%>
<%--                <li class="breadcrumb-item active">Quản lí nhân viên</li>--%>
<%--            </ol>--%>
<%--        </nav>--%>
<%--    </div><!-- End Page Title -->--%>

<%--    <div class="ad-content">--%>
<%--            <div class="ad_container">--%>
<%--                <div class="ad-content-item">--%>
<%--                    <div class="ad_header">--%>

<%--                        <div class="show-flex-row" style="width: 100%; align-items: center">--%>
<%--                            <form class="ad_find-container" action="admin-search" method="get">--%>
<%--                                <input type="text" placeholder="Nhập tên hoặc thông tin liên lạc" class="ad_find-input" name="search" value="">--%>
<%--                                <input type="text" style="display: none" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="object" value="employee" readonly>--%>
<%--                                <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>--%>
<%--                            </form>--%>
<%--                            <div>--%>
<%--                                <a class="ad_btn-control btn-up-pro" href="goto-add-customer?status=0">Thêm khách hàng</a>--%>
<%--                            </div>--%>

<%--                        </div>--%>

<%--                    </div>--%>
<%--                    <table class="table">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th scope="col">Hinh anh</th>--%>
<%--                            <th scope="col">Họ và tên</th>--%>
<%--                            <th scope="col">Liên hệ</th>--%>
<%--                            <th scope="col">Chuc vu</th>--%>
<%--                            <th scope="col">Chi nhanh</th>--%>
<%--                            <th scope="col">Chi tiet</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <%--%>
<%--                            for(int i=0; i<employeeList.size();i++) {--%>
<%--                        %>--%>
<%--                        <tr class="<%= i%2==0 ? "roww" : ""%>">--%>
<%--                            &lt;%&ndash;                    <th scope="row"><%=pus.get(i).getId()%></th>&ndash;%&gt;--%>
<%--                            <td style="height: 100px" ><img src="./assets/img/employee/<%=employeeList.get(i).getImgurl()%>" alt="" style="height: 100%"></td>--%>
<%--                            <td><%=employeeList.get(i).getName() %></td>--%>
<%--                            <td><%=employeeList.get(i).getEmail() %> <br> <%=employeeList.get(i).getPhone()%> <br> <%=employeeList.get(i).getAddress() %> </br></td>--%>

<%--                            &lt;%&ndash;                    <td><%=cuss.get(i).getAddress() %></td>&ndash;%&gt;--%>
<%--                            <td><%=employeeList.get(i).getPosition() %><br> <%=employeeList.get(i).getArea()%></td>--%>
<%--                            <td><%=employeeList.get(i).getBranch() %></td>--%>
<%--                            <td><a href="goto-history-buying?cusID=<%=employeeList.get(i).getId()%>">Chi tiet</a></td>--%>


<%--&lt;%&ndash;                            <td><a class="btn-update-product" href="goto-update-customer?id=<%=customerList.get(i).getId()%>">Cập nhật</a></td>&ndash;%&gt;--%>
<%--                        </tr>--%>
<%--                        <%--%>
<%--                            }--%>
<%--                        %>--%>

<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>

<%--            </div>--%>




<%--        <div class="confirm-popup confirm-stop">--%>
<%--            <div class="modal__overlay">--%>
<%--                <div class="modal__confirm-content" onclick="event.stopPropagation()">--%>
<%--                    <div class="confirm__message">--%>
<%--                        <p>Bạn chắc chắn nhân viên này đã nghỉ việc?<br/>--%>
<%--                            Lưu ý sau khi xác nhận nghỉ không thể mở lại tài khoản này,  <br/>--%>
<%--                            tài khoản này sẽ vẫn tồn tại trong cơ sơ dữ liệu, nhưng không thể mở lại.</p>--%>
<%--                    </div>--%>
<%--                    <div class="confirm__control">--%>
<%--                        <div class="confirm-btn yes-confirm">Cấm</div>--%>
<%--                        <div class="confirm-btn no-confirm">Hủy</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="confirm-popup add-img-box">--%>
<%--            <div class="modal__overlay">--%>
<%--                <div class="modal__confirm-content" onclick="event.stopPropagation()">--%>
<%--                    <div class="confirm__message">--%>
<%--                        <div class="input-wrap">--%>
<%--                            <label for="em-img" class="input-title">Nhập đường dẫn tới hình ảnh.</label>--%>
<%--                            <input type="text" class="input-item" name="em-img" id="em-img">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="confirm__control">--%>
<%--                        <div class="confirm-btn add-img-btn">Thêm</div>--%>
<%--                        <div class="confirm-btn cancel-add-img-btn">Hủy</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>


<%--    </div>--%>





<%--</main><!-- End #main -->--%>



<%--<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>--%>



<%--<!-- Template Main JS File -->--%>
<%--<!--<script src="assets/js/main.js"></script>-->--%>
<%--<script src="assets/js/adEm.js"></script>--%>
<%--<script>--%>
<%--    // getInfo();--%>
<%--    // saveChanged();--%>
<%--    // const inputs = $$('.form-container input');--%>
<%--    // console.log(inputs);--%>
<%--    // for(let i of inputs) {--%>
<%--    //     i.value = "1";--%>
<%--    // }--%>
<%--    // addImg();--%>

<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
