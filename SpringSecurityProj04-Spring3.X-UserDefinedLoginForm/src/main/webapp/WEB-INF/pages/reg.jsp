<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Registertion form</h1>

<frm:form modelAttribute="user" method="post" action="reg">
<h3>Enter Your Name</h3>
<frm:input path="name"/>
<h3>Enter Your userName</h3>
<frm:input path="userName"/>
<h3>Enter Your Password</h3>
<frm:input path="password"/>

<input type="submit" value="register">

</frm:form>

</body>
</html>