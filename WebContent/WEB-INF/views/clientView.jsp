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
	<form action="editClient" name="clientForm" method="post">
		<input type="hidden" name="clientName" value="null">
		<div>
			<table class="clientsTable">
				<tr>
					<td>Наименоваение клиента:</td>
					<td>Опции:</td>
				</tr>

				<c:forEach items="${clientList}" var="client">
					<tr>
						<td>${client.name}</td>
						<td><input type="submit" name="${client.name}"
							value="Редактировать"
							onclick="{document.clientForm.clientName.value=this.name;document.clientForm.submit();}" /></td>
					</tr>

				</c:forEach>

			</table>
		</div>
	</form>
	<hr align="center" width="285" size="1" color="#BDBDBD" />
	<div align="center">

		<form action="addnewClient" method="post">
			<input type="text" name="newClientName"> <input type="submit"
				value="добавить клиента">
		</form>
	</div>


	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>