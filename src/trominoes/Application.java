package trominoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by marios on 23-Jul-16.
 */
public class Application
{

    public static String separator = "-------------------------------------------------------------------";

    public static void main(String args[]) throws IOException {

        int size = 0;
        int x = 0;
        int y = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean validGridSize = false;
        while(!validGridSize)
        {
            System.out.println(separator);
            System.out.println("The size of the grid will be (2^n)x(2^n) where \"n\" is your input.");
            System.out.print("Please provide a positive integer for \"n\": ");
            try
            {
                size = Integer.parseInt(br.readLine());
                if(size > 0)
                {
                    size = (int)Math.pow(2,size);
                    validGridSize = true;
                }
                else
                {
                    System.out.println("*** Please provide a positive integer ***");
                }
            }
            catch(NumberFormatException nfe){
                System.out.println("*** Invalid input, please provide a positive integer ***");
            }
        }

        Grid masterGrid = new Grid(size);
        System.out.println(separator);
        System.out.println("Grid created (Size: "+size+"x"+size+")");

        boolean validCoordinates = false;
        while(!validCoordinates)
        {
            System.out.println(separator);
            System.out.println("Please select a square by providing its' coordinates i.e \"x,y\": ");
            String coordinatesInput = br.readLine();
            String[] coordinates = coordinatesInput.split(",");

            try
            {
                x = Integer.parseInt(coordinates[0]) - 1;
                y = Integer.parseInt(coordinates[1]) - 1;

                if(x < 0 || x > (size-1)
                   || y < 0 || y > (size-1))
                {
                    System.out.println("*** Please provide valid coordinates, within the "+size+"x"+size+" grid ***");
                }
                else
                {
                    validCoordinates = true;
                }
            }
            catch(NumberFormatException nfe){
                System.out.println("*** Invalid input, Please provide valid coordinates: positive integers within the "+size+"x"+size+" grid ***");
            }
        }

        masterGrid.selectSquare(x,y);

        long startTime = System.nanoTime();

        Unit unit = new Unit(new Boundary(0,0), new Boundary(size-1,size-1), masterGrid.getSelectedSquare());
        unit.solve(masterGrid);

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(separator);
        GridPrinter.printGrid(masterGrid);

        System.out.println(separator);
        System.out.println("Time elapsed: hh:mm:ss:SSSSSS");
        System.out.println("              "+transformElapsedTime(elapsedTime)+"");
        System.out.println(separator);
    }

    private static String transformElapsedTime(long elapsedTime)
    {
        return String.format("%02d:%02d:%02d:%06d",
                TimeUnit.NANOSECONDS.toHours(elapsedTime),
                TimeUnit.NANOSECONDS.toMinutes(elapsedTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(elapsedTime)),
                TimeUnit.NANOSECONDS.toSeconds(elapsedTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime)),
                TimeUnit.NANOSECONDS.toMillis(elapsedTime) -
                        TimeUnit.MINUTES.toMillis(TimeUnit.NANOSECONDS.toSeconds(elapsedTime)));
    }
}
