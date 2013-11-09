package com.sjsu.movie.domain;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class MemberTypeDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<MemberType> data;

	public MemberType getNewTransientMemberType(int index) {
        MemberType obj = new MemberType();
        setMemberFee(obj, index);
        setTotalMovies(obj, index);
        setTypeName(obj, index);
        return obj;
    }

	public void setMemberFee(MemberType obj, int index) {
        Double memberFee = new Integer(index).doubleValue();
        obj.setMemberFee(memberFee);
    }

	public void setTotalMovies(MemberType obj, int index) {
        Integer totalMovies = new Integer(index);
        obj.setTotalMovies(totalMovies);
    }

	public void setTypeName(MemberType obj, int index) {
        String typeName = "typeName_" + index;
        if (typeName.length() > 30) {
            typeName = typeName.substring(0, 30);
        }
        obj.setTypeName(typeName);
    }

	public MemberType getSpecificMemberType(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        MemberType obj = data.get(index);
        Long id = obj.getId();
        return MemberType.findMemberType(id);
    }

	public MemberType getRandomMemberType() {
        init();
        MemberType obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return MemberType.findMemberType(id);
    }

	public boolean modifyMemberType(MemberType obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = MemberType.findMemberTypeEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'MemberType' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<MemberType>();
        for (int i = 0; i < 10; i++) {
            MemberType obj = getNewTransientMemberType(i);
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
