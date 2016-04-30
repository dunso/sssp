<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".lastName").change(function(){
				
				var val = $(this).val();
				val = $.trim(val);
				$(this).val(val);
				//若修改的lastName和之前的lastName一致，则不发送Ajax请求，直接alert：lastName可用！
				var _oldLastName= $("._oldLastName").val();
				_oldLastName = $.trim(_oldLastName);
				if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
					alert("LastName可用");
					return;
				}
				
				var url = "${pageContext.request.contextPath}/ajaxValidateLastName";
				var args = {"lastName":val,"date":new Date()};
				$.post(url,args,function(data){
					if(data == "0"){
						alert("LastName可用");
					}else if(data == "1"){
						alert("LastName不可用");
					} 
				});
			});
		})
	</script>
  </head>
  
  <body>
    <h1><center> 增加 ！</center></h1> <br/><br/>
    
    
    <c:set value="${pageContext.request.contextPath}/emp" var="url"></c:set>
    <c:if test="${employee.id != null}">
    
    	<c:set value="${pageContext.request.contextPath}/emp/${employee.id}" var="url"></c:set>
    </c:if>
    
    <form:form acton="${url}" method="POST" modelAttribute="employee">
	   
	    <c:if test="${employee.id != null }">
	    	<form:hidden path="id"/>
 	    <%-- 	<input type="hidden" class="_oldLastName" value="${employee.lastName}"  --%>
	    	<input type="hidden" name="_method" value="PUT"/>
	    </c:if>
    
    	LastName:<form:input path="lastName" class="lastName"/><br/>
    	Email:<form:input path="email"/><br/>
    	Birth:<form:input path="birth"/><br/>
    	Department:
    	<form:select path="department.id" items="${departments }" 
    	itemLabel="departmentName" itemValue="id"></form:select><br/>
    	<input type="submit" value ="submit"/>
    	
    </form:form>
    	
  </body>
</html>
