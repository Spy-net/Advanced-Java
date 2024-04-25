<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discover Home | Login</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
	<div class="LoginPage">
		<div class="FormContainer">
			<div class="ImageSide">
				<img
					src="https://img.freepik.com/free-vector/hand-drawn-tourists-with-baggage_23-2149067035.jpg?t=st=1713635763~exp=1713639363~hmac=65cd0503013cd075923a24cc496bc5522872550a232d92b1fd724608e731d0cb&w=740">
			</div>
			<div class="FormSide">
				<h1>
					Welcome ! <br>Back
				</h1>
				<form action="<%=request.getContextPath()%>/login" method="post">
					<input type="email" id="email" placeholder="email" name="email"
						required> <input type="password" id="password"
						placeholder="Password" name="password" required>
					<button type="submit" class="LoginButton">Login</button>
					<div class="SignupText">
						<p>Don't Have Account ?</p>
						<a href="<%=request.getContextPath()%>/signup">Signup</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>