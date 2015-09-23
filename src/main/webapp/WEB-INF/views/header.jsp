<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head></head>
<body>
<nav>
	<ul>
		<li><a href="<c:url value='/' />">Home</a></li>
		<li>
      		<a href="<c:url value='/person' />">Person<span class="caret"></span></a>
			<div>
				<ul>
					<li><a href="<c:url value='/person/list' />">List</a></li>
					<li><a href="<c:url value='/person/add' />">Add</a></li>
				</ul>
			</div>
		</li>
		<li><a href="<c:url value='/tasks' />">Tasks</a></li>
		<li>
			<a href="<c:url value='/rewards' />">Rewards</a>
			<div>
				<ul>
					<li><a href="<c:url value='/reward/list' />">List</a></li>
					<li><a href="<c:url value='/reward/add' />">Add</a></li>
				</ul>
			</div>
		</li>
	</ul>
</nav>
<br />
</body>
</html>
