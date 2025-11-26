
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.spring.CineSense.DTO.MovieDTO, com.spring.CineSense.Model.User"%>
<%
User user = (User) session.getAttribute("loggedInUser");
if (user == null) {
	response.sendRedirect("login");
	return;
}

MovieDTO movie = (MovieDTO) request.getAttribute("movie");
boolean inWishlist = Boolean.TRUE.equals(request.getAttribute("inWishlist"));
boolean watched = Boolean.TRUE.equals(request.getAttribute("watched"));
%>

<!DOCTYPE html>

<html>
<head>
<title><%=movie.getTitle()%> - Details</title>
<style>
body {
	margin: 0;
	background: #121212;
	color: white;
	font-family: Arial, sans-serif;
}

.container {
	width: 70%;
	margin: 40px auto;
	border: 1px solid white;
	padding: 20px;
	border-radius: 10px;
}

.poster {
	width: 280px;
	height: 420px;
	object-fit: cover;
	border-radius: 10px;
}

button {
	padding: 12px 20px;
	margin-top: 20px;
	background: #ff9900;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-weight: bold;
}

button:hover {
	background: #ffb74a;
}

textarea {
	width: 100%;
	height: 150px;
	padding: 10px;
	border-radius: 8px;
}

</style>

</head>

<body>

<jsp:include page="navbar.jsp" />
	
	<div class="container">

		<h1>${movie.title}(${movie.year})</h1>
		<img class="poster" src="${movie.poster}" alt="Poster" /><br /> <br />

		<c:choose>
			<c:when test="${!inWishlist && !watched}">
				<form action="/movie/addToWishlist" method="post">
					<input type="hidden" name="movieId" value="${movie.imdbId}" />
					<button type="submit">Add to Wishlist</button>
				</form>
			</c:when>
			<c:when test="${inWishlist && !watched}">
				<form action="/movie/markWatched" method="post">
					<input type="hidden" name="movieId" value="${movie.imdbId}" />
					<button type="submit">Mark as Watched</button>
				</form>
			</c:when>
			<c:when test="${watched}">
				<h3>Write a Review</h3>
				<form action="/movie/review" method="post">
					<input type="hidden" name="movieId" value="${movie.imdbId}" />
					Rating (1-5): <select name="rating" required>
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select> <br /> <br />
					<textarea name="reviewText" placeholder="Write your review..."
						required></textarea>
					<br />
					<button type="submit">Submit Review</button>
				</form>
			</c:when>
		</c:choose>

	</div>

</body>
</html>
