package com.mysterium.a1pra.helpinghand.expenses;

/*
 * Author: Prabhutva Agrawal
 */
public class ExpensesModel {
	private String itemName, dateAdded, remarks, price;

	public ExpensesModel(String itemName, String price, String dateAdded, String remarks) {
		this.dateAdded = dateAdded;
		this.price = price;
		this.itemName = itemName;
		this.remarks = remarks;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

