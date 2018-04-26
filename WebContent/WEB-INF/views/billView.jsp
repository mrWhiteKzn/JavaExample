<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<div>
		<form action="editBill" method="post" name="form">
			<input type="hidden" name="billNumberToEdit" value="testValue" />
			<table class="billTable" >
				<tr>
					<td>Номер сборки:</td>
					<td>Клиент:</td>
					<td>Время начала:</td>
					<td>Планируемое время окончания:</td>
					<td>Опции</td>
					<td>Статус:</td>
				</tr>

				<c:forEach items="${billList}" var="bill">
					<tr>
						<td>${bill.billNumber}</td>
						<td>${bill.orgName}</td>
						<td>${bill.startTime }</td>
						<td>${bill.planTime }</td>
						<td><input type="submit" name="${bill.billNumber}"
							value="Редактировать"
							onclick="{document.form.billNumberToEdit.value=this.name;document.form.submit();}" />
						</td>
						<td><c:if test="${bill.state}">Собран</c:if> <c:if
								test="${!bill.state}">Не собран</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>