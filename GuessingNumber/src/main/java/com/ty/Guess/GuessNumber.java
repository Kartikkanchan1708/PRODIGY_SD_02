package com.ty.Guess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/GuessServlet")
public class GuessNumber extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer randomNumber = (Integer) session.getAttribute("randomNumber");
        Integer attempts = (Integer) session.getAttribute("attempts");

        // If the session attributes are null, initialize them
        if (randomNumber == null || attempts == null) {
            Random random = new Random();
            randomNumber = random.nextInt(100) + 1; // Number between 1 and 100
            attempts = 0;
            session.setAttribute("randomNumber", randomNumber);
            session.setAttribute("attempts", attempts);
        }

        // Get the guess from the user
        String guessParam = request.getParameter("guess");
        String message = "";

        if (guessParam != null) {
            try {
                int guess = Integer.parseInt(guessParam);
                attempts++;
                session.setAttribute("attempts", attempts);

                if (guess < randomNumber) {
                    message = "Too low! Try again.";
                } else if (guess > randomNumber) {
                    message = "Too high! Try again.";
                } else {
                    message = "Congratulations! You've guessed the correct number in " + attempts + " attempts!";
                    // Reset the game by removing session attributes
                    session.removeAttribute("randomNumber");
                    session.removeAttribute("attempts");
                }
            } catch (NumberFormatException e) {
                message = "Invalid input. Please enter a number.";
            }
        }

        // Set message and attempts as request attributes to display in the JSP
        request.setAttribute("message", message);
        request.setAttribute("attempts", attempts);
        
        // Forward back to index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

