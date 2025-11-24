<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<title>Login - CineSense</title>
<style>
body {
	font-family: Arial;
	background: #222;
	color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.card {
	background: #333;
	padding: 30px;
	border-radius: 8px;
	width: 350px;
}

input {
	width: 100%;
	padding: 10px;
	margin-top: 8px;
	border-radius: 3px;
}

button {
	width: 100%;
	padding: 10px;
	margin-top: 15px;
	background: #ff4444;
	border: none;
	color: white;
	cursor: pointer;
}

.error {
	color: #ff7777;
	margin-top: 10px;
}
</style>
</head>
<body>

	<div class="card">
		<h2>Login</h2>

		<form action="login" method="post">
			<label>Email</label> <input type="text" name="email" required>

			<label>Password</label> <input type="password" name="password" required>

			<button type="submit">Login</button>

			<% String error = (String) session.getAttribute("error");
       		if (error != null) { %>
			<p class="error"><%= error %></p>
			<% session.removeAttribute("error"); } %>

		</form>

	</div>

</body>
</html>
