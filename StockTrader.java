import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
         int [] years = new int[n];
         int [] months = new int [n];
         double []prices = new double[n];
        // TODO: call loadData, then printData
         loadData(in,years,months,prices);   
         printData(years,months,prices); 
         // highest && lowest
         int highIndex = indexOfHighest(prices);
         System.out.println("Highest:" + years[highIndex] + " $" + prices[highIndex]);
         int lowIndex = indexOfLowest(prices);
         System.out.println("Lowest:" + years[lowIndex] + " $" + prices[lowIndex]);
         //printData(indexOfLowest(prices));
   }
       
    public static void loadData(Scanner in, int[] years, int[] months, double[] prices)
    {
		for(int i=0;i<years.length;i++){
			years[i]= in.nextInt();
			months[i] = in.nextInt();
			prices[i] = in.nextDouble();
	}	
}
    public static String monthName(int months){
		String[]monthname ={"Jan", "Feb", "Mar",
			                "Apr", "May", "Jun",
			                "Jul", "Aug", "Sep",
			                "Oct", "Nov", "Dec"};
		return monthname[months-1];
	 
	}
	public static void printData(int[] years, int[] months, double[] prices){
	for(int i=0;i<years.length;i++){
	     System.out.print(monthName(months[i])+" ");
	     System.out.print(years[i]+": $");
	     System.out.printf("%.2f%n", prices[i]);
	     //or use System.out.println();
     }
	}
	public static int indexOfLowest(double[] prices){
	int lowest=0;
	 for(int i=0; i<prices.length; i++){
	    if(prices[i]<prices[lowest]){
		   lowest =i;
	
		}
	 }
	 return lowest;
	}
	public static int indexOfHighest(double[] prices){
	int highest=0;
	  for(int i=0; i<prices.length;i++){
	     if(prices[i]>prices[highest]){
		     highest =i;
		 }
	 }
	 return highest;
	}
	
	// indexOfBiggestDrop(double[] prices),
	
}
