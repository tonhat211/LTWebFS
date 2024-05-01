<%@ page import="database.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Thêm/Cập nhật khách hàng</title>
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

<%
    if(status!=null)
        if(status.equalsIgnoreCase("updateSuccessful")) {
%>
<script>
    alert("Đã cập nhật thành công thông tin của nhân viên ID " + <%=e.getId()%>);
</script>
<%

        }
%>



<div class="ad-content mt10">
    <div class="modal confirm-stop">
        <div class="modal__overlay">
            <div class="modal__confirm-content" onclick="event.stopPropagation()">
                <div class="confirm__message">
                    <p>Bạn chắc chắn muốn khóa tài khoản này?</p>
                </div>
                <div class="show-flex-row">
                    <a class="btn btn-primary confirm-btn yes-confirm" href="addUpdate-employee?action=lock&id=<%=e.getId()%>">Khóa</a>
                    <div class="btn btn-third confirm-btn no-confirm">Hủy</div>
                </div>
            </div>
        </div>
    </div>
    <div class="ad-content-item">

        <div class="ad_container">
            <a href="goto-employee-admin" class="backto-AdminProduct">Quay lại trang quản lí nhân viên</a>

            <div class="form-container">
                <form action="addUpdate-employee" method="get" id="myForm">
                    <div class="show-flex-row">
                        <h4><%= (e.getName()==""?"Thêm nhân viên mới":"Cập nhật thông tin nhân viên") %></h4>

                    </div>
                    <div class="show-flex-row">
                        <div class="grid__row img-showing">
                            <div class="disabled-showing <%= (e.getAvailable()==0?"active":"") %>" style="<%= (e.getName()==""?"position: relative;  left: 0":"") %>" >
                                <div class="disabled-showing-content">
                                    <%= (e.getName()==""?"CHƯA KÍCH HOẠT":"ĐÃ KHÓA") %>
                                </div>
                            </div>
                        </div>
                        <div class="stop_reSale <%= (e.getName()==""?"hide":"") %>" >
                            <div class="btn btn-stop-pro <%= (e.getAvailable()==1?"active":"") %>">Khóa tài khoản</div>
                            <div class="btn btn-resale-pro <%= (e.getAvailable()==0?"active":"") %>"><a class="no-a"
                                    href="addUpdate-employee?action=unlock&id=<%=e.getId()%>">Mở khóa tài khoản</a></div>

                        </div>
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


                    <div class="form-group w-50">
                        <label class="w-20" for="id">ID</label>
                        <input type="text" size="10" class="form-control w-80" id="id" name="id" aria-describedby="emailHelp" placeholder="ID" value="<%=e.getId()%>" readonly>
                        <!--                       <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>


                    <div class="form-group w-80">
                        <label class="w-20" for="name">Họ và tên: </label>
                        <input type="text" class="form-control w-80" id="name" name="name" aria-describedby="" placeholder="Nhập họ và tên" value="<%=e.getName() %>">
                        <!--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
                    </div>
                    <div class="form-group w-80">
                        <label class="w-20" for="position">Chức vụ</label>
                        <input type="text" size="10" class="form-control w-80" id="position" name="position" aria-describedby="" placeholder="Nhập chức vụ" value="<%=e.getPosition() %>">
                    </div>
                    <div class="form-group w-80">
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
                    <div class="form-group w-80">
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
                        <input type="date" class="form-control w-80" id="datein" name="datein"  aria-describedby="" placeholder="Nhập ngày vào làm" value="<%= e.getDatein().getDateInMonthDayYearSql()%>">
                    </div>
                    <div class="form-group w-50">
                        <label  class="w-20"  for="imgurl">Ảnh đại diện: </label>
                        <input type="text" class="form-control w-80" id="imgurl" name="imgurl"  aria-describedby="" placeholder="Nhập ảnh đại diện" value="<%= e.getImgurl()%>">
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
                            <div><a class="btn btn-third" href="goto-employee-admin">Hủy</a></div>
                        </div>
                        <div class="ad_func-container">
                            <button class="btn btn-primary" type="submit"><%= (e.getName()==""?"Thêm":"Lưu") %></button>
                        </div>
                    </div>
                    <!--                    <button type="submit" class="btn btn-primary">Submit</button>-->
                </form>

            </div>
        </div>
    </div>
</div>
<script src="assets/js/addUpdate.js"></script>
</body>
</html>
