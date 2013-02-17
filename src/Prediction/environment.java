package Prediction;
// little change to see
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//Assumption 1 related linked list structure
class NodeA1{
	private double price;
	private NodeA1 next;
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
	private double Xbar;
	private double Ybar;
	
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
	private NodeA1 Xvalues;
	private NodeA1 Yvalues;
	private XbarYbar XbarYbar;
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
			setXbarYbar(barValues);
			setXY(head);
			
			
			
		}catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
	}//end of environment function
	
	//seperate X values and Y values from linkedlist
	private void setXY(NodeA1 head){
		NodeA1 current = head;
		NodeA1 x = new NodeA1();
		NodeA1 y = new NodeA1();
		
		NodeA1 currentX = x;
		NodeA1 currentY = y;
		while(current.getNext() != null){
			if(currentX.getPrice() == -1){
				currentX.setPrice(current.getPrice());
			}
			else{
				currentX.setNext( new NodeA1(current.getPrice()) );
				currentX = currentX.getNext();
			}
			current = current.getNext();
		}
		if(head.getNext() != null){
			current = head.getNext();
		}else{
			//I want to throw exception and cause stop on program 
		}
		
		while(current != null){
			if(currentY.getPrice() == -1){
				currentY.setPrice(current.getPrice());
			}else{
				currentY.setNext( new NodeA1(current.getPrice()) );
				currentY = currentY.getNext();
			}
			current = current.getNext();
		}
		//finalized x and y values will be stored in global variables
		setX(x);
		setY(y);

	}
	private void setX(NodeA1 head){
		this.Xvalues = head;
	}
	private void setY(NodeA1 head){
		this.Yvalues= head;
		
	}
	public NodeA1 getXValues(){
		return this.Xvalues;
	}
	public NodeA1 getYValues(){
		return this.Yvalues;
	}
	
	
	private void setXbarYbar(XbarYbar XY){
		this.XbarYbar = XY;
	}
	public XbarYbar getXbarYbar(){
		return this.XbarYbar;
	}
	
	//get avg X or Y
	public XbarYbar getAverage(NodeA1 start){
		NodeA1 current = start;
		double N = 0;
		//N value has to be one less than what we have in the linked list
		while (current.getNext() != null){
			N++;
			current = current.getNext();
		}
		current = start;
		double Xbar = 0;
		double Ybar = 0;
		//find sum of X
		while (current.getNext() != null){
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
		
		
	}//getAverage
	
	/* ################# testing functions ##########################*/
	public void printAll(NodeA1 start){
		int count = 0;
		while(start != null){
			count ++;
			System.out.println(start.getPrice());
			start = start.getNext();
		}
		System.out.println("total: " + count);
		
	}
	
	
}//end of environment class
