<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


	<form action="changeClientProperties" method="post" align="center">
		<table class="editClientTable">
			<tr>
				<td>
					<h3 align="center">Имя клиента: ${clientName}</h3>
					<input type="hidden" name="oldName" value="${clientName}">
				</td>
			<tr>
				<td>Изменить имя: <input type="text" name="clientNameField"
					value="" /> <input type="submit" value="Подтвердить" />
				</td>
			</tr>

			</form>

		</table>



		<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>