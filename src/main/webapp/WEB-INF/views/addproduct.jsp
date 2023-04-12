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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="adminnavbar.jsp"></jsp:include>
	<c:choose>
		<c:when test="${product.getProductId() > 0}">
			<h2 class="container mt-5 mb-5 d-flex justify-content-center">Update
				Product</h2>
		</c:when>
		<c:otherwise>
			<h2 class="container mt-5 mb-5 d-flex justify-content-center">Add
				New Product</h2>
		</c:otherwise>
	</c:choose>

	<div class="container mt-5 mb-5 d-flex justify-content-center">

		<form:form action="/admin/product/save" modelAttribute="product"
			method="post">
			<form:hidden path="productId" />
			<div class="row">
				<label for="productCode">Product Code </label>
				<form:input path="productCode" />
			</div>

			<div class="row">
				<label for="productName">Product Name </label>
				<form:input path="productName" />
			</div>

			<div class="row">
				<label for="brand">Brand </label>
				<form:input path="brand" />
			</div>

			<div class="row">
				<label for="size">Size</label>
				<form:input path="size" />
			</div>

			<div class="row">
				<label for="price">Price &#8377</label>
				<form:input path="price" />
			</div>

			<div class="row">
				<label for="productCategory">Product Category</label>
				<form:select class="form-control"
					path="productCategory.productCategoryId" items="${categories}"
					itemValue="productCategoryId" itemLabel="categoryName">
				</form:select>
			</div>

			<div class="row">
				<label for="image_Url">Select Product Image </label>
			</div>
			<!-- select from folder -->

			<div class="row">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="image_url"
						accept="image/jpeg, image/png" />
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<img id="imgPreview" height="64px" width="64px"
						src="/images/products/alt-image.png" style="margin-top: 20px"
						alt="">
					<form:input type="hidden" path="image_url" id="image_url_value"/>
				</div>
			</div>



			<div class="row">
				<input class="btn, btn-success btn-lg .btn-block" type="submit"
					name="Add" value="Save">
			</div>

			<div style="color: red">${errMessageAddCategory}</div>
		</form:form>
	</div>
	<script type="text/javascript">
		document
		.getElementById('image_url')
		.addEventListener(
				'change',
				function(e) {
					if (e.target.files[0]) {
						var fileUri = "/images/products/"
								+ event.target.files[0].name;
						console.log("image_url:change = "+fileUri);
						document.getElementById('image_url_value').value = fileUri;

					}
				});
		$('#image_url_value').addEventListener(
				'change',
				function(event) {
					if (event.target.value) {
						console.log("image_url_value:change = "+event.target.value);
						document.getElementById('imgPreview').src = event.target.value;
					}
				});
	</script>
</body>
</html>