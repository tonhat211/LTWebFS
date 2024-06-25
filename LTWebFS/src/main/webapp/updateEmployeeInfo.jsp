<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
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

    <script src="assets/js/toast.js"></script>

</head>
<body>
<%
    String currentAdminMenu = ((String) session.getAttribute("currentAdminMenu"))==null ? "dashboard" : ((String) session.getAttribute("currentAdminMenu"));
    Employee e = (Employee) session.getAttribute("adminloging");
    if(e== null) {
        e= new Employee();
    }

    ArrayList<Branch> branchList = (ArrayList<Branch>) request.getAttribute("branchList");
    if(branchList ==null){
        branchList= new ArrayList<>();
    }

%>


<div class="ad-content mt10">
    <div id="toast">
        <%--        <script> showSuccessToast("hi")</script>--%>

    </div>

    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()" style="top: 80px">
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
                                    <input type="text" size="10" class="form-control w-50" name="id" aria-describedby="" placeholder="Nhập mật khẩu hiện tại" value="<%=e.getId()%>" hidden>
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
            <a href="admin-menu-controller?adminMenu=<%=currentAdminMenu%>" class="backto-AdminProduct">Quay lại</a>

            <div class="form-container">
                <form action="update-employee-info" method="get" id="infoForm">

                    <div class="show-flex-row" style="justify-content: center">
                        <h4>Cập nhật thông tin</h4>

                    </div>
                    <div class="stop_reSale" style="display: flex; justify-content: right">
                        <a class="btn btn-change-pwd" style="color: #0d6efd; text-decoration: underline" href="#">Đổi mật khẩu</a>
                    </div>

                    <%String info = e.getInfo();
                        String sex = "";
                        String birthday="";
                        String position="";
                        String area ="";
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
                                case 3: {
                                    sex = infoTokens[0];
                                    birthday = infoTokens[1];
                                    position = infoTokens[2];
                                    break;
                                }
                                case 4: {
                                    sex = infoTokens[0];
                                    birthday = infoTokens[1];
                                    position = infoTokens[2];
                                    area = infoTokens[3];
                                    break;
                                }
                                default: {
                                    sex = "";
                                    birthday="";
                                    position="";
                                    area="";
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





                    <div class="form-group w-50" style="display: none">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=e.getId()%>" readonly>
                        <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>


                    <div class="form-group w-80">
                        <label class="w-100" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-100" id="name" name="name" aria-describedby="" placeholder="Nhập họ và tên" value="<%=e.getName() %>">
                        <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>

                    <div class="form-group w-80">
                        <label class="w-100" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-100" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=e.getEmail() %>">
                    </div>
                    <div class="form-group w-80">
                        <label class="w-100" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-100" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=e.getPhone() %>">
                    </div>



                    <div class="show-flex-row">

                        <div class="form-group w-50">
                            <label class="w-100" for="sex">Giới tính: </label>
                            <input type="text" class="form-control w-100" id="sex" name="sex" paria-describedby="" placeholder="Nhập giới tính" value="<%=sex%>">
                        </div>
                        <div class="form-group w-50">
                            <label  class="w-100"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-100" id="birthday" name="birthday"  aria-describedby="" placeholder="Nhập ngày sinh" value="<%=birthday%>">
                            <input type="text" class="form-control w-100" name="position"  aria-describedby="" placeholder="Nhập ngày sinh" value="<%=position%>" hidden>
                            <input type="text" class="form-control w-100" id="area" name="birthday"  aria-describedby="" placeholder="Nhập ngày sinh" value="<%=area%>" hidden>
                        </div>
                    </div>
                    <div class="form-group w-100">
                        <label class="w-100" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-100" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=e.getAddress() %>">
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-100"  for="datein">Ngày vào làm: </label>
                        <input type="date" class="form-control w-100" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày vào làm" value="<%= e.getDatein().getDateInMonthDayYearSql()%>" readonly>
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-100"  for="imgurl">Ảnh đại diện: </label>
                        <input type="text" class="form-control w-100" id="imgurl" name="imgurl"  aria-describedby="" placeholder="Nhập ảnh đại diện" value="<%= e.getImgurl()%>">
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
            var phone = data.get("pho;ne");
            var sex = data.get("sex")
            var birthday = data.get("birthday");
            var position = data.get("position");
            var area = data.get("area");
            var address = data.get("address");
            var imgurl = data.get("imgurl");
            var action = "update";
            var object = "employee";

            updateInfo(action, object, id, name, email, phone, sex, birthday, position, area, address, imgurl);

        });

        function updateInfo(action, object, id, name, email, phone, sex, birthday, position, area, address, imgurl) {
            $.ajax({
                url: "/LTWebFS/update-info",
                method: "POST",
                data: { action: action, object: object, id: id, name: name, email: email, phone: phone, sex: sex, birthday: birthday, position: position, area: area,address: address, imgurl: imgurl},
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

</script>

</body>
</html>
