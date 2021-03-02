<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
	
	<div class="container">
		<form id = "form" method="get" action="/list">
		<input type="hidden" name="boardType" value="COMMUNITY">
		  <div class="row mb-3">
		    <label for="title" class="col-sm-2 col-form-label"><spring:message code="search.keyword"></spring:message></label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="keyword" value="${parameter.keyword}" id="keyword" placeholder="<spring:message code="placeholder.required"></spring:message>">
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary"><spring:message code="button.search"></spring:message></button>
		  <table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col"><spring:message code="board.title"></spring:message></th>
		      <th scope="col"><spring:message code="board.viewCount"></spring:message></th>
		      <th scope="col"><spring:message code="board.regDate"></spring:message></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="board" items="${boardList }" varStatus="status">
		    <tr>
		      <th scope="row">${status.count}</th>
		      <td>${board.title}</td>
		      <td>${board.viewCount}</td>
		      <td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/> </td>
		    </tr>
		    </c:forEach>
		    <c:if test="${fn:length(boardList) ==0 }">
		    <tr>
		    	<td colspan="4"><spring:message code="msg.board.empty"></spring:message>
		    	</td>
		    </tr>
		    </c:if>
		  </tbody>
	</table>
	  </form>
  </div>

	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$(function(){
			var $form = $('#form');
			$form.bind('submit', function() {
				$.ajax({
					url: '/board/save',
					type: 'post',
					data : $form.serialize(),
					dataType: 'json',
					success : function(data){
						if(data.code == 'SUCCESS'){
							
						}else{
							alert(data.message);
						}
						console.log(data);
					}
				});
				return false;
			});
		
		});
		
	</script>
</body>
</html>