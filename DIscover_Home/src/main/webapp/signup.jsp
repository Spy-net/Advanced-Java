<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discover Home | Signup</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/signup.css">
</head>
<body>
	<div class="SignupPage">
		<div class="SignupFormContainer">
			<div class="ImageSide">
				<img
					src="https://img.freepik.com/premium-vector/tourist-with-luggage-bag-young-man-going-trip-with-luggage_549515-721.jpg?w=740">
			</div>
			<div class="SignupFormSide">
				<h1>
					Hello, There <br>welcome !
				</h1>
				<form action="<%=request.getContextPath()%>/signup" method="post">
					<div class="BasicInput">
						<input type="text" id="Username" placeholder="Username"
							name="Username" required> <input type="number"
							id="PhoneNumber" placeholder="PhoneNumber" name="PhoneNumber"
							required>
					</div>
					<input type="email" id="email" placeholder="email" name="email"
						required> <input type="password" id="password"
						placeholder="Password" name="password" required>
					<div class="LoginText">
						<button type="submit">Signup</button>
						<div style="display: flex; align-items: center;">
							<p>Already Have Account ?</p>
							<a href="<%=request.getContextPath()%>/login">Login </a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>