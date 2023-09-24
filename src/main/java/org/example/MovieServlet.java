package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class MovieServlet extends HttpServlet {

    ArrayList<Movie> movies = new ArrayList<>();

    public MovieServlet() {

        Movie batman = new Movie();
        batman.setImdbId("abc");
        Movie pumpingIron = new Movie();
        pumpingIron.setImdbId("def");

        this.movies.add(batman);
        this.movies.add(pumpingIron);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("id");

        if (movieId == null) {
            StringBuilder strMovies = new StringBuilder("[");

            for (Movie movie : this.movies) {
                strMovies.append(movie.toString());
            }

            resp.getWriter().write(strMovies.toString() + ']');
            return;

        }


        for (Movie movie : this.movies) {

            if (movie.getImdbId().equals(movieId)) {

                resp.getWriter().write(movie.toString());
                return;

            }
        }


        resp.setStatus(404);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        System.out.println(req.getParameter("ImdbId"));

        Movie movie = new Movie();

        movie.setImdbId("123");

        this.movies.add(movie);

        resp.getWriter().write(movie.toString());

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("id");

        Movie movie = this.getMovieById(movieId);


        if(movie == null){
            resp.setStatus(404);
            return;

        }

        movie.setDescription("Coucou");



        resp.getWriter().write("Ok");

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("id");

        Movie movie = this.getMovieById(movieId);

        if(movie == null){
            resp.setStatus(404);
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

