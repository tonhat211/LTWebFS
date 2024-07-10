<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/header_footer.css">
    <link rel="stylesheet" href="assets/css/slide.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">
    <title>Trang chủ</title>
    <script src="assets/js/toast.js"></script>
    <link rel="stylesheet" href="assets/css/toast.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>

<div id="main">
    <%@ include file="header.jsp" %>
    <div id="main">

        <div id="slider" style="background-color: #dddddd">

            <div class="slideshow-container">
                <div id="toast">

                </div>
                <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
                    <div id="toast-2" style="margin: auto 0">
                        <%--                <div class="toastt-2 toast-2--success">--%>
                        <%--                    <p class="toast__msg" style="color: white">thanh cong</p>--%>
                        <%--                </div>--%>

                    </div>
                </div>
                <div class="form-group" id="img-container" style="display: none">
                    <label class="w-20" for="myfile">Hình ảnh: </label>
                    <input type="file" id="myfile" name="myfile" accept=".jpg, .png" onchange="preview()" multiple >
                    <div id="imgs-container">
                        <div class="chosen-img">
                            <i class="fa-solid fa-circle-xmark delete-img-btn" onclick="deleteImg(this)"></i>
                            <input class="img-name" name="img-name" value="" readonly>
                        </div>

                    </div>
                </div>
                <script>
                    // de chuyen doi file hinh anh sang base va luu vao database

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
                                function updateImgData(name,data) {
                                    console.log("updating...");
                                    console.log(name);
                                    console.log(data);
                                    $.ajax({
                                        url: "/LTWebFS/execute-image",
                                        method: "post",
                                        data: {name: name, data: data},
                                        success: function(data) {
                                            $("#toast-2").html(data);
                                        }
                                    });
                                }

                                updateImgData(i.name,reader.result);

                            }
                            reader.readAsDataURL(i);
                        }
                    }



                    function deleteImg(e) {
                        if(document.querySelectorAll(".chosen-img").length==1) return;
                        e.parentNode.remove();
                    }



                </script>

                <div class="mySlides fade">
                    <div class="numbertext">1 / 4</div>
                    <img src="assets/img/slide-show/Thiet_bi_y_te_vat_ly_tri_lieu.png"
<%--                    <img src="https://photos.google.com/photo/AF1QipNbrpsDZt3XclVDWB5ei-Sdy7UyZA_MPm1O2UDD"--%>
                         style="width:100%; height: 450px">
                    <div class="text">Thiết bị y tế</div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">2 / 4</div>
                    <img src="assets/img/slide-show/hq720.jpg" style="width:100%; height: 450px;">
                    <div class="text">Tin tức covid 19 hiện nay</div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">3 / 4</div>
                    <img src="assets/img/slide-show/chung-nhan-iso-13485.jpg" style="width:100%; height: 450px">
                    <div class="text">Chứng nhận iso 13485</div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">4 /</div>
                    <img src="assets/img/slide-show/khau-trang.jpg" style="width:100%; height: 450px">
                    <div class="text">Khẩu trang y tế</div>
                </div>

                <a class="prev" onclick="plusSlides(-1)">❮</a>
                <a class="next" onclick="plusSlides(1)">❯</a>

            </div>
            <br>

            <div style="text-align:center">
                <span class="dot" onclick="currentSlide(1)"></span>
                <span class="dot" onclick="currentSlide(2)"></span>
                <span class="dot" onclick="currentSlide(3)"></span>
                <span class="dot" onclick="currentSlide(4)"></span>
            </div>
        </div>
        <div class="JSYDB" style="height: 50px"></div>
        <div id="partners" style="background-color: #dddddd">
            <div class="content">
                <div class="partner-title">
                <span>
                    Đối tác hàng đầu
                </span>
                </div>
                <div class="partner-img">
                    <img src="assets/img/Partner/hoanmy.jpg" alt="Hoàn Mỹ">
                    <img src="assets/img/Partner/metech.png" alt="Metech">
                </div>
            </div>
        </div>
        <div class="JSYDB" style="height: 50px"></div>
        <div id="aboutUs" style="background-color: #dddddd">
            <div class="content">
                <div class="business-title partner-title">
                    <span>Giấy phép kinh doanh</span>
                </div>
                <div class="business-licence partner-img">
                    <img src="assets/img/GPKD/gpkd-1.jpeg" alt="Giấy phép kinh doanh ">
                    <img src="assets/img/GPKD/gpkd-2.jpg" alt="Giấy phép kinh doanh">
                </div>
            </div>
        </div>
        <div class="JSYDB" style="height: 50px"></div>

    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="assets/js/slidebar.js">

</script>
</body>
</html>