package es.unex.tests.dao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.unex.pi.dao.ChollosCategoryDAO;
import es.unex.pi.dao.JDBCChollosCategoryDAOImpl;
import es.unex.pi.model.ChollosCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestChollosCategoryDAO {

	static DBConn dbConn;
	static ChollosCategoryDAO chollosCategoryDAO;
	static Connection conn;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbConn = new DBConn();
		conn = dbConn.create();
	    chollosCategoryDAO = new JDBCChollosCategoryDAOImpl();
		chollosCategoryDAO.setConnection(conn);
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
		
		List<ChollosCategory> chollosCategoryList = chollosCategoryDAO.getAll();
		
		ChollosCategory chollosCategory = chollosCategoryDAO.get(0,0);
		
		assertEquals(chollosCategory.getIdc(),0);
		assertEquals(chollosCategory.getIdct(),0);
		
		assertEquals(chollosCategoryList.get(0).getIdc(),chollosCategory.getIdc());			
			
	}
	
	@Test
	public void test2BaseDataByCategory() {
		
		List<ChollosCategory> chollosCategoryList = chollosCategoryDAO.getAllByCategory(4);
		for(ChollosCategory chollosCategory: chollosCategoryList)			
			assertEquals(chollosCategory.getIdct(),4);			
	}
	
	@Test
	public void test3BaseDataByChollo() {
		
		List<ChollosCategory> chollosCategoryList = chollosCategoryDAO.getAllByChollo(0);
		for(ChollosCategory chollosCategory: chollosCategoryList)			
			assertEquals(chollosCategory.getIdc(),0);			
	}
	
	@Test
	public void test4Add(){
		ChollosCategory chollosCategory01 = new ChollosCategory();
		chollosCategory01.setIdc(3);
		chollosCategory01.setIdct(0);
		chollosCategoryDAO.add(chollosCategory01);
		
		ChollosCategory chollosCategory02 = chollosCategoryDAO.get(3,0);
		
		assertEquals(3,chollosCategory02.getIdc());
		assertEquals(0,chollosCategory02.getIdct());
				
	}
	
	
	@Test
	public void test5Modify(){
		
		ChollosCategory chollosCategory01 = chollosCategoryDAO.get(3,0);
		chollosCategory01.setIdct(1);
		chollosCategoryDAO.save(chollosCategory01);
		
		ChollosCategory chollosCategory02 = chollosCategoryDAO.get(3,1);
		assertEquals(3,chollosCategory02.getIdc());
		assertEquals(1,chollosCategory02.getIdct());
	}
	
	@Test
	public void test6Delete(){
		 
		chollosCategoryDAO.delete(3,1);
		List<ChollosCategory> chollosCategoryList = chollosCategoryDAO.getAll();
		 
		 ChollosCategory chollosCategory01 = new ChollosCategory();
		 chollosCategory01.setIdc(3);
		 chollosCategory01.setIdct(1);
		 		 
		for(ChollosCategory chollosCategory: chollosCategoryList) {
				assertNotEquals(chollosCategory,chollosCategory01);
		}
		 
	}

}
