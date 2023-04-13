<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css'>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-success"
	style="color: white;">
	<div class="container-fluid">
		<a class="navbar-brand" href="/user/home" style="color: white;">sportyShoes-Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<c:forEach var="tg" items="${targetGroups}">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" style="color: white;"
						href="/user/productcategory/searchByTargetGroup?key=${tg}">${tg}</a></li>
				</c:forEach>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: white;" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Category </a>
					<ul class="dropdown-menu">
						<c:forEach var="cat" items="${categoryNames}">
							<li><a class="dropdown-item"
								href="/user/productcategory/searchByName?key=${cat}">${cat}</a></li>
						</c:forEach>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: white;" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Brand </a>
					<ul class="dropdown-menu">
						<c:forEach var="brand" items="${brands}">
							<li><a class="dropdown-item" href="/user/product/searchByBrand?key=${brand}">${brand}</a></li>
						</c:forEach>
					</ul></li>
			</ul>
			<form class="d-flex" role="search" action="/user/product/search">
				<input class="form-control me-2" type="search" name="key"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-light" type="submit">Go</button>
			</form>

			<ul>
				<li class="nav-item" style="list-style-type: none;"><a
					class="nav-link" href="/user/cart"><span id="boot-icon"
						class="bi bi-cart" style="font-size: 2rem"></span></a></li>
			</ul>
			<c:choose>
				<c:when test="${empty user}">
					<ul>
						<li class="nav-item" style="list-style-type: none; style="color: white;"><a
							class="nav-link" href="/user/login">Login/</a></li>
						<li class="nav-item" style="list-style-type: none; style="color: white;"><a
							class="nav-link" href="/user/save">Register</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<label> Welcome!<a href="/user/update?userId=${user.userId}"> ${user.firstName} ${user.lastName} </a> | <a
						href="/user/logout">Logout</a>
					</label>
				</c:otherwise>
			</c:choose>



		</div>
	</div>
</nav>
</html>