<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>My Reviews</title>

<style>
    body {
        background: #121212;
        color: white;
        margin: 0;
        font-family: Arial, sans-serif;
    }

    .container {
        width: 80%;
        margin: 40px auto;
    }

    h1 {
        font-size: 28px;
        margin-bottom: 25px;
    }

    .review-card {
        display: flex;
        background: #1a1a1a;
        padding: 20px;
        border-radius: 14px;
        margin-bottom: 25px;
    }

    .poster {
        width: 150px;
        height: 220px;
        object-fit: cover;
        border-radius: 10px;
        margin-right: 25px;
    }

    .review-details h2 {
        margin: 0;
        font-size: 22px;
        font-weight: bold;
    }

    .rating {
        margin-top: 10px;
        font-size: 16px;
        font-weight: bold;
        color: #ff9800;
    }

    p {
        margin: 12px 0;
        font-size: 15px;
        color: #e0e0e0;
    }

    small {
        color: #bbbbbb;
        font-size: 14px;
    }

    .no-reviews {
        background: #1a1a1a;
        padding: 25px;
        border-radius: 14px;
        font-size: 18px;
        text-align: center;
        color: #cccccc;
    }
</style>

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container">

<h1>Your Reviews</h1>

<c:choose>

    <c:when test="${not empty reviews}">
        <c:forEach var="review" items="${reviews}">

            <div class="review-card">

                <!-- Movie Poster -->
                <img class="poster" src="${review.movie.posterUrl}" alt="${review.movie.title}">

                <!-- Review Content -->
                <div class="review-details">

                    <h2>${review.movie.title} (${review.movie.releaseYear})</h2>

                    <div class="rating">Rating: ${review.rating} / 10</div>

                    <p>${review.reviewText}</p>

                    <small>Reviewed on: ${review.reviewDate}</small>
                </div>

            </div>

        </c:forEach>
    </c:when>

    <c:otherwise>
        <div class="no-reviews">
            You haven't posted any reviews yet.
        </div>
    </c:otherwise>

</c:choose>

</div>

</body>
</html>
