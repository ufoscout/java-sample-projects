<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JMS Test</title>

</head>

<body>

	<div class="container">
		<h1>JMS count is: ${count}</h1>
		<a href="<c:url value='/page/main/sendMessage'/>">Send message to increase counter</a>
	</div>
</body>

</html>