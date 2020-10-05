<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2 id="loan-calc-content">계산내용</h2>
<table class="table table-bordered margin-top-20 loanCalc-table">
	<tr>
		<th>순번</th>
		<th>내용</th>
		<th>상세</th>
		<th>금액</th>
	</tr>
	<c:if test="${ not empty resultVO }">
		<tr>
			<td>1</td>
			<td>주택가격</td>
			<td></td>
			<td>${ resultVO.aptPrice }(만 원)</td>
		</tr>
		<tr>
			<td>2</td>
			<td>지역유형</td>
			<td>${ resultVO.areaType }</td>
			<td></td>
		</tr>
		<c:if test="${ fn:length(resultVO.aptLtvList) eq 1 }">
			<tr>
				<td>3</td>
				<td>LTV계산</td>
				<td>${ resultVO.aptLtvList[0].loanType }:${ resultVO.aptLtvList[0].aptLtv }%</td>
				<td>${ resultVO.aptLtvList[0].aptLtv }%</td>
			</tr>
		</c:if>
		<c:if test="${ fn:length(resultVO.aptLtvList) eq 2 }">
			<tr>
				<td rowspan="2">3</td>
				<td rowspan="2">LTV계산</td>
				<td>${ resultVO.aptLtvList[0].loanType }</td>
				<c:choose>
					<c:when test="${ resultVO.aptLtvList[0].aptLtv eq 0}">
						<td>대출이 불가능합니다.</td>
					</c:when>
					<c:otherwise>
						<td>${ resultVO.aptLtvList[0].aptLtv }%</td>
					</c:otherwise>
				</c:choose>
			</tr>

			<tr>
				<td>${ resultVO.aptLtvList[1].loanType }</td>
				<c:choose>
					<c:when test="${ resultVO.aptLtvList[1].aptLtv eq 0}">
						<td>대출이 불가능합니다.</td>
					</c:when>
					<c:otherwise>
						<td>${ resultVO.aptLtvList[1].aptLtv }%</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:if>
		<tr>
			<td>4</td>
			<td>대출가능 금액</td>
			<td>최대 대출 한도(주택가격 * LTV)</td>
			<td>${ resultVO.loanMaxPrice } (만 원)</td>
		</tr>
	</c:if>
</table>
<div id="loan-detail-flip">
	대출 한도 계산 상세 내용<i class="fas fa-caret-down ltv-info-icon" aria-hidden="true" style="color: #000; font-size: 23px;"></i> <i class="fas fa-caret-up ltv-info-icon" aria-hidden="true" style="color: #000; font-size: 23px; display: none"></i>
</div>
<div id="loan-detail-panel">
	<div class="ltv-info">
		<c:if test="${ fn:length(resultVO.aptLtvList) eq 1 }">
			대출 최대 한도 ${ resultVO.loanMaxPrice }만 원 = 주택가격 ${ resultVO.aptPrice }만 원 * ${ resultVO.aptLtvList[0].aptLtv }% <br>
		</c:if>
		<c:if test="${ fn:length(resultVO.aptLtvList) eq 2 }">
			<c:choose>
				<c:when test="${ resultVO.aptLtvList[0].aptLtv eq 0}">
					대출이 불가능합니다.
				</c:when>
				<c:otherwise>
					대출 최대 한도 ${ resultVO.loanMaxPrice } 만 원 = 90000 만원 * ${ resultVO.aptLtvList[0].aptLtv }% + ${ resultVO.aptPrice - 90000} * ${ resultVO.aptLtvList[1].aptLtv }%
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	<div class="notice">
		<span class="badge badge-danger">주의</span> 실제 대출 금액은 실제 대출한도는 DTI, 우선변제대상 등의 여러 복합적인 요소들에 의해 달라질 수 있습니다.<br>
		<p><a href="#ex1" rel="modal:open">지역별 소액임차보증금(우선변제대상) 자세히 보기</a></p>
	</div>
</div>

<div id="ex1" class="modal">
  <table class="table table-bordered">
		<tr>
			<th colspan="3">지역</th>
			<th>소액임차보증금</th>
		</tr>
		<tr>
			<td colspan="3">서울특별시</td>
			<td>3,700만원</td>
		</tr>
		<tr>
			<td rowspan="8">인천광역시 및 <br>수도권 중과밀<br> 억제 권역</td>
			<td rowspan="3">인천광역시</td>
			<td>강화군, 웅진군</td>
			<td>1,700만원</td>
		</tr>
		<tr>
			<td>서구 대곡동/불로동/마전동/금곡동/오류동/왕길동/당하동/원당동/인천경제자유구역 및 남동 국가산업단지</td>
			<td>2,000만원</td>
		</tr>
		<tr>
			<td>그 밖의 지역</td>
			<td>3,400만원</td>
		</tr>
		<tr>
			<td colspan="2">의정부시/구리시 /하남시/고양시/수원시/성남시/안양시/부천시/광명시/과천시/의왕시/군포시/용인시/화성시/세종시</td>
			<td>3,400만원</td>
		</tr>
		<tr>
			<td rowspan="2">반월특수지역</td>
			<td>반월특수지역</td>
			<td>1,700만원</td>
		</tr>
		<tr>
			<td>그밖의 지역</td>
			<td>3,400만원</td>
		</tr>
		<tr>
			<td rowspan="2">남양주시</td>
			<td>호평동/평내동/금곡동/일패동/이패동/삼패동/가운동/수석동/지금동 및 도농동</td>
			<td>3,400만원</td>
		</tr>
		<tr>
			<td>그밖의 지역</td>
			<td>1,700만원</td>
		</tr>
		<tr>
			<td rowspan="2" colspan="2">광주/대구/대전/부산/울산광역시</td>
			<td>군지역</td>
			<td>1,700만원</td>
		</tr>
		<tr>
			<td>그밖의 지역</td>
			<td>2,000만원</td>
		</tr>
		<tr>
			<td colspan="3">경기도 안산시, 김포시, 광주시, 파주시</td>
			<td>2,000만원</td>
		</tr>
		<tr>
			<td colspan="3">그밖의 지역</td>
			<td>1,700만원</td>
		</tr>
		</table>
</div>
<script>
	$("#loan-detail-panel").hide();
	$("#loan-detail-flip").click(function() {
		$("#loan-detail-panel").slideToggle("slow");
	});
</script>