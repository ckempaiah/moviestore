package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieRentalService;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class MovieRentalIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MovieRentalDataOnDemand dod;

	@Autowired
    MovieRentalService movieRentalService;

	@Test
    public void testCountAllMovieRentals() {
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", dod.getRandomMovieRental());
        long count = movieRentalService.countAllMovieRentals();
        Assert.assertTrue("Counter for 'MovieRental' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMovieRental() {
        MovieRental obj = dod.getRandomMovieRental();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to provide an identifier", id);
        obj = movieRentalService.findMovieRental(id);
        Assert.assertNotNull("Find method for 'MovieRental' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'MovieRental' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMovieRentals() {
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", dod.getRandomMovieRental());
        long count = movieRentalService.countAllMovieRentals();
        Assert.assertTrue("Too expensive to perform a find all test for 'MovieRental', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<MovieRental> result = movieRentalService.findAllMovieRentals();
        Assert.assertNotNull("Find all method for 'MovieRental' illegally returned null", result);
        Assert.assertTrue("Find all method for 'MovieRental' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMovieRentalEntries() {
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", dod.getRandomMovieRental());
        long count = movieRentalService.countAllMovieRentals();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<MovieRental> result = movieRentalService.findMovieRentalEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'MovieRental' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'MovieRental' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        MovieRental obj = dod.getRandomMovieRental();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to provide an identifier", id);
        obj = movieRentalService.findMovieRental(id);
        Assert.assertNotNull("Find method for 'MovieRental' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMovieRental(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'MovieRental' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMovieRentalUpdate() {
        MovieRental obj = dod.getRandomMovieRental();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to provide an identifier", id);
        obj = movieRentalService.findMovieRental(id);
        boolean modified =  dod.modifyMovieRental(obj);
        Integer currentVersion = obj.getVersion();
        MovieRental merged = movieRentalService.updateMovieRental(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'MovieRental' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMovieRental() {
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", dod.getRandomMovieRental());
        MovieRental obj = dod.getNewTransientMovieRental(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'MovieRental' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'MovieRental' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMovieRental() {
        MovieRental obj = dod.getRandomMovieRental();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieRental' failed to provide an identifier", id);
        obj = movieRentalService.findMovieRental(id);
        movieRentalService.deleteMovieRental(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'MovieRental' with identifier '" + id + "'", movieRentalService.findMovieRental(id));
    }
}
