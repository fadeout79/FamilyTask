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
	<spring:url value="/resources/js/jquery.ui.touch-punch.min.js" var="jqueryUiTouchPunchJs" />
	
	<link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <link href="${jqueryUiCss}" rel="stylesheet" />
    <script src="${jqueryUiJs}"></script>
    <script src="${jqueryUiTouchPunchJs}"></script>
    
    <script type="text/javascript">

    function Drop(event, ui) {
      var draggableId = ui.draggable.attr("id");
      var droppableId = $(this).attr("id");
      if (draggableId.indexOf("taskId") > -1 && droppableId.indexOf("taskPerson") > -1) {
          var taskId = draggableId.substr(draggableId.indexOf("taskId")+6);
          var personId = droppableId.substr(droppableId.indexOf("taskPerson")+10);
          var link = "<c:url value='/todoTasks/taskDone' />?tasksId=" + taskId + "&personId=" + personId;
          $.ajax({
              url: link,
              success: function( data ) {
            	  $("#taskId"+taskId).remove();
            	  $("#taskPerson"+personId).empty();
            	  $("#taskPerson"+personId).append("<h3 class='summaryTitle'>Done</h3>");
            	  $.each(data, function(i, item) {
            		  if (item.done) {
            			  $("#taskPerson"+personId).append("<div class='tasksDone' id='taskId" + item.id + "'>" + item.summary + "<br /></div>");
            		  }
            	  });
                  console.log( data );
              }
          })
      }

    }

    </script>
</head>
<body>
<jsp:include page="header.jsp" />

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
	        	<c:if test="${!todoTasks.done}">
					<div class="tasks" id="taskId${todoTasks.id}" >
						<c:if test="${!empty todoTasks.image }">
							<img class="resize" src="<c:url value='/images/getImage/${todoTasks.image}' />" alt="myImage" />
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
	    <div class="taskPerson" id="taskPerson${person.id}" >
	    	<h3 class="summaryTitle">Done</h3>
	        <c:forEach items="${person.todoTasks}" var="todoTasks">
	        	<c:if test="${todoTasks.done}">
					<div class="tasksDone" id="taskId${todoTasks.id}" >
           				${todoTasks.summary}<br />
	    			</div>
	    			<script>
	    				$("#taskId${todoTasks.id}").draggable();
	    			</script>
		    	</c:if>
		    </c:forEach>
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