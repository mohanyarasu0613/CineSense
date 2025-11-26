<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.navbar {
    background: #1f1f1f;
    padding: 15px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.nav-left a, .nav-right a {
    color: white;
    text-decoration: none;
    margin-right: 20px;
    font-weight: bold;
}

.nav-left a:hover, .nav-right a:hover {
    color: #ff9800;
}
</style>

<div class="navbar">

    <div class="nav-left">
    	<a href="/home">Home</a>
        <a href="/movie/wishlist">Wishlist</a>
        <a href="/movie/watched">Watched</a>
        <a href="/movie/myReviews">My Reviews</a>
    </div>

    <div class="nav-right">
        <a href="/account">Account</a>
        <a href="/logout">Logout</a>
    </div>

</div>
