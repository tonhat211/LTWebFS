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
<%@include file="./assets/common/taglib.jsp"%>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/adminCustomer.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/adminProduct.css">
    <link rel="stylesheet" href="assets/css/toast.css">
    <link rel="stylesheet" href="assets/css/login.css">


    <script src="assets/js/toast.js"></script>
<%--    <script src="assets/js/adPro.js">--%>

    <%--    doc file excel--%>
    <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>

    </script>

    <style>
        .exit-btn {
            padding: 4px;
            border-radius: 4px;
            display: inline-block;
        }
        .exit-btn:hover{
            opacity: 0.3;
            cursor: pointer;
            background-color: rgba(13, 110, 253, 0.24);
            opacity: 0.3;

        }
    </style>
    <style>
        table tr .error {
            color: red;
            background-color: rgba(255, 0, 0, 0.24);
        }
        table tr .notfound {
            color: blue;
            background-color: rgba(0, 42, 255, 0.18);
        }
        .btn:hover {
            opacity: 0.3;
        }
    </style>
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
        <div id="toast">

        </div>
        <div class="ad_header" style="width: 100%; flex-direction: column" id="action-product">
            <div class="show-flex-row" style="width: 100%; align-items: center">
                <form class="ad_find-container" action="admin-search-product" method="get" id="searchForm">
                    <input type="text" placeholder="Nhập tên hoặc nhóm" class="ad_find-input" name="input" value="<%=adminCurrentSearchProduct%>">
                    <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div>
                    <a class="ad_btn-control" id="export-product-btn">Xuất kho</a>

                </div>
                <div>
                    <a class="ad_btn-control" id="import-product-btn">Nhập kho</a>
                </div>
                <div>
                    <a class="ad_btn-control btn-up-pro" href="admin-product?action=prepareAdd">Thêm sản phẩm</a>

                </div>

            </div>


            <div style="width: 100%" id="product-action">

                </div>
<%--                <div class="export-container">--%>
<%--                    <div class="seperate-horizontal-90" style="margin: 20px auto"></div>--%>
<%--                    <div style="width: 100%">--%>
<%--                        <form action="" style="width: 70%; margin: 0 auto" id="export-product-form">--%>
<%--                            <h4 style="text-align: center">Xuất kho</h4>--%>
<%--                            <div class="show-flex-row">--%>
<%--                                <div class="form-group w-30" style="margin: 0 10px">--%>
<%--                                    <label class="w-20" for="id">ID: </label>--%>
<%--                                    <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Nhập ID" value="">--%>
<%--                                    <div class="error idinput" hidden>Không được bỏ trống mục này</div>--%>

<%--                                </div>--%>
<%--                                <div class="form-group w-30" style="margin: 0 10px">--%>
<%--                                    <label class="w-20" for="amount">Số lượng: </label>--%>
<%--                                    <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Nhập số lượng" value="">--%>
<%--                                    <div class="error amountinput" hidden>Không được bỏ trống mục này</div>--%>

<%--                                </div>--%>
<%--                                <input type="text" class="form-control w-80" id="action" name="action" aria-describedby="" placeholder="Enter name" value="export" hidden readonly>--%>

<%--                            </div>--%>
<%--                            <div class="form-group w-30" style="margin: 10px 10px">--%>
<%--                                <label class="w-20" for="reason">Nguyên nhân: </label>--%>

<%--                                <textarea  type="text" class="form-control" name="reason" id="reason" rows="6"></textarea>--%>

<%--                            </div>--%>
<%--                            <div class="show-flex-row" style="justify-content: end">--%>
<%--                                <button class="btn btn-primary" type="button" onclick="exportProduct()" style="margin: 10px 10px;">Xuất</button>--%>
<%--                            </div>--%>


<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">--%>
<%--                    <div id="toast-2" style="margin: auto 0">--%>
<%--                        <!--                                <div class="toastt-2 toast-2&#45;&#45;success">-->--%>
<%--                        <!--                                    <p class="toast__msg" style="color: white">thanh cong</p>-->--%>
<%--                        <!--                                </div>-->--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
        </div>

    </div>

<%--    <%--%>
<%--        for(ProductSuperDetail p : psds) {--%>
<%--    %>--%>

    <table class="table" id="info-table" style="width: 100vw;">
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
        <tbody id="info-table-body">
            <%
                for(int i=0; i<pus.size();i++) {
            %>
        <tr class="<%= i%2==0 ? "roww" : ""%>">
            <th scope="row"><%=pus.get(i).getId()%></th>
            <td><%=pus.get(i).getName() %></td>
            <td><%=pus.get(i).getKind() %></td>
            <td><%=pus.get(i).getBrand() %></td>
            <td><fmt:formatNumber value="<%=pus.get(i).getPrice() %>" pattern="#,##0.00"/>
                VND</td>
            <td><%=pus.get(i).getAmount() %></td>
            <td><a class="btn-update-product" href="admin-product?action=prepareUpdate&&id=<%=pus.get(i).getId()%>">Cập nhật</a></td>
        </tr>
            <%
                }
            %>

                    </tbody>
                </table>

        <div class="ad-content-item">


        </div>
    </div>
    <script>
        let runAuto = false;
        let updateIndex = 1;

        function stopAutoUpdate(event) {
            runAuto = false;
            event.style.display = "none";
            document.querySelector("#pre-auto-update-btn").style.display = "block";

        }

        function preAutoUpdate(event,action) {
            autoUpdate(updateIndex,action);
            event.style.display = "none";
            document.querySelector("#stop-auto-update-btn").style.display = "block";
        }
        function autoUpdate(startIndex,action) {
            runAuto = true;
            console.log(document.querySelector("#stop-auto-update-btn"));
            document.querySelector("#stop-auto-update-btn").disabled = false;
            document.querySelector("#start-auto-update-btn").disabled = true;
            let actionVN = "error action";
            if(action=="import") {
                actionVN = "nhập";
            } else if (action=="export"){
                actionVN = "xuất";
            }

            let visitedRows = parseInt(document.querySelector(".visited-rows").innerText,10);

            if(startIndex==1) {
                showSuccessToast("BẮT ĐẦU "+actionVN+" sản phẩm tự động");
                document.querySelector(".visited-rows").innerText = 0;
            } else {
                showSuccessToast("TIẾP TỤC "+actionVN+" sản phẩm tự động");
            }
            const excelData = document.querySelector("#excel_data table");
            const rows = excelData.querySelectorAll("tr");
            const cols = excelData.querySelectorAll("th");

            let keyCol=-1;
            let dataCol=-1;

            for(let i=0;i<cols.length;i++) {
                if(cols[i].innerText=="ID" || cols[i].innerText=="id") {
                    keyCol=i;
                    break;
                }
            }
            dataCol = cols.length - 1;

            let numOfRows = rows.length;
            console.log("action ban dau: " + action);
            if(action=="import") {
                function executeImportAuto(index) {

                    if(runAuto==false) {
                        showSuccessToast("TẠM DỪNG quá trình "+actionVN+" tự động.");
                        updateIndex = index;
                        // visitedRows--;
                        return;
                    }
                    if(index >= excelData.rows.length ) {
                        showSuccessToast("KẾT THÚC quá trình "+actionVN+" tự động.");
                        updateIndex = 1;
                        document.querySelector("#stop-auto-update-btn").disabled = true;
                        return;
                    }
                    let id = excelData.rows[index].cells[keyCol].innerText;
                    let amount = excelData.rows[index].cells[dataCol].innerText;

                    importProductAuto("auto",id,amount,"import");
                    setTimeout(function() {
                        if(excelData.querySelectorAll("tr").length<numOfRows) {
                            numOfRows = excelData.querySelectorAll("tr").length;
                            index= index-1;
                        }
                        executeImportAuto(index + 1);
                    }, 1500);
                    visitedRows++;
                    document.querySelector(".visited-rows").innerText=visitedRows;
                    console.log("hang tiep theo: " + visitedRows);


                }
                executeImportAuto(startIndex);
            } else if (action=="export"){
                console.log("Export");
                function executeExportAuto(index) {
                    if(runAuto==false) {
                        showSuccessToast("TẠM DỪNG quá trình "+actionVN+" tự động.");
                        updateIndex = index;
                        // visitedRows--;
                        return;
                    }
                    if(index >= excelData.rows.length ) {
                        showSuccessToast("KẾT THÚC quá trình "+actionVN+" tự động.");
                        updateIndex = 1;
                        document.querySelector("#stop-auto-update-btn").disabled = true;
                        return;
                    }

                    let amountCol=-1;
                    let reasonCol=-1;
                    for(let i=0;i<cols.length;i++) {
                        if(cols[i].innerText=="amount" || cols[i].innerText=="AMOUNT") {
                            amountCol=i;
                            continue;
                        }
                        if(cols[i].innerText=="reason" || cols[i].innerText=="REASON") {
                            reasonCol=i;
                            continue;
                        }
                    }

                    let id = excelData.rows[index].cells[keyCol].innerText;
                    let amount = excelData.rows[index].cells[amountCol].innerText;
                    let reason = excelData.rows[index].cells[reasonCol].innerText;

                    exportProductAuto("auto",id,amount,"export",reason);
                    setTimeout(function() {
                        if(excelData.querySelectorAll("tr").length<numOfRows) {
                            numOfRows = excelData.querySelectorAll("tr").length;
                            index= index-1;
                        }
                        executeExportAuto(index + 1);
                    }, 1500);
                    visitedRows++;
                    document.querySelector(".visited-rows").innerText=visitedRows;
                    console.log("hang tiep theo: " + visitedRows);


                }
                executeExportAuto(startIndex);
            }

        }

        function updateExcelList(id,status) {
            const excelData = document.querySelector("#excel_data table");
            const cols = excelData.querySelectorAll("th");
            let keyCol=-1;
            for(let i=0;i<cols.length;i++) {
                if(cols[i].innerText=="ID" || cols[i].innerText=="id") {
                    keyCol=i;
                    break;
                }
            }
            for(let i=0;i<excelData.rows.length;i++) {
                if(excelData.rows[i].cells[keyCol].innerText==id) {
                    if(excelData.rows[i].querySelector("td").classList.contains("error")
                        || excelData.rows[i].querySelector("td").classList.contains("notfound"))
                        continue;
                    else {
                        if(status=="successful") {
                            excelData.deleteRow(i);
                        }
                        else if(status=="error"){
                            const tds = excelData.rows[i].querySelectorAll("td");
                            for(let i=0;i<tds.length;i++) {
                                tds[i].classList.add("error");
                            }
                        } else {
                            const tds = excelData.rows[i].querySelectorAll("td");
                            for(let i=0;i<tds.length;i++) {
                                tds[i].classList.add("notfound");
                            }
                        }
                    }

                    break;
                }

            }
        }

        function importProductAuto(method,id,amount,action) {
            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "POST",
                data: {method:method, id : id, amount: amount, action :action},
                success: function(data) {
                    console.log(data);
                    $("#info-table-body").append(data);

                }
            });
        }
        function exportProductAuto(method,id,amount,action,reason) {
            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "POST",
                data: {method:method, id : id, amount: amount, action :action,reason: reason},
                success: function(data) {
                    $("#info-table-body").append(data);

                }
            });
        }

        function previewExcel() {
            const excelFile = document.querySelector("#excel_file");
            if(!['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.ms-excel'].includes(event.target.files[0].type))
            {
                document.getElementById('excel_data').innerHTML = '<div class="alert alert-danger">Only .xlsx or .xls file format are allowed</div>';

                excelFile.value = '';

                return false;
            }

            var reader = new FileReader();

            reader.readAsArrayBuffer(event.target.files[0]);

            reader.onload = function(event){

                var data = new Uint8Array(reader.result);

                var work_book = XLSX.read(data, {type:'array'});

                var sheet_name = work_book.SheetNames;

                var sheet_data = XLSX.utils.sheet_to_json(work_book.Sheets[sheet_name[0]], {header:1});
                var rowLenght = sheet_data[0].length;
                if(sheet_data.length > 0)
                {
                    var table_output = '<table class="table table-striped table-bordered">';

                    for(var row = 0; row < sheet_data.length; row++)
                    {

                        table_output += '<tr>';

                        for(var cell = 0; cell < rowLenght; cell++)
                        {

                            if(row == 0)
                            {

                                table_output += '<th>'+sheet_data[row][cell]+'</th>';

                            }
                            else
                            {
                                if(sheet_data[row][cell]) {
                                    table_output += '<td>'+sheet_data[row][cell]+'</td>';
                                } else {
                                    table_output += '<td>'+"."+'</td>';

                                }

                            }

                        }

                        table_output += '</tr>';

                    }

                    table_output += '</table>';

                    document.getElementById('excel_data').innerHTML = table_output;
                }
                document.querySelector("#auto-action").style.display="block";
                document.querySelector(".total-rows").innerText = document.querySelector("#excel_data table").querySelectorAll("tr").length -1;
                excelFile.value = '';

                document.querySelector("#stop-auto-update-btn").disabled = true;
                document.querySelector("#stop-auto-update-btn").style.display = "block";
                document.querySelector("#pre-auto-update-btn").style.display = "none";
                document.querySelector("#start-auto-update-btn").disabled = false;
                document.querySelector(".visited-rows").innerText = 0;

            }

        }

    </script>

    <script>
        function importProduct() {
            console.log($('#import-product-form'));
            var form = document.querySelector("#import-product-form");
            var formData = new FormData(form);
            var id = formData.get("id");
            var amount = formData.get("amount");
            var action = formData.get("action");

            var isOk = true;

            if(id === "") {
                document.querySelector('.idinput').hidden = false;
                document.querySelector('.idinput').innerText = "Không được bỏ trống mục này";

                isOk = false;
            } else if (isNaN(id)) {
                console.log(isNaN(id));
                document.querySelector('.idinput').innerText = "Mục này phải là số";
                document.querySelector('.idinput').hidden = false;
                isOk = false;
            } else {
                document.querySelector('.idinput').hidden = true;
            }

            if(amount === "") {
                document.querySelector('.amountinput').hidden = false;
                document.querySelector('.amountinput').innerText = "Không được bỏ trống mục này";
                isOk = false;
            } else if (isNaN(amount)) {
                console.log(isNaN(id));
                document.querySelector('.amountinput').innerText = "Mục này phải là số";
                document.querySelector('.amountinput').hidden = false;
                isOk = false;
            } else {
                document.querySelector('.amountinput').hidden = true;
            }

            if(isOk===false) return;


            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "POST",
                data: {id : id, amount: amount, action :action,method: "hand"},
                success: function(data) {
                    $("#info-table-body").append(data);
                }
            });
        }

        function exportProduct() {
            console.log($('#export-product-form'));
            var form = document.querySelector("#export-product-form");
            var formData = new FormData(form);
            var id = formData.get("id");
            var amount = formData.get("amount");
            var action = formData.get("action");
            var reason = formData.get("reason");
            console.log(id);
            console.log(amount);
            console.log(reason);
            var isOk = true;

            if(id === "") {
                document.querySelector('.idinput').hidden = false;
                document.querySelector('.idinput').innerText = "Không được bỏ trống mục này";

                isOk = false;
            } else if (isNaN(id)) {
                console.log(isNaN(id));
                document.querySelector('.idinput').innerText = "Mục này phải là số";
                document.querySelector('.idinput').hidden = false;
                isOk = false;
            } else {
                console.log(isNaN(id));
                document.querySelector('.idinput').hidden = true;

            }

            if(amount === "") {
                document.querySelector('.amountinput').hidden = false;
                document.querySelector('.amountinput').innerText = "Không được bỏ trống mục này";

                isOk = false;
            } else if (isNaN(amount)) {
                console.log(isNaN(id));
                document.querySelector('.amountinput').innerText = "Mục này phải là số";
                document.querySelector('.amountinput').hidden = false;
                isOk = false;
            } else {
                document.querySelector('.amountinput').hidden = true;
            }

            if(isOk===false) return;

            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "POST",
                data: {id : id, amount: amount, action :action, reason: reason, method: "hand"},
                success: function(data) {
                    $("#info-table-body").append(data);
                }
            });
        }

        $(document).ready(function() {

            // showSuccessToast("thanh cong tai");
            document.querySelector("#import-product-btn").addEventListener('click',function (event) {

                event.preventDefault();
                $("#product-action").html(`<div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                <div style="width: 100%">
                    <div class="show-flex-row" style="justify-content: end">
                        <a href=""><i class="bi bi-x-lg exit-btn"></i></a>
                    </div>
                    <form action="" style="width: 70%; margin: 0 auto" id="import-product-form">
                        <h4 style="text-align: center">Nhập kho</h4>
                        <div class="show-flex-row">
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="id">ID: </label>
                                <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Nhập ID" value="">
                                <div class="error idinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="amount">Số lượng: </label>
                                <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Nhập số lượng" value="">
                                <div class="error amountinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <input type="text" class="form-control w-80" id="action" name="action" aria-describedby="" placeholder="Enter name" value="import" hidden readonly>

                        </div>
                        <div class="show-flex-row" style="justify-content: end">
                            <button class="btn btn-primary" type="button" onclick="importProduct()" style="margin: 10px 10px;">Nhập</button>
                        </div>


                    </form>
            <div>
                            <p style="margin-bottom: 0; margin-top: 20px;">Nhập file excel</p>
                            <%--                        <input type="file" id="myfile" name="myfile" accept=".jpg, .png" onchange="preview()" multiple >--%>
                            <input type="file" id="excel_file" accept=".xlxs, .xls" onchange="previewExcel()">
                        </div>
                        <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
                            <div id="toast-2" style="margin: auto 0">
                                <!--                                <div class="toastt-2 toast-2&#45;&#45;success">-->
                                <!--                                    <p class="toast__msg" style="color: white">thanh cong</p>-->
                                <!--                                </div>-->
                            </div>
                        </div>
                        <div style="width: 100%;display: none;" id="auto-action">
                            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                            <div style="width: 100%">
                                <h4 style="text-align: center">Nhập tự động</h4>
                                <div id="excel_data" class="mt-5 show-flex-row" style="margin: 0 auto; width: fit-content">
                                    <table class="table table-striped table-bordered">
                                        <%--                        <tbody><tr><th>id</th><th>action</th></tr><tr><td>17</td><td>delivery</td></tr><tr><td>27</td><td>cancel</td></tr><tr><td>299</td><td>package</td></tr></tbody>--%>
                                        <tbody>
                                        <tr>
                                            <th>ID</th>
                                            <th>quantity</th>
                                        </tr>
                                        <tr>
                                            <td>17</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>24</td>
                                            <td>70</td>
                                        </tr>
                                        <tr>
                                            <td>27</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>19</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>18</td>
                                            <td>90</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="show-flex-row" style="justify-content: space-around;">
                                    <button class="btn btn-primary" id="start-auto-update-btn" type="button" onclick="autoUpdate(1,'import')" style="margin: 10px 10px;">Bắt đầu</button>
                                    <p>Tiến trình <span class="visited-rows">0</span>/<span class="total-rows">0</span> hàng</p>
                                    <button class="btn btn-primary" id="stop-auto-update-btn" type="button" onclick="stopAutoUpdate(this)" style="margin: 10px 10px;background-color: white; color: #0d6efd" disabled>Dừng</button>
                                    <button class="btn btn-primary" id="pre-auto-update-btn" type="button" onclick="preAutoUpdate(this,'import')" style="margin: 10px 10px;background-color: white; color: #0d6efd; display: none">Tiếp tục</button>
                                </div>

                            </div>
                            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>

                        </div>

                </div>`
                );
                $("#info-table").html(`

        <thead>
            <tr>
                <th scope="col" style="width: 10%;">ID</th>
                <th scope="col" style="width: 60%;">Tên</th>
                <th scope="col" style="width: 10%;">Số lượng</th>
                <th scope="col" style="width: 20%">Thời gian</th>
            </tr>
        </thead>

        <tbody id="info-table-body">
<!--            <tr class="roww">-->
<!--                <th scope="row">12</th>-->
<!--                <td>may loc </td>-->
<!--                <td>100</td>-->
<!--                <td>8213</td>-->
<!--            </tr>-->


        </tbody>`);
            });

            document.querySelector("#export-product-btn").addEventListener('click',function (event) {

                event.preventDefault();
                $("#product-action").html(`<div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                <div style="width: 100%">
                    <div class="show-flex-row" style="justify-content: end">
                        <a href=""><i class="bi bi-x-lg exit-btn"></i></a>
                    </div>
                    <form action="" style="width: 70%; margin: 0 auto" id="export-product-form">
                        <h4 style="text-align: center">Xuất kho</h4>
                        <div class="show-flex-row">
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="id">ID: </label>
                                <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Nhập ID" value="">
                                <div class="error idinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="amount">Số lượng: </label>
                                <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Nhập số lượng" value="">
                                <div class="error amountinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <input type="text" class="form-control w-80" id="action" name="action" aria-describedby="" placeholder="Enter name" value="export" hidden readonly>

                        </div>
                        <div class="form-group w-30" style="margin: 10px 10px">
                            <label class="w-20" for="reason">Nguyên nhân: </label>

                            <textarea  type="text" class="form-control" name="reason" id="reason" rows="6"></textarea>

                        </div>
                        <div class="show-flex-row" style="justify-content: end">
                            <button class="btn btn-primary" type="button" onclick="exportProduct()" style="margin: 10px 10px;">Xuất</button>
                        </div>


                    </form>
   <div>
                            <p style="margin-bottom: 0; margin-top: 20px;">Nhập file excel</p>
                            <%--                        <input type="file" id="myfile" name="myfile" accept=".jpg, .png" onchange="preview()" multiple >--%>
                            <input type="file" id="excel_file" accept=".xlxs, .xls" onchange="previewExcel()">
                        </div>
                        <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
                            <div id="toast-2" style="margin: auto 0">
                                <!--                                <div class="toastt-2 toast-2&#45;&#45;success">-->
                                <!--                                    <p class="toast__msg" style="color: white">thanh cong</p>-->
                                <!--                                </div>-->
                            </div>
                        </div>
                        <div style="width: 100%;display: none;" id="auto-action">
                            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                            <div style="width: 100%">
                                <h4 style="text-align: center">Xuất tự động</h4>
                                <div id="excel_data" class="mt-5 show-flex-row" style="margin: 0 auto; width: fit-content">
                                    <table class="table table-striped table-bordered">
                                        <%--                        <tbody><tr><th>id</th><th>action</th></tr><tr><td>17</td><td>delivery</td></tr><tr><td>27</td><td>cancel</td></tr><tr><td>299</td><td>package</td></tr></tbody>--%>
                                        <tbody>
                                        <tr>
                                            <th>ID</th>
                                            <th>quantity</th>
                                        </tr>
                                        <tr>
                                            <td>17</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>24</td>
                                            <td>70</td>
                                        </tr>
                                        <tr>
                                            <td>27</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>19</td>
                                            <td>90</td>
                                        </tr>
                                        <tr>
                                            <td>18</td>
                                            <td>90</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="show-flex-row" style="justify-content: space-around;">
                                    <button class="btn btn-primary" id="start-auto-update-btn" type="button" onclick="autoUpdate(1,'export')" style="margin: 10px 10px;">Bắt đầu</button>
                                    <p>Tiến trình <span class="visited-rows">0</span>/<span class="total-rows">0</span> hàng</p>
                                    <button class="btn btn-primary" id="stop-auto-update-btn" type="button" onclick="stopAutoUpdate(this)" style="margin: 10px 10px;background-color: white; color: #0d6efd" disabled>Dừng</button>
                                    <button class="btn btn-primary" id="pre-auto-update-btn" type="button" onclick="preAutoUpdate(this,'export')" style="margin: 10px 10px;background-color: white; color: #0d6efd; display: none">Tiếp tục</button>
                                </div>

                            </div>
                            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>

                        </div>

                </div>`
                );
                $("#info-table").html(`

        <thead>
            <tr>
                <th scope="col" style="width: 10%;">ID</th>
                <th scope="col" style="width: 60%;">Tên</th>
                <th scope="col" style="width: 10%;">So luong</th>
                <th scope="col" style="width: 20%">Thoi gian</th>
            </tr>
        </thead>

        <tbody id="info-table-body">
<!--            <tr class="roww">-->
<!--                <th scope="row">12</th>-->
<!--                <td>may loc </td>-->
<!--                <td>100</td>-->
<!--                <td>8213</td>-->
<!--            </tr>-->


        </tbody>`);
            });

            // function submitImport() {
            //     var formData =
            // }



        });

        document.querySelector("#searchForm").addEventListener('submit',function (event){
            event.preventDefault();
            var data = new FormData(this);
            var input = data.get("input");
            var action ="search";
            searchProduct(action, input);

        });

        function searchProduct(action, input) {
            $.ajax({
                url: "/LTWebFS/admin-product",
                method: "GET",
                data: {action: action, input: input},
                success: function(data) {
                    console.log(data);
                    $("#info-table-body").html(data);

                }
            });
        }
    </script>





</main><!-- End #main -->



<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<script>



</script>

<!-- Template Main JS File -->
<!--<script src="assets/js/main.js"></script>-->
<%--<script src="assets/js/adPro.js"></script>--%>
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
