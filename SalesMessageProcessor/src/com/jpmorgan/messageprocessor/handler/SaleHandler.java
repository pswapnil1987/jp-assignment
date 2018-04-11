package com.jpmorgan.messageprocessor.handler;

import java.math.BigDecimal;

import com.jpmorgan.messageprocessor.bdo.Message;
import com.jpmorgan.messageprocessor.bdo.PriceAdjustment;
import com.jpmorgan.messageprocessor.bdo.Product;
import com.jpmorgan.messageprocessor.storage.SalesInventory;
/************************************************************************************
 * 
 * Class Name	: Product.java
 * Author 		: Swapnil Prabhavalkar
 * Description	: This class holds the sale data
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class SaleHandler {

	// storage inventory
	public SalesInventory inventory;

	// Adjustment of product price
	private PriceAdjustment adjPrice;

	// product details
	private Product product;


	public SaleHandler() {
		inventory = new SalesInventory();
	}

	/**
	 * 
	 * @param messageStr
	 */
	public void processMessage(String messageStr) {

		Message message;

		// Process the given message
		message = new Message(messageStr);

		// Get the product type
		String productType = message.getProductType();

		// Check if product type is empty
		if (productType.isEmpty()) {
			System.out.println("Empty product");
			return;
		}

		//Returns an existing product else returns a new Product object
		this.product = inventory.getProduct(productType);

		// Prepare the product details for adjustment
		this.adjPrice = new PriceAdjustment(product);

		// Set the product details from the parsed message
		this.product.setSalesQuantity(message.getSalesQuantity());
		this.product.setTotalQuantity(message.getSalesQuantity());
		this.product.setUnitPrice(message.getUnitPrice());
		this.product.setAdjOperationType(message.getAdjOperationType());

		// Set the total value of the product.
		setProductTotalPrice();

		// Set the sale log reports
		inventory.setSalesReports(messageStr);

		// Update the product with the new details
		inventory.updateProduct(product);        
	}

	/**
	 * 
	 */
	private void setProductTotalPrice() {
		BigDecimal adjustedPrice;
		BigDecimal productValue;

		if (!product.getAdjOperationType().isEmpty()) {
			adjustedPrice = adjPrice.getAdjustedPrice();
			inventory.setAdjReports(adjPrice.adjustmentReport());
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getSalesQuantity(), product.getUnitPrice());
			product.appendTotalPrice(productValue);
		}
	}

}
