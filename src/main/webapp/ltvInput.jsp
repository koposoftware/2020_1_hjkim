<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>하나방</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/jquery.alerts.css">
<jsp:include page="/WEB-INF/jsp/include/link.jsp" />
<script type="text/JavaScript" src="${ pageContext.request.contextPath }/resources/js/jquery.alerts.js" ></script>
<script>
	/* 모달팝업 */
	function choiceConfirmWindow() {
		var checkArea = $('#sido option:selected').text()
		var checkAreaCode = $('#sido option:selected').val()
		var st = $(":input:radio[name=radio1]:checked").val();
		var division = $("label[for='" + st +"']").text();
		
		if($('#sigungu option:selected').val() != '000'){
	    	checkArea += ' ' + $('#sigungu option:selected').text(); 
	    	checkAreaCode += $('#sigungu option:selected').val(); 
		}
		if($('#eupmyeondong option:selected').val() != '000'){
	    	checkArea += ' ' + $('#eupmyeondong option:selected').text(); 
	    	checkAreaCode += $('#eupmyeondong option:selected').val(); 
		}
		var checkHtml = ''
		checkHtml += '<div>선택하신 지역</div>'
		checkHtml += '<span>&nbsp;&nbsp;'+ checkArea +'</span>'
		checkHtml += '<div>선택하신 지역 유형</div>'
		checkHtml += '<span>&nbsp;&nbsp;'+ division +'</span>'
	    jConfirm(checkHtml , "등록전 확인해주세요!", function(result) {
	        if(result == true) {
	        	if(division == '투기지역 및 투기과열지구'){
	    			division = '투기지역'
	    		}else if(division == '조정대상 지역 외 수도권'){
	    			division = '수도권'
	    		}
	        	ltvRegister(checkAreaCode.padEnd(10,'0'), division)
	        } 
	    });
	}
	function registerAlert(bool){
		if(bool == true){
			jAlert('등록에 성공하였습니다', '성공');
		}else{
			jAlert('등록에 실패하였습니다.', '실패');
		}
	}
	$(function() {
		bjdCodeAjax("sido", 0);
	})

	$(document).on('change', '#sido', function() {
		var sidoCode = $('#sido option:selected').val();
		$('#sigungu').empty()
		$('#sigungu').append('<option value="000" selected="selected">시/군/구</option>')
		bjdCodeAjax("sigungu", sidoCode)
	})
	$(document).on('change', '#sigungu', function() {
		var eupmyeondongCode = $('#sido option:selected').val() + $('#sigungu option:selected').val();
		$('#eupmyeondong').empty()
		$('#eupmyeondong').append('<option value="000" selected="selected">읍/면/동</option>')
		bjdCodeAjax("eupmyeondong", eupmyeondongCode)
	})

	/* 시도/시군구/읍면동정보를 가지고 오는 함수 */
	function bjdCodeAjax(division, code) {
		var subStartIdx = 0
		var subEndIdx = 0
		var codeJson = {}
		if (division == "sido") {
			subStartIdx = 0
			subEndIdx = 2
			codeJson = {
				code : 0
			}
		} else if (division == "sigungu") {
			subStartIdx = 2
			subEndIdx = 3
		} else if (division == "eupmyeondong") {
			subStartIdx = 5
			subEndIdx = 3
		}

		if (code != 0) {
			codeJson = {
				code : code
			}
		}
		$.ajax({
			url : "${ pageContext.request.contextPath }/admin/" + division,
			type : 'post',
			data : codeJson,
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					var bjdCode = String(data[i].bjdCode)
					$('#' + division).append('<option value="' + bjdCode.substr(subStartIdx, subEndIdx) + '">' + data[i].bjdName + '</option>')
				}
			}
		})
	}

	/* ltv를 db에 저장하는 함수 */
	function ltvRegister(code, areaInfo) {
		$.ajax({
			url: '${ pageContext.request.contextPath }/admin/ltvRegister',
			type: 'post',
			data: {
				bjdCode : code,
				areaInfo : areaInfo
			},
			success: function(){
				registerAlert(true)
			},
			error: function(){
				registerAlert(false)
			}
		})
	}
</script>
</head>
<body class="boxed">
	<!-- Loader -->
	<div class="fh5co-loader"></div>
	<div id="wrap">
		<div id="fh5co-page">
			<header>
				<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
			</header>
			<!-- Home Section -->
		</div>
	</div>
	<section class="margin-top-100 container-fluid">
		<div class="container ltv-container">
			<ul class="nav justify-content-end sub-nav">
				<li class="nav-item"><a class="nav-link" href="#">LTV등록</a></li>
			</ul>
			<div class="row justify-content-center margin-top-20">
				<h3 class="col-9">지역을 선택해주세요.</h3>
			</div>

			<div class="row justify-content-center select-bjdcode">
				<div class="col-3">
					<select id="sido">
						<option value="00" selected="selected">시/도</option>
					</select>
				</div>
				<div class="col-3">
					<select id="sigungu">
						<option value="000" selected="selected">시/군/구</option>
					</select>
				</div>
				<div class="col-3">
					<select id="eupmyeondong">
						<option value="000" selected="selected">읍/면/동</option>
					</select>
				</div>
			</div>
			<div class="row justify-content-center margin-top-40">
				<h3 class="col-9">지역 유형을 선택해주세요.</h3>
			</div>
			<div class="row justify-content-center division-select">
				<div class="col-9 cf">
					<input type="radio" name="radio1" id="free" value="free"> 
					<label class="free-label four col division-col" for="free">투기지역 및 투기과열지구</label> 
					<input type="radio" name="radio1" id="basic" value="basic" checked> 
					<label class="basic-label four col division-col" for="basic">조정대상지역</label> 
					<input type="radio" name="radio1" id="premium" value="premium"> 
					<label class="premium-label four col division-col" for="premium">조정대상 지역 외 수도권</label>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-9 justify-content-right">
					<button class="btn btn-outline-secondary col-3 float-right margin-top-20" type="button" id="myBtn" onclick="choiceConfirmWindow()">등록하기</button>
				</div>
			</div>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>


</body>
</html>