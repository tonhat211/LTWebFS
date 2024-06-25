<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
        <form action="signup" method="post" id="myForm">
            <div class="mb-3">
                <label for="name" class="form-label">Họ và tên</label>
                <input type="text" class="form-control" id="name" name="name"  value="<%= utemp != null ? utemp.getName() : "" %>" placeholder="Nhập họ và tên">
<%--                <div class="required" <%=status.equalsIgnoreCase("nameRequired") ? "" : "hidden"%>><%=status.equalsIgnoreCase("nameRequired") ? "Không được để trống mục này" : ""%></div>--%>
                <div class="error" hidden></div>

            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" id="phone" name="phone"  value="<%= utemp != null ? utemp.getPhone() : "" %>" placeholder="Nhập số điện thoại">
<%--                <div class="required" <%=status.equalsIgnoreCase("phoneRequired") ? "" : "hidden"%>><%=status.equalsIgnoreCase("phoneRequired") ? "Không được để trống mục này" : ""%></div>--%>
<%--                <div class="error" <%=status.equalsIgnoreCase("phoneIncorrect") ? "" : "hidden"%>></div>--%>
                <div class="error" hidden></div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp"  value="<%= utemp != null ? utemp.getEmail() : "" %>" placeholder="Nhập email">
<%--                <div class="required" <%=status.equalsIgnoreCase("emailRequired") ? "" : "hidden"%>><%=status.equalsIgnoreCase("emailRequired") ? "Không được để trống mục này" : ""%></div>--%>
<%--                <div class="error" <%=status.equalsIgnoreCase("existeduser") ? "" : "hidden"%>>Email này đã được dùng để đăng ký trước đó</div>--%>
                <div class="error" hidden></div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>

                <div class="password-container">
                <input type="password" class="form-control" id="password" name="password" required placeholder="Nhập mật khẩu">
                <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>
               <div class="error" hidden></div>
            </div>
            <div class="mb-3">
                <label for="repassword" class="form-label">Nhập lại mật khẩu</label>
                <div class="password-container">
                <input type="password" class="form-control" id="repassword" name="repassword" required placeholder="Nhập lại mật khẩu">
                <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>

                <div class="error" hidden></div>
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
                    <div class="error" hidden></div>
                </div>

            </div>
            <div class="mb-3">
                <div class="form-group">
                    <label class="w-20" for="address">Địa chỉ: </label>
                    <input type="text" class="form-control w-80" id="address" name="address" aria-describedby="" placeholder="Nhập địa chỉ" required value="<%= utemp != null ? utemp.getAddress() : "" %>">
                    <div class="error" hidden></div>
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
    document.addEventListener('DOMContentLoaded', function() {
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

        document.querySelector("#myForm").addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(this);

            const name = formData.get("name").trim();
            const email = formData.get("email").trim();
            const phone = formData.get("phone").trim();
            const password = formData.get("password").trim();
            const repassword = formData.get("repassword").trim();
            const birthday = formData.get("birthday").trim();

            let isValid = true;

            if (!validateNotEmpty(name, "#name", "Không được để trống mục này")) isValid = false;
            if (!validateEmail(email, "#email")) isValid = false;
            if (!validatePhone(phone, "#phone")) isValid = false;
            if (!validatePassword(password, "#password")) isValid = false;
            if (!validateRepassword(password, repassword, "#repassword")) isValid = false;
            if (!validateBirthday(birthday, "#birthday")) isValid = false;

            if (isValid) {
                signup(name, email, password, repassword, phone, address, sex, birthday);
            }
        });

        function validateNotEmpty(value, selector, message) {
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (value === "") {
                showMessage(errorElement, message);
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }

        function validateEmail(email, selector) {
            const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (!validateNotEmpty(email, selector, "Không được để trống mục này")) return false;
            if (!emailRegex.test(email)) {
                showMessage(errorElement, "Email không hợp lệ");
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }

        function validatePhone(phone, selector) {
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (!validateNotEmpty(phone, selector, "Không được để trống mục này")) return false;
            if (phone.length > 11 || isNaN(phone)) {
                showMessage(errorElement, "Số điện thoại không hợp lệ");
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }

        function validatePassword(password, selector) {
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (!validateNotEmpty(password, selector, "Không được để trống mục này")) return false;
            if (!passwordRegex.test(password)) {
                showMessage(errorElement, "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số");
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }

        function validateRepassword(password, repassword, selector) {
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (!validateNotEmpty(repassword, selector, "Không được để trống mục này")) return false;
            if (password !== repassword) {
                showMessage(errorElement, "Mật khẩu không trùng khớp");
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }


        function validateBirthday(birthday, selector) {
            const element = document.querySelector(selector);
            const errorElement = element.parentElement.querySelector('.error');
            if (!validateNotEmpty(birthday, selector, "Không được để trống mục này")) return false;

            var birth = new Date(birthday);
            var year = birth.getFullYear();
            var currentYear = new Date().getFullYear();

            if ((currentYear - year) < 16) {
                showMessage(errorElement, "Chưa đủ 16 tuổi");
                return false;
            } else {
                hideMessage(errorElement);
                return true;
            }
        }

        function showMessage(element, message) {
            if (element) {
                element.hidden = false;
                element.innerText = message;
            }
        }

        function hideMessage(element) {
            if (element) {
                element.hidden = true;
                element.innerText = '';
            }
        }

        function signup(name, email, password, repassword, phone, address, sex, birthday) {
            $.ajax({
                url: "/LTWebFS/signup",
                method: "POST",
                data: {name: name, email: email, password: password, repassword: repassword, phone: phone, address: address, sex: sex, birthday: birthday},
                success: function(data) {
                    $("#myForm").html(data);
                }
            });
        }
    });
</script>
</body>
</html>
