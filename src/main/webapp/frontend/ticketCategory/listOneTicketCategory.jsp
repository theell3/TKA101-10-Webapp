<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ticketCategory.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  // TicketCategoryServlet.java (Controller), 存入 req 的 ticketCategoryVO 物件
  TicketCategoryVO ticketCategoryVO = (TicketCategoryVO) request.getAttribute("ticketCategoryVO"); 
%>

<html>
<head>
<title>票券分類資料 - listOneTicketCategory.jsp</title>

<style>
  table#table-1 {
	background-color: #FFE4E1; /* 更換為淡玫瑰色區分 */
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #FFE4E1;
  }
  th, td {
    padding: 8px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>門票分類資料 - listOneTicketCategory.jsp</h3>
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
		<th>門票分類編號</th>
		<th>門票分類名稱</th>
		<th>門票分類狀態</th>
	</tr>
	<tr>
		<td><%=ticketCategoryVO.getTicketCategoryId()%></td>
		<td><%=ticketCategoryVO.getTicketCategoryName()%></td>
		<td>
			<%= (ticketCategoryVO.getTicketCategoryStatus() == 1) ? "1: 啟用" : "0: 停用" %>
		</td>
	</tr>
</table>

</body>
</html>