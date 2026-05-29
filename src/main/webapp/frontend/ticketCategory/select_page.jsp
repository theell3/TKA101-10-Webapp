<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Ticket Category: Home</title>

<style>
  table#table-1 {
    width: 450px;
    background-color: #FFE4E1; /* 換個顏色區分，使用淡玫瑰色 */
    margin-top: 5px;
    margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Ticket Category: Home</h3></td></tr>
</table>

<p>This is the Home page for Ticket Category</p>

<h3>資料查詢:</h3>
    
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
  <%-- 查詢全部 --%>
  <li><a href='listAllTicketCategory.jsp'>List</a> all Ticket Categories. <br><br></li>
  

  <%-- 實例化 Service 供底下的下拉選單使用 --%>
  <jsp:useBean id="ticketCategorySvc" scope="page" class="com.ticketCategory.model.TicketCategoryService" />
   
  
  <%-- 下拉選單：透過分類名稱查詢 --%>
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/ticketCategory/ticketCategory.do">
       <b>選擇門票分類名稱:</b>
       <select size="1" name="ticketCategoryId">
         <c:forEach var="ticketCategoryVO" items="${ticketCategorySvc.all}" > 
          <option value="${ticketCategoryVO.ticketCategoryId}">${ticketCategoryVO.ticketCategoryName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>門票分類管理</h3>

<ul>
  <li><a href='addTicketCategory.jsp'>Add</a> a new Ticket Category.</li>
</ul>

</body>
</html>