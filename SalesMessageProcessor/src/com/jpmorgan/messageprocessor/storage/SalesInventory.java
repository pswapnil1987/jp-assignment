package com.jpmorgan.messageprocessor.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.messageprocessor.bdo.Product;

/************************************************************************************
 * 
 * Class Name 	: SalesInventory.java 
 * Author 		: Swapnil Prabhavalkar 
 * Description 	: This class stores the data in memory
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class SalesInventory {

	// map stores product details
	private Map<String, Product> productDetails = new HashMap<>();

	// report of all sales messages
	private List<String> salesReports;

	// report of all the adjustment
	private List<String> adjReports;

	private BigDecimal totalSalesValue;

	public SalesInventory() {
		this.salesReports = new ArrayList<String>();
		this.adjReports = new ArrayList<String>();
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public Product getProduct(String type) {
		return productDetails.getOrDefault(type, new Product(type));
	}

	/**
	 * 
	 * @param product
	 */
	public void updateProduct(Product product) {
		productDetails.put(product.getProductType(), product);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getSalesReports() {
		return salesReports;
	}

	/**
	 * 
	 * @param salesReport
	 */
	public void setSalesReports(String salesReport) {
		this.salesReports.add(salesReport);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAdjReports() {
		return adjReports;
	}

	/**
	 * 
	 * @param adjReport
	 */
	public void setAdjReports(String adjReport) {
		this.adjReports.add(adjReport);
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getTotalSalesValue() {
		return totalSalesValue;
	}

	/**
	 * 
	 * @param productTotalPrice
	 */
	public void appendTotalSalesValue(BigDecimal productTotalPrice) {
		totalSalesValue = totalSalesValue.add(productTotalPrice);
	}

	/**
	 * 
	 * @param productTotalPrice
	 */
	public void setTotalSalesValue(BigDecimal productTotalPrice) {
		totalSalesValue = productTotalPrice;
	}

	/**
	 * 
	 */
	public void report() {

		if (salesReports.size() != 0 && salesReports.size() % 10 == 0) {
			setTotalSalesValue(new BigDecimal("0.0"));
			System.out.println("*************** Log Report *****************");
			System.out.println("|Product           |Quantity   |Price      |");
			//iterate over map
			for (Map.Entry<String, Product> entry : productDetails.entrySet())
			{
				formatReports(entry.getKey(),entry.getValue());
			}
			System.out.println("-------------------------------------------");
			System.out.println(String.format(" %-25s %10.2f", "Total price", getTotalSalesValue()));
			System.out.println("-------------------------------------------");
			System.out.println("\n");
			try {
				// Add pause
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Report and stop execution after 50th message
		if ((salesReports.size() % 50) == 0 && salesReports.size() != 0) {
			System.out.println("Stopping after processeing 50 messages. PFB adjustment report\n");
			System.out.println("*************** Adjustment Report *****************");
			System.out.println(String.format("|%-10s |%15s|%s |%-10s |%15s|%15s|",
					"Adj", "Adj Value", "No",
					"Type", "From", "To"));
			List<String> adjReportsList = getAdjReports();
			//iterate over list
			for (String element : adjReportsList) {
				System.out.println(""+element);
			}
			System.exit(1);
		}
	}

	/**
	 * 
	 * @param type
	 * @param product
	 */
	public void formatReports(String type, Product product) {
		String productDetails = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(),
				product.getTotalQuantity(), product.getTotalPrice());
		appendTotalSalesValue(product.getTotalPrice());
		System.out.println(productDetails);
	}

}
