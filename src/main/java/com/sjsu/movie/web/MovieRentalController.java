package com.sjsu.movie.web;
import com.sjsu.movie.domain.MovieRental;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/movierentals")
@Controller
@RooWebScaffold(path = "movierentals", formBackingObject = MovieRental.class)
public class MovieRentalController {
}
