package com.example.tennisscoreboard.servlets;

import com.example.tennisscoreboard.dao.MatchDao;
import com.example.tennisscoreboard.models.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
    /**
     * Сервлет, отвечающий за предоставление информации о всех прошедших матчах
     */
    private final MatchDao matchDao = new MatchDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }
        long pageSize = 5; // количество записей на странице

        long totalItems;
        List<Match> matches;
        if (filterName == null || filterName.trim().isEmpty()) {
            matches = matchDao.getAllPagination((int) page, (int) pageSize);
            totalItems = matchDao.getAllUnique();
        } else {
            matches = matchDao.getByPlayerNamePagination(filterName, (int) page, (int) pageSize);
            totalItems = matchDao.getByPlayerNameUnique(filterName);
        }

        long totalPages = (totalItems / pageSize) + 1;

        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("matches", matches);
        req.setAttribute("totalItems", totalItems);
        log.info("Total pages -> {}", totalPages);
        log.info("Current page -> {}", page);
        log.info("Total_Items -> {}", totalItems);
        req.getRequestDispatcher("/views/matches.jsp").forward(req, resp);
    }

}
