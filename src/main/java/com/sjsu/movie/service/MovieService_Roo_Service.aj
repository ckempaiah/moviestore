// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.movie.service;

import com.sjsu.movie.domain.Movie;
import com.sjsu.movie.service.MovieService;
import java.util.List;

privileged aspect MovieService_Roo_Service {
    
    public abstract long MovieService.countAllMovies();    
    public abstract void MovieService.deleteMovie(Movie movie);    
    public abstract Movie MovieService.findMovie(Long id);    
    public abstract List<Movie> MovieService.findAllMovies();    
    public abstract List<Movie> MovieService.findMovieEntries(int firstResult, int maxResults);    
    public abstract void MovieService.saveMovie(Movie movie);    
    public abstract Movie MovieService.updateMovie(Movie movie);    
}