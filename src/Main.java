import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Program Name: Youngmin_Chung_INFO1214_Project
 * Purpose: Holiday Gift Planner 		
 * Coder: Youngmin Chung for Sec01
 * Date: Dec 28, 2017
 *
 *** PSEUDOCODE ***
 * 1) EXPLAIN PROGRAM
 * 2) STEP 1. 
 * 		CREATE SCANNER / VARIABLES
 * 			input the number of gifts and maximum budget for gifts
 * 3) STEP 2. 
 * 		LOOP
 * 			input details such as Description, Price and name of the person who will receive the gift.
 * 		END LOOP
 * 4) STEP 3.
 * 		CREATE AND USE FOUR METHODS
 * 			printGiftsReport(): will accept the following four arguments: the three arrays
 * 			(descriptions, prices, names of who receives the gifts) and the average amount budgeted per gift.
 *
 * 			getTotal(): will accept the array of gift prices and will return the sum of all the gift prices in the array. 
 * 			calculateSalesTax(): will accept the total price and will return the amount of the sales tax for that total price.
 * 			Note that the sales tax in Ontario (the HST) is 13% of the sales price.
 *
 * 			changeGiftInfo(): will accept the following five arguments: the three arrays
 * 			(descriptions, prices, names of who receives the gifts), the array index that
 * 			corresponds to the gift to be changed and a Scanner object that can be used by the method to read inputs from the keyboard.
 *
 * 			IF TOTAL PRICE WITH TAX IS OVER BUDGET OF GIFTS, REVISE THE DETAILS OF GIFT WHICH IS OVER AVERAGE PRICE OF GIFTS
 *
 */

public class Main
{
    public static void main(String[] args)
    {
        // TITLE
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.println("|"+"			  	Welcome to the Holiday Gift Planner		 	  "+"  |");
        System.out.println("+-------------------------------------------------------------------------------------------+\n");

        // PURPOSE OF THE PROGRAM
        System.out.println("This program will help you plan and control your expenses for any holiday gift purchases."
                + "\nJust follow the easy step-by-step instructions on your screen!");

        // CREATE SCANNER, DECIMAL FORMAT
        Scanner input = new Scanner(System.in);
        DecimalFormat form = new DecimalFormat("#.00");

        // STEP 1.
        System.out.println("\nSTEP 1: First you'll need to provide some basic information.");
        System.out.print("  How many gifts are you planning to purchase? ");
        int giftNum = input.nextInt(); // INPUT THE NUMBER OF GIFTS

        System.out.print("  What is your total maximum budget for gifts? $");
        double budgetPrice = input.nextDouble(); // MAXIMUM BUDGET AND STORE THE INPUT

        double averageBudget = budgetPrice/giftNum; // AVERAGE PRICE OF GIFTS
        System.out.println("\nBased on this information the average gift price should be no greater than $" + form.format(averageBudget));

        //STEP 2.
        System.out.println("\nSTEP 2: Now let's record a description and price for each gift as well as who it's for.");

        // DECLARE VARIABLES
        String [] gift = new String [giftNum+1];
        double [] price = new double [giftNum+1];
        String [] name = new String [giftNum+1];
        int inputNum = 0;
        int i =0;

        // FOR LOOP IN EACH ARRAYS
        for (i = 1; i < giftNum + 1; i++)  {

            System.out.println("\n  Gift #" + i);

            System.out.print("\n  Description: ");
            input.nextLine();
            gift [i] = input.nextLine(); // INPUT DESCRIPTION OF GIFTS

            System.out.print("  Price: CA$");
            price [i] = input.nextDouble(); // INPUT PRICE OF GIFTS

            System.out.print("  For whom?: ");
            name [i] = input.next(); // INPUT NAME OF GIFTS

        }// END FOR

        // PRINT OUT THE GIFT INFORMATION USING printGiftsReport METHOD
        printGiftsReport(name, gift, price, averageBudget);

        // CALCULATE TOTAL PRICE OF GIFTS, TAX OF TOTAL PRICE AND SUM OF TOTAL PRICE AND TAX. PRINT OUT THE VALUES
        // USING getTotal and calculateSalesTax METHODS
        System.out.println("\n  Your total of $" + form.format(getTotal(price)) + " plus $" + form.format(calculateSalesTax((float)getTotal(price))) + " in tax comes to $" +
                form.format((getTotal(price)) + calculateSalesTax((float)getTotal(price))) + " out of a budget of $" + form.format(budgetPrice) + ".");

        // IF THE BUDGET PRICE IS UNDER SUM OF TOTAL PRICE AND TAX, PRINT OUT WARNING MESSAGE
        if (budgetPrice < (getTotal(price) + calculateSalesTax((float)getTotal(price)))) {
            System.out.println("  WARNING: You're over your budget of $" + form.format(budgetPrice) + " by $" +
                    Math.round((getTotal(price) + calculateSalesTax((float)getTotal(price)) - budgetPrice)*100)/100.0);
        }

        // REVISING AND CHANGING VALUES USING changeGiftInfo METHOD
        changeGiftInfo(inputNum, giftNum, gift, price, name);

        // AFTER REVISION, PRINT OUT THE REVISED GIFT INFORMATION
        printGiftsReport(name, gift, price, averageBudget);

        // CALCULATE AND PRINT OUT TOTAL PRICE AND TAX OF GIFTS.
        System.out.println("\n  Your total of $" + form.format(getTotal(price)) + " plus $" + form.format(calculateSalesTax((float)getTotal(price))) + " in tax comes to $" +
                form.format((getTotal(price)) + calculateSalesTax((float)getTotal(price))) + " out of a budget of $" + form.format(budgetPrice) + ".");

        // COMPARE BUDGET PRICE AND SUM OF TOTAL PRICE AND TAX ONCE AGAIN
        if (budgetPrice < (getTotal(price) + calculateSalesTax((float)getTotal(price)))) {
            System.out.println("  WARNING: You're over your budget of $" + form.format(budgetPrice) + " by $" + form.format((getTotal(price) + calculateSalesTax((float)getTotal(price)) - budgetPrice)));
        }
        changeGiftInfo(inputNum, giftNum, gift, price, name);

        // CLOSE SCANNER
        input.close();

    }// END MAIN

//STRING METHOD

    /**
     * Method Name:subTotal()
     * Purpose: a public class method that adds input integer values and returns the sum.
     * Accepts: input values of type double
     * Returns: return a double that is the sum of the one arguments.
     */
    public static double getTotal(double [] array) {
        double sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }//end method

    /**
     * Method Name: calculateSalesTax()
     * Purpose: a public class method that calcluate and returns the double value.
     * Accepts: input value of type double
     * Returns: return a double that is the result of calculation of the one arguments.
     */
    public static double calculateSalesTax(double index) {
        final double HST = 0.13;
        index = index * HST;

        return index;
    }//end method


    /**
     * Method Name:printGiftsReport()
     * Purpose: a public class method that prints the contents of an array to the screen
     * Accepts: input values of type String and double
     * Returns: NOTHING! Void method.
     */
    public static void printGiftsReport(String [] array1, String[] array2, double [] array3, double index1)
    {
        System.out.println("\nSTEP 3: Please review the gift information and make any desired changes.");
        System.out.println("\n  #.             For                      Gift       Price  Over/Under Average");
        System.out.println(" ---             ---                      ----       -----  ------------------");

        for (int i = 1; i < array1.length; i++) {
            int numbering = i;
            String averageBudget = null;
            // Judge the average price is over or under using 'if' statament.
            if ( array3 [i] > index1) // IF EACH PRICE OF GIFT IS OVER AVERAGE PRICE, PRINT OUT OVER
            {
                averageBudget = "over";
            }
            else {
                averageBudget = "under"; // IF EACH PRICE OF GIFT IS UNDER AVERAGE PRICE, PRINT OUT UNDER
            }

            System.out.printf("%3d. %15s %25s      $%4.2f %19s\n", numbering, array1 [i], array2 [i], array3 [i], averageBudget);
        }
    }//end method

    /**
     * Method Name: changeGiftInfo()
     * Purpose: a public class method that prints the contents of an array to the screen
     * Accepts: input values of type int, double and String
     * Returns: NOTHING! Void method.
     */
    public static void changeGiftInfo(int index1, int index2, String [] array1, double [] array2, String [] array4)
    {

        boolean loopFlag = true;
        in = new Scanner(System.in);
        double tmp [] = new double [array1.length];
        String tmp2 [] = new String [array1.length];
        index1 = 0;

        while(loopFlag){
            System.out.print("\n  Enter a gift number to make changes or 0 to quit: ");
            index1 = in.nextInt(); // input the number that user would like to change

            if(index1 == 0) // if input 0, while loop out
            {
                System.out.print("\n  Thank you for using this program!!!");
                loopFlag = false;
            }
            // if the value input is larger than 0 and less than the number of gifts,
            // print out the message of changing process such as description, price and name
            else if(0 < index1 && index1 <= index2)
            {
                System.out.println("\n  For each field you can enter a new value or simply press enter to leave it unchanged." +
                        "\n  The current value for each field is shown in [] for you to examine.");

                System.out.print("\n  Description [" + array1[index1]+"]: ");
                in.nextLine();
                array1[index1] = in.nextLine();

                // price should not be negative!!!
                boolean loopFlag2 = true;
                while (loopFlag2)
                {
                    System.out.print("  Price [" + array2[index1] + "]: ");
                    tmp [index1] = in.nextDouble();

                    if(tmp [index1] >= 0) // if the price is positive value, change from old one to new one on a revised gift report.
                    {
                        array2[index1] = tmp [index1];
                        loopFlag2 = false;
                    }
                    else // if the price is negative, print out ERROR message.
                    {
                        System.out.println("  ERROR: The price must not be negative.");
                        tmp [index1] = array2 [index1];
                    }
                }// end while

                // Name might be changed or not
                boolean loopFlag3 = true;
                do{
                    System.out.print("  For whom? [" + array4[index1] + "]: ");
                    in.nextLine();
                    tmp2 [index1] = in.nextLine();

                    if(tmp2 [index1] == "") // NO CHANGE OF NAME, just press enter
                    {
                        tmp2 [index1] = array4[index1];
                        loopFlag2 = false;
                    }
                    else // If there is new name, change from old one to new one
                    {
                        tmp2 [index1] = array4 [index1];
                    }
                }
                while (loopFlag3 != true);
                loopFlag = false;
            }// end else if
            else{
                System.out.println("  ERROR: You must enter a number between 0 and " + index2);
            }

        }//end while


    }//end method
    private static Scanner in; // close Scanner in here.
// END METHOD

}// END CLASS