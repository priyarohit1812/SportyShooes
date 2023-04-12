<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-success"
	style="color: white;">
	<div class="container-fluid">
		<a class="navbar-brand" href="/admin/home" style="color: white;">SportyShoes
			- Admin</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="/admin/home"
					style="color: white;">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="/admin/products"
					style="color: white;">Product</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/admin/productcategories" style="color: white;">Product
						Category</a></li>
				<li class="nav-item"><a class="nav-link" href="/admins"
					style="color: white;">Admin</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/admin/purchaseorders" style="color: white;">Purchase
						Order</a></li>
			</ul>
			
			<label>
				Welcome! ${admin.firstName} ${admin.lastName} | <a href="/admin/logout">Logout</a>
			</label>

		</div>
	</div>
</nav>
</html>