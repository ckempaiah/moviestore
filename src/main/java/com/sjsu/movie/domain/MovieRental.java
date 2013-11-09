package com.sjsu.movie.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
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
import javax.persistence.ManyToOne;

@Entity
@Configurable
public class MovieRental {


    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date rentedDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date returnedDate;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dueDate;

    /**
     */
    @NotNull
    private String comments;

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
    private Movie movie;

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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new MovieRental().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMovieRentals() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MovieRental o", Long.class).getSingleResult();
    }

	public static List<MovieRental> findAllMovieRentals() {
        return entityManager().createQuery("SELECT o FROM MovieRental o", MovieRental.class).getResultList();
    }

	public static MovieRental findMovieRental(Long id) {
        if (id == null) return null;
        return entityManager().find(MovieRental.class, id);
    }

	public static List<MovieRental> findMovieRentalEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MovieRental o", MovieRental.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            MovieRental attached = MovieRental.findMovieRental(this.id);
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
    public MovieRental merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MovieRental merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Date getRentedDate() {
        return this.rentedDate;
    }

	public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

	public Date getReturnedDate() {
        return this.returnedDate;
    }

	public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

	public Date getDueDate() {
        return this.dueDate;
    }

	public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

	public String getComments() {
        return this.comments;
    }

	public void setComments(String comments) {
        this.comments = comments;
    }

	public Date getUpdatedDate() {
        return this.updatedDate;
    }

	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

	public MovieUser getMovieUser() {
        return this.movieUser;
    }

	public void setMovieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
    }

	public Movie getMovie() {
        return this.movie;
    }

	public void setMovie(Movie movie) {
        this.movie = movie;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
