package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MovieServlet extends HttpServlet {

    List<Movie> movies = Collections.synchronizedList(new ArrayList<>());

    public MovieServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("ImdbId");

        if (movieId == null) {
            StringBuilder strMovies = new StringBuilder();

            for (Movie movie : this.movies) {
                strMovies.append(movie.toString());
                strMovies.append("\n");
            }


            resp.getWriter().write(strMovies.toString());
            return;

        }


        for (Movie movie : this.movies) {

            if (movie.getImdbId().equals(movieId)) {

                resp.getWriter().write(movie.toString());
                return;

            }
        }



        resp.setStatus(404);
        resp.getWriter().write("Not Found");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String ImdbId = req.getParameter("ImdbId");
        String Title = req.getParameter("Title");
        String Description = req.getParameter("Description");
        String Rating = req.getParameter("Rating");
        String Genre = req.getParameter("Genre");
        String Year = req.getParameter("Year");


        Movie existingMovieWithSameId = this.getMovieById(ImdbId);

        if (existingMovieWithSameId != null) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().write("Movie with ImdbId " + ImdbId + " already exists");
            return;
        }

        if (
                ImdbId == null || ImdbId.isBlank() ||
                        Title == null || Title.isBlank() ||
                        Rating == null || Rating.isBlank() ||
                        Genre == null || Genre.isBlank() ||
                        Year == null || Year.isBlank()
        ) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Movie ImdbId, Title, Rating, Genre and Year are mandatory to store a new movie");
            return;
        }
        try {

            new BigDecimal(Rating);

        } catch (NumberFormatException err) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Rating must be an decimal number");
            return;

        }

        try {
            Integer.parseInt(Year);

        } catch (NumberFormatException err) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Year must be an integer");
            return;
        }


        Movie movie = new Movie();

        movie.setImdbId(ImdbId);
        movie.setTitle(Title);
        movie.setRating(new BigDecimal(Rating));
        movie.setGenre(Genre);
        movie.setYear(Integer.parseInt(Year));

        if (Description != null) {
            movie.setDescription(Description);
        }

        movie.updateLastModified();


        this.movies.add(movie);

        resp.getWriter().write(movie.toString());

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("ImdbId");


        Movie movie = this.getMovieById(movieId);

        if (movie == null) {
            resp.setStatus(404);
            resp.getWriter().write("Not Found");
            return;

        }

        String Title = req.getParameter("Title");
        String Description = req.getParameter("Description");
        String Rating = req.getParameter("Rating");
        String Genre = req.getParameter("Genre");
        String Year = req.getParameter("Year");


        if (Title != null && !Title.isBlank()) {
            movie.setTitle(Title);
        }

        if (Description != null && !Description.isBlank()) {
            movie.setDescription(Description);
        }


        if (Rating != null && !Rating.isBlank()) {
            try {

                new BigDecimal(Rating);

            } catch (NumberFormatException err) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Rating must be an decimal number");
                return;

            }

            movie.setRating(new BigDecimal(Rating));

        }

        if (Genre != null && !Genre.isBlank()) {


            movie.setGenre(Genre);

        }

        if (Year != null && !Year.isBlank()) {

            try {
                Integer.parseInt(Year);

            } catch (NumberFormatException err) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Year must be an integer");
                return;
            }

            movie.setYear(Integer.parseInt(Year));

        }

        movie.updateLastModified();

        resp.getWriter().write(movie.toString());

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("ImdbId");

        Movie movie = this.getMovieById(movieId);

        if (movie == null) {

            resp.setStatus(404);
            resp.getWriter().write("Not Found");
            return;

        }


        this.movies.remove(movie);

        resp.getWriter().write("Ok");


    }


    private Movie getMovieById(String id) {

        for (Movie movie : this.movies) {

            if (movie.getImdbId().equals(id)) {

                return movie;

            }
        }

        return null;

    }
}

