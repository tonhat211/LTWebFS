<%@ page import="model.Employee" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 08/12/2023
  Time: 11:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Admin - Quản lý nhân viên</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
    <link rel="stylesheet" href="assets/css/adminEmployee.css">
    <link rel="stylesheet" href="assets/css/toast.css">
</head>
<body>
<%

    String adminCurrentSearch= (String) request.getAttribute("currentSearch");
    if(adminCurrentSearch== null || adminCurrentSearch.equalsIgnoreCase("")){
        adminCurrentSearch = "";
    }
    ArrayList<Employee> employeeList = (ArrayList<Employee>) request.getAttribute("employeeList");
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(employeeList);

    String status = (String) request.getAttribute("status");


%>
<%
    if(status!=null) {
        if(status.equalsIgnoreCase("addSuccessful")) {
%>

        <script> showSuccessToast("Đã thêm thành công một tài khoản"); </script>

<%
        }

    }
%>
<script>
    var ems = <%= json %>;
    // console.log(ems);
</script>
<script src="assets/js/toast.js"></script>

<%@ include file="adminMenu.jsp" %>


<main id="main" class="main">
    <%--thong bao--%>
    <div id="toast">

    </div>

    <div class="pagetitle">
        <h1>Quản lí nhân viên</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Quản lí nhân viên</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <div class="ad-content" style="padding: 10px">
            <div class="ad_container">
                <div class="ad-content-item">
                    <div class="ad_header">

                        <div class="show-flex-row" style="width: 100%; align-items: center">
                            <form class="ad_find-container" action="admin-search" method="get" id="searchForm">
                                <input type="text" placeholder="Nhập tên hoặc thông tin liên quan" class="ad_find-input" name="input" value="<%=adminCurrentSearch%>">
                                <input type="text" style="display: none" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="object" value="employee" readonly>
                                <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </form>
                            <div>
                                <a class="ad_btn-control btn-up-pro" href="employee?action=prepareAdd">Thêm nhân viên</a>
                            </div>

                        </div>

                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Ảnh đại diện</th>
                            <th scope="col">Họ và tên</th>
                            <th scope="col">Liên hệ</th>
                            <th scope="col">Chức vụ</th>
                            <th scope="col">Chi nhánh</th>
                            <th scope="col">Cập nhật</th>
                        </tr>
                        </thead>
                        <tbody id="employee-container">
                        <%
                            for(int i=0; i<employeeList.size();i++) {
                        %>
                        <tr class="<%= i%2==0 ? "roww" : ""%>"  onclick="showDetail(<%=employeeList.get(i).getId()%>)">
                            <%--                    <th scope="row"><%=pus.get(i).getId()%></th>--%>
                            <td style="height: 100px" ><img src="<%=employeeList.get(i).getImgurl()%>" alt="" style="height: 100%"></td>
                            <td><%=employeeList.get(i).getName() %></td>
                            <td><%=employeeList.get(i).getEmail() %> <br> <%=employeeList.get(i).getPhone()%> <br> <%=employeeList.get(i).getAddress() %> </br></td>

                            <%--                    <td><%=cuss.get(i).getAddress() %></td>--%>
                            <td><%=employeeList.get(i).getPosition() %><br> <%=employeeList.get(i).getArea()%></td>
                            <td><%=employeeList.get(i).getBranch() %></td>
                            <td><a href="employee?action=prepareUpdate&&id=<%=employeeList.get(i).getId()%>">Cập nhật</a></td>


<%--                            <td><a class="btn-update-product" href="goto-update-customer?id=<%=customerList.get(i).getId()%>">Cập nhật</a></td>--%>
                        </tr>
                        <%
                            }
                        %>

                        </tbody>
                    </table>
                </div>

            </div>


    </div>
    <div class="confirm-popup confirm-stop">
        <div class="modal__overlay" onclick="unshowDetail()">
            <div class="modal__emplpoyee-detail" onclick="event.stopPropagation()">

            </div>
        </div>
    </div>

    <script>

        function showDetail(id){
            var action = "showInfo";
            $.ajax({
                url: "/LTWebFS/employee",
                method: "GET",
                data: {action: action, id: id},
                success: function(data) {
                    $('.confirm-stop').addClass('active');
                    $('.modal__emplpoyee-detail').html(data);

                }
            });
        }

        function unshowDetail(){
            const $ = document.querySelector.bind(document);
            const $$ = document.querySelectorAll.bind(document);
            $('.confirm-stop').classList.remove('active');
        }

        document.querySelector("#searchForm").addEventListener('submit',function (event){
            event.preventDefault();
            var data = new FormData(this);
            var input = data.get("input");
            var action ="search";
            searchEmployee(action, input);

        });

        function searchEmployee(action, input) {
            $.ajax({
                url: "/LTWebFS/employee",
                method: "GET",
                data: {action: action, input: input},
                success: function(data) {
                    $("#employee-container").html(data);

                }
            });
        }
    </script>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>



<!-- Template Main JS File -->
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
