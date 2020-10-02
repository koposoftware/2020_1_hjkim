<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HH:mm:ss", Locale.KOREA);
    String today = format.format(new Date());
    String days = request.getParameter("days");
    String fileName = "hanabang" + today;
      response.setHeader("Content-Type", "application/vnd.ms-xls");
    response.setHeader("Content-Disposition",
            "attachment; filename=" + new String((fileName).getBytes("KSC5601"), "8859_1") + ".xls");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>엑셀 다운로드</title>
<style>
.table-wrapper-scroll-y {
    display: block;
    max-height: 700px;
    overflow-y: auto;
    -ms-overflow-style: -ms-autohiding-scrollbar;
}
#consultingSummary{
	white-space: pre;
}
#excelTable{
	background-color: #fff;
}
.excelHead {
	background-color: #eefdf7;
}
.tableTitle{
	background-color: #f1f1f1;
	width: 30%;
}
.hanabang{
	color: red;
}
thead {
	font-weight: bold;
}
</style>
</head>
<body>
	<h3> ● Hanabang ● </h3>
	<h5 class="hanabang"> 고객님의 이해를 돕기위한 상담으로 실제 추천 대출 상품 및 금리와 상이할 수 있습니다.<br>
						자세한 사항은 오프라인 대출 상담을 통해 정보를 얻으시길 바랍니다. <br> </h5>
	<br>
	<h4> 상담 요약 </h4>
    <table id="excelTable" border="1">
        <thead class="excelHead">
            <tr>
                <th colspan = "4">선택하신 아파트 정보</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${excelList}" var="data">
                <tr>
                    <td class="tableTitle">${ data.title }</td>
                    <td class="tableContent" colspan="3">${ data.content }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 	<br>
	<h4> 아파트 정보 </h4>
    <table id="excelTable" border="1">
        <thead class="excelHead">
            <tr>
                <th colspan = "4">아파트 정보</th>
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td class="tableTitle">단지명</td>
        		<td colspan="3">${aptBasic.kaptName}</td>
        	</tr>
        	<tr>
        		<td class="tableTitle">주소(지번)</td>
        		<td colspan="3">${aptDetail.kaptAddr}</td>
        	</tr>
        	<tr>
        		<td class="tableTitle">주소(도로명)</td>
        		<td colspan="3">${aptDetail.doroJuso}</td>
        	</tr>
        </tbody>
    </table>
    <br>
    <h4> 선택하신 아파트 정보 </h4>
    <table id="excelTable" border="1">
        <thead class="excelHead">
            <tr>
                <th colspan = "4">아파트 실거래가 정보</th>
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td class="tableTitle">거래년월 </td>
        		<td class="tableTitle">거래일 </td>
        		<td class="tableTitle">거래금액(만 원)</td>
        		<td class="tableTitle">거래층</td>
        		<td class="tableTitle">거래면적</td>
        	</tr>
        	<c:forEach items="${ aptPriceList }" var="price">
                <tr>
                    <td class="tableContent">${ price.yymm }</td>
                    <td class="tableContent">${ price.dd }</td>
                    <td class="tableContent">${ price.price }</td>
                    <td class="tableContent">${ price.floor }</td>
                    <td class="tableContent">${ price.area }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    
</body>
</html>
