<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CineSense | Signup</title>
<style>
body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background: linear-gradient(to bottom right, #0f0f17, #1a1a27);
	color: #eee;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.container {
	background: rgba(255, 255, 255, 0.05);
	width: 450px;
	padding: 40px;
	border-radius: 14px;
	border: 1px solid rgba(255, 255, 255, 0.1);
	box-shadow: 0 0 15px rgba(255, 221, 85, 0.2);
	text-align: center;
}

h1 {
	margin-bottom: 10px;
	font-weight: 600;
}

p {
	color: #c1c1c1;
	margin-bottom: 25px;
	font-size: 15px;
}

form {
	display: flex;
	flex-direction: column;
	gap: 15px;
}

input {
	padding: 12px;
	background: rgba(255, 255, 255, 0.1);
	border: 1px solid rgba(255, 255, 255, 0.2);
	border-radius: 8px;
	color: #eee;
	font-size: 15px;
	outline: none;
}

input::placeholder {
	color: #aaa;
}

input:focus {
	border-color: #ffd751;
}

.btn {
	padding: 12px;
	background: #ffd751;
	border: none;
	font-size: 15px;
	font-weight: 600;
	cursor: pointer;
	color: #141414;
	border-radius: 8px;
	transition: 0.25s ease-in-out;
	margin-top: 10px;
}

.btn:hover {
	background: #f3c93b;
}

.footer-text {
	margin-top: 20px;
	font-size: 13px;
	color: #999;
}

a {
	color: #ffd751;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.error {
	color: #ff6b6b;
	font-size: 14px;
	margin-top: -10px;
}
</style>
</head>
<body>

	<div class="container">
		<h1>Join CineSense 🎬</h1>
		<p>Create your account for personalized movie recommendations.</p>
		<!-- Display error message if present -->
		<c:if test="${not empty param.error}">
			<div class="error">${param.error}</div>
		</c:if>

		<!-- Display success message if present (e.g., on redirect from successful signup) -->
		<c:if test="${not empty param.message}">
			<div class="success">${param.message}</div>
		</c:if>
		
		<form action="signup" method="post">
			<input type="text" name="fullname" placeholder="Full Name" required>
			<input type="email" name="email" placeholder="Email" required>
			<input type="password" name="password" placeholder="Password" required> 
			<input type="password" name="confirm_password" placeholder="Confirm Password" required>
			<button type="submit" class="btn">Signup</button>
		</form>

		<div class="footer-text">
			Already have an account? <a href="login">Login here</a>.
		</div>
	</div>

</body>
</html>