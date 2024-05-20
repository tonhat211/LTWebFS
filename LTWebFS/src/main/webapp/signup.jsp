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
                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>

                <input type="password" class="form-control" id="password" name="password"  placeholder="Nhập mật khẩu">
<%--                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>--%>
                <div class="error" hidden></div>
            </div>
            <div class="mb-3">
                <label for="repassword" class="form-label">Nhập lại mật khẩu</label>
                <div class="password-container">
                <input type="password" class="form-control" id="repassword" name="repassword" required placeholder="Nhập lại mật khẩu">
                <i class="fa-solid fa-eye-slash toggle-password"></i>
                </div>
                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>

                <input type="password" class="form-control" id="repassword" name="repassword"  placeholder="Nhập lại mật khẩu">
<%--                <div class="error"<%=status.equalsIgnoreCase("differentpwd") ? "" : "hidden"%> >Mật khẩu không trùng khớp</div>--%>
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


<script>

    $(document).ready(function() {
        // showSuccessToast("thanh cong tai");
        document.querySelector("#myForm").addEventListener('submit', function (event){
            event.preventDefault();
            var formData = new FormData(this);
            var name = formData.get("name");
            var email = formData.get("email");
            var phone = formData.get("phone");
            var password = formData.get("password");
            var repassword = formData.get("repassword");
            var birthday = formData.get("birthday");

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

            if(password.trim() === "") {
                switchMessage("#password",'.error',1,"Không được để trống mục này");
                isOk = false;
            } else {
                switchMessage("#password",'.error',0);

            }

            if(repassword.trim() === "") {
                switchMessage("#repassword",'.error',1,"Không được để trống mục này");
                isOk = false;
            } else {
                switchMessage("#repassword",'.error',0);

            }

            if(password.trim() !== "" && repassword.trim() !== "") {
                switchMessage("#password",'.error',0);
                switchMessage("#repassword",'.error',0);
                if(password !== repassword) {
                    switchMessage("#password",'.error',1,"Mật khẩu không trùng khớp");
                    switchMessage("#repassword",'.error',1,"Mật khẩu không trùng khớp");
                    isOk = false;

                } else {
                    if(!password.trim().length >= 8 || !password.match(/[a-z]/) || !password.match(/[A-Z]/)
                                || !password.match(/\d/))  {
                        switchMessage("#password",'.error',1,"Mật khẩu phải trên 7 kí tự, bao gồm chữ hoa, chữ thường và số");
                        isOk = false;
                    }
                }

            }

            console.log(password);

            var birth = new Date(birthday);
            var year = birth.getFullYear();
            var currentYear = new Date().getFullYear();
            var curretnMonth = new Date().getMonth()+1;
            var currentDay = new Date().getDate();

            console.log(year);
            if((currentYear - year) < 16) {
                switchMessage("#birthday",'.error',1,"Chưa đủ 16 tuổi");
                isOk = false;
            } else {
                switchMessage("#birthday",'.error',0);
            }


            if(isOk=== false) {
                return;
            }

            // var id = formData.get("id");
            var sex = formData.get("sex");
            var address = formData.get("address");

            document.querySelector("#myForm").submit();


            // signup(name, email, password, repassword, phone, address, sex, birthday);

        });


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
    });











</script>
</body>
</html>
