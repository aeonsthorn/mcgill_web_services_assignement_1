
package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRepository {

    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());


    public Movie getOneById(String id) {
        for (Movie movie : this.movies) {
            if (movie.getImdbId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public Movie[] getAll() {

        Movie[] movies = new Movie[this.movies.size()];

        for (int i = 0; i < this.movies.size(); i++) {
            movies[i] = this.movies.get(i);
        }

        return movies;

    }

    public void add(Movie movie) {
        this.movies.add(movie);
    }


    public void update(Movie movie){
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getImdbId().equals(movie.getImdbId())) {
                this.movies.set(i, movie);
                return;
            }
        }
    }

    public void remove(String movieId){
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getImdbId().equals(movieId)) {
                this.movies.remove(i);
                return;
            }
        }
    }
}
