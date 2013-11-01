package com.sjsu.movie.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MovieUser {

    /**
     */
    private Integer userId;

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String userName;

    /**
     */
    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    /**
     */
    @NotNull
    @Size(min = 2, max = 40)
    private String email;

    /**
     */
    @NotNull
    @Size(max = 100)
    private String addressLine1;

    /**
     */
    @NotNull
    @Size(max = 100)
    private String addressLine2;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String city;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String cState;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String country;

    /**
     */
    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    /**
     */
    @NotNull
    @Size(min = 8, max = 15)
    private String cellphone;

    /**
     */
    @NotNull
    @Size(max = 15)
    private String userRole;

    /**
     */
    private String userState;

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
}
