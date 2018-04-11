package com.jpmorgan.messageprocessor.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.jpmorgan.messageprocessor.handler.SaleHandler;




/************************************************************************************
 * 
 * Class Name	: SalesMessageProcessor.java
 * Author 		: Swapnil Prabhavalkar
 * Description	: This class contains main method
 *
 * ***********************************************************************************
 *	Revision log
 * ***********************************************************************************
 * 		Date					Author					Comments
 * ***********************************************************************************
 * 	10-04-2018					Swapnil P				Initial version
 * ***********************************************************************************
 */
public class SalesMessageProcessor {

	/**
	 * Main method : reads the input message file and delegates the call
	 * @param args
	 */
	public static void main(String[] args) {
		String message;
		SaleHandler sale = new SaleHandler();
        try {
			BufferedReader input = new BufferedReader(new FileReader("src/com/jpmorgan/messageprocessor/test/input.txt"));
			while((message = input.readLine()) != null) {
				// process message from input file
				sale.processMessage(message);
                sale.inventory.report();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
