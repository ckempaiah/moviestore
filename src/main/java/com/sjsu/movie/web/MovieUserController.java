package com.sjsu.movie.web;
import com.sjsu.movie.domain.MovieUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/movieusers")
@Controller
@RooWebScaffold(path = "movieusers", formBackingObject = MovieUser.class)
public class MovieUserController {
}
