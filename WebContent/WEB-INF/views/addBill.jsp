<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

	<form action="addnewBill" method="post">
	<table class="addnewBill">
		<tr><td valign=top>
		<table>
			<tr>
				<td>Наименование клиента:</td>
				<td><input type="text" name="clientName"></td>
			</tr>

			<tr>
				<td>Номер сборки:</td>
				<td><input type="text" name="billNumber"></td>
			</tr>

			<tr>
				<td>Время сборки (мин.):</td>
				<td><input type="text" name="time"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="добавить сборку" style="width:145px"></td>
			</tr>
		</table>
		</td><td>
		<table>
			<c:forEach items="${warehouseList}" var="warehouse">
				<tr>
					<td><input type="checkbox" name="warehouses" value="${warehouse.name}">${warehouse.name}</td>
				</tr>
			</c:forEach>
		
		</table>
		</td></tr>
</table>
	</form>





	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>