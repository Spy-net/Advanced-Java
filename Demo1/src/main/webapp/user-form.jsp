<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #181818;
            color: #fff;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 95px auto;
            padding: 20px;
            background-color: #202020;
            border-radius: 5px;
            box-shadow: 0px 0px 10px #191919;
        }

        h1,h2 {
            text-align: center;
            color: #ff5733;
        }

        a {
            color: #ff5733;
            text-decoration: none;
        }

        .form-container {
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #444;
            color: #fff;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #A34343;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #F2613F;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Management</h1>
        <h2>
            <a href="new">Add New User</a> &nbsp;&nbsp;&nbsp;
            <a href="list">List All Users</a>
        </h2>
        <div class="form-container">
            <c:if test="${user != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${user == null}">
                <form action="insert" method="post">
            </c:if>
                <table>
                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>
                    <tr>
                        <th>User Name:</th>
                        <td><input type="text" name="name" value="<c:out value='${user.name}' />" /></td>
                    </tr>
                    <tr>
                        <th>User Email:</th>
                        <td><input type="text" name="email" value="<c:out value='${user.email}' />" /></td>
                    </tr>
                    <tr>
                        <th>Country:</th>
                        <td><input type="text" name="country" value="<c:out value='${user.country}' />" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
