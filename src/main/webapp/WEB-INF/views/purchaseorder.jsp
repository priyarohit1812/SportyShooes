<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Product Category List</title>
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
	<div class="container mt-5 mb-5 d-flex justify-content-center">
		<div class="card px-1 py-4">
			<div class="card-body">

				<div class="d-flex flex-row">
					<form class="d-flex" method="get" action="/admin/search/productcategories">

						<input class="form-control me-2" type="search" name="key" placeholder="Search" aria-label="Search">
						<button class="btn btn-light" type="submit">Search</button>

					</form>
					
				</div>
			</div>
		</div>
	</div>



	<table class="table table-striped" border="1" style="width: 100%">

		<thead class="thead-dark">
			<tr>
				<th scope="col">Purchase Order Id</th>
				<th scope="col">CartId</th>
				<th scope="col">Address</th>
				<th scope="col">Payment</th>
			</tr>
		</thead>

		<c:forEach var="PurchaseOrder" items="${orders}">
			<tr>
				<td>${PurchaseOrder.getPurchaseOrderId()}</td>
				<td>${PurchaseOrder.getCart()}</td>
				<td>${PurchaseOrder.getAddress()}</td>
				<td>${PurchaseOrder.getPayment()}</td>

			</tr>

		</c:forEach>
	</table>

</body>
</html>