package es.unex.tests.dao;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.unex.pi.model.Category;
import es.unex.pi.model.Shop;
import es.unex.pi.model.User;
import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOImpl;
import es.unex.pi.dao.JDBCShopDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ShopDAO;
import es.unex.pi.dao.UserDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestShopDAO {

	static DBConn dbConn;
	static ShopDAO shopDAO;
	static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbConn = new DBConn();
		conn = dbConn.create();
	    shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
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
		Shop shop00 = shopDAO.get(0);
		assertEquals(shop00.getId(),0);
		assertEquals(shop00.getName(),"El Corte Inglés");
		assertEquals(shop00.getUrl(),"www.elcorteingles.es");
		
		Shop shop01 = shopDAO.get(1);
		assertEquals(shop01.getId(),1);
		assertEquals(shop01.getName(),"amazon");
		assertEquals(shop01.getUrl(),"www.amazon.es");

		Shop shop02 = shopDAO.get(2);
		assertEquals(shop02.getId(),2);
		assertEquals(shop02.getName(),"Wiggle");
		assertEquals(shop02.getUrl(),"www.wiggle.com");
		
		Shop shop03 = shopDAO.get(3);
		assertEquals(shop03.getId(),3);
		assertEquals(shop03.getName(),"BiciMarket");
		assertEquals(shop03.getUrl(),"www.bicimarket.com");
	}
	
	
	@Test
	public void test2Add(){
		Shop shop01 = new Shop();
		shop01.setName("newShop");
		long value = shopDAO.add(shop01);
		
		Shop shop02 = shopDAO.get("newShop");
		assertEquals(shop01.getName(),shop02.getName());
	}
	
	@Test
	public void test3Modify(){
		Shop shop01 = shopDAO.get("newShop");
		shop01.setName("newShopUpdated");
		shopDAO.save(shop01);
		
		Shop shop02 = shopDAO.get("newShopUpdated");		
		assertEquals(shop01.getName(),shop02.getName());
	}
	
	@Test
	public void test4Delete(){
		 Shop shop01 = shopDAO.get("newShopUpdated");
		 shopDAO.delete(shop01.getId());
		 
		 Shop shop02 = shopDAO.get("newShopUpdated");
 		 assertEquals(null, shop02);
 		 
 		 shopDAO.getAll();
	}

}
