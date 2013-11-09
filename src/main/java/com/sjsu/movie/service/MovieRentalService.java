package com.sjsu.movie.service;
import com.sjsu.movie.domain.MovieRental;
import java.util.List;

public interface MovieRentalService {

	public abstract long countAllMovieRentals();


	public abstract void deleteMovieRental(MovieRental movieRental);


	public abstract MovieRental findMovieRental(Long id);


	public abstract List<MovieRental> findAllMovieRentals();


	public abstract List<MovieRental> findMovieRentalEntries(int firstResult, int maxResults);


	public abstract void saveMovieRental(MovieRental movieRental);


	public abstract MovieRental updateMovieRental(MovieRental movieRental);

}
