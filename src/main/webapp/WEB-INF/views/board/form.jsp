<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form id = "form" method="post" action="/{menuType}/save">
		<input type="hidden" name="boardSeq" value="${board ==null ? 0 :board.boardSeq }">
		<input type="hidden" name="boardType" value="COMMUNITY">
		  <div class="row mb-3">
		    <label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"></spring:message></label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="title" value="${board.title}" id="title" placeholder="<spring:message code="placeholder.required"></spring:message>">
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="content" class="col-sm-2 col-form-label"><spring:message code="board.content"></spring:message></label>
		    <div class="col-sm-10">
		      <textarea class="form-control" id="content" name="content" placeholder="<spring:message code="placeholder.required"></spring:message>">${board.content}</textarea>
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary"><spring:message code="button.save"></spring:message></button>
	  </form>
  </div>

	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$(function(){
			var $form = $('#form');
			$form.bind('submit', function() {
				$.ajax({
					url: '/${menuType}/save',
					type: 'post',
					data : $form.serialize(),
					dataType: 'json',
					success : function(response){
						if(response.code == 'SUCCESS'){
							alert("저장되었습니다.")
							location.href ='/${menuType}/'+ response.data;
						}else{
							alert(response.message);
							
						}
						console.log(response);
					}
				});
				return false;
			});
		
		});
		
	</script>
</body>
</html>