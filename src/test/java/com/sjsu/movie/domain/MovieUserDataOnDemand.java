package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieUserService;
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
public class MovieUserDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<MovieUser> data;

	@Autowired
    MovieUserService movieUserService;

	public MovieUser getNewTransientMovieUser(int index) {
        MovieUser obj = new MovieUser();
        setAddressLine1(obj, index);
        setAddressLine2(obj, index);
        setCState(obj, index);
        setCellphone(obj, index);
        setCity(obj, index);
        setCountry(obj, index);
        setCreatedDate(obj, index);
        setEmail(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setPassword(obj, index);
        setUpdatedDate(obj, index);
        setUserName(obj, index);
        setUserRole(obj, index);
        setUserState(obj, index);
        setZipCode(obj, index);
        return obj;
    }

	public void setAddressLine1(MovieUser obj, int index) {
        String addressLine1 = "addressLine1_" + index;
        if (addressLine1.length() > 100) {
            addressLine1 = addressLine1.substring(0, 100);
        }
        obj.setAddressLine1(addressLine1);
    }

	public void setAddressLine2(MovieUser obj, int index) {
        String addressLine2 = "addressLine2_" + index;
        if (addressLine2.length() > 100) {
            addressLine2 = addressLine2.substring(0, 100);
        }
        obj.setAddressLine2(addressLine2);
    }

	public void setCState(MovieUser obj, int index) {
        String cState = "cState_" + index;
        obj.setCState(cState);
    }

	public void setCellphone(MovieUser obj, int index) {
        String cellphone = "cellphone_" + index;
        if (cellphone.length() > 15) {
            cellphone = cellphone.substring(0, 15);
        }
        obj.setCellphone(cellphone);
    }

	public void setCity(MovieUser obj, int index) {
        String city = "city_" + index;
        obj.setCity(city);
    }

	public void setCountry(MovieUser obj, int index) {
        String country = "country_" + index;
        obj.setCountry(country);
    }

	public void setCreatedDate(MovieUser obj, int index) {
        Date createdDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedDate(createdDate);
    }

	public void setEmail(MovieUser obj, int index) {
        String email = "foo" + index + "@bar.com";
        if (email.length() > 40) {
            email = email.substring(0, 40);
        }
        obj.setEmail(email);
    }

	public void setFirstName(MovieUser obj, int index) {
        String firstName = "firstName_" + index;
        if (firstName.length() > 30) {
            firstName = firstName.substring(0, 30);
        }
        obj.setFirstName(firstName);
    }

	public void setLastName(MovieUser obj, int index) {
        String lastName = "lastName_" + index;
        if (lastName.length() > 30) {
            lastName = lastName.substring(0, 30);
        }
        obj.setLastName(lastName);
    }

	public void setPassword(MovieUser obj, int index) {
        String password = "password_" + index;
        if (password.length() > 15) {
            password = password.substring(0, 15);
        }
        obj.setPassword(password);
    }

	public void setUpdatedDate(MovieUser obj, int index) {
        Date updatedDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedDate(updatedDate);
    }

	public void setUserName(MovieUser obj, int index) {
        String userName = "userName_" + index;
        if (userName.length() > 30) {
            userName = userName.substring(0, 30);
        }
        obj.setUserName(userName);
    }

	public void setUserRole(MovieUser obj, int index) {
        String userRole = "userRole_" + index;
        if (userRole.length() > 15) {
            userRole = userRole.substring(0, 15);
        }
        obj.setUserRole(userRole);
    }

	public void setUserState(MovieUser obj, int index) {
        String userState = "userState_" + index;
        obj.setUserState(userState);
    }

	public void setZipCode(MovieUser obj, int index) {
        String zipCode = "zip_" + index;
        if (zipCode.length() > 5) {
            zipCode = zipCode.substring(0, 5);
        }
        obj.setZipCode(zipCode);
    }

	public MovieUser getSpecificMovieUser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        MovieUser obj = data.get(index);
        Long id = obj.getId();
        return movieUserService.findMovieUser(id);
    }

	public MovieUser getRandomMovieUser() {
        init();
        MovieUser obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return movieUserService.findMovieUser(id);
    }

	public boolean modifyMovieUser(MovieUser obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = movieUserService.findMovieUserEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'MovieUser' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<MovieUser>();
        for (int i = 0; i < 10; i++) {
            MovieUser obj = getNewTransientMovieUser(i);
            try {
                movieUserService.saveMovieUser(obj);
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
