<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date JSP Page</title>
</head>
<body>
<h1>Getting Current Date and Time through JSP expression element</h1>
<p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
</body>
</html>