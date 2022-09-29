package de.afikri.demo.jpa;

import de.afikri.demo.jpa.model.Item;
import de.afikri.demo.jpa.repository.ItemDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String PERSISTENCE_UNIT_NAME = "demo";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        Item item = new Item("item 2");
        ItemDao<Item> itemDao = new ItemDao<>(factory, Item.class);
        itemDao.save(item);
        itemDao.findAll().forEach(System.out::println);
    }

}



/**
 * Unit test for simple App.
 */
/*
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



        ItemDao<Item> itemDao = new ItemDao<>(factory, Item.class);

        Item item1 = new Item("item 1");
        itemDao.save(item1);

        LocalDate tomorrow = LocalDate.now().minusDays(-1);



        Item actual = itemDao.findById(item1.getId());

        assertThat(actual.getEntityMetaInfo().getCreatedDateStamp(), equalTo(LocalDate.now()));
        assertThat(actual.getEntityMetaInfo().getUpdatedDateStamp(), nullValue());
        actual = itemDao.findById(item1.getId());
        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class, Mockito.RETURNS_MOCKS )) {
            utilities.when(LocalDate::now).thenReturn(tomorrow);
            System.out.println(LocalDate.now());
            actual.setName(("item 2 has been updated"));
            itemDao.merge(actual);
        }
        actual = itemDao.findById(item1.getId());
        assertThat(actual.getEntityMetaInfo().getCreatedDateStamp(), equalTo(today));
        assertThat(actual.getEntityMetaInfo().getUpdatedDateStamp(), equalTo(tomorrow));

    }
}

 */

