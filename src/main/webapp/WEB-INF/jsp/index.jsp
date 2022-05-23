<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Shorten URL</title>
</head>
<body>
<h1>Shorten URL</h1>
<form action="submit" id="form1" method="post">
	<div>
		<label for="originalURL">Original URL:</label>
		<input type="text" name="originalURL" id="originalURL" value="" />	
		<input type="hidden" name="hash" id="hash" value="1" />	
		<br><br>
		<button form="form1">Submit</button>
	</div>
</form>
<div>${shortURL}</div>
</body>
</html>