package com.sjsu.movie.web;
import com.sjsu.movie.domain.MovieRental;
import com.sjsu.movie.service.MovieRentalService;
import com.sjsu.movie.service.MovieService;
import com.sjsu.movie.service.MovieUserService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/movierentals")
@Controller
public class MovieRentalController {

	@Autowired
    MovieRentalService movieRentalService;

	@Autowired
    MovieService movieService;

	@Autowired
    MovieUserService movieUserService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid MovieRental movieRental, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, movieRental);
            return "movierentals/create";
        }
        uiModel.asMap().clear();
        movieRentalService.saveMovieRental(movieRental);
        return "redirect:/movierentals/" + encodeUrlPathSegment(movieRental.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new MovieRental());
        return "movierentals/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("movierental", movieRentalService.findMovieRental(id));
        uiModel.addAttribute("itemId", id);
        return "movierentals/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("movierentals", movieRentalService.findMovieRentalEntries(firstResult, sizeNo));
            float nrOfPages = (float) movieRentalService.countAllMovieRentals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("movierentals", movieRentalService.findAllMovieRentals());
        }
        addDateTimeFormatPatterns(uiModel);
        return "movierentals/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid MovieRental movieRental, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, movieRental);
            return "movierentals/update";
        }
        uiModel.asMap().clear();
        movieRentalService.updateMovieRental(movieRental);
        return "redirect:/movierentals/" + encodeUrlPathSegment(movieRental.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, movieRentalService.findMovieRental(id));
        return "movierentals/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        MovieRental movieRental = movieRentalService.findMovieRental(id);
        movieRentalService.deleteMovieRental(movieRental);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/movierentals";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("movieRental_renteddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("movieRental_returneddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("movieRental_duedate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("movieRental_updateddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, MovieRental movieRental) {
        uiModel.addAttribute("movieRental", movieRental);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("movies", movieService.findAllMovies());
        uiModel.addAttribute("movieusers", movieUserService.findAllMovieUsers());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
