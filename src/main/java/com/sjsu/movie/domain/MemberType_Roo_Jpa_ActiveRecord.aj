// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.movie.domain;

import com.sjsu.movie.domain.MemberType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MemberType_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MemberType.entityManager;
    
    public static final EntityManager MemberType.entityManager() {
        EntityManager em = new MemberType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MemberType.countMemberTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MemberType o", Long.class).getSingleResult();
    }
    
    public static List<MemberType> MemberType.findAllMemberTypes() {
        return entityManager().createQuery("SELECT o FROM MemberType o", MemberType.class).getResultList();
    }
    
    public static MemberType MemberType.findMemberType(Long id) {
        if (id == null) return null;
        return entityManager().find(MemberType.class, id);
    }
    
    public static List<MemberType> MemberType.findMemberTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MemberType o", MemberType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MemberType.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MemberType.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MemberType attached = MemberType.findMemberType(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MemberType.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MemberType.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MemberType MemberType.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MemberType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
