package stairnsteps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StairNSteps {

    public static String separator = "-------------------------------------------------------------------";


    public static void main(String args[]) throws IOException {

        int stairSize = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(separator);
        System.out.print("Please provide a positive integer for \"stair size\": ");
        try
        {
            stairSize = Integer.parseInt(br.readLine());
            if(stairSize <= 0) {
                System.out.println("*** Please provide a positive integer ***");
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("*** Invalid input, please provide a positive integer ***");
        }

        int result = calculateStepsNumber(stairSize);

        System.out.println(String.format("Ways to get to the top %d",result));
    }

    private static int calculateStepsNumber(int stairSize) {

        if(stairSize == 0){
            return 0;
        }
        else if(stairSize == 1) {
            return 1;
        }
        else if (stairSize == 2){
            return 2;
        }
        else if(stairSize == 3){
            return 4;
        }
        else {
            return calculateStepsNumber(stairSize-1) + calculateStepsNumber(stairSize - 2) + calculateStepsNumber(stairSize - 3);
        }
    }
}
