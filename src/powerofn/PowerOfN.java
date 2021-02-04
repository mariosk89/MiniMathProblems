package powerofn;

import trominoes.Boundary;
import trominoes.Grid;
import trominoes.GridPrinter;
import trominoes.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerOfN {

    public static String separator = "-------------------------------------------------------------------";


    public static void main(String args[]) throws IOException {

        int power = 0;
        int number = 0;
        int orig = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(separator);
        System.out.print("Please provide a positive integer for \"n\": ");
        try
        {
            power = Integer.parseInt(br.readLine());
            if(power <= 0) {
                System.out.println("*** Please provide a positive integer ***");
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("*** Invalid input, please provide a positive integer ***");
        }

        System.out.print("Please provide a positive integer for the examined number: ");
        try
        {
            number = Integer.parseInt(br.readLine());
            orig = number;
            if(number <= 0) {
                System.out.println("*** Please provide a positive integer ***");
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("*** Invalid input, please provide a positive integer ***");
        }

        while (number > 9 && number % power == 0) {
            number /= power;
        }

        if( number == 1){
            System.out.println(String.format("Number %d is power of %d",orig,power));
        }
    }
}
