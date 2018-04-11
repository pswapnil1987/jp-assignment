package com.jpmorgan.messageprocessor.bdo;

import java.math.BigDecimal;
/************************************************************************************
 * 
 * Class Name	: PriceAdjustment.java
 * Author 		: Swapnil Prabhavalkar
 * Description	: This class adjusts the price as per message type 3
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class PriceAdjustment {

	// adjPrice holds the adjusted price value
	private BigDecimal adjPrice;

	// product holds the Product object.
	private Product product;

	// Constructor takes Product as argument.
	public PriceAdjustment(Product product) {
		this.product = product;
		this.adjPrice = new BigDecimal("0.0");
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getAdjustedPrice() {

		String adjOperationType = product.getAdjOperationType().toLowerCase();

		if (adjOperationType.equals("add")) {

			this.adjPrice = this.product.getTotalPrice()
					.add((new BigDecimal(this.product.getTotalQuantity()).multiply(this.product.getUnitPrice())));

		} else if (adjOperationType.equals("subtract")) {

			this.adjPrice = this.product.getTotalPrice()
					.subtract(new BigDecimal(this.product.getTotalQuantity()).multiply(this.product.getUnitPrice()));

		} 
		return adjPrice;
	}

	/**
	 * 
	 * @return
	 */
	public String adjustmentReport() {
		String adjustmentReport = String.format("|%-10s |%11.2f GBP|%d |%-10s |%11.2f GBP|%11.2f GBP|",
				this.product.getAdjOperationType(), this.product.getUnitPrice(), this.product.getTotalQuantity(),
				this.product.getProductType(), this.product.getTotalPrice(), this.adjPrice);
		return adjustmentReport;
	}

}
