<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="model.*" %>
<%@ page import="database.CustomerDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Thống kê</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

    <!--    <link href="assets/img/favicon.png" rel="icon">-->
    <!--    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>

<!-- ======= Header ======= -->
<!-- ======= Sidebar ======= -->
<%@ include file="adminMenu.jsp" %>

<%
    int sales = (int) request.getAttribute("sales");
    double salesGap = (double) request.getAttribute("salesGap");
    int customer = (int) request.getAttribute("customer");
    double customerGap = (double) request.getAttribute("customerGap");

    int revenue = (int) request.getAttribute("revenue");
    double revenueGap = (double) request.getAttribute("revenueGap");

    String reportData = (String) request.getAttribute("reportData");

    Map<ProductUnit,Integer> topPro = (Map<ProductUnit, Integer>) request.getAttribute("topPro");
    if(topPro==null) topPro=new LinkedHashMap<>();

    Map<User,Order> recentCustomer = (Map<User, Order>) request.getAttribute("recentCustomer");
    if(recentCustomer==null) recentCustomer=new LinkedHashMap<>();

    ArrayList<User> unbackCustomer = (ArrayList<User>) request.getAttribute("unbackCustomer");
    if(unbackCustomer==null) unbackCustomer = new ArrayList<>();

%>


<main id="main" class="main">

    <div class="pagetitle">
        <h1>Thống kê</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active">Thống kê</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
        <div class="row">

            <!-- Left side columns -->
            <div class="col-lg-8">
                <div class="row">

                    <!-- Doanh số Card -->
                    <div class="col-xxl-4 col-md-5">
                        <div class="card info-card sales-card">

                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Tùy chọn</h6>
                                    </li>

                                    <li><a class="dropdown-item selectionSales" href="dashboard?action=sales&&timee=1">Hôm nay</a></li>
                                    <li><a class="dropdown-item selectionSales" href="dashboard?action=sales&&timee=2">Tháng này</a></li>
                                    <li><a class="dropdown-item selectionSales" href="dashboard?action=sales&&timee=3">Năm này</a></li>
                                    <li><a class="dropdown-item selectionSales" href="dashboard?action=sales&&timee=4">Tất cả</a></li>
                                </ul>
                            </div>

                            <div class="card-body">
                                <%
                                    String salesStatus ="";
                                    if(salesGap > 100) {
                                        salesStatus = "Tăng";
                                        salesGap = salesGap -100;
                                    }
                                    else if(salesGap <100) {
                                        salesStatus = "Giảm";
                                        salesGap = 100 - salesGap;
                                    } else {
                                        salesStatus = "Duy trì";
                                        salesGap=0;
                                    }
                                %>
                                <h5 class="card-title">Doanh số <span class="SalesContent">| Hôm nay</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-cart"></i>
                                    </div>
                                    <div class="ps-3" id="Sales">
                                        <h6> <%=sales%></h6>
                                        <span class="text-success small pt-1 fw-bold"><%=salesGap%>%</span>
                                        <span
                                            class="text-muted small pt-2 ps-1"><%=salesStatus%></span>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div><!-- End Doanh số Card -->

                    <!-- Customers Card -->
                    <div class="col-xxl-4 col-md-7">

                        <div class="card info-card customers-card">

                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Tùy chọn</h6>
                                    </li>

                                    <li><a class="dropdown-item selectionCustomer" href="dashboard?action=customer&&timee=1">Hôm nay</a></li>
                                    <li><a class="dropdown-item selectionCustomer" href="dashboard?action=customer&&timee=2">Tháng này</a></li>
                                    <li><a class="dropdown-item selectionCustomer" href="dashboard?action=customer&&timee=3">Năm này</a></li>
                                    <li><a class="dropdown-item selectionCustomer" href="dashboard?action=customer&&timee=4">Tất cả</a></li>
                                </ul>
                            </div>

                            <div class="card-body">
                                <%
                                    String customerSatus ="";
                                    if(customerGap > 100) {
                                        customerSatus = "Tăng";
                                        customerGap = customerGap -100;
                                    }
                                    else if(customerGap <100) {
                                        customerSatus = "Giảm";
                                        customerGap = 100 - customerGap;
                                    } else {
                                        customerSatus = "Duy trì";
                                        customerGap=0;
                                    }
                                %>
                                <h5 class="card-title">Khách hàng mới <span class="CustomerContent">| Năm này</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-people"></i>
                                    </div>
                                    <div class="ps-3" id="Customer">
                                        <h6><%=customer%></h6>
                                        <span class="text-danger small pt-1 fw-bold"><%=customerGap%>%</span> <span
                                            class="text-muted small pt-2 ps-1"><%=customerSatus%></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div><!-- End Customers Card -->

                    <!-- Doanh thu Card -->
                    <%
                        String reveneuStatus ="";
                        if(revenueGap > 100) {
                            reveneuStatus = "Tăng";
                            revenueGap = revenueGap -100;
                        }
                        else if(revenueGap <100) {
                            reveneuStatus = "Giảm";
                            revenueGap = 100 - revenueGap;
                        } else {
                            reveneuStatus = "Duy Trì";
                            revenueGap=0;
                        }
                    %>
                    <div class="col-xxl-4 col-xl-12">
                        <div class="card info-card revenue-card">

                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Tùy chọn</h6>
                                    </li>

                                    <li><a class="dropdown-item selectionRevenue" href="dashboard?action=revenue&&timee=1">Hôm nay</a></li>
                                    <li><a class="dropdown-item selectionRevenue" href="dashboard?action=revenue&&timee=2">Tháng này</a></li>
                                    <li><a class="dropdown-item selectionRevenue" href="dashboard?action=revenue&&timee=3">Năm này</a></li>
                                    <li><a class="dropdown-item selectionRevenue" href="dashboard?action=revenue&&timee=4">Tất cả</a></li>
                                </ul>
                            </div>

                            <div class="card-body">
                                <h5 class="card-title">Doanh thu <span class="RevenueContent">| Tháng này</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-currency-dollar"></i>
                                    </div>
                                    <div class="ps-3" id="Revenue">
                                        <h6><%=Order.formatPriceOf(revenue)%> (VND)</h6>
                                        <span class="text-success small pt-1 fw-bold"><%=revenueGap%>%</span> <span
                                            class="text-muted small pt-2 ps-1"><%=reveneuStatus%></span>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div><!-- End Doanh thu Card -->


                    <!-- Báo cáo -->
                    <div class="col-12">
                        <div class="card">
                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Tùy chọn</h6>
                                    </li>

                                    <li><a class="dropdown-item selectionReport" href="dashboard?action=report&&timee=1">Hôm nay</a></li>
                                    <li><a class="dropdown-item selectionReport" href="dashboard?action=report&&timee=2">Tháng này</a></li>
                                    <li><a class="dropdown-item selectionReport" href="dashboard?action=report&&timee=3">Năm này</a></li>
                                    <li><a class="dropdown-item selectionReport" href="dashboard?action=report&&timee=4">Tất cả</a></li>
                                </ul>
                            </div>

                            <div class="card-body">
                                <h5 class="card-title">Báo cáo <span class="reportContent">| Tất cả</span></h5>

                                <!-- Line Chart -->
                                <div id="reportsChart"></div>

                                <script>
                                    document.addEventListener("DOMContentLoaded", () => {
                                        console.log("truoc report")
                                        const jsonObject = JSON.parse(JSON.stringify(<%=reportData%>));
                                        console.log("sau report")

                                        let doanhSo = JSON.parse(jsonObject.doanhSo);
                                        let doanhThu = JSON.parse(jsonObject.doanhThu);
                                        let khachHang = JSON.parse(jsonObject.khachHang);
                                        let categories = JSON.parse(jsonObject.categories);
                                        const reportChart = new ApexCharts(document.querySelector("#reportsChart"), {

                                            series: [{
                                                name: 'Doanh số  (triệu VND)',
                                                data: doanhSo
                                            }, {
                                                name: 'Doanh thu',
                                                data: doanhThu
                                            }, {
                                                name: 'Khách hàng',
                                                data: khachHang
                                            }],
                                            chart: {
                                                height: 350,
                                                type: 'area',
                                                toolbar: {
                                                    show: false
                                                },
                                            },
                                            markers: {
                                                size: 4
                                            },
                                            colors: ['#4154f1', '#2eca6a', '#ff771d'],
                                            fill: {
                                                type: "gradient",
                                                gradient: {
                                                    shadeIntensity: 1,
                                                    opacityFrom: 0.3,
                                                    opacityTo: 0.4,
                                                    stops: [0, 90, 100]
                                                }
                                            },
                                            dataLabels: {
                                                enabled: false
                                            },
                                            stroke: {
                                                curve: 'smooth',
                                                width: 2
                                            },
                                            xaxis: {
                                                // type: 'datetime',
                                                // categories: ["2018-09-19T00:00:00.000+07:00", "2018-09-19T01:30:00.000+07:00", "2018-09-19T02:30:00.000+07:00", "2018-09-19T03:30:00.000+07:00", "2018-09-19T04:30:00.000+07:00", "2018-09-19T05:30:00.000+07:00", "2018-09-19T06:30:00.000+07:00"]
                                                categories: categories
                                            },
                                            tooltip: {
                                                x: {
                                                    format: 'dd/MM/yy HH:mm'
                                                },
                                            }
                                        });

                                        reportChart.render();

                                        const reportSelections = document.querySelectorAll(".selectionReport");
                                        for (let i = 0; i < reportSelections.length; i++) {
                                            reportSelections[i].addEventListener('click', function (event) {
                                                event.preventDefault();
                                                var urlin = event.target.href;
                                                document.querySelector(".reportContent").innerText = "| " + reportSelections[i].innerText;
                                                updateReportChart(urlin);

                                            });
                                        }
                                        function updateReportChart(urlin) {
                                            $.ajax({
                                                url: urlin,
                                                type: 'GET',
                                                success: function(data) {

                                                    console.log(data)
                                                    const jsonObject = JSON.parse(data);

                                                    let doanhSo = JSON.parse(jsonObject.doanhSo);
                                                    let doanhThu = JSON.parse(jsonObject.doanhThu);
                                                    let khachHang = JSON.parse(jsonObject.khachHang);
                                                    let categories = JSON.parse(jsonObject.categories);

                                                    reportChart.updateSeries([{
                                                        name: 'Doanh số (triệu VND)',
                                                        data: doanhSo
                                                    }, {
                                                        name: 'Doanh thu',
                                                        data: doanhThu
                                                    }, {
                                                        name: 'Khách hàng',
                                                        data: khachHang
                                                    }]);

                                                    reportChart.updateOptions({
                                                        xaxis: {
                                                            categories: categories
                                                        },
                                                        tooltip: {
                                                            x: {
                                                                format: 'dd/MM/yy HH:mm'
                                                            },
                                                        }
                                                    });
                                                },
                                                error: function() {
                                                    alert('Failed to load data');
                                                }
                                            });
                                        }

                                    });
                                </script>

<%--                                <script>--%>
<%--                                    t= new ApexCharts(document.querySelector("#reportsChart"), {--%>
<%--                                        null--%>
<%--                                    }).render();--%>
<%--                                    console.log(t)--%>
<%--                                </script>--%>
                                <!-- End Line Chart -->

                            </div>
                        </div>
                    </div><!-- End Báo cáo -->

                    <!-- Top Selling -->
                    <div class="col-12">
                        <div class="card top-selling overflow-auto">

                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Tùy chọn</h6>
                                    </li>

                                    <li><a class="dropdown-item topPro" href="dashboard?action=toppro&&timee=1">Hôm nay</a></li>
                                    <li><a class="dropdown-item topPro" href="dashboard?action=toppro&&timee=2">Tháng này</a></li>
                                    <li><a class="dropdown-item topPro" href="dashboard?action=toppro&&timee=3">Năm này</a></li>
                                    <li><a class="dropdown-item topPro" href="dashboard?action=toppro&&timee=4">Tất cả</a></li>
                                </ul>
                            </div>

                            <div class="card-body pb-0">
                                <h5 class="card-title">Sản phẩm bán chạy nhất <span class="topProContent">| Tháng này</span></h5>
                                <table class="table table-borderless">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Sản phẩm</th>
                                        <th scope="col">Doanh số</th>
                                    </tr>
                                    </thead>
                                    <tbody id="topPro">
                                    <%
                                        for (Map.Entry<ProductUnit, Integer> item : topPro.entrySet()) {
                                    %>
                                            <tr>
                                                <%String imgurl  = item.getKey().getImg();%>
                                                <th scope="row"><img src=<%=imgurl%> alt=""></th>
                                                <td><a href="#" class="text-primary fw-bold"> <%=item.getKey().getName()%> </a></td>
                                                <td class="fw-bold"><%=item.getValue()%></td>
                                            </tr>
                                    <%

                                        }
                                    %>
                                    </tbody>
                                </table>

                            </div>

                        </div>
                    </div><!-- End Top Selling -->

                </div>
            </div><!-- End Left side columns -->

            <!-- Right side columns -->
            <div class="col-lg-4">

                <!-- Recent Activity -->
                <div class="card">



                    <div class="card-body">
                        <h5 class="card-title">Khách hàng gần đây</h5>

                        <div class="activity">
                            <%
                                for (Map.Entry<User, Order> entry : recentCustomer.entrySet()) {
                            %>
                            1
                                <div class="activity-item d-flex">
                                    <div class="activity-content">

                                        <%=entry.getKey().getId() + " | " + entry.getKey().getName()%><br/>
                                        <a href="#" class="fw-bold text-dark">Mã đơn hàng: <%=entry.getValue().getId()%></a> <br/>
                                        <%=entry.getValue().getDateSet() + " | " + entry.getValue().getTimeSet()%>
                                    </div>
                                </div>
                            <!-- End activity item-->

                            <%
                                }
                            %>

                        </div>

                    </div>
                </div><!-- End Recent Activity -->

<%--                noback customer--%>
                <div class="card">
                    <div class="filter">

                        <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                            <li class="dropdown-header text-start">
                                <h6>Tùy chọn</h6>
                            </li>

                            <li><a class="dropdown-item selectionUnback" href="dashboard?action=unback&&timee=1&&index=0">1 tháng</a></li>
                            <li><a class="dropdown-item selectionUnback" href="dashboard?action=unback&&timee=2&&index=0">2 tháng</a></li>
                            <li><a class="dropdown-item selectionUnback" href="dashboard?action=unback&&timee=3&&index=0">Từ 3 tháng</a></li>
                            <li><a class="dropdown-item selectionUnback" href="dashboard?action=unback&&timee=-1&&index=0">Chưa bao giờ</a></li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Khách hàng chưa quay lại <br/> <span class="unbackContent">| Từ 3 tháng</span></h5>


                        <div class="activity" id="Unback">
                            <%
                                for (User u : unbackCustomer) {
                            %>
                            <div class="activity-item d-flex unback-item">
                                <div class="activity-content">
                                    <%=u.getId() + " | " + u.getName()%><br/>
                                    <%=u.getEmail() + " | " + u.getPhone()%><br/>
                                    Ngay mua gan nhat: <%=u.getOrderDate()==null?"chua bao gio":u.getOrderDate()%>
                                </div>
                            </div><!-- End activity item-->

                            <%
                                }
                            %>



                        </div>
                        <div id="moreUnbackBtn"><a href="dashboard?action=unback&&timee=3&&index=10" id="moreUnback"><i class="bi bi-arrow-down-square" id="moreUnbackIcon"></i></a></div>

                    </div>
                </div><!-- End Recent Activity -->





            </div><!-- End Right side columns -->

        </div>
    </section>

</main><!-- End #main -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>

<script>

    $(document).ready(function() {

    //     top pro
        const topPro = document.querySelectorAll('.topPro');
        for (let i = 0; i < topPro.length; i++) {
            topPro[i].addEventListener('click', function (event) {
                event.preventDefault();
                var urlin = event.target.href;
                document.querySelector('.topProContent').innerText = "| " + topPro[i].innerText;
                lookupTopPro(urlin);

            });
        }

        function lookupTopPro(urlin) {
            $.ajax({
                url: urlin,
                method: "POST",
                // data: { aciton: action, timee: timee, top: top},
                success: function(data) {
                    $("#topPro").html(data);
                }
            });
        }
    });





    function handleInfo(kind) {
        const selections = document.querySelectorAll(".selection" + kind);
        for (let i = 0; i < selections.length; i++) {
            selections[i].addEventListener('click', function (event) {
                event.preventDefault();
                var urlin = event.target.href;
                document.querySelector("."+kind+"Content").innerText = "| " + selections[i].innerText;
                lookupInfo(urlin, kind);

            });
        }
    }

    function lookupInfo(urlin,container) {
        // console.log(urlin);
        $.ajax({
            url: urlin,
            method: "POST",
            // data: { aciton: action, timee: timee, top: top},
            success: function(data) {
                // console.log(data);
                $("#"+container).html(data);
            }
        });
    }

    handleInfo("Sales"); //sales
    handleInfo("Revenue"); //revenue
    handleInfo("Customer"); //customer

//     unback customer
    const unbackSelections = document.querySelectorAll(".selectionUnback");
    for (let i = 0; i < unbackSelections.length; i++) {
        unbackSelections[i].addEventListener('click', function (event) {
            event.preventDefault();
            var urlin = event.target.href;
            document.querySelector('.unbackContent').innerText = "| " + unbackSelections[i].innerText;

            // cap nhat content
            lookupInfo(urlin,"Unback");

            // cap nhat href cho nut more
            var lastIndex = urlin.lastIndexOf("=");
            var newUrl = urlin.slice(0,lastIndex) + "=10";
            const moreUnbackBtn = document.querySelector("#moreUnback");
            moreUnbackBtn.href = newUrl;
            console.log(moreUnbackBtn);


        });
    }

    function moreUnback(urlin) {
        $.ajax({
            url: urlin,
            method: "POST",
            success: function(data) {
                $("#Unback").append(data);
            },
            error: function() {
                document.querySelector("#moreUnback").hidden = true;
            }
        });
    }
    const moreUnbackIcon = document.querySelector("#moreUnbackIcon");
    moreUnbackIcon.addEventListener('click', function (event) {
        event.preventDefault();
        // document.querySelector("#moreUnback").click();
    });

    const moreUnbackBtn = document.querySelector("#moreUnback");
    moreUnbackBtn.addEventListener('click', function (event){
        event.preventDefault();
        console.log(event.target);
        var urlin = document.querySelector("#moreUnback").href;
        moreUnback(urlin);

        var lastIndex = urlin.lastIndexOf("=");
        var newIndex = urlin.slice(lastIndex+1);
        var newUrl = urlin.slice(0,lastIndex) + "=" + (parseInt(newIndex)+10);
        document.querySelector("#moreUnback").href = newUrl;
        console.log(document.querySelector("#moreUnback"));
    });

</script>

</body>
</html>
