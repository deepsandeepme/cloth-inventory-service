package com.clothes.inventory.clothinventoryservice.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.clothes.inventory.clothinventoryservice.exceptions.InvalidInputException;
import com.clothes.inventory.clothinventoryservice.model.ClothDetails;
import com.clothes.inventory.clothinventoryservice.repository.InventoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

	@Mock
	private InventoryRepository repository;

	@InjectMocks
	private InventoryService service;

	@Test
	public void testGetClothDetailsBrands() throws InvalidInputException {
		when(repository.getClothDetails(123)).thenReturn(buildCLothes());
		ResponseEntity<Map<Object, List<ClothDetails>>> result = service.getClothDetails(123, "brand");
		assertEquals(1, result.getBody().get("brand1").size());
	}

	@Test
	public void testGetClothDetailsPrice() throws InvalidInputException {
		when(repository.getClothDetails(123)).thenReturn(buildCLothes());
		ResponseEntity<Map<Object, List<ClothDetails>>> result = service.getClothDetails(123, "price");
		assertEquals(102, (result.getBody().get(new BigDecimal(100))).get(0).getClothId());
	}

	@Test
	public void testGetClothDetailsColor() throws InvalidInputException {
		when(repository.getClothDetails(123)).thenReturn(buildCLothes());
		ResponseEntity<Map<Object, List<ClothDetails>>> result = service.getClothDetails(123, "color");
		assertEquals(101, (result.getBody().get("red")).get(0).getClothId());
	}

	@Test
	public void testGetClothDetailsSize() throws InvalidInputException {
		when(repository.getClothDetails(123)).thenReturn(buildCLothes());
		ResponseEntity<Map<Object, List<ClothDetails>>> result = service.getClothDetails(123, "size");
		assertEquals(101, (result.getBody().get("M")).get(0).getClothId());
	}

	@Test
	public void testGetClothDetails() throws InvalidInputException {
		when(repository.getClothDetails(123)).thenReturn(buildCLothes());
		ResponseEntity<List<ClothDetails>> result = service.getClothDetails(123);
		assertEquals(101, (result.getBody().get(0).getClothId()));
	}

	@Test
	public void testDeleteCloth() throws InvalidInputException {
		when(repository.deleteCloth(123)).thenReturn(1);
		ResponseEntity<Integer> result = service.deleteCloth(123);
		assertEquals(1, result.getBody().intValue());
	}

	private List<ClothDetails> buildCLothes() {
		List<ClothDetails> clothes = new ArrayList<>();
		clothes.add(buildClothDetailsBrand1());
		clothes.add(buildClothDetailsBrand2());
		return clothes;
	}

	private ClothDetails buildClothDetailsBrand1() {
		ClothDetails clothDetails = new ClothDetails();
		clothDetails.setBrandName("brand1");
		clothDetails.setClothId(101);
		clothDetails.setColor("red");
		clothDetails.setPrice(new BigDecimal(123.10));
		clothDetails.setSize("M");
		clothDetails.setType("Shirt");
		return clothDetails;
	}

	private ClothDetails buildClothDetailsBrand2() {
		ClothDetails clothDetails = new ClothDetails();
		clothDetails.setBrandName("brand2");
		clothDetails.setClothId(102);
		clothDetails.setColor("Blue");
		clothDetails.setPrice(new BigDecimal(100.00));
		clothDetails.setSize("L");
		clothDetails.setType("Pant");
		return clothDetails;
	}

}
