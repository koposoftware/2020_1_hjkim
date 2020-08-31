<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h2>${ detailVO.aptBasicVO.kaptName }</h2>
<hr>
<ul class="nav nav-tabs justify-content-center">
	<li id='apt-detail-nav' class="nav-item active" onclick="aptDetailInfo('${detailVO.aptBasicVO.kaptCode}')"><a class="nav-link">아파트 상세보기</a></li>
	<li id='apt-price-nav' class="nav-item" onclick="aptDetailPrice('${detailVO.aptBasicVO.kaptCode}')"><a class="nav-link" href="#">아파트 실거래가</a></li>
	<li class="nav-item"><a class="nav-link" href="#">아파트 상담하기</a></li>
</ul>

<div class="detail-content">
	<table class="table table-bordered">
		<tr>
			<th>아파트명</th>
			<td colspan="3">${ detailVO.aptBasicVO.kaptName }</td>
		</tr>
		<tr>
			<th>법정동주소</th>
			<td colspan="3">${ detailVO.aptDetailVO.kaptAddr }</td>
		</tr>
		<tr>
			<th>도로명</th>
			<td colspan="3">${ detailVO.aptDetailVO.doroJuso }</td>
		</tr>
		<tr>
			<th>분양형태</th>
			<td>${ detailVO.aptDetailVO.codeSaleNm }</td>
			<th>난방방식</th>
			<td>${ detailVO.aptDetailVO.codeHeatNm }</td>
		</tr>
		<tr>
			<th>복도유형</th>
			<td>${ detailVO.aptDetailVO.codeHallNm }</td>
			<th>건축물 대장상 연면적</th>
			<td>${ detailVO.aptDetailVO.kapttarea }</td>
		</tr>
		<tr>
			<th>동수</th>
			<td>${ detailVO.aptDetailVO.kaptDongCnt }</td>
			<th>세대수</th>
			<td>${ detailVO.aptDetailVO.kaptDaCnt }</td>
		</tr>
		<tr>
			<th>호수</th>
			<td colspan="3">${ detailVO.aptDetailVO.hocnt }</td>
		</tr>
		<tr>
			<th>시공사</th>
			<td>${ detailVO.aptDetailVO.kaptBCompany }</td>
			<th>시행사</th>
			<td>${ detailVO.aptDetailVO.kaptACompany }</td>
		</tr>
		<tr>
			<th>관리사무소연락처</th>
			<td>${ detailVO.aptDetailVO.kaptTel }</td>
			<th>관리사무소팩스</th>
			<td>${ detailVO.aptDetailVO.kaptFax }</td>
		</tr>
		<tr>
			<th>단지분류</th>
			<td>${ detailVO.aptDetailVO.codeAptNm }</td>
			<th>홈페이지주소</th>
			<td>${ detailVO.aptDetailVO.kaptUrl }</td>
		</tr>
		<tr>
			<th>관리방식</th>
			<td>${ detailVO.aptDetailVO.codeMgrNm }</td>
			<th>사용승인일</th>
			<td>${ detailVO.aptDetailVO.kaptUseDate }</td>
		</tr>
		<tr>
			<th>관리비부과면적</th>
			<td>${ detailVO.aptDetailVO.kaptMArea}</td>
			<th>단지 전용면적합</th>
			<td>${ detailVO.aptDetailVO.privArea}</td>
		</tr>
		<tr>
			<th colspan="4">전용면적별 세대현황</th>
		</tr>
		<tr>
			<th>60㎡ 이하</th>
			<td>${ detailVO.aptDetailVO.kaptMPArea60 }</td>
			<th>60㎡ ~ 85㎡ 이하</th>
			<td>${ detailVO.aptDetailVO.kaptMPArea85 }</td>
		</tr>
		<tr>
			<th>85㎡ ~ 135㎡ 이하</th>
			<td>${ detailVO.aptDetailVO.kaptMPArea135 }</td>
			<th>135㎡ 초과</th>
			<td>${ detailVO.aptDetailVO.kaptMPArea136 }</td>
		</tr>
	</table>
	<button onclick="goBackToMap()" class="btn btn-outline-info">지도로 돌아가기</button>
</div>


<script>
	function goBackToMap() {
		$.ajax({
			url : '${ pageContext.request.contextPath }/apt/aptMap',
			type : 'get',
			success : function() {
				$(".map-class").css('display', 'block');
				$(".apt-detail").css('display', 'none');
				$(".apt-detail").empty();
			}
		})
	}

	function aptDetailPrice(aptCode) {
		$.ajax({
			url : '${ pageContext.request.contextPath }/apt/' + aptCode + '/detailPrice',
			type : 'get',
			success : function(data) {
				$('.detail-content').html(data)
				$('#apt-detail-nav').removeClass('active')
				$('#apt-price-nav').addClass('active')
			}
		})
	}
</script>