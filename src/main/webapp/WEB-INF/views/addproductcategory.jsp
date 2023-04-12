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
</head>
<body>
	<jsp:include page="adminnavbar.jsp"></jsp:include>
	<c:choose>
		<c:when test="${category.productCategoryId > 0}">
			<h2 class="container mt-5 mb-5 d-flex justify-content-center">Update
				Product Category</h2>
		</c:when>
		<c:otherwise>
			<h2 class="container mt-5 mb-5 d-flex justify-content-center">Add
				New Product Category</h2>
		</c:otherwise>
	</c:choose>
	<div class="container mt-5 mb-5 d-flex justify-content-center">

		<form:form action="/admin/productcategory/save"
			modelAttribute="category" method="post">
			<form:hidden path="productCategoryId"/>
			<div class="row">
				<label for="categoryName">Product Category Name </label>
				<form:input path="categoryName" />
			</div>

			<div class="row">
				<label for="targetGroup">Target Group </label>
				<form:input path="targetGroup" />
			</div>

			<div class="row" style="margin-top: 10px;">
				<input class="btn, btn-success btn-lg .btn-block" type="submit"
					name="Add" value="Save">
			</div>

			<div style="color: red">${errMessageAddCategory}</div>
		</form:form>
	</div>
</body>
</html>