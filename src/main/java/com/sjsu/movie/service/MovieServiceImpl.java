package com.sjsu.movie.service;

import com.sjsu.movie.domain.Movie;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	public long countAllMovies() {
        return Movie.countMovies();
    }

	public void deleteMovie(Movie movie) {
        movie.remove();
    }

	public Movie findMovie(Long id) {
        return Movie.findMovie(id);
    }

	public List<Movie> findAllMovies() {
        return Movie.findAllMovies();
    }

	public List<Movie> findMovieEntries(int firstResult, int maxResults) {
        return Movie.findMovieEntries(firstResult, maxResults);
    }

	public void saveMovie(Movie movie) {
        movie.persist();
    }

	public Movie updateMovie(Movie movie) {
        return movie.merge();
    }
}
