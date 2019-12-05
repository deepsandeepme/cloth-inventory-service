package com.clothes.inventory.clothinventoryservice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.clothes.inventory.clothinventoryservice.model.ClothDetails;

@Component
public class InventoryRowMapper implements RowMapper<ClothDetails> {

	@Override
	public ClothDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClothDetails clothDetails = new ClothDetails();
		clothDetails.setBrandName(rs.getString("BRAND_NAME"));
		clothDetails.setPrice(rs.getBigDecimal("PRICE"));
		clothDetails.setClothId(rs.getInt("CLOTH_ID"));
		clothDetails.setColor(rs.getString("COLOR"));
		clothDetails.setSize(rs.getString("SIZE"));
		return clothDetails;
	}

}
