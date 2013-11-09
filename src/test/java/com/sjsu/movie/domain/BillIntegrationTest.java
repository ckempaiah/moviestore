package com.sjsu.movie.domain;
import com.sjsu.movie.service.BillService;
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

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class BillIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    BillDataOnDemand dod;

	@Autowired
    BillService billService;

	@Test
    public void testCountAllBills() {
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", dod.getRandomBill());
        long count = billService.countAllBills();
        Assert.assertTrue("Counter for 'Bill' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindBill() {
        Bill obj = dod.getRandomBill();
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Bill' failed to provide an identifier", id);
        obj = billService.findBill(id);
        Assert.assertNotNull("Find method for 'Bill' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Bill' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllBills() {
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", dod.getRandomBill());
        long count = billService.countAllBills();
        Assert.assertTrue("Too expensive to perform a find all test for 'Bill', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Bill> result = billService.findAllBills();
        Assert.assertNotNull("Find all method for 'Bill' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Bill' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindBillEntries() {
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", dod.getRandomBill());
        long count = billService.countAllBills();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Bill> result = billService.findBillEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Bill' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Bill' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Bill obj = dod.getRandomBill();
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Bill' failed to provide an identifier", id);
        obj = billService.findBill(id);
        Assert.assertNotNull("Find method for 'Bill' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyBill(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Bill' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateBillUpdate() {
        Bill obj = dod.getRandomBill();
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Bill' failed to provide an identifier", id);
        obj = billService.findBill(id);
        boolean modified =  dod.modifyBill(obj);
        Integer currentVersion = obj.getVersion();
        Bill merged = billService.updateBill(obj);
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Bill' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveBill() {
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", dod.getRandomBill());
        Bill obj = dod.getNewTransientBill(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Bill' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Bill' identifier to be null", obj.getId());
        try {
            billService.saveBill(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'Bill' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteBill() {
        Bill obj = dod.getRandomBill();
        Assert.assertNotNull("Data on demand for 'Bill' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Bill' failed to provide an identifier", id);
        obj = billService.findBill(id);
        billService.deleteBill(obj);
        obj.flush();
        Assert.assertNull("Failed to remove 'Bill' with identifier '" + id + "'", billService.findBill(id));
    }
}
