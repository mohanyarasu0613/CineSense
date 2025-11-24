<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CineSense 🎬</title>

<style>
/* Base Theme Variables (Light Mode) */
:root {
	--bg-color: #ffffff;
	--text-color: #1c1c1c;
	--accent-color: #6a11cb;
	--accent-gradient: linear-gradient(135deg, #6a11cb, #2575fc);
	--card-bg: #f1f1f1;
	--border-color: #ccc;
}

/* Dark Theme Variables (System Dark Mode) */
:root[color-scheme="dark"] {
	--bg-color: #0f1117;
	--text-color: #e6e6e6;
	--card-bg: #161920;
	--border-color: #3d3f46;
}

/* Global Styling */
body {
	margin: 0;
	font-family: "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
	background-color: var(--bg-color);
	color: var(--text-color);
	line-height: 1.6;
}

header {
	padding: 50px 20px;
	text-align: center;
	background: var(--accent-gradient);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	color: transparent;
}

header h1 {
	font-size: 3rem;
	margin: 0;
	font-weight: 700;
}

.container {
	width: 1000px;
	margin: 40px auto;
	padding: 20px;
}

.card {
	background: var(--card-bg);
	border: 1px solid var(--border-color);
	padding: 25px;
	border-radius: 14px;
	margin-bottom: 30px;
	transition: transform 0.2s ease, box-shadow 0.3s ease;
}

.card:hover {
	transform: translateY(-4px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.card h2 {
	margin-top: 0;
	font-size: 1.8rem;
	background: var(--accent-gradient);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

ul {
	padding-left: 18px;
}

footer {
	text-align: center;
	padding: 40px 10px;
	opacity: 0.7;
	font-size: 0.9rem;
}

.btn {
	display: inline-block;
	padding: 12px 24px;
	border-radius: 8px;
	background: var(--accent-gradient);
	text-decoration: none;
	color: white;
	margin-top: 15px;
	font-weight: 600;
	letter-spacing: .5px;
}

.btn:hover {
	opacity: 0.9;
}
</style>
</head>
<body>

	<header>
		<h1>CineSense</h1>
	</header>

	<div class="container">

		<div class="card">
			<h2>Welcome to CineSense 🎬</h2>
			<p>CineSense is your personalized movie companion. Discover
				movies based on your taste, save your favorites, rate what you
				watch, and explore what everyone loves.</p>
			<a href="get-started" class="btn">Get Started</a>
		</div>

		<div class="card">
			<h2>How It Works</h2>
			<ul>
				<li><strong>Sign up & Log in</strong> to access your cine
					dashboard.</li>
				<li><strong>Discover movies</strong> live using external Movie
					APIs.</li>
				<li><strong>Save movies</strong> to your personal watchlist.</li>
				<li><strong>Mark movies as watched</strong> once you're done.</li>
				<li><strong>Rate & Review</strong> movies for public views.</li>
				<li><strong>Admin users</strong> can manage users and data.</li>
			</ul>
		</div>

		<div class="card">
			<h2>Why CineSense?</h2>
			<p>
				Other platforms often recommend content randomly. CineSense learns
				from <strong>your own taste and watch behavior</strong> and helps
				you choose movies you'll actually enjoy.
			</p>
		</div>

	</div>

	<footer> Â© 2025 CineSense â All rights reserved. </footer>

</body>
</html>
