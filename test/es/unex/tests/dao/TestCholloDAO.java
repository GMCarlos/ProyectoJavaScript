package es.unex.tests.dao;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import es.unex.pi.dao.CholloDAO;
import es.unex.pi.dao.JDBCCholloDAOImpl;
import es.unex.pi.model.Chollo;

import org.junit.Test;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCholloDAO {

	static DBConn dbConn;
	static CholloDAO cholloDAO;
	static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbConn = new DBConn();
		conn = dbConn.create();
	    cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		dbConn.destroy(conn);
	    
	}

	@Before
	public void setUpBeforeMethod() throws Exception {
	
	}

	@Test
	public void test1BaseData() {
		
		Chollo chollo01 = cholloDAO.get(0);
		assertEquals(chollo01.getId(),0);
		assertEquals(chollo01.getTitle(),"IPhone");

		Chollo chollo02 = cholloDAO.get(1);
		assertEquals(chollo02.getId(),1);
		assertEquals(chollo02.getDescription(),"Beats HeadPhones");
		
		Chollo chollo03 = cholloDAO.get(2);
		assertEquals(chollo03.getId(),2);
		assertEquals(chollo03.getTitle(),"MacBookPro");
		
		Chollo chollo04 = cholloDAO.get(3);
		assertEquals(chollo04.getId(),3);
		assertEquals(chollo04.getDescription(),"Bici de montaña");
		
		Chollo chollo05 = cholloDAO.get(4);
		assertEquals(chollo05.getId(),4);
		assertEquals(chollo05.getDescription(),"Casco de ciclismo aero");
		
		Chollo chollo06 = cholloDAO.getAllBySearchTitle("MacBookPro").iterator().next();
		assertEquals(chollo06.getDescription(),"Laptop");
		
		Chollo chollo07 = cholloDAO.getAllBySearchDescription("Casco").iterator().next();
		assertEquals(chollo07.getTitle(),"Casco aero");
		
		Chollo chollo08 = cholloDAO.getAllBySearchAll("bici").iterator().next();
		assertEquals(chollo08.getDescription(),"Bici de montaña");
		
		
//		cholloDAO.getAllBySearchTitle("Rebel");
//		cholloDAO.getAllBySearchDescription("and");
//		cholloDAO.getAllBySearchAll("Imperial");
//						
//		cholloDAO.getAll();	
		
	}
	
	
	@Test
	public void test2Add(){
		Chollo chollo01 = new Chollo();
		chollo01.setTitle("newChollo");
		chollo01.setDescription("new description");
		chollo01.setLink("www.google.com");
		chollo01.setPrice(300);
		chollo01.setIdu(0);
		chollo01.setIds(2);
		chollo01.setLikes(10);
		chollo01.setSoldout(0);
		
		cholloDAO.add(chollo01);
		
		Chollo chollo02 = cholloDAO.getAllBySearchTitle("newChollo").iterator().next();
		assertEquals(chollo01.getDescription(),chollo02.getDescription());
		
//		cholloDAO.getAll();
	}
	
	@Test
	public void test3Modify(){
		Chollo chollo01 = cholloDAO.getAllBySearchTitle("newChollo").iterator().next();
		chollo01.setTitle("newCholloUpdated");
		chollo01.setDescription("new description updated");
		cholloDAO.save(chollo01);
		
		Chollo chollo02 = cholloDAO.getAllBySearchTitle("newCholloUpdated").iterator().next();
		assertEquals(chollo01.getDescription(),chollo02.getDescription());
		
		cholloDAO.getAll();
	}
	
	@Test
	public void test4Delete(){
		
		 Chollo chollo01 = cholloDAO.getAllBySearchTitle("newCholloUpdated").iterator().next();
		 long idChollo= chollo01.getId();
		 cholloDAO.delete(idChollo);
		 
		 Chollo chollo02 = cholloDAO.get(idChollo);
		 assertEquals(null,chollo02);
		 
		cholloDAO.getAll();
	}

}
