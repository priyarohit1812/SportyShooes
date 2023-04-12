<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Address List</title>
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
	<a class="btn btn-light" href="/address/save" role="button"
		style="margin-left: 5px">Add New Address</a>
	<table class="table table-striped" border="1" style="width: 100%">

		<thead class="thead-dark">
			<tr>
				<th scope="col">Address</th>
				<th scope="col">Edit</th>
				<th scope="col">Delete</th>
			</tr>
		</thead>

		<c:forEach var="address" items="${addresses}">
			<tr>
				<td><c:if test="${not empty address.getAddress1()}">
						<p>${address.getAddress1()}</p>
					</c:if> <c:if test="${not empty address.getAddress2()}">
						<p>${address.getAddress2()}</p>
					</c:if> <c:if test="${not empty address.getAddress3()}">
						<p>${address.getAddress3()}</p>
					</c:if>
					<p>${address.getCity()},${address.getState()}</p>
					<p>${address.getCountry()}-${address.getZipCode()}</p></td>
				<td><a href="/address/save?addressId=${address.getAddressId()}">Edit</a></td>
				<td><a
					href="/address/delete?addressId=${address.getAddressId()}"
					onclick="if (!('are you sure you want to delete this todo?'))) return false">Delete</a></td>
			</tr>

		</c:forEach>
	</table>

</body>
</html>