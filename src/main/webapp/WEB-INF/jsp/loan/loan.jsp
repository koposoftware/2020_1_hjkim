<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
<script type="text/JavaScript" src="${ pageContext.request.contextPath }/resources/js/jquery.alerts.js"></script>
<script src="${ pageContext.request.contextPath }/resources/js/jAlert-functions.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<script>
	$(document).ready(function() {
		$('#ltv-info').hide()
		$('#loan-law').hide()
		$('.lgFormGroupArea').hide();
		$('.lgFormGroupFloor').hide();
		$('#loan-cal-info').hide()
		$("#flip").click(function() {
			$("#panel").slideToggle("slow");
		});
		$('#panel > div').click(function() {
			var className = $(this).attr("class");
			$('#' + className).toggle();
			$('.' + className + "-icon").toggle();
		})
		$('input[name="price-type"]').on('click', function(e) {
			var check = $('input[name="price-type"]:checked').val()
			var floor = $('#lgFormGroupFloor').val()
			var area = $('#lgFormGroupArea').val()
			var price = "";
			var text = ''
			text += '<label for="lgFormGroupPrice" class="col-sm-3 col-form-label col-form-label-lg loan-form">아파트 가격</label>'
			text += '<div class="col-sm-7 priceInput">'
			text += '</div>'
			text += '<div class="col-sm-2 loan-form-sub-text">(만원)</div>'
			$('.lgFormGroupPrice').html(text)

			if (check == 'auto') {
				$('.lgFormGroupArea').show();
				$('.lgFormGroupFloor').show();
				$(".priceInput").empty()
				if (floor == "") {
					$(".priceInput").html('<input type="text" class="form-control form-control-lg" id="lgFormGroupPrice" placeholder="층수를 먼저 입력해주세요">')
				} else {
					priceGet(floor, area, '${aptCode}');
				}
			} else {
				price = "아파트 가격을 입력해주세요"
				$(".priceInput").empty()
				$(".priceInput").html('<input type="text" class="form-control form-control-lg" id="lgFormGroupPricePassive" placeholder="아파트 가격을 입력해주세요">')
				$('#lgFormGroupFloor').val("")
				$('.lgFormGroupArea').hide();
				$('.lgFormGroupFloor').hide();
			}

		})
		$('#lgFormGroupFloor').on("keyup", function() {
			if ($('#lgFormGroupFloor').val() != "" && $('input[name="price-type"]:checked').val() == "auto") {
				var floor = $('#lgFormGroupFloor').val()
				var area = $('#lgFormGroupArea').val()
				priceGet(floor, area, '${aptCode}');
			}
		})
	});
	function priceGet(floor, area, aptCode) {
		$.ajax({
			url : '${ pageContext.request.contextPath}/loan/priceGet',
			type : 'post',
			data : {
				floor : floor,
				area : area,
				aptCode : aptCode,
			},
			success : function(data) {
				if (data == 0) {
					$(".priceInput").html('<input type="text" class="form-control form-control-lg" id="lgFormGroupPrice" placeholder="정보가 없습니다. 직접기입을 선택하세요">')
				} else {
					$(".priceInput").html('<input type="text" class="form-control form-control-lg" id="lgFormGroupPrice" placeholder="' + data + '" value = "' + data + '">')
				}

			},
			error : function(data) {
				$(".priceInput").html('<input type="text" class="form-control form-control-lg" id="lgFormGroupPrice" placeholder="오류가 났습니다. 직접기입을 선택하세요">')
			}
		})
	}
	/* 대출 한도 계산하기 버튼 클릭시 수행되는 함수 */
	function calcLoan() {
		if ($('input[name="price-type"]:checked').val() == undefined) {
			jAlert('가격 입력 유형을 선택해 주세요', '가격 입력 유형');
		} else if ($('input[name="price-type"]:checked').val() == "auto") {
			if ($('#lgFormGroupFloor').val() == "") {
				jAlert('층수를 선택해 주세요', '층수 입력');
			} else if ($("#lgFormGroupPrice").val() == "") {
				jAlert('가격을 확인해 주세요', '가격 확인');
			} else {
				ltvCalc('${ aptCode }', $("#lgFormGroupPrice").val(), $('input[name="user-price"]:checked').val());
			}
		} else if ($('input[name="price-type"]:checked').val() == "passive") {
			if ($("#lgFormGroupPricePassive").val() == "") {
				jAlert('가격을 확인해 주세요', '가격 확인');
			} else {
				ltvCalc('${ aptCode }', $("#lgFormGroupPricePassive").val(), $('input[name="user-price"]:checked').val());
			}
		}
	}

	function ltvCalc(aptCode, price, userPriceInfo) {
		$.ajax({
			url : '${ pageContext.request.contextPath }/loan/loanCalc',
			type : 'post',
			data : {
				aptCode : aptCode,
				price : price,
				userPriceInfo : userPriceInfo
			},
			success : function(data) {
				$('.ltv-calc-result').html(data)
			}
		})
	}
</script>
<div id="flip">대출 한도 계산법 자세히보기</div>
<div id="panel">
	<div class="ltv-info">
		<span>LTV(Loan To Value)란 뭔가요? <i class="fas fa-caret-down ltv-info-icon" aria-hidden="true" style="color: #000; font-size: 23px;"></i> <i class="fas fa-caret-up ltv-info-icon" aria-hidden="true" style="color: #000; font-size: 23px; display: none"></i>
		</span>
		<div id="ltv-info">
			LTV(Loan TO Value)는 담보 대비 대출금액의 비율을 나타내는 지표로, 주로 주택 담보 대출의 대출가능 금액을 산출할 때 사용합니다. <br> 예를 들어 주택 가격이 2억이고, LTV가 70%라면 대출액의 최대한도는 1억 4000만원이 됩니다. <br> 주택가격(담보가치)의 산정을 위해 4가지 방법 중 하나를 선택하여 적용할 수 있습니다. 일반적으로 국세청의 기준시가 또는 국민은행에서 제공하는 KB 부동산 시세가 많이 사용됩니다.
		</div>
	</div>
	<div class="loan-law">
		<span>규제 현황 <i class="fas fa-caret-down loan-law-icon" aria-hidden="true" style="color: #000; font-size: 23px;"></i> <i class="fas fa-caret-up loan-law-icon" aria-hidden="true" style="color: #000; font-size: 23px; display: none"></i>
		</span>
		<div id="loan-law">
			지역별 LTV 기준
			<table class="table">
				<thead class="loan-law-thead">
					<tr>
						<th scope="col">주택가격</th>
						<th scope="col">구분</th>
						<th scope="col">투기지역<br>투기과열지구
						</th>
						<th scope="col">조정대상 지역</th>
						<th scope="col">그 외의 수도권</th>
						<th scope="col">비 규제 지역</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row" rowspan="4">9억이하</th>
						<td>서민실수요자</td>
						<td>50%</td>
						<td>60%</td>
						<td>70%</td>
						<td>70%</td>
					</tr>
					<tr>
						<td>무주택자</td>
						<td>40%</td>
						<td>50%</td>
						<td>70%</td>
						<td>70%</td>
					</tr>
					<tr>
						<td>1주택 보유자</td>
						<td>40%</td>
						<td>50%</td>
						<td>70%</td>
						<td>70%</td>
					</tr>
					<tr>
						<td>2주택 보유자</td>
						<td>불가</td>
						<td>불가</td>
						<td>60%</td>
						<td>60%</td>
					</tr>
					<tr>
						<th scope="row" rowspan="2">9억 초과</th>
						<td>9억 이하분</td>
						<td>40%</td>
						<td>50%</td>
						<td colspan="4" rowspan="4">공시가격 9억 이하<br>주택 구입 기준과 동일
						</td>
					</tr>
					<tr>
						<td>9억 초과분</td>
						<td>20%</td>
						<td>30%</td>
					</tr>
					<tr>
						<th scope="row" rowspan="2">15억 초과</th>
						<td>9억 이하분</td>
						<td>불가</td>
						<td>50%</td>
					</tr>
					<tr>
						<td>9억 초과분</td>
						<td>불가</td>
						<td>30%</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="loan-cal-info">
		<span>대출 가능 금액은 어떻게 추출되나요? <i class="fas fa-caret-down loan-cal-info-icon" aria-hidden="true" style="color: #000; font-size: 23px;"></i> <i class="fas fa-caret-up loan-cal-info-icon" aria-hidden="true" style="color: #000; font-size: 23px; display: none"></i>
		</span>

		<div id="loan-cal-info">
			대출가능금액은 주택가격과 LTV 규제의 영향을 반영한 대출최대한도에서 우선변제대상(방공제) 영향을 반영한 예상 대출한도입니다.<br> 실제 대출한도는 DTI 등 여러 복합적인 요소들에 의해 달라질 수 있습니다.<br> 하나방 주택가격 : 실거래가를 기준으로 주택가격이 측정됩니다.<br> 방공제 : 은행 등 대출기관이 임대되지 않은 방에 대한 소액임차보증금을 대출한도에서 차감하는 것<br>
		</div>
	</div>
</div>
<div class="loanPreview justify-content-center margin-top-20">
	<div class="container col-9">
		<form>
			<div class="form-group row">
				<label for="lgFormGroupInput" class="col-sm-3 col-form-label col-form-label-lg loan-form">선택한 아파트</label>
				<div class="col-sm-7">
					<input type="text" class="form-control form-control-lg" id="lgFormGroupInput" value="${ aptName }" autocomplete="off">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-5">
					<input type="radio" class="lgFormGroupPriceCheck" value="auto" name="price-type"> <label for="auto">실거래가 기초로 가격 입력</label>
				</div>
				<div class="col-sm-5">
					<input type="radio" class="lgFormGroupPriceCheck" value="passive" name="price-type"> <label for="passive">직접 기입</label>
				</div>
			</div>
			<c:if test="${ not empty aptAreaList }">
				<div class="form-group row lgFormGroupArea">
					<label for="lgFormGroupArea" class="col-sm-3 col-form-label col-form-label-lg loan-form">아파트 면적</label>
					<div class="col-sm-7">
						<select id="lgFormGroupArea" name="area" class="form-control form-control-lg loan-select-arrow">
							<c:forEach items="${ aptAreaList }" var="aptArea">
								<option value="${ aptArea }">${ aptArea }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-2 loan-form-sub-text">
						m<sup>2</sup>
					</div>
				</div>

				<div class="form-group row lgFormGroupFloor">
					<label for="lgFormGroupFloor" class="col-sm-3 col-form-label col-form-label-lg loan-form">아파트 층수</label>
					<div class="col-sm-7">
						<input type="text" class="form-control form-control-lg" id="lgFormGroupFloor" placeholder="층수를 입력하세요" autocomplete="off">
					</div>
					<div class="col-sm-2 loan-form-sub-text">층</div>
				</div>
			</c:if>
			<div class="form-group row lgFormGroupPrice"></div>
			<div class="form-group row">
				<div class="col-sm-3">
					<input type="radio" class="userPriceInfo" value="서민실수요자" name="user-price" checked="checked"> <label for="서민실수요자">서민실수요자</label>
				</div>
				<div class="col-sm-3">
					<input type="radio" class="userPriceInfo" value="무주택자" name="user-price"> <label for="무주택자">무주택자</label>
				</div>
				<div class="col-sm-3">
					<input type="radio" class="userPriceInfo" value="1주택" name="user-price"> <label for="1주택">1주택 보유자</label>
				</div>
				<div class="col-sm-3">
					<input type="radio" class="userPriceInfo" value="2주택" name="user-price"> <label for="2주택">2주택 보유자</label>
				</div>
			</div>
			<button type="button" class="btn btn-outline-info btn-sm btn-block" onclick="calcLoan()">대출 한도 계산하기</button>
		</form>
	</div>
	<div class="container col-9 ltv-calc-result"></div>
</div>
<div class="loan-info">
	<span class="badge badge-danger">참고사항</span> 고객님의 이해를 돕기위한 조회로 실제 대출금액 및 금리와 상이할 수 있습니다.
</div>
