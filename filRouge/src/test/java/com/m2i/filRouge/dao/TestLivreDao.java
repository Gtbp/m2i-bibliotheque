package com.m2i.filRouge.dao;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.m2i.filRouge.idao.IDaoLivre;

@SpringBootTest
// @ActiveProfiles{{oracle}}
public class TestLivreDao {

	Logger logger = LoggerFactory.getLogger(TestLivreDao.class);
	
	@Autowired
	private IDaoLivre iDaoLivre;
	
	 @Test
	 public void testInsert() {
		 
	 }
	 
	 @Test
	 public void testFindBy() {
		 
	 }
	 
	 @Test
	 public void testFindAll() {
		 
	 }
	 
	 @Test
	 public void testUpdate() {
		 
	 }
	 
	 @Test
	 public void testDelete() {
		 
	 }
	 
	 
}
