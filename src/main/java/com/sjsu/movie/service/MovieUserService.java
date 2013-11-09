package com.sjsu.movie.service;
import com.sjsu.movie.domain.MovieUser;
import java.util.List;

public interface MovieUserService {

	public abstract long countAllMovieUsers();


	public abstract void deleteMovieUser(MovieUser movieUser);


	public abstract MovieUser findMovieUser(Long id);


	public abstract List<MovieUser> findAllMovieUsers();


	public abstract List<MovieUser> findMovieUserEntries(int firstResult, int maxResults);


	public abstract void saveMovieUser(MovieUser movieUser);


	public abstract MovieUser updateMovieUser(MovieUser movieUser);

}
