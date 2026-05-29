<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketCategory.model.*"%>

<% 
   // 見 com.ticketCategory.controller.TicketCategoryServlet.java 存入 req 的 ticketCategoryVO 物件
   TicketCategoryVO ticketCategoryVO = (TicketCategoryVO) request.getAttribute("ticketCategoryVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>票券分類資料修改 - update_ticketCategory_input.jsp</title>

<style>
  table#table-1 {
	background-color: #FFE4E1; /* 淡玫瑰色區分 */
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #FFE4E1;
  }
  th, td {
    padding: 5px; /* 稍微留點空隙比較好點擊 */
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>票券分類資料修改 - update_ticketCategory_input.jsp</h3>
		 <h4>
		    <a href="<%=request.getContextPath()%>/frontend/ticketCategory/select_page.jsp">回首頁</a>
		</h4>
		 <!-- 
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		  -->
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="ticketCategory.do" name="form1">
<table>
	<tr>
		<td>門票分類編號:<font color=red><b>*</b></font></td>
		<td><%=ticketCategoryVO.getTicketCategoryId()%></td>
	</tr>
	<tr>
		<td>門票分類名稱:</td>
		<td><input type="TEXT" name="ticketCategoryName" value="<%=ticketCategoryVO.getTicketCategoryName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>門票分類狀態:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="ticketCategoryStatus">
				<option value="1" <%= (ticketCategoryVO.getTicketCategoryStatus() == 1) ? "selected" : "" %>>1: 啟用</option>
				<option value="0" <%= (ticketCategoryVO.getTicketCategoryStatus() == 0) ? "selected" : "" %>>0: 停用</option>
			</select>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ticketCategoryId" value="<%=ticketCategoryVO.getTicketCategoryId()%>">
<input type="submit" value="送出修改">
</FORM>

</body>
</html>