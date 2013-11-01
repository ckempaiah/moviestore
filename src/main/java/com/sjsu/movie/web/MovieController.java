package com.sjsu.movie.web;
import com.sjsu.movie.domain.Movie;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/movies")
@Controller
@RooWebScaffold(path = "movies", formBackingObject = Movie.class)
public class MovieController {
}
