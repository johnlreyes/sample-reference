package net.test.jpa;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import net.test.jpa.Game;


@RunWith(Arquillian.class)
public class GamePersistenceTestCase {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Game.class.getPackage())
				.addManifestResource("test-persistence.xml", "persistence.xml")
				.addWebResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private static final String[] GAME_TITLES = {
		"Super Mario Brothers",
		"Mario Kart",
		"F-Zero"
	};

	@PersistenceContext
	EntityManager em;

	@Inject
	UserTransaction utx;


	public void insertSampleRecords() throws Exception {
		utx.begin();
		em.joinTransaction();

		try {	
			System.out.println("Clearing the database ...");
			em.createQuery("delete from Game").executeUpdate();
		} catch (Exception ex) {
		}

		System.out.println("Inserting records...");
		for (String title : GAME_TITLES) {
			Game game = new Game(title);
			em.persist(game);
		}

		utx.commit();
	}


	@Test
	public void should_be_able_to_select_games_usering_jpql() throws Exception {
		insertSampleRecords();

		utx.begin();
		em.joinTransaction();

		System.out.println("Selecting (using JPQL) ...");
		List<Game> games = em.createQuery("select g from Game g order by g.id", Game.class).getResultList();
		System.out.println("Found " + games.size() + " games (using JPQL)");
		assertEquals(GAME_TITLES.length, games.size());

		System.out.println("["+getClass().getName()+":should_be_able_to_select_games_usering_jpql] START");
		for (int i=0; i<GAME_TITLES.length; i++) {
			assertEquals(GAME_TITLES[i], games.get(i).getTitle());
			System.out.println(games.get(i));
		}
		System.out.println("["+getClass().getName()+":should_be_able_to_select_games_usering_jpql] END");
		
		utx.commit();
	}


	@Test
	public void should_be_able_to_select_games_using_criteria_api() throws Exception {
		insertSampleRecords();

		utx.begin();
		em.joinTransaction();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Game> criteria = builder.createQuery(Game.class);

		Root<Game> game = criteria.from(Game.class);
		criteria.select(game);
		criteria.orderBy(builder.asc(game.get(Game_.id)));

		System.out.println("Selecting (using Criteria) ...");
		List<Game> games = em.createQuery(criteria).getResultList();
		System.out.println("Found " + games.size() + " games (using Criteria)");
		assertEquals(GAME_TITLES.length, games.size());

		System.out.println("["+getClass().getName()+":should_be_able_to_select_games_using_criteria_api] START");
		for (int i=0; i<GAME_TITLES.length; i++) {
			assertEquals(GAME_TITLES[i], games.get(i).getTitle());
			System.out.println(games.get(i));
		}
		System.out.println("["+getClass().getName()+":should_be_able_to_select_games_using_criteria_api] END");
		
		utx.commit();
	}


}
