<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/styles.css" var="mainCss" />
<spring:url value="/resources/css/jquery-ui.css" var="jqueryUiCss" />
<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
<spring:url value="/resources/js/jquery-ui.js" var="jqueryUiJs" />
<spring:url value="/resources/js/jquery.ui.touch-punch.min.js"
	var="jqueryUiTouchPunchJs" />

<link href="${mainCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<link href="${jqueryUiCss}" rel="stylesheet" />
<script src="${jqueryUiJs}"></script>
<script src="${jqueryUiTouchPunchJs}"></script>

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="taskPerson" id="personId${person.id}">
          ${person.name}<br />
          ${person.dateOfBirth}<br />
	<c:forEach items="${person.todoTasks}" var="todoTasks">
		<c:if test="${!todoTasks.done}">
			<div class="tasks" id="taskId${todoTasks.id}">
				<c:if test="${!empty todoTasks.image }">
					<img class="resize"
						src="<c:url value='/images/getImage/${todoTasks.image}' />"
						alt="myImage" />
				</c:if>
				${todoTasks.summary}<br />
			</div>
			<script>
  				$("#taskId${todoTasks.id}").draggable({
  					helper: "clone"
  				});
  			</script>
		</c:if>
	</c:forEach>
	</div>

</body>
</html>