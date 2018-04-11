package com.jpmorgan.messageprocessor.bdo;

import java.math.BigDecimal;
/************************************************************************************
 * 
 * Class Name	: MessageParser.java
 * Author 		: Swapnil Prabhavalkar
 * Description	: This class parses sales message and sets appropriate values of
 * 				  attributes
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class Message{

	// Parsed product type
	private String productType;

	// Parsed unit price
	private BigDecimal unitPrice;

	// Parsed sales quantity
	private int salesQuantity;

	// Parsed adjOperationType e.g Add, Subtract
	private String adjOperationType;

	private String type;

	public Message(String message) {
		this.productType = "";
		this.unitPrice = new BigDecimal("0.0");
		this.salesQuantity = 0;
		this.adjOperationType = "";
		this.type = "";
		parseInputMessages(message);
	}

	private void parseInputMessages(String message) {
		if (message == null || message.isEmpty()) {
			System.out.println("Empty message");
			return;
		}
		String[] messageArray = message.trim().split("\\s+");
		String firstWord = messageArray[0];
		if (messageArray.length == 3 && messageArray[1].contains("at")) {
			productType = messageArray[0].toLowerCase();
			unitPrice = parsePrice(messageArray[2]);
			salesQuantity = 1; // Will always be 1
			type = "1";
		} else if (firstWord.matches("^\\d+")) {
			productType = messageArray[3].toLowerCase();
			unitPrice = parsePrice(messageArray[5]);
			salesQuantity = Integer.parseInt(messageArray[0]);
			type = "2";
		} else if (firstWord.matches("Add|Subtract|Multiply")) {
			adjOperationType = messageArray[0];
			productType = messageArray[2].toLowerCase();
			unitPrice = parsePrice(messageArray[1]);
			type = "3";
		} else {
			System.out.println("Invalid message");
		}
	}
	
	/**
	 * This method parses price coming from input message and removes p
	 * @param rawPrice
	 * @return
	 */
	public BigDecimal parsePrice(String rawPrice) {
		BigDecimal price = new BigDecimal(rawPrice.replaceAll("p", ""));
		price = price.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
		return price;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
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
	 * @param salesQuantity
	 *            the salesQuantity to set
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
	 * @param adjOperationType
	 *            the adjOperationType to set
	 */
	public void setAdjOperationType(String adjOperationType) {
		this.adjOperationType = adjOperationType;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
