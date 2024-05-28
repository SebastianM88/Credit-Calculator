import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static final int MONTHS_IN_YEAR = 12;
    static final int PERCENT = 100;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CsvWriter csvWriter;

        try {
            // The code for required initializations
            FileWriter writer = new FileWriter(FileProvider.getFile());
            csvWriter = new CsvWriter(writer);

            // Declaration of the table header
            csvWriter.writeHeader();
        } catch (IOException e) {
            System.out.println("Some error occurred when initializing the CsvWriter: " + e.getMessage());
            return;
        }

        System.out.println("Please enter the amount: ");

        int amount;
        int period;
        double interestRate;

        // The code to read the amount borrowed
        try{
            amount = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("The amount is mandatory to be numeric!");
            return;
        }

        System.out.println("Please enter the loan period in years: ");

        // The code to read the loan period
        try{
            period = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("The period is mandatory to be numeric!");
            return;
        }

        System.out.println("Please enter the annual interest rate");

        // The code to read the annual interest rate
        try{
            interestRate = Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("The period is mandatory to be numeric!");
            return;
        }

        // The code required to calculate the values for each month in part
        double balance = amount;
        for(int month=1; month<=period * MONTHS_IN_YEAR; month++){
            double lastMonthBalance = balance;
            double monthlyMortgage = calculateMortgage(amount, period, interestRate);
            double monthlyInterest = calculateInterest(lastMonthBalance, interestRate);
            double paidAmount = monthlyMortgage - monthlyInterest;

            balance = (lastMonthBalance - paidAmount) < 0 ? 0 : (lastMonthBalance - paidAmount);

            // The code that will make the calculated volories to be transmitted in the report
            try {
                csvWriter.writeRecord(month, monthlyMortgage, balance, monthlyInterest, paidAmount);
            } catch (IOException e) {
                System.out.println("Error while writing the csv file: " + e.getMessage());
            }
        }

        // Code meant to complete all operations
        try{
            csvWriter.closeFile();
        }catch (IOException e){
            System.out.println("Something went wrong when trying to close the csv file: " + e.getMessage());
        }
    }

    // The code to calculate the monthly rate
    private static double calculateMortgage(int amount, int period, double interestRate){
        double monthlyRate = interestRate / PERCENT / MONTHS_IN_YEAR;
        return (monthlyRate * amount) / (1 - Math.pow(1 + monthlyRate, (-period * MONTHS_IN_YEAR)));
    }

    // The code for calculating interest for one month
    private static double calculateInterest(double balance, double interestRate){
        double interestPerYear = balance * interestRate / PERCENT;
        return interestPerYear / MONTHS_IN_YEAR;
    }
}