package com.sjsu.movie.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Configurable
public class MemberType {

    /**
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String typeName;

    /**
     */
    private Integer totalMovies;

    /**
     */
    private Double memberFee;

	public String getTypeName() {
        return this.typeName;
    }

	public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

	public Integer getTotalMovies() {
        return this.totalMovies;
    }

	public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

	public Double getMemberFee() {
        return this.memberFee;
    }

	public void setMemberFee(Double memberFee) {
        this.memberFee = memberFee;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new MemberType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMemberTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MemberType o", Long.class).getSingleResult();
    }

	public static List<MemberType> findAllMemberTypes() {
        return entityManager().createQuery("SELECT o FROM MemberType o", MemberType.class).getResultList();
    }

	public static MemberType findMemberType(Long id) {
        if (id == null) return null;
        return entityManager().find(MemberType.class, id);
    }

	public static List<MemberType> findMemberTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MemberType o", MemberType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            MemberType attached = MemberType.findMemberType(this.id);
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
    public MemberType merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MemberType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
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
}
