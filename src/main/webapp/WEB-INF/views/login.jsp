<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<%-- <jsp:include page="navbar.jsp"></jsp:include> --%>
	<section class="vh-100">
		<div class="container mt-5 mb-5 d-flex justify-content-center">
			<div class="row d-flex justify-content-center align-items-center">
				<div class="col ">
					<div class="card-body p-4 p-lg-5 text-black"
						style="border-radius: 1rem;">
						<div class="row g-0">

							<div class="col-xl-6 col-lg-7 d-flex align-items-center">
								<div class="col-auto mb-3">
									<div class="card text-center">
										<div class="card-body text-black">

											<form action="/user/login" method="post">

												<div class="d-flex align-items-center mb-3 pb-1">
													<i class="fas fa-cubes fa-2x me-3"></i> <span
														class="h1 fw-bold mb-0">Sporty Shooes.com</span>
												</div>

												<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign
													into your account</h5>

												<div class="form-outline mb-4">
													<label class="form-label" for="inputemail">Email
														address</label> <input type="email" id="inputemail" name="email"
														class="form-control form-control-lg" required="required" />
												</div>

												<div class="form-outline mb-4">
													<label class="form-label" for="inputpassword">Password</label>
													<input type="password" id="inputpassword" name="password"
														class="form-control form-control-lg" required="required" />
												</div>

												<div class="pt-1 mb-4">
													<button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
												</div>

												<a class="small text-muted" href="#!">Forgot password?</a>

											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>