<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HH24:mm:ss", Locale.KOREA);
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
</style>
</head>
<body>
    <table id="excelTable" border="1">
        <thead class="excelHead">
            <tr>
                <th colspan = "2">Hanabang 상담 요약</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${excelList}" var="data">
                <tr>
                    <td class="tableTitle">${ data.title }</td>
                    <td class="tableContent">${ data.content }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 
</body>
</html>
