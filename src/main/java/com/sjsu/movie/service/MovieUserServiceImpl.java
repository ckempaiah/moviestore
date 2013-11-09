package com.sjsu.movie.service;

import com.sjsu.movie.domain.MovieUser;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieUserServiceImpl implements MovieUserService {

	public long countAllMovieUsers() {
        return MovieUser.countMovieUsers();
    }

	public void deleteMovieUser(MovieUser movieUser) {
        movieUser.remove();
    }

	public MovieUser findMovieUser(Long id) {
        return MovieUser.findMovieUser(id);
    }

	public List<MovieUser> findAllMovieUsers() {
        return MovieUser.findAllMovieUsers();
    }

	public List<MovieUser> findMovieUserEntries(int firstResult, int maxResults) {
        return MovieUser.findMovieUserEntries(firstResult, maxResults);
    }

	public void saveMovieUser(MovieUser movieUser) {
        movieUser.persist();
    }

	public MovieUser updateMovieUser(MovieUser movieUser) {
        return movieUser.merge();
    }
}
