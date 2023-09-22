package org.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MovieServlet extends HttpServlet {

    ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String movieId = req.getParameter("id");

        if(movieId == null){
            String strMovies = "[";

            for(int i = 0 ; i < this.movies.size(); i++){
                strMovies += this.movies.get(i).toString();
            }

            resp.getWriter().write(strMovies + ']');
            return;

        }


        for(int i = 0 ; i < this.movies.size(); i++){

            Movie movie = this.movies.get(i);

            if(movie.getImdbId().equals(movieId)){

                resp.getWriter().write(movie.toString());
                return;

            }
        }


        resp.setStatus(404);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{

        Movie movie = new Movie();

        movie.setImdbId("123");

        this.movies.add(movie);

        resp.getWriter().write(movie.toString());

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException{


        resp.getWriter().write("Hello put");
    }


    @Override
    protected  void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String movieId = req.getParameter("id");

        for(int i = 0 ; i < this.movies.size(); i++){

            Movie movie = this.movies.get(i);

            if(movie.getImdbId().equals(movieId)){

                this.movies.remove(i);
                return;

            }
        }


        resp.setStatus(404);


    }
}

