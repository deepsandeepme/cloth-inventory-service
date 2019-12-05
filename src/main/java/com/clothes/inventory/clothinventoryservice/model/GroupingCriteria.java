package com.clothes.inventory.clothinventoryservice.model;

public enum GroupingCriteria {

	BRAND("brand"), PRICE("price"), COLOR("COLOR"), SIZE("SIZE");

	private String criteria;

	GroupingCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getCriteria() {
		return criteria;
	}
}
