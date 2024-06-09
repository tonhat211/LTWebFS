<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.User" %>
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
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/addUpdate.css">
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/updateCustomerInfo.css">
    <link rel="stylesheet" href="assets/css/toast.css">
    <link rel="stylesheet" href="assets/css/payment.css">

    <script src="assets/js/toast.js"></script>

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
                    hideModal();
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
                canelEditAddress();
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
%>

<div class="ad-content mt10">
    <div id="toast">
<%--        <script> showSuccessToast("hi")</script>--%>

    </div>
    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" id="modal_content" onclick="event.stopPropagation()" style="top: 80px">
                <div class="form-container">

                    <form action="change-pwd" method="post" id="pwdForm">
                        <div class="show-flex-row" style="justify-content: center">
                            <h4>Đổi mật khẩu</h4>

                        </div>
                        <div class="form-group w-100" style="padding: 0px;">
                            <div class="show-flex-col w-100">
                                <div class="show-flex-row w-100">
                                    <label class="w-50" for="currentPwd">Mật khẩu hiện tại</label>
                                    <input type="password" size="10" class="form-control w-50" id="currentPwd" name="currentPwd" aria-describedby="" placeholder="Nhập mật khẩu hiện tại">
                                    <input type="text" size="10" class="form-control w-50" name="id" aria-describedby="" placeholder="Nhập mật khẩu hiện tại" value="<%=u.getId()%>" hidden>
                                </div>
                                <div class="error active" hidden>Sai mật khẩu</div>

                            </div>
                        </div>
                        <div class="form-group w-100" style="padding: 0px;">
                            <div class="show-flex-col w-100">
                                <div class="show-flex-row w-100">
                                    <label class="w-50" for="newPwd">Mật khẩu mới</label>
                                    <input type="password" size="10" class="form-control w-50" id="newPwd" name="newPwd" aria-describedby="" placeholder="Nhập mật khẩu mới">
                                </div>
                                <div class="error active" hidden>Mật khẩu không trùng khớp</div>


                            </div>

                        </div>
                        <div class="form-group w-100" style="padding: 0px;">
                            <div class="show-flex-col w-100">
                                <div class="show-flex-row w-100">
                                    <label class="w-50" for="renewPwd">Nhập lại mật khẩu mới</label>
                                    <input type="password" size="10" class="form-control w-50" id="renewPwd" name="renewPwd" aria-describedby="" placeholder="Nhập lại mật khẩu mới">

                                </div>
                                <div class="error active" hidden>Mật khẩu không trùng khớp</div>
                            </div>

                        </div>

                        <div class="show-flex-row" style="justify-content: center; margin-top: 40px">
                            <div class="show-flex-row w-50">
                                <a class="btn btn-third confirm-btn no-confirm" href="#">Hủy</a>
                                <button class="btn btn-primary confirm-btn yes-confirm" type="submit">Lưu</button>
                            </div>
                        </div>



                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="ad-content-item">

        <div class="ad_container">
            <a href="menucontrol?menu=product&kind=A" class="backto-AdminProduct">Quay lại</a>

            <div class="form-container">
                <form action="update-info" method="get" id="infoForm">
                    <div class="show-flex-row" style="justify-content: center">
                        <h4>Cập nhật thông tin</h4>

                    </div>
                    <div class="show-flex-row">
                        <div class="stop_reSale" style="display: flex; justify-content: left">
                            <button class="btn" id="changeAddress" type="button" style="color: #0d6efd; text-decoration: underline">Sổ địa chỉ</button>
                        </div>
                        <div class="stop_reSale" style="display: flex; justify-content: right">
                            <a class="btn btn-change-pwd" style="color: #0d6efd; text-decoration: underline" href="#">Đổi mật khẩu</a>
                        </div>
                    </div>

                    <div class="form-group w-50" style="display:none;">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=u.getId()%>" hidden>
                        <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>

                    <div class="form-group w-80">
                        <label class="w-100" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập tên" value="<%=u.getName() %>">
                        <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>

                    <div class="form-group w-80">
                        <label class="w-100" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-80" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=u.getEmail() %>">
                    </div>
                    <div class="form-group w-80">
                        <label class="w-100" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-80" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=u.getPhone() %>">
                    </div>
                    <div class="form-group w-100">
                        <label class="w-100" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-100" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=u.getAddress() %>">
                    </div>

                    <div class="show-flex-row">
                        <%String info = u.getInfo();
                            String sex = "";
                            String birthday="";
                            if(info!=null){
                                String infoTokens[] = info.split("=");

                                if(infoTokens.length <2){
                                    sex = "trống";
                                    birthday="trống";
                                }
                                else {
                                    sex = infoTokens[0];
                                    birthday = infoTokens[1];
                                    String[] bdTokens = birthday.split("-");
                                    if((bdTokens[1].length()<2))
                                        bdTokens[1]="0" + bdTokens[1];
                                    if((bdTokens[2]).length()<2)
                                        bdTokens[2]="0" + bdTokens[2];

                                    birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];

                                }
                            }


                        %>
                        <div class="form-group w-50">
                            <label class="w-100" for="sex">Giới tính: </label>
                            <input type="text" class="form-control w-80" id="sex" name="sex" paria-describedby="" placeholder="Enter kind" value="<%=sex%>">
                        </div>
                        <div class="form-group w-50">
                            <label  class="w-100"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Enter date import" value="<%=birthday%>">
                        </div>
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-100"  for="datein">Ngày tham gia: </label>
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày tham gia" value="<%= u.getDateIn().getDateInMonthDayYearSql() %>" readonly>
                    </div>

                    <div class="form-group" style="display: none">
                        <label class="w-20" for="action" class="input-title">action</label>
                        <input type="text" class="form-control" id="action" name="action" aria-describedby="" placeholder="Enter img url" value="update">
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="w-20" for="status" class="input-title">status</label>
                        <input type="text" class="form-control" id="status" name="status" aria-describedby="" placeholder="Enter img url" value="1">
                    </div>

                    <div class="show-flex-row" style="justify-content: center; margin-top: 40px">
                        <div class="show-flex-row w-50" >
                            <div class="ad_func-container">
                                <div><a class="btn btn-third" href="menucontrol?menu=product&kind=A">Hủy</a></div>
                            </div>
                            <div class="ad_func-container">
                                <button class="btn btn-primary" type="submit">Lưu</button>
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
    const p$ = document.querySelector.bind(document);
    const p$$ = document.querySelectorAll.bind(document);

    const confirmStopModalOverlay =p$('.modal__overlay');
    const confirmStopModal =p$('.confirm-stop');
    const changePwdBtn = p$('.btn-change-pwd');
    const yesConfirmBtn = p$('.yes-confirm');
    const noConfirmBtn = p$('.no-confirm');

    document.querySelector('.no-confirm').addEventListener('click',function (event) {
        event.preventDefault();
        hideModal();

    });

    $(".modal__overlay").click(hideModal);

    // changePwdBtn.addEventListener('click',showModal);
    // confirmStopModalOverlay.addEventListener('click',hideModal);
    // noConfirmBtn.addEventListener('click',hideModal);

    function showModal() {
        confirmStopModal.classList.add('active');
    }

    function hideModal(){
        confirmStopModal.classList.remove('active');
    }

    document.querySelector(".btn-change-pwd").addEventListener('click', function (event) {
        event.preventDefault();
        $('.modal').addClass("active");
    });

    // document.getElementById("pwdForm").addEventListener("submit", function(event) {
    //     event.preventDefault(); // Ngăn chặn hành động mặc định của form
    //     console.log("Đã gửi form pwd");
    //
    // });
    //
    document.getElementById("pwdForm").addEventListener("submit", function(event) {
        event.preventDefault();
        var data = new FormData(this);
        var currentPwd = data.get("currentPwd");
        var newPwd = data.get("newPwd");
        var renewPwd = data.get("renewPwd");
        var action = "changePassword";
        var id = data.get("id");

        var isOk = true;

        if(currentPwd==="") {
            isOk=false;
            showError('#currentPwd',"Không được để trống mục này");
        } else {
            hideError('#currentPwd');
        }
        if(newPwd==="") {
            isOk=false;
            showError('#newPwd',"Không được để trống mục này");
        } else {
            hideError('#newPwd');
        }
        if(renewPwd==="") {
            isOk=false;
            showError('#renewPwd',"Không được để trống mục này");
        } else {
            hideError('#renewPwd');
        }


        if(newPwd !=="" && renewPwd!=="") {
            if(newPwd!== renewPwd) {
                isOk=false;
                showError('#newPwd',"Mật khẩu không trùng khớp");
                showError('#renewPwd',"Mật khẩu không trùng khớp");
            } else {
                hideError('#newPwd');
                hideError('#renewPwd');
                if (newPwd.length < 8 || !/[A-Z]/.test(newPwd) || !/[a-z]/.test(newPwd) || !/\d/.test(newPwd)) {
                    showError('#newPwd',"Mật khẩu phải trên 8 kí tự, bao gồm chữ hoa, chữ thường và số");
                    isOk=false;
                }
            }
        }

        if(isOk===true) {
            changePwd(action, id, currentPwd, newPwd);
        }


    });

    function changePwd(action, id, currentPwd, newPwd) {
        $.ajax({
            url: "/LTWebFS/update-info",
            method: "POST",
            data: { action: action, id: id, currentPwd:currentPwd, newPwd: newPwd},
            success: function(data) {
                if(data==="success") {
                    showSuccessToast("Đổi mật khẩu thành công");
                    $('.modal').removeClass("active");
                } else if(data==="wrongPwd") {
                    showError("#currentPwd","Mật khẩu sai");
                } else if(data==="thesamePwd") {
                    showError("#newPwd","Mật khẩu mới phải khác mật khẩu cũ");
                }
            }
        });
    }


    $(document).ready(function() {
        document.querySelector('#infoForm').addEventListener('submit', function (event) {
            event.preventDefault();
            var data = new FormData(this);
            var id = data.get("id");
            var name = data.get("name");
            var email = data.get("email");
            var phone = data.get("phone");
            var sex = data.get("sex");
            var birthday = data.get("birthday");
            var address = data.get("address");
            var datein = data.get("datein");
            var action = "update";
            var object = "customer";

            updateInfo(action, object, id, name, email, phone, sex, birthday, address, datein);

        });

        function updateInfo(action, object, id, name, email, phone, sex, birthday, address, datein) {
            $.ajax({
                url: "/LTWebFS/update-info",
                method: "POST",
                data: { action: action, object: object, id: id, name: name, email: email, phone: phone, sex: sex, birthday: birthday, address: address, datein: datein},
                success: function(data) {
                    console.log(data);
                    $('#toast').html(data);
                }
            });
        }



    });

    function showError(parent,error) {
        document.querySelector(parent).parentNode.parentNode.querySelector(".error").hidden = false;
        document.querySelector(parent).parentNode.parentNode.querySelector(".error").innerText = error;
    }
    function hideError(parent) {
        document.querySelector(parent).parentNode.parentNode.querySelector(".error").hidden = true;
    }

    $('#changeAddress').click(showAddressList);

    function showAddressList() {
        console.log("chang add ýe")
        $.ajax({
            url: "/LTWebFS/address-management?action=init",
            method: "POST",
            success: function(data) {
                $('.modal').addClass("active");
                $('#modal_content').html(data);
                addressListHtml = data;
            }
        });


    }

</script>

</body>
</html>
