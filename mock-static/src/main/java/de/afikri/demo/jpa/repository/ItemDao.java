package de.afikri.demo.jpa.repository;

import javax.persistence.EntityManagerFactory;

public class ItemDao<Item> extends Dao<Item>{
    public ItemDao(EntityManagerFactory factory, Class<Item> entityClass) {
        super(factory, entityClass);
    }

}
