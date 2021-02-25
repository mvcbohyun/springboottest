<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/utils.tld"%>
<%@ attribute name="item"
	type="com.bhjang.mvc.domain.BaseCodeLabelEnum[]" required="true"%>
<%@ attribute name="values" type="com.bhjang.mvc.domain.BaseCodeLabelEnum[]" required="false"%>
<c:forEach var="boardType" items="${boardTypes}" varStatus="status">
	<div class="form-check form-check-inline">
		<input class="form-check-input" 
		name="boardTypes" 
		type="checkbox"
		value="${boardType.code()}"
		${utils:isSelected(values,boardType)? 'checked="checked"' :'' }
		id="board-type${status.count}"> 
		<label class="form-check-label"for="board-type${status.count}"> ${boardType.label()} </label>
	</div>
	
</c:forEach>






