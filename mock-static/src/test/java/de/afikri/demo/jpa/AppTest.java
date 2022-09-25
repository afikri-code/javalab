package de.afikri.demo.jpa;


import de.afikri.demo.jpa.model.Item;
import de.afikri.demo.jpa.repository.ItemDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final String PERSISTENCE_UNIT_NAME = "demo";
    private static EntityManagerFactory factory;
    @BeforeAll
    static void setUp(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    @Test
    void createAndUpdateEntity(){
        LocalDate x = LocalDate.now().minusDays(-10);


        ItemDao<Item> itemDao = new ItemDao<>(factory, Item.class);

        Item item1 = new Item();
        item1.setName("item 1");
        Item item2 = new Item();
        item2.setName("item 2");
        itemDao.create(item1);
        itemDao.create(item2);
        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class,Mockito.RETURNS_MOCKS)) {
            utilities.when(LocalDate::now).thenReturn(x);
            System.out.println(LocalDate.now());
            item2.setName(("item 2 has been updated"));
            itemDao.update(item2);
        }

        List<Item> items = itemDao.findAll();

        assertThat(items, hasSize(2));

        //assertThat()
    }
}
