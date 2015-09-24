<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
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
<title>Add a new reward</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1>Add a reward</h1>

	<c:url var="addAction" value="/reward/add" />

	<form:form action="${addAction}" method="post" modelAttribute="newReward">
		<table>
			<tr>
				<td><form:label path="id">
						<spring:message text="ID" />
					</form:label></td>
				<td><form:input path="id" readonly="true" size="8"
						disabled="true" /> <form:hidden path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="summary">
						<spring:message text="Summary" />
					</form:label></td>
				<td><form:input path="summary" /></td>
			</tr>
			<tr>
				<td><form:label path="description">
						<spring:message text="Description" />
					</form:label></td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="points">
						<spring:message text="Points" />
					</form:label></td>
				<td><form:input path="points" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Add Reward"/>" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>