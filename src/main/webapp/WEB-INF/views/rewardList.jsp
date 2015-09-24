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

	<h1>Rewards list</h1>
	<c:if test="${!empty listRewards}">
    	<c:forEach items="${listRewards}" var="reward">
    		<c:choose>
    			<c:when test="${reward.points >= 10}">
    				<div class="highReward">
    			</c:when>
    			<c:when test="${reward.points >= 5}">
    				<div class="mediumReward">
    			</c:when>
    			<c:otherwise>
    				<div class="lowReward">
    			</c:otherwise>
    		</c:choose>
					<h3 class="reward">${reward.summary}</h3>
					${reward.description}<br />
					${reward.points}
   				</div>
    	</c:forEach>
   	</c:if>	
</body>
</html>	