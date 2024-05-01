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

    <!--fontawesome-->
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/addUpdate.css">
    <link rel="stylesheet" href="assets/css/modal.css">
    <link rel="stylesheet" href="assets/css/updateCustomerInfo.css">

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

    String status = (String) request.getAttribute("status");
%>

<%
    if(status != null) {
        if(status.equalsIgnoreCase("updateInfoSuccessful")){
%>
<script>
    alert("Cập nhật thông tin thành công");
</script>
<%

        } else if(status.equalsIgnoreCase("changePwdSuccessful")){
%>
<script>
    alert("Đổi mật khẩu thành công");
</script>
<%
        }
    }
%>


<div class="ad-content mt10">
    <div class="modal confirm-stop">
        <div class="modal__overlay">
        </div>
    </div>
    <div class="ad-content-item">

        <div class="ad_container">
            <a href="goto-<%=currentAdminMenu%>-admin" class="backto-AdminProduct">Quay lại</a>

            <div class="form-container">
                <form action="update-employee-info" method="get" id="myForm">

                    <div class="show-flex-row" style="justify-content: center">
                        <h4>Cập nhật thông tin</h4>

                    </div>
                    <div class="stop_reSale" style="display: flex; justify-content: right">
                        <a class="btn btn-change-pwd" style="color: #0d6efd; text-decoration: underline" href="goto-changePwd?object=employee">Đổi mật khẩu</a>
                    </div>

                    <%String info = e.getInfo();
                        String sex = "";
                        String birthday="";
                        String position="";
                        String area ="";
                        if(info!=null){
                            String infoTokens[] = info.split("=");

                            if(infoTokens.length <4){
                                sex = "";
                                birthday="";
                                position="";
                                area="";
                            }
                            else {
                                sex = infoTokens[0];
                                birthday = infoTokens[1];
                                position = infoTokens[2];
                                area = infoTokens[3];
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
                        <label class="w-20" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập họ và tên" value="<%=e.getName() %>">
                        <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>
                    <div class="form-group w-80" style="display: none">
                        <label class="w-20" for="position">Chức vụ</label>
                        <input type="text" size="10" class="form-control w-80" id="position" name="position" aria-describedby="" placeholder="Nhập chức vụ" value="<%=e.getPosition() %>">
                    </div>
                    <div class="form-group w-80" style="display: none">
                        <label class="w-20" for="area">Phòng ban: </label>
                        <input type="text" class="form-control w-80" id="area" name="area" aria-describedby="" placeholder="Nhập phòng ban" value="<%=e.getArea() %>">
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-80" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=e.getEmail() %>">
                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-80" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=e.getPhone() %>">
                    </div>
                    <div class="form-group w-80"  style="display: none">
                        <label class="w-20" for="branch">Chi nhánh: </label>
                        <select class="form-select" aria-label="Default select example" id="branch" name="branch">
                            <% for(Branch b : branchList) {
                            %>
                            <option <%=b.getId()==e.getBranchID() ? "selected" : "" %> value="<%=b.getId()+"="+b.getName()%>"><%=b.getName() %></option>
                            <%
                                }
                            %>

                        </select>
                    </div>

                    <%
                        String roles = e.getRole();
                        if(roles == null) {
                            roles="";
                        }

                    %>

                    <div id="role" class="info-container" style="display: none">
                        <div class="info-container__title">
                            <label for="role">Quyền truy cập
                            </label>
                        </div>
                        <div class="info-container__content">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="employee"name="employee" value="employee" <%=roles.contains("employee")?"checked":""%>>
                                <label class="form-check-label" for="employee">
                                    Nhân viên
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="customer" name="customer" value="customer" <%=roles.contains("customer")?"checked":""%>>
                                <label class="form-check-label" for="customer">
                                    Khách hàng
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="product" name="product" value="product" <%=roles.contains("product")?"checked":""%>>
                                <label class="form-check-label" for="product">
                                    Sản phẩm
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="order" name="order" value="order" <%=roles.contains("order")?"checked":""%>>
                                <label class="form-check-label" for="order">
                                    Đơn hàng
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="dashboard" name="dashboard" value="dashboard" <%=roles.contains("dashboard")?"checked":""%>>
                                <label class="form-check-label" for="dashboard">
                                    Thống kê
                                </label>
                            </div>
                        </div>

                    </div>

                    <div class="show-flex-row">

                        <div class="form-group w-50">
                            <label class="w-20" for="sex">Giới tính: </label>
                            <input type="text" class="form-control w-80" id="sex" name="sex" paria-describedby="" placeholder="Nhập giới tính" value="<%=e.getSex()%>">
                        </div>
                        <div class="form-group w-50">
                            <label  class="w-20"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Nhập ngày sinh" value="<%=e.getBirthday()%>">
                        </div>
                    </div>
                    <div class="form-group w-100">
                        <label class="w-20" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=e.getAddress() %>">
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-20"  for="datein">Ngày vào làm: </label>
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày vào làm" value="<%= e.getDatein().getDateInMonthDayYearSql()%>" readonly>
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-20"  for="imgurl">Ảnh đại diện: </label>
                        <input type="text" class="form-control w-80" id="imgurl" name="imgurl"  aria-describedby="" placeholder="Nhập ảnh đại diện" value="<%= e.getImgurl()%>">
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

    // changePwdBtn.addEventListener('click',showModal);
    // confirmStopModalOverlay.addEventListener('click',hideModal);
    // noConfirmBtn.addEventListener('click',hideModal);

    function showModal() {
        confirmStopModal.classList.add('active');

        // document.getElementById('myForm').onsubmit = function() {
        //     var newPwd = p$('#renewPwd').value;
        //     var renewPwd = p$('#renewPwd').value;
        //     var checked =false
        //     if (newPwd === renewPwd) {
        //         checked= true;
        //
        //
        //     }
        //
        //     if (!checked) {
        //         console.log("khong khop");
        //         p$$('.error').forEach(function(errorElement) {
        //             errorElement.classList.add("active");
        //         });
        //         return false;
        //     }
        //
        // };



    }

    function hideModal(){
        confirmStopModal.classList.remove('active');
    }

    document.getElementById("pwdForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của form
        console.log("Đã gửi form pwd");

    });

    document.getElementById("infoForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của form

        // Thực hiện xử lý cho form 2
        console.log("Đã gửi form info");
    });








</script>

</body>
</html>
