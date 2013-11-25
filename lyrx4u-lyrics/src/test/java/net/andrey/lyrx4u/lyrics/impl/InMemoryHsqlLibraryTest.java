package net.andrey.lyrx4u.lyrics.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.andrey.lyrx4u.lyrics.facade.AbstractLibraryTest;
import net.andrey.lyrx4u.lyrics.facade.LyricsLibrary;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public final class InMemoryHsqlLibraryTest extends AbstractLibraryTest {
	private static EntityManager em;
	private static EntityManagerFactory factory;

	@Override
	protected LyricsLibrary createTestObject() {
		return new SongJpaDao(em);
	}

	@BeforeClass
	public static void setUpDB() {

		factory = Persistence.createEntityManagerFactory("lyrx4u");
		em = factory.createEntityManager();
	}

	@AfterClass
	public static void tearDownDB() {
		if (em != null) {
			em.close();
			factory.close();
		}
	}

}
