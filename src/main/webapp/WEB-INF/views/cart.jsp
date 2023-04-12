<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
<style type="text/css">
.button {
	display: block;
	width:fit-content;
	background: rgb(25,135,84);
	padding: 10px;
	text-align: center;
	border-radius: 5px;
	color: white;
	font-weight: bold;
	line-height: 25px;
	text-decoration: none;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<section class="h-100 h-custom">
		<div class="container h-100 py-5">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col">

					<div class="table-responsive">
						<table class="table">
							<c:forEach var="item" items="${orderItems}">
								<c:set var="product" value="${item.getProduct()}"></c:set>
								<tr>
									<td><img src="${product.getImage_url()}" height="64px"
										width="auto" class="card-img-top p-3" alt="Product Image" /></td>
									<td>
										<h5>
											<a href="/product?productId=${product.getProductId()}">
												${product.getProductName()} </a>
										</h5>
									</td>
									<td>
										<h5>Quantity: ${item.getQuantity()}</h5>
									</td>
									<td>
										<h5>&#8377 ${item.getTotalPrice()}</h5>
									</td>
									<td>
										<h5>
											<a href="/cart/remove?productId=${product.getProductId()}">
												Delete </a>
										</h5>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					
					<div class="row">
						<h3>Total Cart Price : &#8377 ${cart.getTotalCartPrice()}</h3>
					</div>
					<div class="row">
						<a class="button col-2" href="#">Proceed to Checkout</a>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>