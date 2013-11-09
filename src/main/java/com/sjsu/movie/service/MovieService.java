package com.sjsu.movie.service;
import com.sjsu.movie.domain.Movie;
import java.util.List;

public interface MovieService {

	public abstract long countAllMovies();


	public abstract void deleteMovie(Movie movie);


	public abstract Movie findMovie(Long id);


	public abstract List<Movie> findAllMovies();


	public abstract List<Movie> findMovieEntries(int firstResult, int maxResults);


	public abstract void saveMovie(Movie movie);


	public abstract Movie updateMovie(Movie movie);

}
