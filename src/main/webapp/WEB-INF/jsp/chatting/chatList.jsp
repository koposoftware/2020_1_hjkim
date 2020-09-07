<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-hover chat-list">
	<thead>
		<tr>
			<th scope="col">no</th>
			<c:if test="${ loginVO.type eq 'u' or loginVO.type eq 'U' }">
				<th scope="col">상담사</th>
			</c:if>
			<c:if test="${ loginVO.type eq 'c' or loginVO.type eq 'C' }">
				<th scope="col">고객이름</th>
			</c:if>
			<th scope="col">시작시간</th>
			<th scope="col">종료시간</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ chatListUserNameList }" var="chat">
			<tr>
				<td>${ chat.chatListVO.rn }</td>
				<c:if test="${ loginVO.type eq 'u' or loginVO.type eq 'U' }">
					<td width="40%">${ chat.counselorVO.name }상담사</td>
				</c:if>
				<c:if test="${ loginVO.type eq 'c' or loginVO.type eq 'C' }">
					<td width="40%">${ chat.userVO.name }고객님</td>
				</c:if>
				<td>${ chat.chatListVO.startDate }</td>
				<td>${ chat.chatListVO.endDate }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="paginationBox">
	<ul class="pagination justify-content-end">
		<c:if test="${pagination.prev}">
			<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a></li>
		</c:if>
		<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
			<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></li>
		</c:forEach>
		<c:if test="${pagination.next}">
			<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')">Next</a></li>
		</c:if>
	</ul>
</div>

