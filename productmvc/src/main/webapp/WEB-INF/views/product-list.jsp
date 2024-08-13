<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.productmvc.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

 List<Product> products = (ArrayList<Product>)request.getAttribute("list");
Iterator<Product> itr = products.iterator();
while(itr.hasNext()){
	Product p = itr.next();
	%>
	<p>Id: <%=p.getId() %>, Name: <%=p.getName() %>, Price: Rs.<%=p.getPrice() %>, Qty: <%=p.getQty() %>, <a href="remove-product/<%=p.getId()%>">Delete</a></p>
	<%
}
%>
</body>
</html>