import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
//ANSWER TO PART2
// Q1: 
//This means the same amount of stock worth less after 2000.
//people who bought it lose their money.
//Q2:
// answer later

// ANSWER TO PART3


public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sp500_1990_2025.txt"));
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
         
         // output of result
         double result = simulate(years, months, prices);
         System.out.printf("Final value: $%,.2f%n", result);
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
	
	
	        public static final double START_CASH = 10000.0;
            public static final double FEE = 10.0;

            public static double simulate(int[] years, int[] months, double[] prices) {
             double cash = START_CASH;
              int shares = 0;
            double boughtAt = 0.0;

             for (int i = 0; i < prices.length; i++) {
                double[] history = Arrays.copyOf(prices, i + 1);
                 String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
                 int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
        }
    }
    return cash + shares * prices[prices.length - 1];
}
           public static String decide(double[] history, boolean holding, double boughtAt) {
			   
            return buyAndHold(history, holding, boughtAt);   
           // change this line to switch strategies
}
      // step a
      public static void 
      //step b
      // step c
      // my strat     
}
