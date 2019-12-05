package com.clothes.inventory.clothinventoryservice.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.clothes.inventory.clothinventoryservice.model.ClothDetails;

@RunWith(MockitoJUnitRunner.class)
public class InventoryRowMapperTest {

	InventoryRowMapper inventoryRowMapper;

	@Before
	public void setup() {
		inventoryRowMapper = new InventoryRowMapper();
	}

	@Mock
	private ResultSet rs;

	@Test
	public void testRowMapper() throws SQLException {
		when(rs.getString("BRAND_NAME")).thenReturn("brand");
		when(rs.getBigDecimal("PRICE")).thenReturn(new BigDecimal(103));
		when(rs.getInt("CLOTH_ID")).thenReturn(101);
		when(rs.getString("COLOR")).thenReturn("blue");
		when(rs.getString("SIZE")).thenReturn("M");
		ClothDetails result = inventoryRowMapper.mapRow(rs, 0);
		assertEquals("brand", result.getBrandName());
		assertEquals("103", result.getPrice().toString());
		assertEquals(101, result.getClothId());
		assertEquals("blue", result.getColor());
		assertEquals("M", result.getSize());

	}

}
