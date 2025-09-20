<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Login from </h2>

<h1>Fill the details for login</h1>

<frm:form modelAttribute="user" method="post" action="login">
<h3>Enter Username</h3>
<frm:input path="userName"/>

<h3>Enter Password</h3>
<frm:input path="password"/>

<input type="submit" value="sumbit" />
</frm:form>

</body>
</html>