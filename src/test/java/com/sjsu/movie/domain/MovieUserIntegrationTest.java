package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieUserService;
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
public class MovieUserIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MovieUserDataOnDemand dod;

	@Autowired
    MovieUserService movieUserService;

	@Test
    public void testCountAllMovieUsers() {
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", dod.getRandomMovieUser());
        long count = movieUserService.countAllMovieUsers();
        Assert.assertTrue("Counter for 'MovieUser' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMovieUser() {
        MovieUser obj = dod.getRandomMovieUser();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to provide an identifier", id);
        obj = movieUserService.findMovieUser(id);
        Assert.assertNotNull("Find method for 'MovieUser' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'MovieUser' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMovieUsers() {
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", dod.getRandomMovieUser());
        long count = movieUserService.countAllMovieUsers();
        Assert.assertTrue("Too expensive to perform a find all test for 'MovieUser', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<MovieUser> result = movieUserService.findAllMovieUsers();
        Assert.assertNotNull("Find all method for 'MovieUser' illegally returned null", result);
        Assert.assertTrue("Find all method for 'MovieUser' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMovieUserEntries() {
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", dod.getRandomMovieUser());
        long count = movieUserService.countAllMovieUsers();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<MovieUser> result = movieUserService.findMovieUserEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'MovieUser' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'MovieUser' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        MovieUser obj = dod.getRandomMovieUser();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to provide an identifier", id);
        obj = movieUserService.findMovieUser(id);
        Assert.assertNotNull("Find method for 'MovieUser' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMovieUser(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'MovieUser' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMovieUserUpdate() {
        MovieUser obj = dod.getRandomMovieUser();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to provide an identifier", id);
        obj = movieUserService.findMovieUser(id);
        boolean modified =  dod.modifyMovieUser(obj);
        Integer currentVersion = obj.getVersion();
        MovieUser merged = movieUserService.updateMovieUser(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'MovieUser' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMovieUser() {
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", dod.getRandomMovieUser());
        MovieUser obj = dod.getNewTransientMovieUser(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'MovieUser' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'MovieUser' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMovieUser() {
        MovieUser obj = dod.getRandomMovieUser();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MovieUser' failed to provide an identifier", id);
        obj = movieUserService.findMovieUser(id);
        movieUserService.deleteMovieUser(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'MovieUser' with identifier '" + id + "'", movieUserService.findMovieUser(id));
    }
}
