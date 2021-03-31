<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 등록</title>
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/resources/css/style.css" rel="stylesheet">
<script type="text/javascript">
	function beforeSubmit() {
		var productNameNode = document.getElementById("productName");
		var vendorNode = document.getElementById("vendorName");
		var priceNode = document.getElementById("price");
		var introduceNode = document.getElementById("introduce");
		if(!(productNameNode.value && productNameNode.value.trim())) {
			alert("제품명을 입력해주세요.");
			productNameNode.focus();
			return false;
		}
		if(!(vendorNode.value && vendorNode.value.trim())) {
			alert("제조사를 입력해주세요.");
			vendorNode.focus();
			return false;
		}
		if(!(priceNode.value && priceNode.value.trim())) {
			alert("가격을 입력해주세요.");
			priceNode.focus();
			return false;
		}
		if(!(introduceNode.value && introduceNode.value.trim())) {
			alert("제품 소개를 입력해주세요.");
			introduceNode.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<a href="${ctx}">제품목록</a>
	<h3>제품등록</h3>
	<br>
	<form action="${ctx}/regist" method="post" onsubmit="return beforeSubmit();">
		<table class="table">
		<colgroup>
			<col width="150">
			<col width="*">
		</colgroup>
			<tr>
				<th>제품명</th>
				<td><input id="productName"  name="name" class="form-control" type="text" value=""></td>
			</tr>
			<tr>
				<th>제조사</th>
				<td><input id="vendorName" name="vendor" class="form-control" type="text" value=""></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input id="price" name="price" class="form-control" type="number" value=""></td>
			</tr>
			<tr>
				<th>제품소개</th>
				<td><textarea id="introduce" name="introduce" class="form-control" rows="7"></textarea>
			</tr>
		</table><br>
		<div align="center"><input class="btn" type="reset" value="취소"> <input class="btn btn-success" type="submit" value="저장"></div>
	</form>
	<br>
</body>
</html>
