package com.sjsu.movie.domain;
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
public class MemberTypeIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MemberTypeDataOnDemand dod;

	@Test
    public void testCountMemberTypes() {
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", dod.getRandomMemberType());
        long count = MemberType.countMemberTypes();
        Assert.assertTrue("Counter for 'MemberType' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMemberType() {
        MemberType obj = dod.getRandomMemberType();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to provide an identifier", id);
        obj = MemberType.findMemberType(id);
        Assert.assertNotNull("Find method for 'MemberType' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'MemberType' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMemberTypes() {
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", dod.getRandomMemberType());
        long count = MemberType.countMemberTypes();
        Assert.assertTrue("Too expensive to perform a find all test for 'MemberType', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<MemberType> result = MemberType.findAllMemberTypes();
        Assert.assertNotNull("Find all method for 'MemberType' illegally returned null", result);
        Assert.assertTrue("Find all method for 'MemberType' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMemberTypeEntries() {
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", dod.getRandomMemberType());
        long count = MemberType.countMemberTypes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<MemberType> result = MemberType.findMemberTypeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'MemberType' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'MemberType' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        MemberType obj = dod.getRandomMemberType();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to provide an identifier", id);
        obj = MemberType.findMemberType(id);
        Assert.assertNotNull("Find method for 'MemberType' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMemberType(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'MemberType' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        MemberType obj = dod.getRandomMemberType();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to provide an identifier", id);
        obj = MemberType.findMemberType(id);
        boolean modified =  dod.modifyMemberType(obj);
        Integer currentVersion = obj.getVersion();
        MemberType merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'MemberType' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", dod.getRandomMemberType());
        MemberType obj = dod.getNewTransientMemberType(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'MemberType' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'MemberType' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'MemberType' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        MemberType obj = dod.getRandomMemberType();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'MemberType' failed to provide an identifier", id);
        obj = MemberType.findMemberType(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'MemberType' with identifier '" + id + "'", MemberType.findMemberType(id));
    }
}
