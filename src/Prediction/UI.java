package Prediction;

public class UI {
	public static void main(String[] arg){
		
		String fileLocation = "table2.csv";
		dataProcessing assumption1 = new dataProcessing(fileLocation); //process data 
		
		environment env = new environment(assumption1.getFile()); //set environment for simulation, and run(including reporting result)
		
		//ch moon
		
		
	}
}
