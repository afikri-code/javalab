package de.afikri.demo.jpa;

import de.afikri.demo.jpa.model.Item;
import de.afikri.demo.jpa.repository.ItemDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String PERSISTENCE_UNIT_NAME = "demo";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        Item item = new Item();
        item.setName("item 2");
        ItemDao<Item> itemDao = new ItemDao<>(factory, Item.class);
        itemDao.create(item);
        itemDao.findAll().forEach(System.out::println);
    }

}
