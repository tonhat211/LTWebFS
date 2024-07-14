<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="database.CommentDAO" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: TO NHAT
  Date: 04/12/2023
  Time: 3:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./assets/common/taglib.jsp"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!--fontawesome-->
  <link rel="stylesheet" href="./assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

  <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">

  <!--    google font-->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

  <!--    css-->

  <!--    header- footer-->
  <link rel="stylesheet" href="assets/css/header_footer.css">
  <link rel="shortcut icon" href="assets/img/Logo/favicon_icon.png" type="image/x-icon">
  <link rel="stylesheet" href="assets/font/themify-icons/themify-icons.css">

  <!--    content -->
  <link rel="stylesheet" href="assets/css/baseN.css">
  <link rel="stylesheet" href="assets/css/slidebar.css">
  <link rel="stylesheet" href="assets/css/productDetail.css">
  <link rel="stylesheet" href="assets/css/toast.css">



  <title>Chi tiết sản phẩm</title>

  <style>
    .comment-section {
      margin-top: 20px;
      margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 15px;
    }
    .comment-form {
        margin: 20px auto;
        padding: 8px;

    }

    .form-group {
        margin-bottom: 15px;
    }

    label {
        font-weight: bold;
        margin-bottom: 5px;
        display: block;
    }

    /*input[type="text"],*/
    textarea {
        width: 1084px;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ddd;
        border-radius: 4px;
        margin-top: 4px;
    }

    textarea {
        resize: vertical;
    }

    .send-comment-btn {
        background-color: #1877f2;
        color: #fff;
        border: none;
        padding: 10px 20px;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
    }

    .send-comment-btn:hover {
        background-color: #0e5a9f;
    }

    .send-comment-btn:focus {
        outline: none;
    }
    .existing-comments {
        margin-top: 20px;
    }

    .comment-item {
        margin-bottom: 20px;
    }

    .comment-item p {
        margin: 5px 0;
    }

    .comment-item strong {
        font-weight: 600;
        color: #385898;
        font-size: 16px;
    }

    .comment-item p:nth-child(2) {
        margin-top: 8px;
        font-size: 16px;
        line-height: 1.4;
        color: #333;
    }

  </style>

</head>
<body>
<script>
  function gotoLogin() {
    var login = confirm("Đăng nhập để sử dung giỏ hàng.");
    if (login) {
      window.location.href = "${pageContext.request.contextPath}/login.jsp";
    }
  }
</script>
<div>
  <%@ include file="header.jsp" %>


  <%
    ProductUnit pu = (ProductUnit) request.getAttribute("productDetail");
    String status = (String) request.getAttribute("status");
    User u = (User) session.getAttribute("userloging");
  %>

  <%
    if (status != null) {
      if(status.equalsIgnoreCase("addToCartSuccessful")){

  %>
  <script>
    alert("Da them thanh cong san pham vao gio hang");
  </script>
  <%
      }
    }
  %>
  <div class="product__container">
    <div id="toast">

    </div>
    <div class="product-item">
      <div class="prd__slidebar">

        <div class="product-img-list">
          <%
            ArrayList<String>  urls = pu.getImgrls();
            if(urls == null) {
              urls = new ArrayList<>();
              urls.add(pu.getImg());
            }
            for(int i  =  0;i< urls.size();i++){
          %>
                <div class="prd__sliderbar-item <%=  i==0 ? "active"  : ""%> ">
                  <img src="<%=urls.get(i)%>" alt="">
                </div>

          <%
            }
          %>

        </div>
        <div class="prd__slidebar-indicator">
          <ul class="prd__slidebar-indicator-list">
            <%

              for(int i  =  0;i< urls.size();i++){
            %>
            <li class="prd__slidebar-indicator-item <%=  i==0 ? "active"  : ""%>"><i class="fa-solid fa-circle"></i></li>

            <%
              }
            %>
          </ul>


        </div>
        <div class="move-left">
          <i class="fa-solid fa-circle-chevron-left"></i>
        </div>
        <div class="move-right">
          <i class="fa-solid fa-circle-chevron-right"></i>
        </div>
      </div>

      <div class="product-info">
        <div class="product-name">
          <p> <%= pu.getName()%> </p>
        </div>
        <div class="product-price">
          <p class="product-price">
            <fmt:formatNumber value="<%= pu.getPrice()%>" pattern="#,##0.00"/>
            VND
          </p>
        </div>
        <div class="product-info-buying">
          <div class="product-info-short">
            <p>Xuất xứ: <span class="product-country"><%= pu.getMadeIn()%></span></p>
            <p>Hãng sản xuất: <span class="product-warranty"><%= pu.getBrand()%></span></p>
            <p>Năm sản xuất: <span class="product-warranty"><%= pu.getYearMade()%></span></p>
<%--            <p>Phân loại: <span class="product-warranty"><%= pu.get()%></span></p>--%>
            <p>Tình trạng: <span class="product-available"><%=pu.getAmount()>0 ? "Còn hàng" : "Hết hàng"%></span></p>
          </div>

          <%--          test them san pham vao gio hang bang servlet--%>
          <form class="product-qty-buying" method="post" action="add-to-cart" id="addToCartForm">
            <div class="product-qty-control">

              <i class="fa-solid fa-plus qty-control btn-plus-qty"></i>
              <input type="text" class="product-qty-input" name="qty" value="1" maxlength="2" size="2">
              <i class="fa-solid fa-minus qty-control btn-minus-qty"></i>

                <input type="text" hidden class="product-qty-input" name="productID" value="<%=pu.getId()%>" maxlength="2" size="2">

            </div>
            <button type="submit" class="add-to-cart-btn"><p>Thêm vào giỏ hàng</p></button>

          </form>


<%--          test them san pham vao gio hang bang ajax--%>
<%--          <div class="product-qty-buying" method="post" action="add-to-cart">--%>
<%--            <div class="product-qty-control">--%>

<%--              <i class="fa-solid fa-plus qty-control btn-plus-qty"></i>--%>
<%--              <input type="text" class="product-qty-input qtyClass" name="qty" value="1" maxlength="2" size="2">--%>
<%--              <i class="fa-solid fa-minus qty-control btn-minus-qty"></i>--%>

<%--              <input type="text" hidden class="product-productID-input productIDClass" name="productID" value="<%=pu.getId()%>" maxlength="2" size="2">--%>

<%--            </div>--%>
<%--            <div class="add-to-cart-btn"><p>Thêm vào giỏ hàng</p></div>--%>

<%--          </div>--%>


        </div>
        <!--                <div class="asking">-->
        <!--                    <input type="text" class="asking-input client-phone" maxlength="11">-->
        <!--                    <div class="asking-btn"><p>Nhan tu van</p></div>-->
        <!--                </div>-->
      </div>
    </div>
    <div class="seperate-horizontal">
      <div class="seperate-horizontal-item"></div>
    </div>
    <div class="product-detail">
      <h2>Mô tả</h2>
      <div class="product-detail-content">
        <p><%= pu.getName()%><br/>
            <%= pu.getDes()%>
      </div>
    </div>
    <!--        <div class="product-evaluate">-->

    <!--        </div>-->

    <!-- Comment Section -->
      <div class="comment-section">
          <h2>Bình luận</h2>

          <% if (user != null) { %>
          <div class="comment-form">
              <form id="comment-form" method="post" action="add-comment">
                  <div class="form-group">
                      <label class="username"><%= user.getName() %></label>
                  </div>
                  <div class="form-group">
                      <textarea id="comment" name="comment" rows="4" placeholder="Viết bình luận của bạn..." required></textarea>
                  </div>
                  <input type="hidden" name="productId" value="<%= pu.getId() %>">
                  <input type="hidden" name="userId" value="<%= user.getId() %>">
                  <input type="hidden" name="userName" value="<%= user.getName() %>">
                  <div class="form-group">
                      <button class="send-comment-btn" type="submit">Gửi</button>
                  </div>
              </form>
          </div>
          <% } else { %>
          <p>Vui lòng <a href="login.jsp">đăng nhập</a> để có thể bình luận.</p>
          <% } %>

          <div class="existing-comments">
              <%
                  // Lấy danh sách bình luận từ CommentDAO
                  List<Comment> comments = (List<Comment>) request.getAttribute("comments");

                  // Kiểm tra nếu danh sách bình luận không rỗng
                  if (!comments.isEmpty()) {
                      for (Comment comment : comments) {
              %>
              <div class="comment-item">
                  <p><strong><%= comment.getUsername() %></strong> - <%= comment.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %></p>
                  <p><%= comment.getContent() %></p>
              </div>
              <%
                  }
              } else {
              %>
              <p>Chưa có bình luận nào cho sản phẩm này.</p>
              <% } %>
          </div>

          <div class="fb-comments"
               data-href="productDetail.jsp?id=<%= pu.getId() %>" data-width="1100" data-numposts="5"></div>
      </div>

    </div>
  </div>

</div>
  <%@ include file="footer.jsp" %>
</div>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v20.0&appId=1443587996285926" nonce="nCjAkXvC"></script>

<script type="module"  src="assets/js/productDetail.js"></script>
<script src="assets/js/toast.js"></script>

<script>

//   ajax jquery

  $(document).ready(function () {
    document.querySelector('#addToCartForm').addEventListener('submit',function (event){
      event.preventDefault();

      var data = new FormData(this);
      console.log(data);
      var productID = data.get("productID");
      var qty = data.get("qty");
      addToCart(productID,qty);
    });

    function addToCart(productID, qty) {

      $.ajax({
        url: "/LTWebFS/add-to-cart",
        method: "POST",
        data: {productID: productID, qty: qty},
        success: function (data) {
          $('#toast').html(data);
        }
      });
    }

    document.querySelector("#comment-form").addEventListener("submit", function (event){
        event.preventDefault();
        var data = new FormData(this);
        console.log(data);
        var comment = data.get("comment");
        var productId = data.get("productId");
        var userId = data.get("userId");
        var userName = data.get("userName");
        // var qty = data.get("qty");
        addComment(comment, productId,userId,userName);
    });

      function addComment(comment, productId,userId,userName) {

          $.ajax({
              url: "/LTWebFS/add-comment",
              method: "POST",
              data: {comment: comment, productId: productId,userId:userId,userName:userName},
              success: function (data) {
                  var html = document.querySelector('.existing-comments').innerHTML;
                  document.querySelector("#comment").value="";
                  var newContent = data + html;
                  $('.existing-comments').html(newContent);
              }
          });
      }



  });

  // $.ajax({
  //   url,
  //   method
  //   data: {},
  //   success: function (message) {
  //
  //   }
  // });





</script>
</body>
</html>
