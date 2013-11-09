package com.sjsu.movie.domain;
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
public class MembershipInfoDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<MembershipInfo> data;

	@Autowired
    MemberTypeDataOnDemand memberTypeDataOnDemand;

	@Autowired
    MovieUserDataOnDemand movieUserDataOnDemand;

	public MembershipInfo getNewTransientMembershipInfo(int index) {
        MembershipInfo obj = new MembershipInfo();
        setBalance(obj, index);
        setCreatedDate(obj, index);
        setMembershipNumber(obj, index);
        setOutstandingMovies(obj, index);
        setUpdatedDate(obj, index);
        return obj;
    }

	public void setBalance(MembershipInfo obj, int index) {
        Double balance = new Integer(index).doubleValue();
        obj.setBalance(balance);
    }

	public void setCreatedDate(MembershipInfo obj, int index) {
        Date createdDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreatedDate(createdDate);
    }

	public void setMembershipNumber(MembershipInfo obj, int index) {
        String membershipNumber = "membershipNumber_" + index;
        obj.setMembershipNumber(membershipNumber);
    }

	public void setOutstandingMovies(MembershipInfo obj, int index) {
        Integer outstandingMovies = new Integer(index);
        obj.setOutstandingMovies(outstandingMovies);
    }

	public void setUpdatedDate(MembershipInfo obj, int index) {
        Date updatedDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdatedDate(updatedDate);
    }

	public MembershipInfo getSpecificMembershipInfo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        MembershipInfo obj = data.get(index);
        Long id = obj.getId();
        return MembershipInfo.findMembershipInfo(id);
    }

	public MembershipInfo getRandomMembershipInfo() {
        init();
        MembershipInfo obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return MembershipInfo.findMembershipInfo(id);
    }

	public boolean modifyMembershipInfo(MembershipInfo obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = MembershipInfo.findMembershipInfoEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'MembershipInfo' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<MembershipInfo>();
        for (int i = 0; i < 10; i++) {
            MembershipInfo obj = getNewTransientMembershipInfo(i);
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
            data.add(obj);
        }
    }
}
