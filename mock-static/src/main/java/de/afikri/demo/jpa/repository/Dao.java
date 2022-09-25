package de.afikri.demo.jpa.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

abstract class Dao<T> {
    final private EntityManagerFactory factory;
    final private Class<T> entityClass;

    public Dao(EntityManagerFactory factory, Class<T> entityClass){
        this.factory = factory;
        this.entityClass = entityClass;
    }

    protected final EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    public final void create(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    public final void update(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        em.close();
    }
    public final List<T> findAll() {
      return getEntityManager().createQuery("from " + entityClass.getName()).getResultList();
    }

}
