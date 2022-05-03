<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<%--@ page import="java.util.List"--%>
<%--@ page import="com.model2.mvc.service.domain.Product"--%>
<%--@ page import="com.model2.mvc.common.Search"--%>
<%--@page import="com.model2.mvc.common.Page"--%>
<%--@page import="com.model2.mvc.common.util.CommonUtil"--%>


<%--
String menu = (String) request.getAttribute("menu");
Search search = (Search) request.getAttribute("search");
Page resultPage = (Page) request.getAttribute("resultPage");
List<Product> list = (List<Product>) request.getAttribute("list");

//==> null 을 ""(nullString)으로 변경
String searchCondition = CommonUtil.null2str(search.getSearchCondition());
String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
--%>


<html>
<head>
<title>상품 관리</title>

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
		<form name="detailForm" action="/listProduct.do?menu=${menu}" method="post">


		<!-- table 1 : 상품관리 OR 상품목록조회 title -->
			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">


							<c:if test="${! empty menu && menu eq 'manage'}">
								<tr>
								<td width="93%" class="ct_ttl01">상품 관리</td>
								</tr>						
							</c:if>
							
							<c:if test="${! empty menu && menu eq 'search'}">
								<tr>
								<td width="93%" class="ct_ttl01">상품 목록 조회</td>
								</tr>						
							</c:if>


						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


		<!-- table 2 : searchCondition, searchKeyword, 검색 버튼 테이블-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

				<!-- condition, keyword -->
					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="0"
								${search.searchCondition eq '0'? 'selected':''}>상품번호</option>
							<option value="1"
								${search.searchCondition eq '1'? 'selected':''}>상품명</option>
							<option value="2"
								${search.searchCondition eq '2'? 'selected':''}>상품가격</option>
					</select> <input type="text" name="searchKeyword" value="${search.searchKeyword}"
						class="ct_input_g" style="width: 200px; height: 19px" /></td>

				<!-- 검색버튼 -->
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetDomainList(1);">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


		<!-- table 3 : 상품관리 OR 상품목록조회 data 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${requestScope.resultPage.totalCount} 건수, 현재 ${requestScope.resultPage.currentPage}
						페이지
					</td>
				</tr>

				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				
				
				
				<c:set var="no" value="0"/>
				<c:forEach var="product" items="${list}">
					<c:set var="no" value="${no+1}" />
					<tr class="ct_list_pop">
						<td align="center">${no}</td>
						<td></td>
						
							<c:if test="${! empty menu && menu eq 'search'}">
								<td align="left"><a
								href="/getProduct.do?prodNo=${product.prodNo}&menu=search">${product.prodName}</a></td>
								<td></td>
								<td align="left">${product.price}</td>
								<td></td>
								<td align="left">${product.manuDate}</td>
								<td></td>
								
								<c:choose>
									<c:when test="${empty product.proTranCode}">
										<td align="left">판매중</td>
									</c:when>
									<c:when test="${! empty product.proTranCode}">
										<td align="left">재고없음</td>
									</c:when>
								</c:choose>
							</c:if>
	
							<c:if test="${! empty menu && menu eq 'manage'}">
								<td align="left"><a
								href="/updateProductView.do?prodNo=${product.prodNo}&menu=manage">${product.prodName}</a></td>
								<td></td>
								<td align="left">${product.price}</td>
								<td></td>
								<td align="left">${product.manuDate}</td>
								<td></td>
								
								<c:choose>
									<c:when test="${empty product.proTranCode}">
										<td align="left">판매중</td>			
									</c:when>
									<c:when test="${fn:startsWith(product.proTranCode,'1')}">
										<td align="left">구매완료 <a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2">배송하기</a></td>
									</c:when>
									<c:when test="${fn:startsWith(product.proTranCode,'2')}">
										<td align="left">배송중</td>
									</c:when>
									<c:when test="${fn:startsWith(product.proTranCode,'3')}">
										<td align="left">배송완료</td>
									</c:when>
								</c:choose>
								
							</c:if>
					
				</c:forEach>
										
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
			</table>


			<!-- PageNavigation Start... -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="currentPage" name="currentPage" value="" /> 
					 
					 <jsp:include page="../common/pageNavigator.jsp"/>
					 
					 </td>
				</tr>
			</table>
			<!-- PageNavigation End... -->



		</form>

	</div>
</body>
</html>