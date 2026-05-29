<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticketCategory.model.*"%>

<%
    TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
    List<TicketCategoryVO> list = dao.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>門票分類資料 - listAllTicketCategory.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
    width: 800px;
    background-color: white;
    margin-top: 5px;
    margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有門票分類資料 - listAllTicketCategory.jsp</h3>
		 <h4>
		    <a href="<%=request.getContextPath()%>/frontend/ticketCategory/select_page.jsp">回首頁</a>
		</h4>
		 <!-- 
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		  -->
		 
	</td></tr>
</table>

<table>
	<tr>
		<th>分類編號</th>
		<th>分類名稱</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<!--  先拿掉分頁 page1.file -->
	
	<!-- 
	TODO 問老師為什麼會有 
		Multiple annotations found at this line:
		- The TagExtraInfo class for "c:forEach" (org.apache.taglibs.standard.tei.ForEachTEI) could not be 
		 instantiated
		- The TagExtraInfo class for "c:forEach" (org.apache.taglibs.standard.tei.ForEachTEI) could not be 
		 instantiated
	-->
	<c:forEach var="ticketCategoryVO" items="${list}">
		<tr>
			<td>${ticketCategoryVO.ticketCategoryId}</td>
			<td>${ticketCategoryVO.ticketCategoryName}</td>
			<td>
			    <c:if test="${ticketCategoryVO.ticketCategoryStatus == 0}">0：停用</c:if>
			    <c:if test="${ticketCategoryVO.ticketCategoryStatus == 1}">1：啟用</c:if>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ticketCategory/ticketCategory.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ticketCategoryId" value="${ticketCategoryVO.ticketCategoryId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ticketCategory/ticketCategory.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ticketCategoryId" value="${ticketCategoryVO.ticketCategoryId}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
	
</table>
<!--  先拿掉分頁 page2.file -->

</body>
</html>