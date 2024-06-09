<%@ page import="model.cartitem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--fontawesome-->
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.4.0-web/css/all.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!--    google font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/cart.css">
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="assets/css/baseN.css">

    <link rel="stylesheet" href="./assets/fonts/fontawesome-free-6.4.0-web/css/all.min.css">
</head>
<body>
<%
    ArrayList<cartitem> cart = (ArrayList<cartitem>) request.getAttribute("cart");
//    if(cart == null) {
//        cart = new ArrayList<>();
//    }

    String status = (String) request.getAttribute("status");

%>

<%
    if(status != null) {
        if(status.equalsIgnoreCase("noProductChosen")) {

%>
            <script>
                alert("Chưa có sản phẩm nào được chọn!");
            </script>
<%
        }
        else if(status.equalsIgnoreCase("orderSuccessful")){
            %>
            <script>
                alert("Dat hang thanh cong");
            </script>
            <%

        }
    }
%>

<main id="main" class="main">
    <%@ include file="header.jsp" %>
    <div class="ad_content" style="margin-top: 110px">
        <div class="cart__container">
            <form action="payment" method="post" id="myForm">
                <div class="grid">
                    <div class="grid__row">
                        <div class="grid-col-9">
                            <h2 class="title">Giỏ hàng</h2>
                            <p class="title">Bạn có <%=cart == null ? "0" : cart.size()%> sản phẩm trong giỏ hàng</p>
                            <table class="table">
                                <thead>
                                <tr>

                                    <th scope="col">Chọn  <input class="chooseAll" type="checkbox" name="price" value=""></th>
                                    <th scope="col">Hình ảnh</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Đơn giá</th>
                                    <th scope="col">Số lượng </th>
                                    <th scope="col">Xóa</th>

                                </tr>
                                </thead>
                                <tbody>

                                <%
                                    if(cart != null) {
                                        for(int i=0; i<cart.size();i++) {
                                %>
                                <tr class="<%= i % 2 == 0 ? "roww" : ""%>>" id="<%="item" + cart.get(i).getId() + "=" + cart.get(i).getP().getId()%>">
                                    <th scope="row">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="check" value="<%=cart.get(i).getP().getId()%>">

                                        </div>
                                    </th>
                                    <td style="height: 100px" ><img src="./assets/img/products/<%=cart.get(i).getP().getImg()%>" alt="" style="height: 100%"></td>

                                    <td><%= cart.get(i).getP().getName() %></td>
                                    <td class="price" ><%= cart.get(i).getP().getPrice() %></td>
                                    <td>
                                        <div class="product-qty-control">

                                            <i class="fa-solid fa-plus qty-control btn-plus-qty increaseBtn"></i>
                                            <input type="number" class="idproduct" name="idproduct" hidden value="<%= cart.get(i).getP().getId() %>" maxlength="2" size="2">
                                            <input class="idcart" type="number" hidden name="idcart" value="<%= cart.get(i).getId()%>">

                                            <input type="text" class="product-qty-input qty" name="qty" style="width: 40px;" readonly value="<%= cart.get(i).getQty() %>" maxlength="2" size="2">
                                            <i class="fa-solid fa-minus qty-control btn-minus-qty decreaseBtn" ></i>

                                        </div>
                                    </td>
                                    <td style="text-align: center"><i class="fa-solid fa-x deleteProductBtn"></i></td>
                                </tr>
                                <%
                                        }
                                    } else {

                                        %>
                                <div class="successful" style="font-size: 14px;">Chưa có sản phẩm nào trong giỏ hàng</div>

                                <%

                                        }
                                %>

                                </tbody>
                            </table>
                        </div>
                        <div class="grid-col-3 money">
                            <h2 class="title">Tổng tiền</h2>
                            <!--                            <h2>Tong tien</h2>-->
                            <p style="font-size: 14px"><span class="totalMoney">0</span> (VND) </p>
                            <p style="font-size: 14px">Tổng sản phẩm: <span class="totalProduct">0</span> (sản phẩm) </p>
                            <div class="ad_func-container">
                                <button class="btn btn-primary btn-buy" type="submit">Mua hàng</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>




        </div>
    </div>

    <%@ include file="footer.jsp" %>
</main>




<script>


</script>

<!--
- #BACK TO TOP
-->
<a href="#top" class="back-top-btn" aria-label="Back to top"
   data-back-top-btn> <ion-icon name="chevron-up"></ion-icon>
</a>

<script src="assets/js/cart.js"></script>



</body>
</html>
