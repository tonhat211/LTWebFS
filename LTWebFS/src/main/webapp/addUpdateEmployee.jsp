<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Thêm/Cập nhật nhân viên</title>
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
    <style>
        #imgs-container {
            display: flex;
            flex-direction: row;
        }

        .chosen-img {
            position: relative;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 150px;
            height: 150px;
            margin: 15px;
            border: 1px solid #B0CFFE;
            border-radius: 4px;
            padding: 4px;

        }

        .chosen-img i {
            position: absolute;
            font-size: 20px;
            top: -5px;
            left: -5px;
            z-index: 1;
            display: none;
        }

        .chosen-img:hover i {
            display: block;
            cursor: pointer;
        }

        .chosen-img img {
            width: 100%;
            height: 100%;
        }

        .chosen-img .img-name {
            font-size: 12px;
            border: none;
            outline: none;
            text-align: center;
        }




    </style>
</head>
<body>
<%
    Employee e = (Employee) request.getAttribute("employee");
    if(e==null) {
        e = new Employee();
    }

    ArrayList<Branch> branchList = (ArrayList<Branch>) request.getAttribute("branchList");
    if(branchList ==null){
        branchList= new ArrayList<>();
    }
    String status = (String) request.getAttribute("status");
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
                    <a class="btn btn-primary confirm-btn yes-confirm" href="employee?action=lock&id=<%=e.getId()%>">Khóa</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>
        </div>
    </div>
        <div class="modal confirm-unlock">
            <div class="modal__overlay">
                <div class="modal__confirm-content" onclick="event.stopPropagation()">
                    <div class="confirm__message">
                        <p><%= (e.getAvailable()==0?"Kích hoạt tài khoản?":"Mở khóa tài khoản này?") %></p>
                    </div>
                    <div class="show-flex-row">
                        <a class="btn btn-primary confirm-btn unlock-user" href="employee?action=unlock&id=<%=e.getId()%>"><%=(e.getAvailable()==0?"Kích hoạt":"Mở khóa") %></a>
                        <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                    </div>
                </div>

            </div>
        </div>
    <div class="ad-content-item">

        <div class="ad_container" style="width: 70%;">
            <a href="admin-menu-controller?adminMenu=employee" class="backto-AdminProduct">Quay lại trang quản lí nhân viên</a>

            <div class="form-container">
                <form action="addUpdate-employee" method="get" id="employeeInfoForm">
                    <div class="show-flex-row">
                        <h4><%= (e.getName()==""?"Thêm nhân viên mới":"Cập nhật thông tin nhân viên") %></h4>

                    </div>
                    <div class="show-flex-row">
                        <div class="grid__row img-showing">
                            <div class="disabled-showing <%= (e.getAvailable()<1?"active":"") %>" style="<%= (e.getName()==""?"position: relative;  left: 0":"") %>" >
                                <div class="disabled-showing-content">
                                    <%= (e.getAvailable()==0?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA/TẠM KHÓA") %>
                                </div>
                            </div>
                        </div>
                        <div class="stop_reSale <%= (e.getName()==""?"hide":"") %>" >
                            <div class="btn btn-stop-pro <%= (e.getAvailable()>0?"active":"") %>"  onclick="showModal()">Khóa tài khoản</div>
                            <div class="btn btn-stop-pro <%= (e.getAvailable()==0?"active":"") %>" onclick="showModalUnlock()">Kích hoạt tài khoản</div>
                            <div class="btn btn-resale-pro <%= (e.getAvailable()<0?"active":"") %>" onclick="showModalUnlock()">Mở khóa tài khoản</div>


                        </div>
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


                    <div class="form-group w-50">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=e.getId()%>" readonly>
                        <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập họ và tên" value="<%=e.getName() %>">
                        <div class="error" hidden></div>

                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="position">Chức vụ</label>
                        <input type="text" size="10" class="form-control w-80" id="position" name="position" aria-describedby="" placeholder="Nhập chức vụ" value="<%=position %>">
                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="area">Phòng ban: </label>
                        <input type="text" class="form-control w-80" id="area" name="area" aria-describedby="" placeholder="Nhập phòng ban" value="<%=area %>">
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="email">Email</label>
                        <input type="text" size="10" class="form-control w-80" id="email" name="email" aria-describedby="" placeholder="Nhập email" value="<%=e.getEmail() %>">
                        <div class="error" hidden></div>

                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="phone">Số điện thoại: </label>
                        <input type="text" class="form-control w-80" id="phone" name="phone" aria-describedby="" placeholder="Nhập số điện thoại" value="<%=e.getPhone() %>">
                        <div class="error" hidden></div>

                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="branch">Chi nhánh: </label>
                        <select class="form-select w-80" aria-label="Default select example" id="branch" name="branch">
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

                    <div id="role" class="info-container">
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
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="log" name="log" value="log" <%=roles.contains("log")?"checked":""%>>
                                <label class="form-check-label" for="log">
                                    Log
                                </label>
                            </div>
                        </div>

                    </div>

                    <div class="show-flex-row">

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
                            <label  class="w-20"  for="birthday">Ngày sinh: </label>
                            <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Nhập ngày sinh" value="<%=birthday%>">
                            <div class="error" hidden></div>

                        </div>
                    </div>
                    <div class="form-group w-100">
                        <label class="w-20" for="address">Địa chỉ: </label>
                        <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập Địa chỉ" value="<%=e.getAddress() %>">
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-40"  for="datein">Ngày vào làm: </label>
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày vào làm" value="<%= e.getDatein().getDateInMonthDayYearSql()%>">
                        <div class="error" hidden></div>

                    </div>
<%--                    <div class="form-group w-50">--%>
<%--                        <label  class="w-40"  for="imgurl">Ảnh đại diện: </label>--%>
<%--                        <input type="text" class="form-control w-80" id="imgurl" name="imgurl"  aria-describedby="" placeholder="Nhập ảnh đại diện" value="<%= e.getImgurl()%>">--%>
<%--                    </div>--%>
                    <div class="form-group" id="img-container">
                        <label class="w-40" for="myfile">Ảnh đại diện: </label>
                        <input type="file" id="myfile" name="myfile" accept=".jpg, .png" onchange="preview()">
                        <div id="imgs-container">
                            <div class="chosen-img">
                                <i class="fa-solid fa-circle-xmark delete-img-btn" onclick="deleteImg(this)"></i>
                                <img src="./assets/img/products/<%= e.getImgurl()%>" alt="" style="width: 100px" >
                                <input class="img-name" name="imgurl" value="<%= e.getImgurl()%>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="w-20" for="action" class="input-title">action</label>
                        <input type="text" class="form-control" id="action" name="action" aria-describedby="" placeholder="Enter img url" value="<%= (e.getName()==""?"add":"update") %>">
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="w-20" for="status" class="input-title">status</label>
                        <input type="text" class="form-control" id="status" name="status" aria-describedby="" placeholder="Enter img url" value="1">
                    </div>

                    <div class="show-flex-row">
                        <div class="ad_func-container">
                            <div><a class="btn btn-third" href="admin-menu-controller?adminMenu=employee">Hủy</a></div>
                        </div>
                        <div class="ad_func-container">
                            <button class="btn btn-primary" type="submit"><%= (e.getName()==""?"Thêm":"Lưu") %></button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script src="assets/js/addUpdate.js"></script>
<script src="assets/js/toast.js"></script>
<script>

    $(document).ready(function() {
        // showSuccessToast("thanh cong tai");
        document.querySelector("#employeeInfoForm").addEventListener('submit', function (event){
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
                switchMessage("#name",'.error',1,"Không được để trống mục này");
                isOk = false;
            } else {
                switchMessage("#name",'.error',0);

            }

            if(email.trim() === "") {
                switchMessage("#email",'.error',1,"Không được để trống mục này");
                isOk = false;
            } else {
                switchMessage("#email",'.error',0);
                var regex= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                if(regex.test(email)) {
                    switchMessage("#email",'.error',0);
                } else {
                    switchMessage("#email",'.error',1,"Email không hợp lệ");
                    isOk=false;
                }
            }

            if(phone.trim() ===""){
                switchMessage("#phone",'.error',1,"Không được để trống mục này");
                isOk = false;
            } else {
                switchMessage("#phone",'.error',0);
                if(phone.length > 11) {
                    switchMessage("#phone",'.error',1,"Số điện thoại không hợp lệ");
                    isOk=false;

                } else {
                    var test;
                    for(var i = 0; i<phone.length; i++) {
                        test = parseInt(phone.charAt(i));
                        if(isNaN(test)) {
                            switchMessage("#phone",'.error',1,"Số điện thoại không hợp lệ");
                            isOk=false;
                            break;
                        }
                    }
                }
            }

            if(address.trim() === "") {
                address = "";
            }

            var birth = new Date(birthday);

            var year = birth.getFullYear();
            var currentYear = new Date().getFullYear();
            var curretnMonth = new Date().getMonth()+1;
            var currentDay = new Date().getDate();

            if((currentYear - year) < 18) {
                switchMessage("#birthday",'.error',1,"Chưa đủ 18 tuổi");
                isOk = false;
            } else {
                switchMessage("#birthday",'.error',0);
            }

            var date_in = new Date(datein);
            var year = date_in.getFullYear();
            var month = date_in.getMonth()+1;
            var day = date_in.getDate();

            if((date_in <= new Date())){
                switchMessage("#datein",'.error',0);

            } else {
                switchMessage("#datein",'.error',1,"Ngày vào làm không hợp lệ");
                isOk = false;
            }


            if(isOk=== false) {
                return;
            }

            var id = formData.get("id");
            var sex = formData.get("sex");
            var action = formData.get("action");
            var position = formData.get("position");
            var area = formData.get("area");
            var branch = formData.get("branch");
            var employee = formData.get("employee");
            var customer = formData.get("customer");
            var order = formData.get("order");
            var dashboard = formData.get("dashboard");
            var log = formData.get("log");
            var imgurl = formData.get("imgurl");


            executeEmployee(id,name, email, phone, address, birthday, datein, action, sex,
                position, area,branch,employee,customer,order,dashboard,log,imgurl);

        });


        function executeEmployee(id, name, email, phone, address, birthday, datein, action, sex,position, area,branch,employee,customer,order,dashboard,log,imgurl) {
            $.ajax({
                url: "/LTWebFS/employee",
                method: "POST",
                data: { id: id, name: name, email: email, phone: phone, address: address, birthday: birthday, datein: datein, action: action, sex: sex,position: position, area:area,branch:branch,employee:employee,customer:customer,order:order,dashboard:dashboard,log:log,imgurl:imgurl },
                success: function(data) {
                    $("#employeeInfoForm").html(data);
                    if(action==="add") {
                        let url = window.location.href;
                        url = url.slice(0,url.indexOf('?'));
                        url += "?action=prepareUpdate&&id=" + id;
                        window.history.pushState('string','',url);
                    }

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

        function switchMessage(parentSelector,selector, status, msg) {
            var e = document.querySelector(parentSelector).parentElement.querySelector(selector);
            if(status === 1) {
                e.hidden = false;
                e.innerText = msg;


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
            url: urlin, // Đường dẫn đến Servlet
            method: "POST",
            // data: { action: action, id : id },
            success: function(data) {
                $("#employeeInfoForm").html(data);
            }
        });
    }


//     thao tac hinh anh
        let myfile = document.getElementById("myfile");
        let imageContainer = document.getElementById("imgs-container");
        // let numOfFiles = document.getElementById("num-of-files");

        function preview() {
        let html = "";
        <%--numOfFiles.textContent = `${fileInput.files.length} Files Selected`;--%>
        for (let i of myfile.files) {
        let reader = new FileReader();
        reader.onload = () => {
        console.log("reader successful");
        html = ` <div class="chosen-img">
                                                <i class="fa-solid fa-circle-xmark" onclick="deleteImg(this)"></i>
                                                <img src="` + reader.result + `" alt="" style="width: 100px" >
                                                <input class="img-name" name="img-name" value="` + i.name + `" readonly>
                                            </div>`;
        imageContainer.innerHTML += html;

    }
        reader.readAsDataURL(i);
    }
    }



        function deleteImg(e) {
        if(document.querySelectorAll(".chosen-img").length==1) return;
        e.parentNode.remove();
    }







</script>
</body>
</html>
