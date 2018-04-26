<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include><p>
		<jsp:include page="_menu.jsp"></jsp:include>
		<p>Hello. This is start page. You may find more information <a href=>here</a>.</p>

		<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>