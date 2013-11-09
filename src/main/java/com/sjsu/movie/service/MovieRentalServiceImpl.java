package com.sjsu.movie.service;

import com.sjsu.movie.domain.MovieRental;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieRentalServiceImpl implements MovieRentalService {

	public long countAllMovieRentals() {
        return MovieRental.countMovieRentals();
    }

	public void deleteMovieRental(MovieRental movieRental) {
        movieRental.remove();
    }

	public MovieRental findMovieRental(Long id) {
        return MovieRental.findMovieRental(id);
    }

	public List<MovieRental> findAllMovieRentals() {
        return MovieRental.findAllMovieRentals();
    }

	public List<MovieRental> findMovieRentalEntries(int firstResult, int maxResults) {
        return MovieRental.findMovieRentalEntries(firstResult, maxResults);
    }

	public void saveMovieRental(MovieRental movieRental) {
        movieRental.persist();
    }

	public MovieRental updateMovieRental(MovieRental movieRental) {
        return movieRental.merge();
    }
}
