<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<title>Spring test</title>
</head>
<body>
	<h1>Temporary Title</h1>
	<h2>Insert some random stuff</h2>
	<button id="btnMostrar" type="button">Show/Hide EVERYTHING</button>
	<form id="thingForm" action="/addThing" method="POST">
		<label>Name</label> <br> <input id="name" name="name"></input> <br>
		<br> <label>Number</label> <br> <input id="number"
			name="number"></input>

		<button id="btnRandom" type="button">some random number</button>
		<br> <br>

		<button id="btnSubmit" type="button">submit</button>
	</form>

	<a href="/viewThings">view things</a>
	<br>
	<a href="/save">save those things</a>

	<c:if test="${not empty strings}">
		<c:forEach items="${strings}" var="string">
			<label>${string}</label>
		</c:forEach>
	</c:if>

	<script src="http://code.jquery.com/jquery-3.1.1.js"
		integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$("#btnRandom").click(function() {
				var randomNumber = Math.floor((Math.random() * 1000000) + 1);
				$("#number").val(randomNumber);
			});
			$("#btnMostrar").click(function() {
				if ($("#thingForm").is(":visible")) {
					$("#thingForm").hide();
					$("#number").val("");
					$("#name").val("");
				} else {
					$("#thingForm").show();
				}
			});
			$("#btnSubmit").click(function() {
				var isCorrect = true;

				if ($("#name").val() == "") {
					alert("Preencha o nome");
					isCorrect = false;
				}

				if ($("#number").val() == "") {
					$("#btnRandom").click();
				}

				if (isCorrect) {
					$("#thingForm").submit();
				}
			})
		});
	</script>

</body>
</html>