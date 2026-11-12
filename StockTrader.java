/*
 * StockTrader Case Study
 *
 * PART 2 — LOWEST AND HIGHEST PRICES
 *
 *
 * File                  Lowest Price  Lowest Date  Highest Price  Highest Date
 * ---------------------------------------------------------------------------
 * aapl_2000_2010.txt           $7.07     2003-03        $223.02      2010-03
 * amzn_2000_2010.txt           $5.97     2001-09        $135.91      2009-11
 * falling.txt                $30.36     2014-12        $100.00      2010-01
 * goog_2004_2010.txt         $102.37     2004-08        $707.00      2007-10
 * ibm_2000_2010.txt           $53.01     2002-09        $130.32      2009-12
 * msft_2000_2010.txt          $15.81     2009-02         $43.22      2000-03
 * rising.txt                 $20.00     2010-01         $48.14      2014-12
 * sbux_2007_2016.txt           $4.09     2008-11         $62.16      2015-10
 * sp500_1925_1945.txt          $4.77     1932-06         $31.30      1929-09
 * sp500_1990_2025.txt        $307.12     1990-10      $6,853.03      2025-12
 * tiny.txt                   $30.00     2020-03         $70.00      2020-07
 * zigzag.txt                 $35.00     2010-07         $65.00      2010-03
 *
 * Q1:
 * A lower stock price means that the same number of shares is worth less.
 * Someone who bought at a higher price would have an unrealized loss
 * while holding the shares, and would realize the loss if they sold
 * at the lower price.
 *
 * Q2:
 * Starbucks reached its lowest price in this data set in November 2008.
 * This may be related to the 2008 global financial crisis, when consumers
 * reduced spending and many businesses were affected.
 *
 * The S&P 500 reached its lowest price in the 1925-1945 data set in
 * June 1932, during the Great Depression, which followed the 1929
 * stock market crash.
 *
 * Therefore, the timing of these low prices makes sense.
 *
 *
 * PART 3 — STRATEGY RESULTS
 *
 *
 * File                   Buy and Hold     Dip Buyer    Moving Average
 * --------------------------------------------------------------------
 * aapl_2000_2010.txt        $85,865.80     $36,610.49       $114,231.31
 * amzn_2000_2010.txt        $19,886.04     $14,192.52        $38,170.87
 * falling.txt               $3,095.64      $3,159.28        $10,000.00
 * goog_2004_2010.txt        $54,398.54    $136,555.41        $18,706.20
 * ibm_2000_2010.txt         $12,467.97     $27,998.86         $7,549.75
 * msft_2000_2010.txt         $7,237.50     $49,273.15         $7,208.23
 * rising.txt               $24,031.86     $10,000.00        $24,011.93
 * sbux_2007_2016.txt        $36,280.60     $15,759.50        $67,500.23
 * sp500_1925_1945.txt       $16,362.00     $11,483.45        $37,195.81
 * sp500_1990_2025.txt      $198,868.74     $40,836.68       $199,113.67
 * tiny.txt                 $12,975.00     $14,975.00        $10,000.00
 * zigzag.txt               $12,101.39     $38,071.99         $9,848.78
 *
 *
 * MY STRATEGY
 *
 * Rules:
 * When I do not own shares, my strategy waits for three consecutive
 * monthly price decreases followed by a monthly price increase.
 * It then buys as many whole shares as the available cash allows,
 * after accounting for the transaction fee.
 *
 * When I own shares, my strategy sells if the price rises by at least
 * 5% or falls by at least 8% from the buying price.
 * Otherwise, it holds the shares.
 *This strategy tries to buy after a price decline when a recovery
 * may be starting. The 5% target aims to capture a gain, while the
 * 8% selling threshold aims to limit losses. Since the simulation
 * checks monthly prices, the actual loss can exceed 8% if the price
 * falls past that threshold between observations.
 *
 * Recorded results:
 *
 * File                      Final Value              Net Change
 * ---------------------------------------------------------
 * tiny.txt                   $10,000.00                $0.00
 * rising.txt                 $10,000.00                $0.00
 * falling.txt                $10,000.00                $0.00
 * zigzag.txt                 $52,660.74           +$42,660.74
 * aapl_2000_2010.txt          $10,544.74              +$544.74
 * amzn_2000_2010.txt           $9,395.68              -$604.32
 * msft_2000_2010.txt          $13,442.35            +$3,442.35
 * ibm_2000_2010.txt           $10,234.43              +$234.43
 * goog_2004_2010.txt          $10,041.73               +$41.73
 * sbux_2007_2016.txt           $9,603.52              -$396.48
 * sp500_1925_1945.txt          $7,514.13            -$2,485.87
 * sp500_1990_2025.txt         $14,434.05            +$4,434.05
 * ---------------------------------------------------------
 * Total                     $167,871.37           +$47,871.37
 *
 * These are 12 independent simulations, each starting with $10,000.
 * The total is the sum of their results.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

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
