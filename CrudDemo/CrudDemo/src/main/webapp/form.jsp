<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome Home</h1>
<%
	if(request.getAttribute("msg")!=null){
		%>
		<h3> <%=request.getAttribute("msg") %></h3>
		<%
	}
%>
<a href="StudentSave">Show Lit of the Students</a>
<form action="StudentSave" method="post">
<input type="text" placeholder="Enter ID" name="id" >
<br><br>
<input type="text" placeholder="Enter Name" name="name" >
<br><br>
<input type="text" placeholder="Enter Marks" name="marks" >
<br><br>
<input type="text" placeholder="Enter Phone Number" name="num" >
<br><br>
<input type="submit" placeholder="Submit" >
<br><br>
</form>
</body>
</html>