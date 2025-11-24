<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CineSense | Login or Signup</title>
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

        .btn-container {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .btn {
            flex: 1;
            padding: 12px;
            background: #ffd751;
            border: none;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            color: #141414;
            border-radius: 8px;
            transition: 0.25s ease-in-out;
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

    </style>
</head>
<body>

    <div class="container">
        <h1>Welcome to CineSense 🎬</h1>
        <p>Your personalized gateway to smart movie recommendations.</p>

        <div class="btn-container">
            <form action="signup">
                <button type="submit" class="btn">Signup</button>
            </form>

            <form action="login">
                <button type="submit" class="btn">Login</button>
            </form>
        </div>

        <div class="footer-text">
            New here? Create an account.  <br>
            Already have one? Login anytime.
        </div>
    </div>

</body>
</html>
