<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세조회</title>
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<a href="${ctx}">목록으로</a>
	<h3>상세조회</h3>
	<table class="table">
		<colgroup>
			<col width="150">
			<col width="*">
		</colgroup>
		<tr>
			<th>제품명</th>
			<td><input id="lectureName" class="form-control" type="text" value="${product.name}" readonly></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input id="instructorName" class="form-control" type="text" value="${product.price}" readonly></td>
		</tr>
		<tr>
			<th>제조사</th>
			<td><input id="instructorName" class="form-control" type="text" value="${product.vendor}" readonly></td>
		</tr>
		<tr>
			<th>제품소개</th>
			<td><textarea id="lectureIntroduce" class="form-control" rows="7" readonly>${product.introduce}</textarea>
		</tr>
	</table>
	<br>

</body>
</html>
