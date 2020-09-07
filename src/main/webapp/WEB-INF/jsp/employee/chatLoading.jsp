<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ not empty history }">
	<c:forEach items="${ history }" var="history">
		<c:if test="${ history.sender eq userNo}">
			<li class="right clearfix">' <span class="chat-img pull-right">
			<img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />
			</span>
				<div class="chat-body clearfix">
					<div class="header">
						<strong class="pull-right primary-font">나</strong>
					</div>
					<p>${ history.content }</p>
				</div>
			</li>
		</c:if>
		<c:if test="${ history.sender ne userNo }">
			<li class="left clearfix"><span class="chat-img pull-left"> 
			<img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />
			</span>
				<div class="chat-body clearfix">
					<div class="header">
						<strong class="primary-font">고객</strong>
					</div>
					<p>${ history.content }</p>
				</div>
			</li>
		</c:if>
	</c:forEach>
	<hr>
</c:if>