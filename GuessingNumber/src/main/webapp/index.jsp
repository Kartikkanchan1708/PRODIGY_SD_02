<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guess the Number Game</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Guess the Number Game</h1>
        <form action="GuessServlet" method="post">
            <label for="guess">Enter your guess (1-100):</label>
            <input type="number" id="guess" name="guess" min="1" max="100" required>
            <button type="submit">Submit</button>
        </form>
        <p>${message}</p>
        <p>Attempts: ${attempts}</p>
    </div>
</body>
</html>
