<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<form>
		<table class="warehouseTable">
			<tr>
				<td>Наименование склада: </td>
				<td>Опции:</td>
			</tr>
			<c:forEach items="${warehouseList}" var="warehouse">
				<tr>
					<td>
						${warehouse.name}
					</td>
					<td>
						<a href="deleteWh?name=${warehouse.name}">Удалить</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	
	<hr align="center" width="270" size="1" color="#BDBDBD" />
	<form action="addnewWarehouse" method="post" align="center">
		<input type="text" name="newWarehouse">
		<input type="submit" value="Добавить склад">
	</form>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>