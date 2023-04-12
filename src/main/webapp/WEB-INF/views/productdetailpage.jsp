<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
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
	<div class="row p-5">
		<div class="col-3">
			<div class="card">
				<img src="${product.getImage_url()}" class="card-img-top p-3"
					alt="Product Image" />
			</div>
		</div>
		<div class="col-9">
			<h3 class="mb-4">${product.getProductName()}</h3>
			<table>
				<tr>
					<td>Product Code:</td>
					<td>${product.getProductCode()}</td>
				</tr>
				<tr>
					<td>Brand:</td>
					<td>${product.getBrand()}</td>
				</tr>
				<tr>
					<td>Size:</td>
					<td>${product.getSize()}</td>
				</tr>
				<tr>
					<td>Category:</td>
					<td>${product.getProductCategory().getCategoryName()}</td>
				</tr>
				<tr>
					<td>Target Group:</td>
					<td>${product.getProductCategory().getTargetGroup()}</td>
				</tr>
			</table>
			<h5 class="mt-4">&#8377 ${product.getPrice()}</h5>
			<div>
				<a class="button" href="/cart/add?productId=${product.getProductId()}">Add to Cart</a>
			</div>
		</div>
	</div>
</body>
</html>