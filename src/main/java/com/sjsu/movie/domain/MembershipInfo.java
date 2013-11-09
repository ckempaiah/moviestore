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
public class MembershipInfo {

    /**
     */
    @NotNull
    private String membershipNumber;

    /**
     */
    private Double balance;


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

	public String getMembershipNumber() {
        return this.membershipNumber;
    }

	public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

	public Double getBalance() {
        return this.balance;
    }

	public void setBalance(Double balance) {
        this.balance = balance;
    }

	public Integer getOutstandingMovies() {
        return this.outstandingMovies;
    }

	public void setOutstandingMovies(Integer outstandingMovies) {
        this.outstandingMovies = outstandingMovies;
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

	public MovieUser getMovieUser() {
        return this.movieUser;
    }

	public void setMovieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
    }

	public MemberType getMemberType() {
        return this.memberType;
    }

	public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new MembershipInfo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMembershipInfoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MembershipInfo o", Long.class).getSingleResult();
    }

	public static List<MembershipInfo> findAllMembershipInfoes() {
        return entityManager().createQuery("SELECT o FROM MembershipInfo o", MembershipInfo.class).getResultList();
    }

	public static MembershipInfo findMembershipInfo(Long id) {
        if (id == null) return null;
        return entityManager().find(MembershipInfo.class, id);
    }

	public static List<MembershipInfo> findMembershipInfoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MembershipInfo o", MembershipInfo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            MembershipInfo attached = MembershipInfo.findMembershipInfo(this.id);
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
    public MembershipInfo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MembershipInfo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
