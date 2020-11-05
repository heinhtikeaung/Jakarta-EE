package com.kmd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.kmd.jpa.entity.Employee;
import com.kmd.jpa.entity.Qualification;

@TestMethodOrder(OrderAnnotation.class)
class CascadeTest {
	
	static EntityManagerFactory emf;
	
	EntityManager em;

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

	@Test
	@Order(1)
	void testForPersist() {

		Qualification qualification1 = new Qualification();
		qualification1.setQualification("BSC");
		
		Employee employee = new Employee();
		employee.setName("Tun Tun");
		employee.save(qualification1);
		
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		
		assertEquals(1, employee.getQualifications().size());
	}
	
	@Test
	@Order(2)
	void testForMerge() {
		Employee employee = em.find(Employee.class, 1);
		employee.setName("Su Su");
		
		Qualification qualification2 = new Qualification();
		qualification2.setQualification("Phd");
		
		employee.save(qualification2);
		
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		
		assertEquals(2, employee.getQualifications().size());
	}
	
	@Test
	@Order(3)
	void testForRemove() {
		Employee employee = em.find(Employee.class, 1);
		
		Qualification qualification = employee.getQualifications().get(1);
		
		em.getTransaction().begin();
		employee.remove(qualification);
		em.remove(qualification);
		em.getTransaction().commit();
		
		assertEquals(1, employee.getQualifications().size());
	}

}
