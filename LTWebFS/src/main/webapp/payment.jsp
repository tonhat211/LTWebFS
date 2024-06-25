<%@ page import="model.cartitem" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.DeCart" %>
<%@ page import="model.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng</title>

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--fontawesome-->
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <link rel="stylesheet" href="assets/css/cart.css">
<%--    <link href="assets/css/style.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/addUpdate.css">

    <link rel="stylesheet" href="assets/css/payment.css">

    <link rel="stylesheet" href="./assets/fonts/fontawesome-free-6.4.0-web/css/all.min.css">

    <link rel="stylesheet" href="assets/css/toast.css">
    <script src="assets/js/toast.js"></script>
<%--    <script src="assets/js/payment.js"></script>--%>
</head>
<body>
<%
    User  u = (User) session.getAttribute("userloging");
    if(u== null) {
        u= new User();
    }
    ArrayList<cartitem> cartTemp = (ArrayList<cartitem>) request.getAttribute("cartTemp");

    ArrayList<Address> addresses = (ArrayList<Address>) request.getAttribute("addresses");
    if(addresses.isEmpty()) {
        addresses.add(new Address(u.getId(),"không xác định","không xác định","không xác định"));
    }

    double totalMoney = 0;
    double deliveryFee = 30000;


%>

<script>
    function showEditAddress(event) {
        event.stopPropagation();
        let addressItem = this.parentNode.parentNode.parentNode;
        let id = addressItem.querySelector('.id').innerText;
        let receiver = addressItem.querySelector('.receiver').innerText;
        let phone = addressItem.querySelector('.receiver-phone').innerText;
        let address = addressItem.querySelector('.address').innerText;

        let html = '<form class="editAddressForm">'+
            '<div class="show-flex-row" style="margin-top: 10px">'+
            '   <input type="text"  class="form-control w-80" id="receiver" name="receiver"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ receiver + '">'+
            '   <div style="margin:0 10px"></div>'+
            '   <input type="text" class="form-control w-80" id="phone" name="phone"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ phone +'">'+
            '</div>'+
            '<input type="text" style="margin-top: 10px" class="form-control w-100" id="address" name="address"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ address +'">'+
            '<input type="text" class="form-control w-80" id="id" name="id"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ id + '" hidden>'+
            '<div class="show-flex-row" style="margin-top:20px">'+
            '   <div class="ad_func-container">'+
            '       <button class="btn btn-third" type="button" onclick="canelEditAddress()">Hủy</button>'+
            '   </div>'+
            '   <div class="ad_func-container">'+
            '       <button class="btn btn-primary" type="submit">Lưu</button>'+
            '   </div>'+
            '</div>'+
            '</form>'+
            '<div class="seperate-horizontal-linear"></div>';

        // reset hien thi danh sach item
        canelEditAddress();

        // hien thi edit box
        const edits = document.querySelector('#modal_content').querySelectorAll('.address-item');
        for(let i=0;i<edits.length;i++) {
            if(edits[i].querySelector('.id').innerText===id) {
                edits[i].classList.add("active");
                edits[i].innerHTML = html;
            }
        }

        const forms = document.querySelectorAll(".editAddressForm");
        for(let i =0;i<forms.length;i++) {
            forms[i].addEventListener('submit',function (event) {
                event.preventDefault();
                const data = new FormData(forms[i]);
                const id = data.get("id");
                const receiver = data.get("receiver");
                const phone = data.get("phone");
                const address = data.get("address");
                const action = "update";
                executeAddress(action,id,receiver,phone,address);
            });
        }

        const addressItems = document.querySelector('#address-container').querySelectorAll('.address-item');
        console.log(addressItems);
        for(let i=0; i<addressItems.length;i++) {
            addressItems[i].addEventListener('click', function () {
                console.log("yes item");
                let receiver = this.querySelector('.receiver');
                let phone = this.querySelector('.phone');
                let address = this.querySelector('.address');
                changeAddress(receiver,phone,address);
                hideAddresses();
            });
        }




    }

    function canelEditAddress() {
        // reset hien thi danh sach item
        let modal = document.querySelector('#modal_content');
        modal.innerHTML= addressListHtml;
    }

    function showAddAddress() {
        let html = ' <div class="address-item active">' +
            '<form class="editAddressForm">'+
            '<div class="show-flex-row" style="margin-top: 10px">'+
            '   <input type="text"  class="form-control w-80" id="receiver" name="receiver"  aria-describedby="" placeholder="Nhập tên người nhận" value="">'+
            '   <div style="margin:0 10px"></div>'+
            '   <input type="text" class="form-control w-80" id="phone" name="phone"  aria-describedby="" placeholder="Nhập số điện thoại người nhận" value="">'+
            '</div>'+
            '<input type="text" style="margin-top: 10px" class="form-control w-100" id="address" name="address"  aria-describedby="" placeholder="Nhập địa chỉ nhận hàng" value="">'+
            '<input type="text" class="form-control w-80" id="id" name="id"  aria-describedby="" placeholder="Nhập id người nhận" value="" hidden>'+
            '<div class="show-flex-row" style="margin-top:20px">'+
            '   <div class="ad_func-container">'+
            '       <button class="btn btn-third" type="button" onclick="canelEditAddress()">Hủy</button>'+
            '   </div>'+
            '   <div class="ad_func-container">'+
            '       <button class="btn btn-primary" type="submit">Thêm</button>'+
            '   </div>'+
            '</div>'+
            '</form>'+
            '<div class="seperate-horizontal-linear"></div>'+
            '</div>';

        canelEditAddress();

        const addressContainer = document.querySelector('#add-address-container');
        addressContainer.innerHTML = html;

        const showAddAddressBtn = document.querySelector('.confirm__message .yes-confirm');
        showAddAddressBtn.disabled = true;

        const form = document.querySelector('#add-address-container .editAddressForm');
        console.log(form);
        form.addEventListener('submit',function (event) {
            event.preventDefault();
            const data = new FormData(this);
            const id = data.get("id");
            console.log(id);
            const receiver = data.get("receiver");
            const phone = data.get("phone");
            const address = data.get("address");
            console.log(address);
            const action = "add";
            executeAddress(action,id,receiver,phone,address);
        });

    }

    function executeAddress(action, id, receiver, phone, address) {
        $.ajax({
            url: "/LTWebFS/address-management",
            method: "POST",
            data: {action: action, id: id, receiver: receiver, phone: phone, address: address},
            success: function(data) {
                if(action==="update")
                    showSuccessToast("Cập nhật địa chỉ thành công");
                else if(action==="add")
                    showSuccessToast("Thêm địa chỉ thành công");
                $('#modal_content').html(data);
            }
        });
    }

    function changeAddress() {
        if(!this.classList.contains("active")) {
            let receiver = this.querySelector('.receiver').innerText;
            let phone = this.querySelector('.receiver-phone').innerText;
            let address = this.querySelector('.address').innerText;

            const container = document.querySelector('.address-info-showing');
            container.querySelector('.sp-receiver').innerText = receiver;
            container.querySelector('.sp-phone').innerText = phone;
            container.querySelector('.sp-address').innerText = address;

            container.querySelector('.ip-receiver').value = receiver;
            container.querySelector('.ip-phone').value = phone;
            container.querySelector('.ip-address').value = address;

            hideModal();
        }


    }




</script>


<main id="main" class="main">
    <%@ include file="header.jsp" %>
    <div class="ad_content"  style="padding: 10px">
        <div id="toast">

        </div>
        <div class="modal confirm-stop  " style="z-index: 9">
            <div class="modal__overlay">
                <div class="modal__confirm-content" id="modal_content" onclick="event.stopPropagation()" style="top:40px;">


                </div>
            </div>
        </div>

        <form action="order" method="get">
            <h2>Thanh toán</h2>
            <div class="seperate-horizontal-linear"></div>
            <div class="info-container address-info-showing">
                <h3>Địa chỉ nhận hàng</h3>
                <div class="show-flex-row">
                    <div class="info-item"><span class="sp-receiver"><%=addresses.get(0).getReceiver()%></span><span class="sp-phone"><%=addresses.get(0).getPhone()%></span><span class="sp-address"><%=addresses.get(0).getAddress()%></span></div>
                    <input type="text" class="ip-receiver" name="receiver" value="<%=addresses.get(0).getReceiver()%>" style="display: none">
                    <input type="text" class="ip-phone" name="phone" value="<%=addresses.get(0).getPhone()%>" style="display: none">
                    <input type="text" class="ip-address" name="address" value="<%=addresses.get(0).getAddress()%>" style="display: none">
                    <div id="changeAddress" onclick="event.stopPropagation()">Thay đổi

                    </div>
                </div>
            </div>

            <div class="info-container">
                <h3>Sản phẩm</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Số lượng </th>
                        <th scope="col">Thành tiền</th>

                    </tr>
                    </thead>
                    <tbody>

                    <%
                        if(cartTemp !=null) {
                            for(int i = 0; i<cartTemp.size();i++) {
                    %>
                    <tr class="">
                        <th scope="row">
                            <%=i+1%>
                        </th>
                        <td style="height: 100px" ><img src="./assets/img/products/<%=cartTemp.get(i).getP().getImg()%>" alt="" style="height: 100%"></td>

                        <td><%=cartTemp.get(i).getP().getName()%></td>
                        <td><%=cartTemp.get(i).getP().getPrice()%></td>
                        <td>
                            <%=cartTemp.get(i).getQty()%>
                        </td>
                        <td><%=cartTemp.get(i).getP().getPrice() * cartTemp.get(i).getQty()%></td>
                        <input type="text" name="productID" hidden value="<%=cartTemp.get(i).getP().getId()%>">
                        <input type="text" name="priceUnit" hidden value="<%=cartTemp.get(i).getP().getPrice()%>">
                        <input type="text" name="qty" hidden value="<%=cartTemp.get(i).getQty()%>">

                    </tr>

                    <%
                                totalMoney +=cartTemp.get(i).getP().getPrice() * cartTemp.get(i).getQty();

                            }
                        }

                        if(totalMoney > 1000000){
                            deliveryFee=0;
                        }
                    %>


                    </tbody>
                </table>
            </div>

            <div class="info-container">
                <h3>Thanh toán</h3>
                <div class="show-flex-row">
                    <div class="info-item"></div>
                    <div class="payment-container w-30">
                        <div class="payment-content">
                            <p>Phương thức thanh toán</p>
                            <div class="show-flex-row"><span>Thanh toán khi nhận hàng</span><p>Thay đổi</p></div>
                            <div class="show-flex-row"><p class="txt-color">Tổng tiền sản phẩm</p><p><span class="product-money"><%=totalMoney%></span>(vnd)</p></div>
                            <div class="show-flex-row"><p class="txt-color">Phí vận chuyển</p><p><span class="delivery-money"><%=deliveryFee%></span>(vnd)</p></div>
                            <p class="note"> Mua trên <span>1000000</span> VND để được miễn phí vận chuyển</p>
                            <div class="show-flex-row"><p class="txt-color">Tổng thanh toán</p><p><span class="total-money"><%=totalMoney + deliveryFee%></span> (VND)</p></div>
                            <input type="text" name="orderMoney" value="<%=totalMoney + deliveryFee%>" hidden>
                            <input type="text" name="deliveryFee" value="<%=deliveryFee%>" hidden>
                            <div class="show-flex-row center"><button class="btn btn-primary btn-buy w-70" style="border:none" type="submit">Đặt hàng</button></div>
                        </div>

                    </div>
                </div>
            </div>
        </form>
    </div>
    <%@ include file="footer.jsp" %>

</main>

<script>
    //     gan xu kien cho cac element

    const confirmStopModalOverlay =$('.modal__overlay');
    const confirmStopModal =$('.confirm-stop');
    const yesConfirmBtn = $('.yes-confirm');
    const noConfirmBtn = $('.no-confirm');

    confirmStopModalOverlay.click(hideModal);
    noConfirmBtn.click(hideModal);
    // confirmStopModalOverlay.click(hideModalUnlock);
    // noConfirmBtn.click(hideModalUnlock);

    function showModal() {
        $('.confirm-stop').addClass('active');
    }

    function hideModal(){
        $('.confirm-stop').removeClass('active');
    }


    function showAddresses() {
        console.log("Add");
        $('.sub-nav').addClass('active');
    }

    function hideAddresses(){
        console.log("remove");
        $('.sub-nav').removeClass('active');
    }

    $(document).click(hideAddresses);
    $('#changeAddress').click(showAddresses);

    $('.add-address').click(showModal);


    const addressItems = document.querySelectorAll(".address-item");
    for (let i = 0; i < addressItems.length; i++) {
        var id = addressItems[i].querySelector('.id').innerText;
        var receiver = addressItems[i].querySelector('.receiver').innerText;
        var phone = addressItems[i].querySelector('.receiver-phone').innerText;
        var address = addressItems[i].querySelector('.address').innerText;

    }

    const editIs = document.querySelectorAll(".editBtn i");
    for(let i=0; i< editIs.length;i++) {
        editIs[i].addEventListener('click',function (event){
            event.preventDefault();
        });
    }


    const editAddressBtns = document.querySelectorAll(".editBtn");
    for(let i=0; i< editAddressBtns.length;i++) {
        editAddressBtns[i].addEventListener('click', function () {

        });
    }

    $('#changeAddress').click(showAddressList);
    let addressListHtml;
    function showAddressList() {

        $.ajax({
            url: "/LTWebFS/address-management?action=init",
            method: "POST",
            // data: { action: action},
            success: function(data) {
                // console.log(action);
                $('.modal').addClass("active");
                $('#modal_content').html(data);
                addressListHtml = data;
            }
        });


    }

</script>





</body>
</html>
