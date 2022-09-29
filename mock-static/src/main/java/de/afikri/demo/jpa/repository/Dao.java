package de.afikri.demo.jpa.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

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
    public final void save(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    public final void merge(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        em.close();
    }
    public T findById(Integer id){
        EntityManager em = getEntityManager();
        return em.find(entityClass, id);
    }

    public final List<T> findAll() {
      return getEntityManager().createQuery("from " + entityClass.getName()).getResultList();
    }

}
