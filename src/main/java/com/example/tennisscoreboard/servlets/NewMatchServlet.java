package com.example.tennisscoreboard.servlets;

import com.example.tennisscoreboard.models.Player;
import com.example.tennisscoreboard.services.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getOngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String playerOne = req.getParameter("player-1");
        String playerTwo = req.getParameter("player-2");
        int setsInMatch = Integer.parseInt(req.getParameter("match-sets"));

        Player firstPlayer = new Player(playerOne.toUpperCase());
        Player secondPlayer = new Player(playerTwo.toUpperCase());
        UUID uuid = UUID.randomUUID();

        ongoingMatchesService.createNewMatch(uuid, firstPlayer, secondPlayer, setsInMatch);

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}