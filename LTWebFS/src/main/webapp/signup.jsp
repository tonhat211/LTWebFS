<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="assets/css/signup.css">
    <link rel="stylesheet" href="assets/css/adminN.css">
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <style>
        .password-container {
            position: relative;
        }

        .password-container .form-control {
            padding-right: 40px; /* Đảm bảo đủ chỗ cho icon */
        }

        .password-container .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            z-index: 2;
        }
    </style>
</head>
<body>
<%
    String status = (String) request.getAttribute("status");
    if(status==null){
        status ="";
    }

    User utemp = (User) request.getAttribute("userTemp");

%>

<div class="ad-content mt10">
    <div class="form-container">
        <div class="title"><h1>Đăng ký</h1></div>
        <form action="signup" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Họ và tên</label>
                <input type="text" class="form-control" id="name" name="name" required value="<%= utemp != null ? utemp.getName() : "" %>" placeholder="Nhập họ và tên">
<%--                <div class="error" <%=status != null ? "" : "hidden"%>> <%=status%></div>--%>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="number" class="form-control" id="phone" name="phone" required value="<%= utemp != null ? utemp.getPhone() : "" %>" placeholder="Nhập số điện thoại">
<%--                <div class="error" <%=status != null ? "" : "hidden"%>> <%=status%></div>--%>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required value="<%= utemp != null ? utemp.getEmail() : "" %>" placeholder="Nhập email">
                <div class="error" <%=status.equalsIgnoreCase("existeduser") ? "" : "hidden"%>>Email này đã được dùng để đăng ký trước đó</div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <div class="password-container">
                <input type="password" class="form-control" id="password" name="password" required placeholder="Nhập mật khẩu">
                <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>
                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>

            </div>
            <div class="mb-3">
                <label for="repassword" class="form-label">Nhập lại mật khẩu</label>
                <div class="password-container">
                <input type="password" class="form-control" id="repassword" name="repassword" required placeholder="Nhập lại mật khẩu">
                <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>
                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>

            </div>
            <div class="mb-3">
                <div id="sex" class="info-container">
                    <div class="info-container__title">
                        <label for="sex">Giới tính
                        </label>
                    </div>

                    <%
                        String sex = "Nam";
                        String birthday="";
                    if(utemp!= null) {
                        String info = utemp.getInfo();

                        if(info!=null){
                            String infoTokens[] = info.split("=");

                            if(infoTokens.length <2){
                                sex = "Nam";
                                birthday="";
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
                    }



                    %>
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
            </div>
            <div class="mb-3">
                <div class="form-group">
                    <label  class="w-20"  for="birthday">Ngày sinh: </label>
                    <input type="date" class="form-control w-80" id="birthday" name="birthday"  aria-describedby="" placeholder="Nhập ngày sinh" required value="<%=birthday%>" >
                </div>

            </div>
            <div class="mb-3">
                <div class="form-group">
                    <label class="w-20" for="address">Địa chỉ: </label>
                    <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập địa chỉ" required value="<%= utemp != null ? utemp.getAddress() : "" %>">
                </div>

            </div>

            <div style="display: flex; justify-content: center">
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </div>
            <div style="margin-top: 40px; text-align: center">
                <a href="login.jsp">Đã có tài khoản? Đăng nhập</a>
            </div>

        </form>
    </div>

</div>

<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const togglePasswordElements = document.querySelectorAll('.toggle-password');

        togglePasswordElements.forEach(togglePassword => {
            togglePassword.addEventListener('click', function (e) {
                const passwordInput = this.previousElementSibling;
                const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
                passwordInput.setAttribute('type', type);

                this.classList.toggle('fa-eye-slash');
                this.classList.toggle('fa-eye');
            });
        });
    });
</script>

</body>
</html>
