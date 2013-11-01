package com.sjsu.movie.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MembershipInfo {

    /**
     */
    private Integer membershipId;

    /**
     */
    @NotNull
    private String membershipNumber;

    /**
     */
    private Double balance;

    /**
     */
    @NotNull
    private Integer userId;

    /**
     */
    private Integer outstandingMovies;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updatedDate;

    /**
     */
    @ManyToOne
    private MovieUser movieUser;

    /**
     */
    @ManyToOne
    private MemberType memberType;
}
