<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.spring.CineSense.Model.User" %>

<%
User user = (User) session.getAttribute("loggedInUser");
if (user == null) {
response.sendRedirect("login");
return;
}
%>

<!DOCTYPE html>

<html>
<head>
<title>CineSense - Account</title>
<style>
body {
    margin: 0;
    background: #121212;
    color: white;
    font-family: Arial, sans-serif;
}

/* Navbar */
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
.account-box {
width: 60%;
border: 2px solid white;
margin: 40px auto;
padding: 30px;
border-radius: 8px;
}

/* Form */
.account-form {
margin-top: 20px;
}

.account-form input {
width: 95%;
height: 35px;
padding: 5px 10px;
margin: 10px 0;
border-radius: 6px;
border: none;
}

.save-btn {
background: #ff9900;
color: black;
font-weight: bold;
padding: 10px 25px;
border: none;
border-radius: 6px;
margin-top: 10px;
cursor: pointer;
}

.label {
margin-top: 10px;
font-size: 15px;
} </style>

</head>
<body>

<!-- Navbar -->

<div class="navbar">
    <div>CineSense</div>
    <div>
        <a href="/home">Home</a>
        <a href="/logout">Logout</a>
    </div>
</div>

<!-- Account Box -->

<%
String success = (String) session.getAttribute("success");
if (success != null) {
%>
    <div style="background: #28a745; padding: 12px; border-radius: 6px; margin-bottom: 15px;">
        <%= success %>
    </div>
<%
    session.removeAttribute("success");
}
%>

<div class="account-box">
    <h2>Your Account Details</h2>

<form action="/updateAccount" method="post" class="account-form">

    <div class="label">Full Name</div>
    <input type="text" name="fullName" value="<%= user.getFullName() %>" required>

    <div class="label">Email</div>
    <input type="email" name="email" value="<%= user.getEmail() %>" required>

    <div class="label">Password</div>
    <input type="text" name="password" value="<%= user.getPasswordHash() %>" required>

    <button class="save-btn" type="submit">Save Changes</button>

</form>
</div>

</body>
</html>
