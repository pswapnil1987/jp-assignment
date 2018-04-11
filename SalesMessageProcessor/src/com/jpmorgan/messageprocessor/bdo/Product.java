package com.jpmorgan.messageprocessor.bdo;

import java.math.BigDecimal;
/************************************************************************************
 * 
 * Class Name	: Product.java
 * Author 		: Swapnil Prabhavalkar
 * Description	: This class holds the product data
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class Product {

	// unit price of product
	private BigDecimal unitPrice;

	// product sale quantity
	private int salesQuantity;

	// adjustment operation
	private String adjOperationType;

	// product type
	private String productType;

	// total quantity for product;
	private int totalQuantity;

	// total price
	private BigDecimal totalPrice;

	// Constructor
	public Product(String type) {
		this.totalPrice = new BigDecimal("0.0");
		this.totalQuantity = 0;
		this.productType = type;
		this.adjOperationType = null;
	}

	/**
	 * 
	 * @param productQuantity
	 * @param unitPrice
	 * @return
	 */
	public BigDecimal calculatePrice(Integer productQuantity, BigDecimal unitPrice) {
		return unitPrice.multiply(new BigDecimal(productQuantity.toString()));
	}

	/**
	 * .
	 * @param totalPrice
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * 
	 * @param productPrice
	 */
	public void appendTotalPrice(BigDecimal productPrice) {
		this.totalPrice = totalPrice.add(productPrice);
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setTotalQuantity(int quantity) {
		this.totalQuantity += quantity;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalQuantity() {
		return this.totalQuantity;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * 
	 * @return
	 */
	public String getProductType() {
		return this.productType;
	}

	/**
	 * 
	 * @param type
	 */
	public void setProductType(String type) {
		this.productType = type;
	}

	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the salesQuantity
	 */
	public int getSalesQuantity() {
		return salesQuantity;
	}

	/**
	 * @param salesQuantity the salesQuantity to set
	 */
	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * @return the adjOperationType
	 */
	public String getAdjOperationType() {
		return adjOperationType;
	}

	/**
	 * @param adjOperationType the adjOperationType to set
	 */
	public void setAdjOperationType(String adjOperationType) {
		this.adjOperationType = adjOperationType;
	}

}
