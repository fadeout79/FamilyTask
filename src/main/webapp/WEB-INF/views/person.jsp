<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<spring:url value="/resources/css/styles.css" var="mainCss" />
	<spring:url value="/resources/css/jquery-ui.css" var="jqueryUiCss" />
	<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/jquery-ui.js" var="jqueryUiJs" />
	
	<link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <link href="${jqueryUiCss}" rel="stylesheet" />
    <script src="${jqueryUiJs}"></script>
    
    <script type="text/javascript">

    function Drop(event, ui) {
      var draggableId = ui.draggable.attr("id");
      var droppableId = $(this).attr("id");
      var link = "/taskDone?tasksI" =+ draggableId + "&personId=" + droppableId;
      $.ajax({
          url: link
          success: function( data ) {
              // But, this will!
              console.log( data );
              alert(draggableId);
          }
      })

    }
    var data;       

    </script>
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
<div class="col1">
<c:if test="${!empty listPersons}">
    <c:forEach items="${listPersons}" var="person">
		<div class="taskPerson" id="personId${person.id}">
            ${person.name}<br />
            ${person.dateOfBirth}<br />
            <a href="<c:url value='/edit/${person.id}' />" >Edit</a><br />
            <a href="<c:url value='/remove/${person.id}' />" >Delete</a><br />
	        <c:forEach items="${person.todoTasks}" var="todoTasks">
	        	<c:if test="${!todoTasks.isDone}">
				<div class="tasks" id="taskId${todoTasks.id}" >
            		${todoTasks.summary}<br />
		    	</div>
		    	<script>
		    		$("#taskId${todoTasks.id}").draggable();
		    	</script>
		    	</c:if>
		    </c:forEach>
	    </div>
	    <div class="taskPerson" id="taskPerson${person.id}" >
	    	<h3 class="summaryTitle">Done</h3>
	    </div>
	    <script>
	    	$("#taskPerson${person.id}").droppable({ drop: Drop });
	    </script>
    </c:forEach>
</c:if>
</div>
<div class="col2">
	<div class="summary">
		<h1 class="summaryTitle">Open tasks</h1>
	</div>
</div>
</body>
</html>