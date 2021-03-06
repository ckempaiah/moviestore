package com.sjsu.movie.domain;
import com.sjsu.movie.service.MovieService;
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
public class MovieIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MovieDataOnDemand dod;

	@Autowired
    MovieService movieService;

	@Test
    public void testCountAllMovies() {
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = movieService.countAllMovies();
        Assert.assertTrue("Counter for 'Movie' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMovie() {
        Movie obj = dod.getRandomMovie();
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = movieService.findMovie(id);
        Assert.assertNotNull("Find method for 'Movie' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Movie' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMovies() {
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = movieService.countAllMovies();
        Assert.assertTrue("Too expensive to perform a find all test for 'Movie', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Movie> result = movieService.findAllMovies();
        Assert.assertNotNull("Find all method for 'Movie' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Movie' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMovieEntries() {
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        long count = movieService.countAllMovies();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Movie> result = movieService.findMovieEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Movie' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Movie' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Movie obj = dod.getRandomMovie();
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = movieService.findMovie(id);
        Assert.assertNotNull("Find method for 'Movie' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMovie(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Movie' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMovieUpdate() {
        Movie obj = dod.getRandomMovie();
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = movieService.findMovie(id);
        boolean modified =  dod.modifyMovie(obj);
        Integer currentVersion = obj.getVersion();
        Movie merged = movieService.updateMovie(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Movie' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMovie() {
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", dod.getRandomMovie());
        Movie obj = dod.getNewTransientMovie(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Movie' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Movie' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Movie' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMovie() {
        Movie obj = dod.getRandomMovie();
        Assert.assertNotNull("Data on demand for 'Movie' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Movie' failed to provide an identifier", id);
        obj = movieService.findMovie(id);
        movieService.deleteMovie(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'Movie' with identifier '" + id + "'", movieService.findMovie(id));
    }
}
