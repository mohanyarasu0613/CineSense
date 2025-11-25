<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, com.spring.CineSense.Model.MovieDTO, com.spring.CineSense.Model.User"%>

<%
User user = (User) session.getAttribute("loggedInUser");
if (user == null) {
	response.sendRedirect("login");
	return;
}

List<MovieDTO> movies = (List<MovieDTO>) request.getAttribute("movies");
%>

<!DOCTYPE html>

<html>
<head>
<title>CineSense - Dashboard</title>
<style>
body {
	margin: 0;
	background: #121212;
	color: white;
	font-family: Arial, sans-serif;
}

/* Header */
.navbar {
	display: flex;
	justify-content: space-between;
	padding: 20px 40px;
	background-color: #1b1b1b;
	font-size: 18px;
}

.navbar a {
	color: white;
	text-decoration: none;
	margin-left: 20px;
}

/* Content Box */
.content-box {
	width: 95%;
	border: 2px solid white;
	margin: 20px auto;
	padding: 20px;
}

.welcome-text {
	font-size: 20px;
	margin-bottom: 10px;
}

/* Search */
.action-bar {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	margin-bottom: 20px;
	font-size: 16px;
}

.action-bar input {
	height: 35px;
	width: 250px;
	padding: 5px 10px;
	border: none;
	border-radius: 6px;
	margin-left: 15px;
}

.action-bar button {
	height: 39px;
	width: 100px;
	margin-left: 10px;
	background: #ff9900;
	border: none;
	border-radius: 6px;
	font-weight: bold;
	cursor: pointer;
	color: black;
}

/* Movie Tiles */
.tiles-container {
	width: 98%;
	border: 2px solid white;
	margin: 10px auto;
	padding: 20px;
	min-height: 450px;
}

.movie-grid {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.movie-tile {
	width: 220px;
	height: 300px;
	background: #222;
	border: 2px solid white;
	margin: 15px;
	text-align: center;
	border-radius: 10px;
	padding: 10px;
	cursor: pointer;
	transition: 0.2s;
}

.movie-tile:hover {
	border-color: #ff9900;
	transform: scale(1.05);
}

.movie-tile img {
	width: 100%;
	height: 80%;
	object-fit: cover;
	border-radius: 6px;
}

.movie-title {
	font-size: 14px;
	margin-top: 8px;
}
</style>

</head>
<body>

	<!-- Navbar -->
	<div class="navbar">
		<div>CineSense</div>
		<div>
			<a href="/wishlist">Wishlist</a> <a href="/watched">Watched</a> <a
				href="/myreviews">My Reviews</a> <a href="/account">Account</a> <a
				href="/logout">Logout</a>
		</div>
	</div>

	<!-- Content -->
	<div class="content-box">

		<div class="welcome-text">
			Welcome, <b><%=user.getFullName()%></b>
		</div>

		<!-- Search Bar -->
		<div class="action-bar">
			<form action="/searchMovie" method="get">
				<input type="text" name="movieName" placeholder="Search movies..."
					required>
				<button type="submit">Search</button>
			</form>
		</div>

		<!-- Movie Tiles -->
		<div class="tiles-container">
			<div class="movie-grid">
				<%
				if (movies != null && !movies.isEmpty()) {
					for (MovieDTO m : movies) {
				%>
				<form action="/movieDetails" method="get"
					style="text-decoration: none; color: white;">
					<input type="hidden" name="movieId" value="<%=m.getImdbId()%>" />
					<div class="movie-tile" onclick="this.parentNode.submit();">
						<img src="<%=m.getPoster()%>" alt="Poster">
						<div class="movie-title">
							<%=m.getTitle()%>
							(<%=m.getYear()%>)
						</div>
					</div>
				</form>
				<%
				}
				} else {
				%>
				<!-- Show empty placeholders -->
				<%
				for (int i = 0; i < 8; i++) {
				%>
				<div class="movie-tile"></div>
				<% } %>
				<% } %>
			</div>
		</div>

	</div>

</body>
</html>
