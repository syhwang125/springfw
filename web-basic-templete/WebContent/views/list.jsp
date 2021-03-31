<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 목록</title>
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>제품 목록</h3>
				<table class="table table-hover table-condensed">
					<colgroup>
						<col width="80" align="center">
						<col width="100">
						<col width="25%">
						<col width="100">
						<col width="100">
						<col width="100">
						<col width="100">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제품번호</th>
							<th>제품명</th>
							<th>제조사</th>
							<th>가격</th>
							<th>UPDATE</th>
							<th>DELETE</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${products eq null || empty products}">
								<tr>
									<td colspan="6" align="center">등록된 제품 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${products}" var="product" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td>${product.serialNumber}</td>
										<td><a href="detail?serialNumber=${product.serialNumber}">${product.name}</a></td>
										<td>${product.vendor}</td>
										<td>${product.price}</td>
										<td><a class="btn btn-xs btn-warning" href="modify?serialNumber=${product.serialNumber}">UPDATE</a></td>
										<td><a class="btn btn-xs btn-danger" href="remove?serialNumber=${product.serialNumber}">DELETE</a></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<a class="btn btn-sm btn-success" href="regist">제품등록</a>
			</div>
		</div>
	</div>
</body>
</html>