<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page session="false"%>
<%@ page import="groovy.simple.Util"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Groovy web test</title>
  </head>

  <body>

	<h1>Groovy test page</h1>
	<br/>
	<span id="applicationName"><%=Util.applicationName() %></span>
	
  </body>
</html>
