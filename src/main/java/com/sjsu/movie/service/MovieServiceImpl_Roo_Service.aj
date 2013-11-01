// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.movie.service;

import com.sjsu.movie.domain.Movie;
import com.sjsu.movie.service.MovieServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MovieServiceImpl_Roo_Service {
    
    declare @type: MovieServiceImpl: @Service;
    
    declare @type: MovieServiceImpl: @Transactional;
    
    public long MovieServiceImpl.countAllMovies() {
        return Movie.countMovies();
    }
    
    public void MovieServiceImpl.deleteMovie(Movie movie) {
        movie.remove();
    }
    
    public Movie MovieServiceImpl.findMovie(Long id) {
        return Movie.findMovie(id);
    }
    
    public List<Movie> MovieServiceImpl.findAllMovies() {
        return Movie.findAllMovies();
    }
    
    public List<Movie> MovieServiceImpl.findMovieEntries(int firstResult, int maxResults) {
        return Movie.findMovieEntries(firstResult, maxResults);
    }
    
    public void MovieServiceImpl.saveMovie(Movie movie) {
        movie.persist();
    }
    
    public Movie MovieServiceImpl.updateMovie(Movie movie) {
        return movie.merge();
    }
    
}