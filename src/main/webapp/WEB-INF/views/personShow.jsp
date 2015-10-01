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
		${person.name}<br /> ${person.dateOfBirth}<br />
		<canvas id="myCanvas" width="578" height="200"></canvas>
		<script>
	      var canvas = document.getElementById('myCanvas');
	      var context = canvas.getContext('2d');
	      var centerX = canvas.width / 2;
	      var centerY = canvas.height / 2;
	      var imageRadius = 70;
		  var pointsOffset = Math.sqrt(imageRadius*imageRadius/2);
	      var pointCenterX = centerX - pointsOffset;
	      var pointCenterY = centerY - pointsOffset;
	      var pointRadius = 20;
	      context.beginPath();
	      context.arc(centerX, centerY, imageRadius, 0, 2 * Math.PI, false);
	      context.fillStyle = 'green';
	      context.fill();
	      context.lineWidth = 7;
	      context.strokeStyle = '#003300';
	      context.stroke();
	      context.arc(100,100, 50, 0, Math.PI*2,true); // you can use any shape
	      context.save();
 	      context.clip();
	      var img = new Image();
	      img.addEventListener('load', function(e) {
	          context.drawImage(this, centerX-100, centerY-150, 200, 320);
			  context.closePath();
			  context.restore();
		      context.beginPath();
		      
	          context.arc(pointCenterX, pointCenterY, pointRadius, 0, 2*Math.PI, false);
		      context.fillStyle = 'red';
		      context.fill();
		      context.lineWidth = 5;
		      context.strokeStyle = '#330000';
		      context.stroke();
		      context.font="20px Georgia";
		      context.fillStyle = 'black';
		      context.fillText("2", pointCenterX-5, pointCenterY+5);
	      }, true);
          img.src="<c:url value='/images/getImage/${person.imagePath}' />";


     </script>


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