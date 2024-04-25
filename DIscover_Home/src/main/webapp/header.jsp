<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<header class="header">
		<p>Discover Home</p>
		<nav>
			<ul>
				<li><a href="Home.jsp">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/Mytours">My
						Tours</a></li>
				<li><a href="TourList.jsp">Tours</a></li>
				<li><a href="<%=request.getContextPath()%>/login?logout=true">Logout</a></li>
			</ul>
		</nav>
	</header>
</body>
</html>
