<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Address</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class="container mt-5 mb-5 d-flex justify-content-center">

		<form:form action="/address/save"
			modelAttribute="address" method="post">
			<form:hidden path="addressId"/>
			<div class="row">
				<label for="address1">Address1</label>
				<form:input path="address1" />
			</div>

			<div class="row">
				<label for="address2">Address2 </label>
				<form:input path="address2" />
			</div>
			
			<div class="row">
				<label for="address3">Address3 </label>
				<form:input path="address3" />
			</div>
			
			<div class="row">
				<label for="city">City </label>
				<form:input path="city" />
			</div>
			
			<div class="row">
				<label for="state">State </label>
				<form:input  path="state" />
			</div>
						
			<div class="row">
				<label for="country">Country </label>
				<form:input  path="country" />
			</div>
			
			<div class="row">
				<label for="zipCode">ZipCode </label>
				<form:input  path="zipCode" />
			</div>
			
			
			<div class="row" style="margin-top: 10px;">
				<input class="btn, btn-success btn-lg .btn-block" type="submit"
					name="Save" value="Save">
			</div>
			
			<div style="color: red">${errMessageAddCategory}</div>
		</form:form>
	</div>
</body>
</html>