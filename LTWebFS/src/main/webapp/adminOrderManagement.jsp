<%@ page import="model.Order" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 12/12/2023
  Time: 6:58 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Quản lý đơn hàng - Tất cả</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons-->
    <link href="assets/img/Logo/favicon_icon.png" rel="icon">
    <!--        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

    <!-- CSS file -->
<%--    <link rel="stylesheet" href="assets/css/qldh.css">--%>
    <link rel="stylesheet" href="assets/css/orderManagement.css">
    <link rel="stylesheet" href="assets/css/toast.css">

    <script src="assets/js/toast.js"></script>

<%--    doc file excel--%>
    <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>

<%--    in file excel--%>
    <script src="bower_components\jquery-table2excel\dist\jquery.table2excel.min.js"></script>
    <script src="assets/js/jquery.table2excel.js"></script>
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
    Map<Order,String> orders = (Map<Order, String>) request.getAttribute("orders");
    if(orders == null) orders = new HashMap<>();
%>

<%@ include file="adminOrderMenu.jsp" %>


<main id="main" class="main" style="margin-left: 180px">
    <div class="ad-content" style="flex-direction: column">
        <div id="toast">

        </div>
        <h2 style="text-align: center">Tất cả đơn hàng</h2>
        <div class="ad_header" style="width: 100%;">
            <div class="show-flex-row" style="width: 100%; align-items: start; justify-content: space-between;">
                <form class="ad_find-container" action="update-order" method="post" id="searchOrderForm">
                    <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="input" value="">
                    <input type="text" placeholder="Nhập ID hoặc thời gian" class="ad_find-input" name="action" value="search" hidden readonly>
                    <button class="ad_find-btn" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <div class="show-flex-col">
                    <div>
                        <form class="ad_find-container" action="confirm-order" method="post" style="width: 200px;">
                            <input type="text" placeholder="Nhập ID đơn hàng" class="ad_find-input" name="search" value="">
                        </form>
                    </div>
                    <div>
                        <p style="margin-bottom: 0; margin-top: 20px;">Nhập file excel</p>
<%--                        <input type="file" id="myfile" name="myfile" accept=".jpg, .png" onchange="preview()" multiple >--%>
                        <input type="file" id="excel_file" accept=".xlxs, .xls" onchange="previewExcel()">
                    </div>
                </div>


            </div>

        </div>
        <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
            <div id="toast-2" style="margin: auto 0">
<%--                <div class="toastt-2 toast-2--success">--%>
<%--                    <p class="toast__msg" style="color: white">thanh cong</p>--%>
<%--                </div>--%>
            </div>
        </div>
        <div style="width: 100%;display: none;" id="auto-action">
            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>
            <div style="width: 100%">
                <h4 style="text-align: center">Cập nhật tự động</h4>
                <div id="excel_data" class="mt-5 show-flex-row" style="margin: 0 auto; width: fit-content">
                    <table class="table table-striped table-bordered">
<%--                        <tbody><tr><th>id</th><th>action</th></tr><tr><td>17</td><td>delivery</td></tr><tr><td>27</td><td>cancel</td></tr><tr><td>299</td><td>package</td></tr></tbody>--%>
                        <tbody>
                        <tr>
                            <th>ID</th>
                            <th>action</th>
                        </tr>
                        <tr>
                            <td>17</td>
                            <td>confirm</td>
                        </tr>
                        <tr>
                            <td>24</td>
                            <td>cancel</td>
                        </tr>
                        <tr>
                            <td>27</td>
                            <td>more</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>cancel</td>
                        </tr>
                        <tr>
                            <td>19</td>
                            <td>more</td>
                        </tr>
                        <tr>
                            <td>18</td>
                            <td>delivery</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="show-flex-row" style="justify-content: space-around;">
                    <button class="btn btn-primary" id="start-auto-update-btn" type="button" onclick="autoUpdate(1)" style="margin: 10px 10px;">Bắt đầu</button>
                    <p>Tiến trình <span class="visited-rows">0</span>/<span class="total-rows">0</span> hàng</p>
                    <button class="btn btn-primary" id="stop-auto-update-btn" type="button" onclick="stopAutoUpdate(this)" style="margin: 10px 10px;background-color: white; color: #0d6efd" disabled>Dừng</button>
                    <button class="btn btn-primary" id="pre-auto-update-btn" type="button" onclick="preAutoUpdate(this)" style="margin: 10px 10px;background-color: white; color: #0d6efd; display: none">Tiếp tục</button>
                    <button class="btn btn-primary" type="button" onclick="exportCSVExcel('#excel_data table','ds_capnhat_donhang_auto')" style="margin: 10px 10px;background-color: white; color: #0d6efd;">In</button>

                </div>

            </div>
            <div class="seperate-horizontal-90" style="margin: 20px auto"></div>

        </div>

        <script>
            let runAuto = false;
            let updateIndex = 1;

            function exportCSVExcel(table,filename='') {
                var downloadLink;
                var dataType = 'application/vnd.ms-excel';
                var tableSelect = document.querySelector(table);
                var tableHTML = tableSelect.outerHTML.replace(/ /g,'%20');

                filename = filename?filename+'xls':'thietbiyte_data.xls';
                downloadLink = document.createElement("a");
                document.body.appendChild((downloadLink));

                if(navigator.msSaveOrOpenBlob) {
                    var blob= new Blob(['\ufeff', tableHTML], {
                        type: dataType
                    });
                    navigator.msSaveOrOpenBlob(blob, filename);
                } else {
                    downloadLink.href = 'data:' + dataType +',' + tableHTML;
                    downloadLink.click();
                }
            }

            function stopAutoUpdate(event) {
                runAuto = false;
                event.style.display = "none";
                document.querySelector("#pre-auto-update-btn").style.display = "block";

            }

            function preAutoUpdate(event) {
                autoUpdate(updateIndex);
                event.style.display = "none";
                document.querySelector("#stop-auto-update-btn").style.display = "block";
            }
            function autoUpdate(startIndex) {
                runAuto = true;
                console.log(document.querySelector("#stop-auto-update-btn"));
                document.querySelector("#stop-auto-update-btn").disabled = false;
                document.querySelector("#start-auto-update-btn").disabled = true;

                let visitedRows = parseInt(document.querySelector(".visited-rows").innerText,10);

                if(startIndex==1) {
                    showSuccessToast("BẮT ĐẦU cập nhật đơn hàng tự động");
                    document.querySelector(".visited-rows").innerText = 0;
                } else {
                    showSuccessToast("TIẾP TỤC cập nhật đơn hàng tự động");
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
                function executeUpdateAuto(index) {

                    if(runAuto==false) {
                        showSuccessToast("TẠM DỪNG quá trình cập nhật tự động.");
                        updateIndex = index;
                        // visitedRows--;
                        return;
                    }
                    if(index >= excelData.rows.length ) {
                        showSuccessToast("KẾT THÚC quá trình cập nhật tự động.");
                        updateIndex = 1;
                        document.querySelector("#stop-auto-update-btn").disabled = true;

                        return;
                    }
                    let id = excelData.rows[index].cells[keyCol].innerText;
                    let action = excelData.rows[index].cells[dataCol].innerText;

                    updateOrderAuto(action, id,"auto");
                    setTimeout(function() {
                        if(excelData.querySelectorAll("tr").length<numOfRows) {
                            numOfRows = excelData.querySelectorAll("tr").length;
                            index= index-1;
                        }
                        executeUpdateAuto(index + 1);
                    }, 1500);
                    visitedRows++;
                    document.querySelector(".visited-rows").innerText=visitedRows;
                    console.log("hang tiep theo: " + visitedRows);


                }
                executeUpdateAuto(startIndex);
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

            function updateOrderAuto(action,id,method) {
                $.ajax({
                    url: "/LTWebFS/update-order",
                    method: "POST",
                    data: {action: action, id: id, method: method},
                    success: function(data) {
                        $(".order-info-container").html(data);

                    }
                });
            }

            function previewExcel() {
                const excelFile = document.querySelector("#excel_file");
                console.log("preview");
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

                    if(sheet_data.length > 0)
                    {
                        var table_output = '<table class="table table-striped table-bordered">';

                        for(var row = 0; row < sheet_data.length; row++)
                        {

                            table_output += '<tr>';

                            for(var cell = 0; cell < sheet_data[row].length; cell++)
                            {

                                if(row == 0)
                                {

                                    table_output += '<th>'+sheet_data[row][cell]+'</th>';

                                }
                                else
                                {

                                    table_output += '<td>'+sheet_data[row][cell]+'</td>';

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

<%--        <script> showSuccessToast2("hi");</script>--%>

        <table class="table">
            <thead>
            <tr>
                <th scope="col" class="w-5" style="width: 5%;"> ID</th>
                <th scope="col" style="width: 10%;">Thời gian</th>
                <th scope="col" style="width: 45%;">Danh sách sản phẩm</th>
                <th scope="col" style="width: 15%;">Tổng tiền</th>
                <th scope="col" style="width: 15%;">Tình trạng</th>
            </tr>
            </thead>
            <tbody class="order-info-container">
            <%=(orders.isEmpty()?"<p>Không có đơn hàng nào</p>":"")
            %>
            <%
                int i=0;
                for (Map.Entry<Order, String> item : orders.entrySet()) {
                    i++;
//                    System.out.println(item.getKey() + " - " + item.getValue());

            %>
            <tr class="<%= (i%2==0) ? "roww": ""%>">
                <td><%=item.getKey().getId()%></td>
                <td><%=item.getKey().getDateSet()%> <br/> <%=item.getKey().getTimeSet()%></td>
                <td><%=item.getValue()%></td>
                <td><%=item.getKey().getTotalPrice()%></td>
                <td class="<%=item.getKey().getColorByStatus()%>"><%=item.getKey().getStatusBefore()%></td>
            </tr>
            <%
                }
            %>

            </tbody>
        </table>

    </div>
</main>

<script>
    function searchOrder(action, input) {
        console.log('goi update order');
        $.ajax({
            url: "/LTWebFS/update-order",
            method: "GET",
            data: {action: action, input: input},
            success: function(data) {
                $("#ordersTable").html(data);

            }
        });
    }



    $(document).ready(function() {
        document.querySelector("#searchOrderForm").addEventListener('submit',function (event) {
            event.preventDefault();
            var formData = new FormData(this);
            var action = formData.get("action");
            var  input = formData.get("input");
            if(input === "") {
                input = -1;
            }

            searchOrder(action, input);
        })

    });
</script>



<!-- Vendor JS Files -->
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
