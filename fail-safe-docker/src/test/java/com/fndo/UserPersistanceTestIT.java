package com.fndo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.function.Consumer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class UserPersistanceTestIT {

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void beforeClass() {
        emf = Persistence.createEntityManagerFactory("FailSafeDocker");
    }

    @AfterClass
    public static void afterClass() {
        emf.close();
    }

    @Before
    public void before() {
        doInTransaction(em -> {
            em.createQuery("DELETE FROM User").executeUpdate();
        });
    }

    @Test
    public void should_persist_a_new_user() {
        final User user = new User("bill", "bill@bill.com");

        doInTransaction(em -> {
            em.persist(user);
        });

        assertNotNull(user.getId());

        doInTransaction(em -> {
            final User persisted = em.find(User.class, user.getId());
            assertEquals(user.getName(), persisted.getName());
            assertEquals(user.getEmail(), persisted.getEmail());
        });
    }

    @Test
    public void should_update_a_user() {
        final User user = new User("bill", "bill@bill.com");

        doInTransaction(em -> em.persist(user));

        doInTransaction(em -> em.find(User.class, user.getId()).setName("bill2"));

        doInTransaction(em -> assertEquals("bill2", em.find(User.class, user.getId()).getName()));
    }

    @Test
    public void should_remove_a_user() {
        final User user = new User("bill", "bill@bill.com");

        doInTransaction(em -> em.persist(user));

        doInTransaction(em -> assertNotNull(em.find(User.class, user.getId())));

        doInTransaction(em -> em.remove(em.find(User.class, user.getId())));

        doInTransaction(em -> assertNull(em.find(User.class, user.getId())));
    }

    private static void doInTransaction(final Consumer<EntityManager> consumer) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (final Exception exception) {
            em.getTransaction().rollback();
            throw exception;
        }
    }
}
