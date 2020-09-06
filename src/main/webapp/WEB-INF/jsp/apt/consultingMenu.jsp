<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container ">
	<div class="card-deck row justify-content-center">
		<div class="card consulting-card col-lg-4" onclick="onlineChat('${kaptCode}')">
			<img src="${ pageContext.request.contextPath }/resources/images/onlineChat.png" class="card-img-top card-img-consulting mx-auto consulting-img" alt="...">
			<div class="card-body">
				<h5 class="card-title">온라인 상담</h5>
				<p class="card-text">해당 아파트를 가지고 온라인 주택담보대출 상담을 할 수 있습니다.</p>
			</div>
		</div>
		<div class="card consulting-card col-lg-4">
			<img src="${ pageContext.request.contextPath }/resources/images/reservation.png" class="card-img-top card-img-consulting mx-auto consulting-img" alt="...">
			<div class="card-body">
				<h5 class="card-title">오프라인 상담 예약</h5>
				<p class="card-text">오프라인 상담을 예약할 수 있습니다. </p>
			</div>
		</div>
	</div>
</div>