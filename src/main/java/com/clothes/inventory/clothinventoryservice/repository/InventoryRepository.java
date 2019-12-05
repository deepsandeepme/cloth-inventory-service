package com.clothes.inventory.clothinventoryservice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clothes.inventory.clothinventoryservice.mapper.InventoryRowMapper;
import com.clothes.inventory.clothinventoryservice.model.ClothDetails;

@Repository
public class InventoryRepository {

	private static final String query = "SELECT BCP.CLOTH_ID, BC.PRICE, B.BRAND_NAME, C.TYPE, BCP.COLOR, BCP.SIZE  FROM BRAND_CLOTHES_PROPERTIES BCP INNER JOIN BRAND_CLOTHES BC ON BCP.CLOTH_ID=BC.CLOTH_ID INNER JOIN BRANDS B ON B.ID=BC.BRAND_ID INNER JOIN CLOTHES C ON C.ID=BC.CLOTH_ID WHERE BCP.CLOTH_ID=?";
	private static final String delete_query = "DELETE FROM BRAND_CLOTHES_PROPERTIES WHERE CLOTH_ID=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private InventoryRowMapper rowMapper;

	public List<ClothDetails> getClothDetails(int clothId) {
		return jdbcTemplate.query(query, new Object[] { clothId }, rowMapper);
	}

	public int deleteCloth(int clothId) {

		return jdbcTemplate.update(delete_query, new Object[] { clothId });
	}
}
