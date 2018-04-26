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
	<h3 align="center">Сборка № ${billNumber}</h3>

	<form name="formWork" action="confirmWarehouseWork" method="post">
		<input type="hidden" name="warehouseName" value="1" /> <input
			type="hidden" name="billNumber" value="${billNumber}" />
		<table class="editBillTable">
			<tr>
				<td>Наименоваение склада</td>
				<td>Отметить готовность</td>
			</tr>
			<c:forEach items="${warehouseListInJob}" var="warehouse">
				<tr>
					<td>${warehouse.name}</td>
					<td><input type="submit" name="${warehouse.name}"
						value="Заказ собран"
						onclick="{document.formWork.warehouseName.value=this.name;document.form.submit();}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>