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

@Configurable
@Entity
public class Movie {


    /**
     */
    @NotNull
    private String movieName;

    /**
     */
    private String category;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date releaseDate;

    /**
     */
    private String summary;

    /**
     */
    private String banner;

    /**
     */
    private Double rentFee;

    /**
     */
    private Integer numberOfCopies;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdDate;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String getMovieName() {
        return this.movieName;
    }

	public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

	public String getCategory() {
        return this.category;
    }

	public void setCategory(String category) {
        this.category = category;
    }

	public Date getReleaseDate() {
        return this.releaseDate;
    }

	public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

	public String getSummary() {
        return this.summary;
    }

	public void setSummary(String summary) {
        this.summary = summary;
    }

	public String getBanner() {
        return this.banner;
    }

	public void setBanner(String banner) {
        this.banner = banner;
    }

	public Double getRentFee() {
        return this.rentFee;
    }

	public void setRentFee(Double rentFee) {
        this.rentFee = rentFee;
    }

	public Integer getNumberOfCopies() {
        return this.numberOfCopies;
    }

	public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

	public Date getCreatedDate() {
        return this.createdDate;
    }

	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new Movie().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMovies() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Movie o", Long.class).getSingleResult();
    }

	public static List<Movie> findAllMovies() {
        return entityManager().createQuery("SELECT o FROM Movie o", Movie.class).getResultList();
    }

	public static Movie findMovie(Long id) {
        if (id == null) return null;
        return entityManager().find(Movie.class, id);
    }

	public static List<Movie> findMovieEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Movie o", Movie.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Movie attached = Movie.findMovie(this.id);
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
    public Movie merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Movie merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
