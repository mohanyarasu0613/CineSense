<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Your Wishlist</title>

<style>
body {
    background: #121212;
    color: white;
    margin: 0;
    font-family: Arial;
}

.container {
    width: 80%;
    margin: 40px auto;
}

.movie-card {
    display: flex;
    background: #1f1f1f;
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 20px;
}

.movie-card img {
    width: 150px;
    height: 220px;
    object-fit: cover;
    border-radius: 10px;
    margin-right: 20px;
}

button {
    padding: 10px 15px;
    background: #ff9800;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
button:hover {
    background: #ffb547;
}
</style>

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">

    <h1>Your Wishlist</h1>

    <c:if test="${empty wishlistMovies}">
        <h3>No movies in wishlist.</h3>
    </c:if>

    <c:forEach var="movie" items="${wishlistMovies}">
        <div class="movie-card">
            <img src="${movie.poster}" alt="${movie.title}" />

            <div>
                <h2>${movie.title} (${movie.year})</h2>

                <form action="/movie/markWatched" method="post">
                    <input type="hidden" name="movieId" value="${movie.imdbId}">
                    <button type="submit">Mark as Watched</button>
                </form>
            </div>
        </div>
    </c:forEach>

</div>

</body>
</html>
