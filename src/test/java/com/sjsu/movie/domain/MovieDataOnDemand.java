package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class MovieDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Movie> data;

	@Autowired
    MovieService movieService;

	public Movie getNewTransientMovie(int index) {
        Movie obj = new Movie();
        setBanner(obj, index);
        setCategory(obj, index);
        setCreatedDate(obj, index);
        setMovieName(obj, index);
        setNumberOfCopies(obj, index);
        setReleaseDate(obj, index);
        setRentFee(obj, index);
        setSummary(obj, index);
        return obj;
    }

	public void setBanner(Movie obj, int index) {
        String banner = "banner_" + index;
        obj.setBanner(banner);
    }

	public void setCategory(Movie obj, int index) {
        String category = "category_" + index;
        obj.setCategory(category);
    }

	public void setCreatedDate(Movie obj, int index) {
        Date createdDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedDate(createdDate);
    }

	public void setMovieName(Movie obj, int index) {
        String movieName = "movieName_" + index;
        obj.setMovieName(movieName);
    }

	public void setNumberOfCopies(Movie obj, int index) {
        Integer numberOfCopies = new Integer(index);
        obj.setNumberOfCopies(numberOfCopies);
    }

	public void setReleaseDate(Movie obj, int index) {
        Date releaseDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setReleaseDate(releaseDate);
    }

	public void setRentFee(Movie obj, int index) {
        Double rentFee = new Integer(index).doubleValue();
        obj.setRentFee(rentFee);
    }

	public void setSummary(Movie obj, int index) {
        String summary = "summary_" + index;
        obj.setSummary(summary);
    }

	public Movie getSpecificMovie(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Movie obj = data.get(index);
        Long id = obj.getId();
        return movieService.findMovie(id);
    }

	public Movie getRandomMovie() {
        init();
        Movie obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return movieService.findMovie(id);
    }

	public boolean modifyMovie(Movie obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = movieService.findMovieEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Movie' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Movie>();
        for (int i = 0; i < 10; i++) {
            Movie obj = getNewTransientMovie(i);
            try {
                movieService.saveMovie(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
