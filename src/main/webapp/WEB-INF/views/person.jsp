<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<spring:url value="/resources/css/styles.css" var="mainCss" />
	<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
	<!-- spring:url value="/resources/js/main.js" var="mainJs" /-->
	
	<link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <!-- script src="${mainJs}"></script-->
</head>
<body>
<ul>
  <li><a href="persons">Home</a></li>
  <li><a href="tasks">Tasks</a></li>
  <li><a href="rewards">Rewards</a></li>
  <li><a href="login">Login</a></li>
</ul>
<br />

<h1>
    Add a Person
</h1>
 
<c:url var="addAction" value="/person/add" ></c:url>
 
<form:form action="${addAction}" commandName="person">
<table>
    <c:if test="${!empty person.name}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8"  disabled="true" />
            <form:hidden path="id" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name" />
        </td> 
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty person.name}">
                <input type="submit"
                    value="<spring:message text="Edit Person"/>" />
            </c:if>
            <c:if test="${empty person.name}">
                <input type="submit"
                    value="<spring:message text="Add Person"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
    <c:forEach items="${listPersons}" var="person">
		<div class="summary">
            ${person.id}<br />
            ${person.name}<br />
            ${person.dateOfBirth}<br />
            <a href="<c:url value='/edit/${person.id}' />" >Edit</a><br />
            <a href="<c:url value='/remove/${person.id}' />" >Delete</a><br />
	        <c:forEach items="${person.todoTasks}" var="todoTasks">
				<div class="tasks">
            		${todoTasks.id}<br />
            		${todoTasks.summary}<br />
		    	</div>
		    </c:forEach>
	    </div>
	    
    </c:forEach>
</c:if>

</body>
</html>