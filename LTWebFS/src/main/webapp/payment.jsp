<%@ page import="model.cartitem" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.DeCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng</title>

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
    <link rel="stylesheet" href="assets/css/baseN.css">
    <link rel="stylesheet" href="assets/css/payment.css">

    <link rel="stylesheet" href="./assets/fonts/fontawesome-free-6.4.0-web/css/all.min.css">
</head>
<body>
<%
    User  u = (User) session.getAttribute("userloging");
    if(u== null) {
        u= new User();
    }
    ArrayList<cartitem> cartTemp = (ArrayList<cartitem>) request.getAttribute("cartTemp");
//    String json = (String) request.getAttribute("cartTemp");
//    Gson gson = new Gson();
//
//    cartitem[] cartTemp1 = gson.fromJson(json, cartitem[].class);
//    ArrayList<cartitem> cartTemp = new ArrayList<>();
//    for(int i =0; i<cartTemp1.length;i++) {
//        cartTemp.add(cartTemp1[i]);
//    }

    double totalMoney = 0;
    double deliveryFee = 30000;


%>


<main id="main" class="main">
    <%@ include file="header.jsp" %>
    <div class="ad_content">
        <form action="order" method="post">
            <h2>Thanh toán</h2>
            <div class="seperate-horizontal-linear"></div>
            <div class="info-container">
                <h3>Địa chỉ nhận hàng</h3>
                <div class="show-flex-row">
                    <div class="info-item"><span><%=u.getName()%></span><span><%=u.getPhone()%></span><span><%=u.getAddress()%></span></div>
                    <div>Thay đổi</div>
                </div>
            </div>

            <div class="info-container">
                <h3>Sản phẩm</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Số lượng </th>
                        <th scope="col">Thành tiền</th>

                    </tr>
                    </thead>
                    <tbody>

                    <%
                        if(cartTemp !=null) {
                            for(int i = 0; i<cartTemp.size();i++) {
                    %>
                    <tr class="">
                        <th scope="row">
                            <%=i+1%>
                        </th>
                        <td style="height: 100px" ><img src="./assets/img/products/<%=cartTemp.get(i).getP().getImg()%>" alt="" style="height: 100%"></td>

                        <td><%=cartTemp.get(i).getP().getName()%></td>
                        <td><%=cartTemp.get(i).getP().getPrice()%></td>
                        <td>
                            <%=cartTemp.get(i).getQty()%>
                        </td>
                        <td><%=cartTemp.get(i).getP().getPrice() * cartTemp.get(i).getQty()%></td>
                        <input type="text" name="productID" hidden value="<%=cartTemp.get(i).getP().getId()%>">
                        <input type="text" name="priceUnit" hidden value="<%=cartTemp.get(i).getP().getPrice()%>">
                        <input type="text" name="qty" hidden value="<%=cartTemp.get(i).getQty()%>">

                    </tr>

                    <%
                                totalMoney +=cartTemp.get(i).getP().getPrice() * cartTemp.get(i).getQty();
                            }

                        }

                        if(totalMoney > 1000000){
                            deliveryFee=0;
                        }
                    %>


                    </tbody>
                </table>
            </div>

            <div class="info-container">
                <h3>Thanh toán</h3>
                <div class="show-flex-row">
                    <div class="info-item"></div>
                    <div class="payment-container w-30">
                        <div class="payment-content">
                            <p>Phương thức thanh toán</p>
                            <div class="show-flex-row"><span>Thanh toán khi nhận hàng</span><p>Thay đổi</p></div>
                            <div class="show-flex-row"><p class="txt-color">Tổng tiền sản phẩm</p><p><span class="product-money"><%=totalMoney%></span>(vnd)</p></div>
                            <div class="show-flex-row"><p class="txt-color">Phí vận chuyển</p><p><span class="delivery-money"><%=deliveryFee%></span>(vnd)</p></div>
                            <p class="note"> Mua trên <span>1000000</span> VND để được miễn phí vận chuyển</p>
                            <div class="show-flex-row"><p class="txt-color">Tổng thanh toán</p><p><span class="total-money"><%=totalMoney + deliveryFee%></span> (VND)</p></div>
                            <input type="text" name="orderMoney" value="<%=totalMoney + deliveryFee%>" hidden>
                            <input type="text" name="deliveryFee" value="<%=deliveryFee%>" hidden>
                            <div class="show-flex-row center"><button class="btn btn-primary btn-buy w-70" style="border:none" type="submit">Đặt hàng</button></div>
                        </div>

                    </div>
                </div>
            </div>
        </form>

    </div>
    <%@ include file="footer.jsp" %>

</main>

<script>


</script>





</body>
</html>
