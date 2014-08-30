<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cache Console</title>

</head>

<body>

	<div class="container">
	<h1>Cache Console</h1>
    
    <h2>Add entry to the cache: </h2>
    <form:form action="console/add" method="post" commandName="formBean">
    	<form:input path="value"/>
    	<input type="submit" value="Add" />
    </form:form>
    
    <h2>Evict all cache entries: </h2>
    <a href="<c:url value='console/evictAll'/>">Evict-all</a>
    
    <h2>Cache content: </h2>   		
	<c:forEach items="${sharedList}" var="entry" >
		<span>${entry}</span><br/>
	</c:forEach>
	</div>
</body>

</html>