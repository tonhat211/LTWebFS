<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Cập nhật thông tin</title>
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
<%--    <link href="assets/css/style.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="assets/css/addUpdate.css">
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/updateCustomerInfo.css">
    <link rel="stylesheet" href="assets/css/toast.css">
    <link rel="stylesheet" href="assets/css/payment.css">
    <link rel="stylesheet" href="assets/css/cusOrderManagement.css">
    <link rel="stylesheet" href="assets/css/progressStep.css">

    <script src="assets/js/toast.js"></script>
<script>
    function showDetailsOrder() {
        let id = this.parentNode.querySelector(".orderID").innerText;
        let status = this.parentNode.querySelector(".orderStatusNumber").innerText;
        let receiver = this.parentNode.querySelector(".orderReceiver").innerText;
        let receiverPhone = this.parentNode.querySelector(".orderReceiverPhone").innerText;
        let address = this.parentNode.querySelector(".orderAddress").innerText;

        let html = '   ' +
            '<div>' +
                '<div class="steps">' +
                    '<p class="step-item "><span class="circle "><i class="bi bi-card-checklist"></i></span>xac nhan</p>' +
                    '<p class="step-item "><span class="circle "><i class="bi bi-box-seam"></i></span>Dong goi</p>' +
                    '<p class="step-item "><span class="circle "><<i class="bi bi-truck"></i></span>Van chuyen</p>' +
                    '<p class="step-item "><span class="circle "><i class="bi bi-check-circle-fill"></i></span>Hoan thanh</p>' +
                    '<div class="progress-bar">' +
                        '<span class="indicator"></span>' +
                    '</div>' +
                '</div>' +
            '</div>' +
            '<div style="margin-top: 40px">' +
                '<p>Dia chi nhan hang</p>' +
                '<p>Nguoi nhan: <span class="receiver">'+ receiver +'</span>   |   <span class="phone">'+ receiverPhone +'</span></p>' +
                '<p>Dia chi: <span class="address">'+ address +'</span></p>' +
            '</div>';


        document.querySelector('.modal').classList.add("active");
        document.querySelector("#modal_content").innerHTML = html;

        const stepItems = document.querySelectorAll(".step-item");
        const progressBar = document.querySelector(".indicator");

        updateProgessbar(status);
        console.log(status);
        function updateProgessbar(num) {
            if(num==0) {
                console.log("0");
                progressBar.style.width = "0";
            } else if (num==1) {
                console.log("1");
                progressBar.style.width = "0";
            } else if (num==2) {
                console.log("2");
                progressBar.style.width = "33.3%";
            } else if (num==3) {
                console.log("3");
                progressBar.style.width = "66.6%";
            } else if (num==4) {
                console.log("4");
                progressBar.style.width = "99%";
            }
            for(let i=0;i<num;i++) {
                stepItems[i].classList.add("active");
            }
        }



    }
</script>
</head>
<body>
<%
    User u = (User) session.getAttribute("userloging");
    if(u==null) {
        u = new User();
    }

    Map<model.Order,ArrayList<Map.Entry<DeOrder, ProductUnit>>> os = (Map<Order, ArrayList<Map.Entry<DeOrder, ProductUnit>>>) request.getAttribute("orderList");
    if(os==null) {
        os = new LinkedHashMap<>();
    }
%>
<%@ include file="header.jsp" %>
<div class="ad-content" style="margin-top: 117px;">
    <div id="toast">
<%--        <script> showSuccessToast("hi")</script>--%>
    </div>
    <div class="modal confirm-stop" style="z-index: 99">
        <div class="modal__overlay">
            <div class="modal__confirm-content" id="modal_content" onclick="event.stopPropagation()" style="top: 80px">

            </div>
        </div>
    </div>
    <div class="ad-content-item" style="background-color: #dbe7ff;">

        <div class="ad_container" style="width: 80%">
            <ul class="order-menu show-flex-row" style="align-items: center">
                <li class="order-menu-item active"><a href="cus-order?action=all">Tất cả</a></li>
                <li class="order-menu-item"><a href="cus-order?action=waiting">Chờ xác nhận</a></li>
                <li class="order-menu-item"><a href="cus-order?action=confirm-packaged">Chuẩn bị</a></li>
                <li class="order-menu-item"><a href="cus-order?action=deliverying">Vận chuyển</a></li>
                <li class="order-menu-item"><a href="cus-order?action=completed">Hoàn thành</a></li>
                <li class="order-menu-item"><a href="cus-order?action=canceled">Hủy</a></li>
<%--                <li>Cho ban giao</li>--%>
<%--                <li>Cho dang van chuyen</li>--%>
<%--                <li>Cho hoan thanh</li>--%>
<%--                <li>Cho huy</li>--%>
            </ul>
            <div class="order-content" style="margin-bottom: 20px">
                <%
                    for (Map.Entry<Order, ArrayList<Map.Entry<DeOrder,ProductUnit>>> item : os.entrySet()) {
                        ArrayList<Map.Entry<DeOrder,ProductUnit>> ps = item.getValue();
                        String status = item.getKey().getStatusBefore();


                %>
                <div class="order-container">
                    <div class="show-flex-row">
                        <p>Ma don hang: <span class="orderID"><%=item.getKey().getId()%></span> </p>
                        <span class="orderStatusNumber" style="display: none"><%=item.getKey().getStatus()%></span>
                        <span class="orderReceiver" style="display: none"><%=item.getKey().getReceiver()%></span>
                        <span class="orderReceiverPhone" style="display: none"><%=item.getKey().getReceiverPhone()%></span>
                        <span class="orderAddress" style="display: none"><%=item.getKey().getAddress()%></span>
                        <p class="orderStatus <%=item.getKey().getColorByStatus()%>"><%=status%></p>
                    </div>
                <%
                    for(Map.Entry<DeOrder,ProductUnit> p : ps) {
                %>
                    <div class="order-item" onclick="showDetailsOrder.call(this)">
                        <div class="show-flex-row" style="justify-content: left; align-items: start">
                            <div class="product-img-container" style="width: 10%">
                                <img src="./assets/img/products/<%=p.getValue().getImg()%>" alt="" class="produc-img" style="width: 100%">
                            </div>
                            <div class="product-info show-flex-col" style="width: 90%; height: 100%; justify-content: space-between">
                                <p class="product-name" style="font-size: 20px; font-weight: 600"><%=p.getValue().getName()%></p>
                                <div class="show-flex-row" style="justify-content: space-between; height: 100%">
                                    <p><span class="product-price"><%=p.getValue().getPrice()%></span>VND</p>
                                    <p style="display: block">So luong: <span class="product-qty"><%=p.getKey().getQty()%></span></p>
                                </div>
                            </div>
                        </div>
                        <div class="seperate-horizontal-linear"></div>
                    </div>

                    <%
                            }
                    %>

                    <div class="show-flex-row">
                        <div>
                            <p>Ngay dat: <span class="order-dateSet">10/1/2024</span></p>
                            <p>Ngay hoan thanh: <span class="order-dateFinish">12/1/2024</span></p>
                        </div>
                        <div class="show-flex-col" style="align-items: end">
                            <p>Tong tien: <span class="order-Price">21982357</span> (VND)</p>
                            <% if(item.getKey().getStatus()>=0 && item.getKey().getStatus() <3) {%>
                                <button type="button" class="btn btn-third" >Huy</button>
                            <%
                                }
                            %>
<%--                            <%  if(item.getKey().getStatus()==4) {%>--%>
<%--                                <button type="button" class="btn btn-primary" >Da nhan hang</button>--%>
<%--                            <%--%>
<%--                                }--%>
<%--                            %>--%>



                        </div>

                    </div>



                </div>
                <%
                    }
                %>
            </div>



        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script>
    $(".modal__overlay").click(hideModal);

    function showModal() {
        document.querySelector('.confirm-stop').classList.add('active');
    }

    function hideModal(){
        document.querySelector('.confirm-stop').classList.remove('active');
    }


    const orderMenu = document.querySelectorAll(".order-menu-item a");
    for(let i=0;i<orderMenu.length;i++) {
        orderMenu[i].addEventListener('click',function (event) {
            event.preventDefault();
            let url = event.target.href;
            for(let i=0;i<orderMenu.length;i++) {
                console.log(orderMenu[i].parentNode);
                orderMenu[i].parentNode.classList.remove("active");
            }

            event.target.parentNode.classList.add("active");

            queryOrder(url);
        });
    }

    function queryOrder(urlin) {
        $.ajax({
            url: urlin,
            method: "GET",
            success: function(data) {
                // console.log(data);
                $('.order-content').html(data);
            }
        });
    }

</script>

</body>
</html>
