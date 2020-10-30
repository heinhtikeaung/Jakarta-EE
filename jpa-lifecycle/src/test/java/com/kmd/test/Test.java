package com.kmd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.kmd.entity.Developer;

class Test {
	
	static EntityManagerFactory emf;
	
	static EntityManager em;

	@org.junit.jupiter.api.Test
	void test() {
		
		Developer developer = new Developer();		
		developer.setName("Tun Tun");		
		developer.setPosition("Senior Developer");
		developer.setQualification(Arrays.asList("BSE", "BIT"));
		
		em.getTransaction().begin();
		em.persist(developer);
		em.getTransaction().commit();
		
		assertEquals(1, developer.getId());
	}
	
	@org.junit.jupiter.api.Test
	void test2() {
		
		Developer developer = em.find(Developer.class, 1);
		
		developer.setPosition("Team Lead");
		
//		em.detach(developer);
		
		developer.getQualification().add("PhD");
		
		em.getTransaction().begin();
		em.merge(developer);
		em.getTransaction().commit();
		
		assertEquals("Team Lead", developer.getPosition());
	}
	
	@org.junit.jupiter.api.Test
	void test3() {
	
		Developer developer = em.find(Developer.class, 1);
		
		em.getTransaction().begin();
		em.remove(developer);
		em.getTransaction().commit();
		
		assertNull(em.find(Developer.class, 1));
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("jpa");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}


}
