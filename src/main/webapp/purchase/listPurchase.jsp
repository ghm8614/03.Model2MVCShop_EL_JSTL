<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  tr : table row -->
<!--  th : table head : column명 (기준 cell)-->
<!--  td : table cell -->

<!-- 일반회원의 구매이력조회 -->

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetDomainList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<!-- 전체 -->
		<form name="detailForm" action="/listPurchase.do" method="post">

			<!-- table 1 :  -->
			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37"></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">구매 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
				</tr>
			</table>


			<!-- table 2 : 구매목록 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${resultPage.totalCount}, 현재 ${resultPage.currentPage} 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="50">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="120">주문일자</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">수령자명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="200">수령주소</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="300">배송현황</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">결제방법</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>

				<c:set var="count" value="0" />
				<c:forEach var="purchase" items="${list}">
					<c:set var="count" value="${count+1}" />
					
					<tr class="ct_list_pop">
						<td align="center"><a
							href="/getPurchase.do?tranNo=${purchase.tranNo}">${count}</a></td>
						<td></td>
						<td align="center">${purchase.orderDate}</td>
						<td></td>
						<td align="left"><a
							href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a>
						</td>
						<td></td>
						<td align="left">${purchase.receiverName}</td>
						<td></td>
						<td align="left">${purchase.divyAddr}</td>
						<td></td>

						<c:choose>
							<c:when test="${purchase.tranCode eq '1'}">
								<td align="left">구매완료</td>
								<td></td>
							</c:when>
							
							<c:when test="${purchase.tranCode eq '2'}">
								<td align="left"><a
									href="updateTranCode.do?tranNo=${purchase.tranNo}&tranCode=3">물건도착</a></td>
								<td></td>
							</c:when>
							
							<c:when test="${purchase.tranCode eq '3'}">
								<td align="left">배송완료</td>
								<td></td>
							</c:when>
						</c:choose>


						<c:choose>
							<c:when test="${purchase.paymentOption eq '1'}">
								<td align="left">현금결제</td>
								<td></td>
							</c:when>
							
							<c:when test="${purchase.paymentOption eq '2'}">
								<td align="left">신용결제</td>
								<td></td>
							</c:when>
						</c:choose>
						
					</tr>
				</c:forEach>
				
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>


			</table>


			<!-- table 3 : PageNavigation 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="currentPage" name="currentPage" value="" /> 
					 
					 <jsp:include page="../common/pageNavigator.jsp"/>
					 
					 </td>
				</tr>
			</table>


		</form>

	</div>

</body>
</html>