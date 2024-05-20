<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ProductSuperDetail" %>
<%@ page import="model.ProductUnit" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Thêm/Cập nhật khách hàng</title>
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
    <link rel="stylesheet" href="assets/css/toast.css">

    <script src="assets/js/toast.js"></script>
</head>
<body>
<%
    User u = (User) request.getAttribute("customer");
    if(u==null) {
        u = new User();
    }
%>




<div class="ad-content mt10">
    <%--thong bao--%>
    <div id="toast">

    </div>
    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p>Bạn chắc chắn muốn khóa tài khoản này?</p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn yes-confirm" href="addUpdate-customer?action=lock&id=<%=u.getId()%>">Khóa</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>

        </div>
    </div>
    <div class="modal confirm-unlock">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p><%= (u.getAvailable()==0?"Kích hoạt tài khoản?":"Mở khóa tài khoản này?") %></p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn unlock-user" href="addUpdate-customer?action=unlock&id=<%=u.getId()%>"><%= (u.getAvailable()==0?"Kích hoạt":"Mở khóa") %></a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>

        </div>
    </div>

    <div class="ad-content-item">

        <div class="ad_container" style="width: 70%;">
            <a href="goto-customer-admin" class="backto-AdminProduct">Quay lại trang quản lí khách hàng</a>

            <div class="form-container">
                <form action="addUpdate-customer" method="post" id="customerInfoForm">
                    <div class="show-flex-row">
                        <h4><%= (u.getName()==""?"Thêm khách hàng mới":"Cập nhật thông tin khách hàng") %></h4>

                    </div>
                    <div class="show-flex-row">
                        <div class="grid__row img-showing">
                            <div class="disabled-showing <%= (u.getAvailable()<1?"active":"") %>" style="<%= (u.getName()==""?"position: relative;  left: 0":"") %>" >
                                <div class="disabled-showing-content">
                                    <%= (u.getAvailable()==0?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA/TẠM KHÓA") %>
                                </div>
                            </div>
                        </div>
                        <div class="stop_reSale <%= (u.getName()==""?"hide":"") %>" >
                            <div class="btn btn-stop-pro <%= (u.getAvailable()>0?"active":"") %>" onclick="showModal()">Khóa tài khoản</div>
                            <div class="btn btn-stop-pro <%= (u.getAvailable()==0?"active":"") %>" onclick="showModalUnlock()">Kích hoạt tài khoản</div>
                            <div class="btn btn-resale-pro <%= (u.getAvailable()<0?"active":"") %>" onclick="showModalUnlock()">Mở khóa tài khoản</div>

                        </div>
                    </div>


                    <div class="form-group w-50">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=u.getId()%>" readonly>
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập tên" value="<%=u.getName() %>">
                        <div class="required" hidden>Không được để trống mục này</div>
                    </div>

                    <div class="form-group w-80">
                        <label class="w-20" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-80" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=u.getEmail() %>">
                        <div class="error" hidden>Email không hợp lệ</div>
                        <div class="required" hidden>Không được để trống mục này</div>

                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-80" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=u.getPhone() %>">
                        <div class="required" hidden>Không được để trống mục này</div>
                        <div class="error" hidden>Số điện thoại không hợp lệ</div>

                    </div>
                    <div class="form-group w-100">
                        <label class="w-20" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=u.getAddress() %>">
<%--                        <div class="required" hidden>Không được để trống mục này</div>--%>

                    </div>

                    <div class="show-flex-row">
                        <%String info = u.getInfo();
                            String sex = "";
                            String birthday="";
                            if(info!=null){
                                String infoTokens[] = info.split("=");

                                switch (infoTokens.length) {
                                    case 1: {
                                        sex = infoTokens[0];
                                        break;
                                    }
                                    case 2: {
                                        sex = infoTokens[0];
                                        birthday = infoTokens[1];
                                        break;
                                    }
                                    default: {
                                        sex = "";
                                        birthday ="";
                                        break;
                                    }
                                }

                                if(!birthday.equals("")) {
                                    String[] bdTokens = birthday.split("-");
                                    if((bdTokens[1].length()<2))
                                        bdTokens[1]="0" + bdTokens[1];
                                    if((bdTokens[2]).length()<2)
                                        bdTokens[2]="0" + bdTokens[2];
                                    birthday = bdTokens[0] + "-" + bdTokens[1] + "-" + bdTokens[2];
                                }
                            }


                        %>

                        <div id="sex" class="info-container">
                            <div class="info-container__title">
                                <label for="sex">Giới tính
                                </label>
                            </div>

                            <div class="info-container__content">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sex" id="flexRadioDefault1" value="nam" <%=sex.equalsIgnoreCase("nam") ? "checked" : ""%>>
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Nam
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="sex" id="flexRadioDefault2" value="nu"  <%=sex.equalsIgnoreCase("nu") ? "checked" : ""%>>
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Nữ
                                    </label>
                                </div>
                            </div>

                        </div>
                        <div class="form-group w-50">
                            <label  class="w-40"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Enter date import" value="<%=birthday%>">
                            <div class="error" hidden>Chưa đủ 16 tuổi</div>

                        </div>
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-40"  for="datein">Ngày tham gia: </label>
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày tham gia" value="<%= u.getDateIn().getDateInMonthDayYearSql() %>">
                        <div class="error" hidden>Ngày tham gia không hợp lệ</div>
                    </div>

                    <div class="form-group" style="display: none">
                        <label class="w-20" for="action" class="input-title">action</label>
                        <input type="text" class="form-control" id="action" name="action" aria-describedby="" placeholder="Enter img url" value="<%= (u.getName()==""?"add":"update") %>">
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="w-20" for="status" class="input-title">status</label>
                        <input type="text" class="form-control" id="status" name="status" aria-describedby="" placeholder="Enter img url" value="1">
                    </div>

                    <div class="show-flex-row">
                        <div class="ad_func-container">
                            <div><a class="btn btn-third" href="goto-customer-admin">Hủy</a></div>
                        </div>
                        <div class="ad_func-container">
                            <button class="btn btn-primary" type="submit"><%= (u.getName()==""?"Thêm":"Lưu") %></button>
                        </div>
                    </div>
                    <!--                    <button type="submit" class="btn btn-primary">Submit</button>-->
                </form>

            </div>
        </div>
    </div>
</div>
<%--<script> showSuccessToast("thanh cong script");</script>--%>
<script src="assets/js/addUpdate.js"></script>
<script src="assets/js/toast.js"></script>
<script>

    $(document).ready(function() {
        // showSuccessToast("thanh cong tai");
        document.querySelector("#customerInfoForm").addEventListener('submit', function (event){
            event.preventDefault();

            var formData = new FormData(this);
            var name = formData.get("name");
            var email = formData.get("email");
            var phone = formData.get("phone");
            var address = formData.get("address");
            var birthday = formData.get("birthday");
            var datein = formData.get("datein");

            var isOk = true;

            if(name.trim() === "") {
                switchMessage("#name",'.required',1);
                isOk = false;
            } else {
                switchMessage("#name",'.required',0);

            }

            if(email.trim() === "") {
                switchMessage("#email",'.required',1);
                isOk = false;
            } else {
                switchMessage("#email",'.required',0);
                var regex= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                if(regex.test(email)) {
                    switchMessage("#email",'.error',0);
                } else {
                    switchMessage("#email",'.error',1);
                    isOk=false;
                }
            }

            if(phone.trim() ===""){
                switchMessage("#phone",'.required',1);
                isOk = false;
            } else {
                switchMessage("#phone",'.required',0);
                if(phone.length > 11) {
                    switchMessage("#phone",'.error',1);
                    isOk=false;

                } else {
                    var test;
                    for(var i = 0; i<phone.length; i++) {
                        test = parseInt(phone.charAt(i));
                        if(isNaN(test)) {
                            switchMessage("#phone",'.error',1);
                            isOk=false;
                            break;
                        }
                    }
                }
            }



            if(address.trim() === "") {
                address = "";
            } else {


            }

            var birth = new Date(birthday);

            var year = birth.getFullYear();
            var currentYear = new Date().getFullYear();
            var curretnMonth = new Date().getMonth()+1;
            var currentDay = new Date().getDate();

            if((currentYear - year) < 18) {
                switchMessage("#birthday",'.error',1);
                isOk = false;
            } else {
                switchMessage("#birthday",'.error',0);
            }

            var date_in = new Date(datein);
            var year = date_in.getFullYear();
            var month = date_in.getMonth()+1;
            var day = date_in.getDate();
            console.log(year);
            console.log(month);
            console.log(day);

            if((year <= currentYear && month <= curretnMonth && day <= currentDay)){
                switchMessage("#datein",'.error',0);

            } else {
                switchMessage("#datein",'.error',1);
                isOk = false;
            }


            if(isOk=== false) {
                return;
            }

            var id = formData.get("id");
            var sex = formData.get("sex");
            var action = formData.get("action");


            updateCustomer(id,name, email, phone, address, birthday, datein, action, sex);

        });


        function updateCustomer(id, name, email, phone, address, birthday, datein, action, sex) {
            $.ajax({
                url: "/LTWebFS/addUpdate-customer",
                method: "POST",
                data: { id: id, name: name, email: email, phone: phone, address: address, birthday: birthday, datein: datein, action: action, sex: sex },
                success: function(data) {
                    $("#customerInfoForm").html(data);

                }
            });
        }

        function switchMessage(parentSelector,selector, status) {
            var e = document.querySelector(parentSelector).parentElement.querySelector(selector);
            if(status === 1) {
                e.hidden = false;
            } else {
                e.hidden = true;
            }
        }

    //     gan xu kien cho cac element
        const confirmStopModalOverlay =$('.modal__overlay');
        const confirmStopModal =$('.confirm-stop');

        const yesConfirmBtn = $('.yes-confirm');
        const noConfirmBtn = $('.no-confirm');



        confirmStopModalOverlay.click(hideModal);
        noConfirmBtn.click(hideModal);

        confirmStopModalOverlay.click(hideModalUnlock);
        noConfirmBtn.click(hideModalUnlock);




    });
    document.querySelector('.yes-confirm').addEventListener('click', function (event) {
        event.preventDefault();
        var urlin = event.target.href;
        availableUser(urlin);
        hideModal();

    });

    document.querySelector('.unlock-user').addEventListener('click', function (event) {
        event.preventDefault();
        var urlin = event.target.href;
        availableUser(urlin);
        hideModalUnlock();
        // showSuccessToast("thanh cong r nef");

    });

    function showModal() {
        $('.confirm-stop').addClass('active');
    }

    function hideModal(){
        $('.confirm-stop').removeClass('active');
    }

    function showModalUnlock() {
        $('.confirm-unlock').addClass('active');
    }

    function hideModalUnlock(){
        $('.confirm-unlock').removeClass('active');
    }

    function availableUser(urlin) {

        $.ajax({
            // url: "/LTWebFS_war_exploded/addUpdate-customer", // Đường dẫn đến Servlet
            url: urlin, // Đường dẫn đến Servlet
            method: "POST",
            // data: { action: action, id : id },
            success: function(data) {
                $("#customerInfoForm").html(data);
            }
        });
    }




</script>

</body>
</html>
