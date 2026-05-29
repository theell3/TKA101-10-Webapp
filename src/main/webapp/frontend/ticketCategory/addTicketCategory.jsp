<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketCategory.model.*"%>

<% //見com.ticketCategory.controller.TicketCategoryServlet.java存入req的ticketCategoryVO物件 (此為輸入格式有錯誤時的ticketCategoryVO物件)
   TicketCategoryVO ticketCategoryVO = (TicketCategoryVO) request.getAttribute("ticketCategoryVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>票券種類資料新增 - addTicketCategory.jsp</title>

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
    width: 450px;
    background-color: white;
    margin-top: 1px;
    margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>票券種類資料新增 - addTicketCategory.jsp</h3></td><td>
		 <h4>
		    <a href="<%=request.getContextPath()%>/frontend/ticketCategory/select_page.jsp">回首頁</a>
		</h4>
		 <!-- 
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		  -->
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="../../ticketCategory/ticketCategory.do" name="form1">

<table>
	
	<tr>
		<td>票券種類名稱:</td>
		<td><input type="TEXT" name="ticketCategoryName" value="<%= (ticketCategoryVO==null)? "測試用" : ticketCategoryVO.getTicketCategoryName()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>票券狀態:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="ticketCategoryStatus">
				<option value="1" ${(ticketCategoryVO.ticketCategoryStatus == 1)? 'selected':'' }>上架販售</option>
				<option value="0" ${(ticketCategoryVO.ticketCategoryStatus == 0)? 'selected':'' }>暫不販售</option>
			</select>
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>