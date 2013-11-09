package com.sjsu.movie.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;

import com.sjsu.movie.bean.MovieUserBean;

@Configurable
@Entity
public class MovieUser {

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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new MovieUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMovieUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MovieUser o", Long.class).getSingleResult();
    }

	public static List<MovieUser> findAllMovieUsers() {
        return entityManager().createQuery("SELECT o FROM MovieUser o", MovieUser.class).getResultList();
    }

	public static MovieUser findMovieUser(Long id) {
        if (id == null) return null;
        return entityManager().find(MovieUser.class, id);
    }

	public static List<MovieUser> findMovieUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MovieUser o", MovieUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MovieUser attached = MovieUser.findMovieUser(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public MovieUser merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MovieUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String getUserName() {
        return this.userName;
    }

	public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public String getFirstName() {
        return this.firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getEmail() {
        return this.email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getAddressLine1() {
        return this.addressLine1;
    }

	public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

	public String getAddressLine2() {
        return this.addressLine2;
    }

	public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

	public String getCity() {
        return this.city;
    }

	public void setCity(String city) {
        this.city = city;
    }

	public String getCState() {
        return this.cState;
    }

	public void setCState(String cState) {
        this.cState = cState;
    }

	public String getCountry() {
        return this.country;
    }

	public void setCountry(String country) {
        this.country = country;
    }

	public String getZipCode() {
        return this.zipCode;
    }

	public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	public String getCellphone() {
        return this.cellphone;
    }

	public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

	public String getUserRole() {
        return this.userRole;
    }

	public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

	public String getUserState() {
        return this.userState;
    }

	public void setUserState(String userState) {
        this.userState = userState;
    }

	public Date getCreatedDate() {
        return this.createdDate;
    }

	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	public Date getUpdatedDate() {
        return this.updatedDate;
    }

	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
	
	public MovieUserBean getBean(){
		return new MovieUserBean(id, userName, password, firstName,
				lastName, email, addressLine1,
				addressLine2, city, cState, country,
				zipCode, cellphone, userRole,
				userState, createdDate, updatedDate);
	}
	
	public MovieUser(){
		
	}
	public MovieUser copyData(MovieUserBean bean){
		
		this.userName = bean.getUserName();
		this.password = bean.getPassword();
		this.firstName = bean.getPassword();
		this.lastName = bean.getLastName();
		this.email = bean.getEmail();;
		this.addressLine1 = bean.getAddressLine1();
		this.addressLine2 = bean.getAddressLine2();
		this.city = bean.getCity();
		this.cState = bean.getcState();
		this.country = bean.getCountry();
		this.zipCode = bean.getZipCode();
		this.cellphone = bean.getCellphone();
		this.userRole = bean.getUserRole();
		this.userState = bean.getUserState();
		this.createdDate = bean.getCreatedDate();
		this.updatedDate = new Date();
		return this;
		
	}
}
