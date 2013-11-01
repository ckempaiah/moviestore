// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sjsu.movie.domain;

import com.sjsu.movie.domain.MovieRental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MovieRental_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MovieRental.entityManager;
    
    public static final EntityManager MovieRental.entityManager() {
        EntityManager em = new MovieRental().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MovieRental.countMovieRentals() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MovieRental o", Long.class).getSingleResult();
    }
    
    public static List<MovieRental> MovieRental.findAllMovieRentals() {
        return entityManager().createQuery("SELECT o FROM MovieRental o", MovieRental.class).getResultList();
    }
    
    public static MovieRental MovieRental.findMovieRental(Long id) {
        if (id == null) return null;
        return entityManager().find(MovieRental.class, id);
    }
    
    public static List<MovieRental> MovieRental.findMovieRentalEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MovieRental o", MovieRental.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MovieRental.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MovieRental.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MovieRental attached = MovieRental.findMovieRental(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MovieRental.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MovieRental.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MovieRental MovieRental.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MovieRental merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
