package de.afikri.demo.jpa;


import de.afikri.demo.jpa.model.Item;
import de.afikri.demo.jpa.repository.ItemDao;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().minusDays(-1);


        ItemDao<Item> itemDao = new ItemDao<>(factory, Item.class);

        Item item = new Item("item 1");
        itemDao.save(item);





        Item actual = itemDao.findById(item.getId());

        assertThat(actual.getEntityMetaInfo().getCreatedDateStamp(), equalTo(LocalDate.now()));
        assertThat(actual.getEntityMetaInfo().getUpdatedDateStamp(), nullValue());

        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class , Mockito.RETURNS_MOCKS)) {
            utilities.when(LocalDate::now).thenReturn(tomorrow);
            System.out.println(LocalDate.now());
            actual.setName(("item  has been updated"));
            itemDao.merge(actual);
        }

        actual = itemDao.findById(item.getId());
        assertThat(actual.getEntityMetaInfo().getCreatedDateStamp(), equalTo(today));
        assertThat(actual.getEntityMetaInfo().getUpdatedDateStamp(), equalTo(tomorrow));

    }
}
