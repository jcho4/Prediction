package Prediction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//Assumption 1 related linked list structure
class NodeA1{
	double price;
	NodeA1 next;
	public NodeA1(){
		this.price = -1;
		this.next = null;
	}
	public NodeA1(double price){
		this.price = price;
		this.next = null;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setNext(NodeA1 next){
		this.next = next;
	}
	public double getPrice(){
		return this.price;
	}
	public NodeA1 getNext(){
		return this.next;
	}
}
class XbarYbar{
	double Xbar;
	double Ybar;
	
	public XbarYbar(double Xbar, double Ybar){
		this.Xbar = Xbar;
		this.Ybar = Ybar;
	}
	public double getXbar(){
		return this.Xbar;
	}
	public double getYbar(){
		return this.Ybar;
	}
}


public class environment {
	//Assumption 1: create data of linked list, two integers from csv file.
	//environment function: at this point we don't take input but eventually we will take csv file 
	public environment(File file){
		/*
		FileInputStream FIS = null; 
		BufferedInputStream BIS = null; 
		DataInputStream DIS = null;
		*/
		NodeA1 head = new NodeA1(); //need to adjust location
		FileInputStream FIS;
		BufferedReader BR;
		try{
			FIS = new FileInputStream(file);
			BR = new BufferedReader(new InputStreamReader(FIS));
			NodeA1 current = head;
			String aLine;
			while((aLine = BR.readLine()) != null){
				//if it is very first value 
				if(current.getPrice() == -1){
					current.setPrice(Double.valueOf(aLine));
				}else{
					NodeA1 temp = new NodeA1(Double.valueOf(aLine));
					current.setNext(temp);
					current = current.getNext();
				}
				
			}
			FIS.close();
			BR.close();
			XbarYbar barValues = getAverage(head);
			
			//funSimulation startSimulation = new funSimulation(head); //need to adjust location
			
			
		}catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
	}//end of environment function
	//get avg X or Y
	public XbarYbar getAverage(NodeA1 start){
		NodeA1 current = start;
		double N = 0;
		//N value has to be one less than what we have in the linked list
		while (current.next != null){
			N++;
			current = current.getNext();
		}
		current = start;
		double Xbar = 0;
		double Ybar = 0;
		//find sum of X
		while (current.next != null){
			Xbar += current.getPrice();
			current = current.getNext();
		}
		//find sum of X
		current = start.getNext();
		while(current!= null){
			Ybar += current.getPrice();
			current = current.getNext();
		}
		Xbar = Xbar / N;
		Ybar = Ybar / N;
		//estimate N for X and Y
		XbarYbar avgXY = new XbarYbar(Xbar, Ybar);
		
		return avgXY;
		
		
	}
	
	//testing linked list
	public void printAll(NodeA1 start){
		while(start != null){
			System.out.println(start.getPrice());
			start = start.getNext();
		}
		
	}
	
}//end of environment class
