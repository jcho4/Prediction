package Prediction;

import java.io.File;

/*
 * process data retrieved from finance.yahoo.com csv file so that it only
 * include close value of stock.  
 * csv file contains data, open, high, low, close, volume, adj close
 */
public class dataProcessing {
	File file = null;
	public dataProcessing(String fileLocation){

		this.file = new File(fileLocation);
		//environment env = new environment(file);
		
		
	}
	public File getFile(){
		return this.file;
	}
	
}
