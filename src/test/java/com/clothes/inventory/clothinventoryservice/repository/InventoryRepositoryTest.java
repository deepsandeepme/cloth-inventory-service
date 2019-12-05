package com.clothes.inventory.clothinventoryservice.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.clothes.inventory.clothinventoryservice.mapper.InventoryRowMapper;

@RunWith(MockitoJUnitRunner.class)
public class InventoryRepositoryTest {

	private static final String query = "SELECT BCP.CLOTH_ID, BC.PRICE, B.BRAND_NAME, C.TYPE, BCP.COLOR, BCP.SIZE  FROM BRAND_CLOTHES_PROPERTIES BCP INNER JOIN BRAND_CLOTHES BC ON BCP.CLOTH_ID=BC.CLOTH_ID INNER JOIN BRANDS B ON B.ID=BC.BRAND_ID INNER JOIN CLOTHES C ON C.ID=BC.CLOTH_ID WHERE BCP.CLOTH_ID=?";
	private static final String delete_query = "DELETE FROM BRAND_CLOTHES_PROPERTIES WHERE CLOTH_ID=?";

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private InventoryRowMapper rowMapper;

	@InjectMocks
	private InventoryRepository repository;

	@Test
	public void testGetClothDetails() {
		when(jdbcTemplate.query(query, new Object[] { 101 }, rowMapper)).thenReturn(new ArrayList<>());
		assertNotNull(repository.getClothDetails(101));
		verify(jdbcTemplate).query(query, new Object[] { 101 }, rowMapper);
	}

	@Test
	public void testDeleteClothDetails() {
		when(jdbcTemplate.update(delete_query, new Object[] { 201 })).thenReturn(1);
		assertNotNull(repository.deleteCloth(201));
		verify(jdbcTemplate).update(delete_query, new Object[] { 201 });
	}

}
