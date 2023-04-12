<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
<style>
th a:link {
	text-decoration: none;
	color: black
}

th a:visited {
	text-decoration: none;
	color: black
}

.rows {
	background-color: white
}

.datagrid {
	display: grid;
	height: 100%;
	/* grid-template-columns: 20% 20% 20% 20% 20%; */
	grid-template-columns: repeat(5, 0.5fr);
	grid-auto-rows: 0.5fr;	
	background-color: white;
	padding: 4px;
}

.grid-item {
	text-align: center;
	padding: 8px;
}
</style>
<div class="datagrid">
	<c:forEach var="product" items="${products}">
		<div class="grid-item">
			<div class="card h-100">
				<img src="${product.getImage_url()}" height="270px" width="auto"
					class="card-img-top p-3" alt="Product Image" />
				<div class="card-body">
					<h5 class="card-title">${product.getProductName()}</h5>
					<p class="card-text">&#8377 ${product.getPrice()}</p>
					<a href="/product?productId=${product.getProductId()}" class="stretched-link"></a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
</html>