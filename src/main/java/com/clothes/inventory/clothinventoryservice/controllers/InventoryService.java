package com.clothes.inventory.clothinventoryservice.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.inventory.clothinventoryservice.exceptions.InvalidInputException;
import com.clothes.inventory.clothinventoryservice.model.ClothDetails;
import com.clothes.inventory.clothinventoryservice.repository.InventoryRepository;

@RestController
public class InventoryService {

	@Autowired
	private InventoryRepository repository;

	@GetMapping("/cloths/{clothId}")
	public ResponseEntity<List<ClothDetails>> getClothDetails(@PathVariable int clothId) {

		List<ClothDetails> details = repository.getClothDetails(clothId);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}

	@DeleteMapping("/cloths/{clothId}")
	public ResponseEntity<Integer> deleteCloth(@PathVariable int clothId) {
		return new ResponseEntity<>(repository.deleteCloth(clothId), HttpStatus.OK);
	}

	@GetMapping("/cloths")
	public ResponseEntity<Map<Object, List<ClothDetails>>> getClothDetails(@RequestParam int clothId,
			@RequestParam String groupBy) throws InvalidInputException {

		List<ClothDetails> details = repository.getClothDetails(clothId);
		Map<Object, List<ClothDetails>> details1;
		switch (groupBy) {
		case "brand":
			details1 = details.stream().collect(Collectors.groupingBy(ClothDetails::getBrandName));
			break;
		case "price":
			details1 = details.stream().collect(Collectors.groupingBy(ClothDetails::getPrice));
			break;
		case "color":
			details1 = details.stream().collect(Collectors.groupingBy(ClothDetails::getColor));
			break;
		case "size":
			details1 = details.stream().collect(Collectors.groupingBy(ClothDetails::getSize));
			break;
		default:
			throw new InvalidInputException("Invalid input for the grouping Criteria");
		}

		return new ResponseEntity<>(details1, HttpStatus.OK);

	}

	// Other Potential Service endpoints that can be developed
	// @PutMapping("/cloths/{clothId}")
	// @PostMapping("/cloths")

	@RequestMapping(value = "/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/cloth-inventory-service/swagger-ui.html");
	}

}
