package Prediction;


public class funSimulation {
	private double b2;
	private double b1;
	public funSimulation(NodeA1 XValues, NodeA1 YValues, XbarYbar XbarYbar){
		b1 = 0;
		b2 = 0;
		calculateBeta2(XValues, YValues, XbarYbar);
		calculateBeta1(XbarYbar);
	}
	public double getBeta1(){
		return this.b1;
	}
	public double getBeta2(){
		return this.b2;
	}
	public void calculateBeta1(XbarYbar barValues){
		b1 = barValues.getYbar() - (b2 * barValues.getXbar());
		
	}
	public void calculateBeta2(NodeA1 XValues, NodeA1 YValues, XbarYbar barValues){
		
		double numorator = 0;
		double denominator = 0;

		while(XValues != null && YValues != null){
			double tempX = XValues.getPrice();
			double tempY = YValues.getPrice();
			numorator += ( (tempX - barValues.getXbar()) * (tempY - barValues.getYbar()) ); //above
			denominator += Math.pow((tempX - barValues.getXbar()), 2); // below 
			
			
			YValues = YValues.getNext();
			XValues = XValues.getNext();
			
			/* 
			double tempX = XValues.getPrice();
			double tempY = YValues.getPrice();
			double numorator = ( (tempX - barValues.getXbar()) * (tempY - barValues.getYbar()) ); //above
			double denominator = Math.pow((tempX - barValues.getXbar()), 2); // below 
			
			this.b2 += (numorator / denominator);
			YValues = YValues.getNext();
			XValues = XValues.getNext();
			*/
		}
		this.b2 = (numorator / denominator);
	}
	
	
	
	
	
}
