<%@page import="java.util.List"%>
<%@page import="entity.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Users"%>
<%@page import="dao.DAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="./assets/common/taglib.jsp"%>



<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>FastFood is the best choice for you</title>

<!-- 
    - favicon
  -->
<link rel="shortcut icon" href="./favicon.svg" type="image/svg+xml">

<!-- 
    - custom css link
  -->
<link rel="stylesheet" href="./assets/css/style.css">

<!-- 
    - google font link
  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&family=Rubik:wght@400;500;600;700&family=Shadows+Into+Light&display=swap"
	rel="stylesheet">



<!-- 
    - bootstrap link
  -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

<style>
.header.active {
	top: -145px !important;
}

.title {
	margin-bottom: 5vh;
}

.card {
	margin: 160px auto 0px auto;
	max-width: 1110px;
	width: 90%;
	box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	border-radius: 1rem;
	border: transparent;
}

@media ( max-width :767px) {
	.card {
		margin: 3vh auto;
	}
}

.cart {
	background-color: #fff;
	padding: 4vh 5vh;
	border-bottom-left-radius: 1rem;
	border-top-left-radius: 1rem;
}

@media ( max-width :767px) {
	.cart {
		padding: 4vh;
		border-bottom-left-radius: unset;
		border-top-right-radius: 1rem;
	}
}

.summary {
	background-color: #ddd;
	border-top-right-radius: 1rem;
	border-bottom-right-radius: 1rem;
	padding: 4vh;
	color: rgb(65, 65, 65);
}

@media ( max-width :767px) {
	.summary {
		border-top-right-radius: unset;
		border-bottom-left-radius: 1rem;
	}
}

.summary .col-2 {
	padding: 0;
}

.summary .col-10 {
	padding: 0;
}

.row {
	margin: 0;
}

.title b {
	font-size: 1.5rem;
}

.main {
	margin: 0;
	padding: 2vh 0;
	width: 100%;
}

.col-2, .col {
	padding: 0 1vh;
}

a {
	padding: 0 1vh;
}

.close {
	margin-left: auto;
	font-size: 0.7rem;
}

.back-to-shop {
	margin-top: 4.5rem;
}

h5 {
	margin-top: 4vh;
}

hr {
	margin-top: 1.25rem;
}

form {
	padding: 2vh 0;
}

select {
	border: 1px solid rgba(0, 0, 0, 0.137);
	padding: 1.5vh 1vh;
	margin-bottom: 4vh;
	outline: none;
	width: 100%;
	background-color: rgb(247, 247, 247);
}

input {
	border: 1px solid rgba(0, 0, 0, 0.137);
	padding: 1vh;
	margin-bottom: 4vh;
	outline: none;
	width: 100%;
	background-color: rgb(247, 247, 247);
}

input:focus::-webkit-input-placeholder {
	color: transparent;
}

.btn {
	background-color: #000;
	border-color: #000;
	color: white;
	width: 100%;
	font-size: 0.7rem;
	margin-top: 4vh;
	padding: 1vh;
	border-radius: 0;
}

.btn:focus {
	box-shadow: none;
	outline: none;
	box-shadow: none;
	color: white;
	-webkit-box-shadow: none;
	-webkit-user-select: none;
	transition: none;
}

.btn:hover {
	color: white;
}

a {
	color: black;
}

a:hover {
	color: black;
	text-decoration: none;
}

th, td {
	text-align: center;
}

.form-inline {
	display: flex;
	align-items: center; /* Căn giữa theo chiều dọc */
}

.form-group {
	display: flex;
	align-items: center; /* Căn giữa theo chiều dọc */
}

.btn-incre {
	font-size: 12px;
	margin: 0 5px; /* Khoảng cách giữa icon và input */
}

/* Căn giữa theo chiều ngang */
.form-control {
	margin: 0;
	text-align: center;
}

.text-muted {
	margin-left: 25px;
	color: black !important;
}

.table td, .table th {
	vertical-align: middle !important;
}

.img-product {
	width: 100px;
	height: 100px; # code { background-image : linear-gradient( to left,
	rgba( 255, 255, 255, 0.253), rgba( 255, 255, 255, 0.185)),
	url("https://img.icons8.com/small/16/000000/long-arrow-right.png");
	background-repeat: no-repeat;
	background-position-x: 95%;
	background-position-y: center;
}
</style>

</head>

<body id="top">



	<%@ include file="header.jsp"%>

	<div class="card">
		<div class="row">
			<div class="col-md-8 cart">
				<div class="title">
					<div class="row">
						<div class="col">
							<h4>
								<b>Giỏ hàng</b>
							</h4>
						</div>

					</div>
				</div>

				<table class="table table-loght">

					<thead>

						<tr>
							<th scope="col"></th>
							<th scope="col">Tên sản phẩm</th>
							<th scope="col">Giá tiền</th>
							<th scope="col">Số lượng</th>
							<th scope="col">Hủy bỏ</th>
						</tr>
					</thead>

					<c:forEach items="${cart}" var="item">

						<tbody>

							<td>
								<div class="col-2">
									<img class="img-product" src="${item.image}"
										alt="Product Image">
								</div>
							</td>

							<td>
								<div class="row text-muted">${item.name}</div>

							</td>


							<td><fmt:formatNumber value=" ${item.price}" type="currency"
									currencyCode="VND" /></td>

							<td>
								<!-- Số lượng -->
								<form action="updateCart" method="post" class="form-inline">

									<input type="hidden" name="codeProduct"
										value="${item.codeProduct}" class="form-input">

									<div class="form-group d-flex justify-content-between">

										<a class=" btn-sm btn-incre"
											href="quantityIncDec?action=inc&codeProduct=${item.codeProduct}">
											<i class="fa fa-plus-square-o" aria-hidden="true"></i>
										</a> <input type="text" name="quantity" class="form-control"
											value="${item.quantity}" readonly="readonly"> <a
											class=" btn-sm btn-incre"
											href="quantityIncDec?action=dec&codeProduct=${item.codeProduct}">
											<i class="fa fa-minus-square-o" aria-hidden="true"></i>
										</a>

									</div>
								</form>

							</td>

							<!-- hủy mua -->

							<td><a class="btn-sm btn-danger"
								href="removeFromCart?codeProduct=${item.codeProduct}"
								style="text-items: center; padding: 0.375rem 0.75rem; font-size: 1.2rem; border-radius: 0.25rem; height: calc(1.5em + 0.75rem + 2px); color: white; font-family: Rubik, sans-serif; margin-left: 1.2rem;">
									Xóa </a></td>

						</tbody>

					</c:forEach>


				</table>

				<div class="back-to-shop">
					<a href="<c:url value='/home'/>">&leftarrow;<span class="text-muted">Quay
							lại trang chủ</span>
					</a>
				</div>
			</div>

			<!-- check out -->
			<div class="col-md-4 summary">
				<div>
					<h5>
						<b>Tổng</b>
					</h5>
				</div>
				<hr>
				<div class="row">
					<div class="col" style="padding-left: 0;">Số sản phẩm:</div>
					<div class="col text-right">${cartSize}</div>
				</div>

				<div class="row"
					style="border-top: 1px solid rgba(0, 0, 0, .1); padding: 2vh 0;">
					<div class="col">Tổng giá:</div>
					<div class="col text-right">
						<fmt:formatNumber value="${cartTotal == null ? 0 : cartTotal}"
							type="currency" currencyCode="VND" />
					</div>
				</div>

				<button class="btn" onclick="handlePayment()">THANH TOÁN</button>

			</div>
		</div>


	</div>



	<%@ include file="footer.jsp"%>

	<script>
		function handlePayment() {
			// Kiểm tra xem giỏ hàng có sản phẩm hay không
			<c:if test="${not empty cart}">
			// Nếu có sản phẩm, kiểm tra xem người dùng đã đăng nhập hay chưa
			<c:if test="${empty sessionScope.userName}">
			// Nếu chưa đăng nhập, chuyển hướng đến trang login.jsp
			window.location.href = '<c:url value="/login.jsp"/>';
			</c:if>

			<c:if test="${not empty sessionScope.userName}">
			// Nếu đã đăng nhập, chuyển hướng đến trang checkout.jsp
			window.location.href = '<c:url value="/checkout.jsp"/>';
			</c:if>
			</c:if>

			<c:if test="${empty cart}">
			// Nếu giỏ hàng trống, hiển thị thông báo lỗi
			alert("Giỏ hàng của bạn đang trống. Vui lòng thêm sản phẩm vào giỏ hàng trước khi thanh toán.");
			</c:if>
		}
	</script>

	<!-- 
    - #BACK TO TOP
  -->
	<a href="#top" class="back-top-btn" aria-label="Back to top"
		data-back-top-btn> <ion-icon name="chevron-up"></ion-icon>
	</a>





	<!-- 
    - custom js link
  -->
	<script src="./assets/js/script.js" defer></script>


	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>



</body>
</html>