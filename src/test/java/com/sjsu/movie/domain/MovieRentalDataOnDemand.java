package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieRentalService;
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

@Configurable
@Component
public class MovieRentalDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<MovieRental> data;

	@Autowired
    MovieDataOnDemand movieDataOnDemand;

	@Autowired
    MovieUserDataOnDemand movieUserDataOnDemand;

	@Autowired
    MovieRentalService movieRentalService;

	public MovieRental getNewTransientMovieRental(int index) {
        MovieRental obj = new MovieRental();
        setComments(obj, index);
        setDueDate(obj, index);
        setRentedDate(obj, index);
        setReturnedDate(obj, index);
        setUpdatedDate(obj, index);
        return obj;
    }

	public void setComments(MovieRental obj, int index) {
        String comments = "comments_" + index;
        obj.setComments(comments);
    }

	public void setDueDate(MovieRental obj, int index) {
        Date dueDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDueDate(dueDate);
    }

	public void setRentedDate(MovieRental obj, int index) {
        Date rentedDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setRentedDate(rentedDate);
    }

	public void setReturnedDate(MovieRental obj, int index) {
        Date returnedDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setReturnedDate(returnedDate);
    }

	public void setUpdatedDate(MovieRental obj, int index) {
        Date updatedDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedDate(updatedDate);
    }

	public MovieRental getSpecificMovieRental(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        MovieRental obj = data.get(index);
        Long id = obj.getId();
        return movieRentalService.findMovieRental(id);
    }

	public MovieRental getRandomMovieRental() {
        init();
        MovieRental obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return movieRentalService.findMovieRental(id);
    }

	public boolean modifyMovieRental(MovieRental obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = movieRentalService.findMovieRentalEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'MovieRental' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<MovieRental>();
        for (int i = 0; i < 10; i++) {
            MovieRental obj = getNewTransientMovieRental(i);
            try {
                movieRentalService.saveMovieRental(obj);
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
