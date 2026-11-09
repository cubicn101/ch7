
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
//ANSWER TO PART2
// Q1: 
//This means the same amount of stock worth less after 2000.
//people who bought it lose their money.
//Q2:
//For starbucks, the lowest price may be related to the 2008 global financial crisis.
//During the crisis, consumers spent less money, which affected many businesses.
//For S&P500, this may be related to the Great Depression, which began after the 1929 stock market crash.
//Therefore, yes, the timing makes sense.
// ANSWER TO PART3
// explaination:
   
    // My strategy buys a stock after its price has
    // decreased for three consecutive months and
    // then starts to rise.
    // It sells when the price increases by at least
    // 5% or decreases by at least 8% from the buying price.
    // This strategy tries to buy after a price decline
    // and make a profit during the recovery.
    // The 8% stop-loss helps limit potential losses.


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
           int lowIndex = indexOfLowest(prices);

            System.out.printf("Highest: $%.2f in %s %d%n",
            prices[highIndex],
             monthName(months[highIndex]),
             years[highIndex]);

          System.out.printf("Lowest: $%.2f in %s %d%n",
            prices[lowIndex],
            monthName(months[lowIndex]),
            years[lowIndex]);

      
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
			   
              //return buyAndHold(history, holding, boughtAt);
              //return dipBuyer(history, holding, boughtAt);
              //return movingAverage(history, holding, boughtAt);
               return myStrategy(history, holding, boughtAt);
}
      // step a
      
        public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
    return "BUY";
}

     
      //step b
      
       
    public static String dipBuyer(double[] history, boolean holding, double boughtAt) {
    int len = history.length;
    double today = history[len - 1];

    if (holding == false) {
        if (len >= 3) {
            if (history[len - 1] < history[len - 2] &&
                history[len - 2] < history[len - 3]) {
                return "BUY";
            }
        }
    } else {
        if (today >= boughtAt * 1.10) {
            return "SELL";
        }
    }

    return "HOLD";
}


      // step c
      
     public static String movingAverage(double[] history, boolean holding, double boughtAt) {
    int len = history.length;

    if (len < 12) {
        return "HOLD";
    }

    double sum = 0;

    for (int i = len - 12; i < len; i++) {
        sum += history[i];
    }

    double average = sum / 12;
    double today = history[len - 1];

    if (holding == false && today > average) {
        return "BUY";
    } else if (holding == true && today < average) {
        return "SELL";
    } else {
        return "HOLD";
    }
}

     // my strat
    public static String myStrategy(double[] history,
                                boolean holding,
                                double boughtAt){
    int len = history.length;
    double today = history[len - 1];

    if (holding == false) {
        if (len >= 5) {
            if (history[len - 5] > history[len - 4] &&
                history[len - 4] > history[len - 3] &&
                history[len - 3] > history[len - 2] &&
                today > history[len - 2]) {
                return "BUY";
            }
        }
    } else {
        if (today >= boughtAt * 1.05) {
            return "SELL";
        } else if (today <= boughtAt * 0.92) {
            return "SELL";
        }
    }

    return "HOLD";
}
    
}
