import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mario on 23-Jul-16.
 */
public class Application {
    public static void main(String args[]) throws IOException {

        int size = 0;
        int x = 0;
        int y = 0;


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean validGridSize = false;
        while(!validGridSize)
        {
            System.out.print("Please provide a size for the sides of the grid: ");
            try
            {
                size = Integer.parseInt(br.readLine());
                size = (int)Math.pow(2,size);
                validGridSize = true;
            }
            catch(NumberFormatException nfe){
                System.out.println("*** Invalid input, please give a number ***");
            }
        }


        System.out.println("Creating master grid ("+size+"x"+size+")");
        Grid masterGrid = new Grid(size);

        boolean validCoordinates = false;
        while(!validCoordinates)
        {
            System.out.println("Please provide the coordinates for the blank square i.e \"x,y\": ");
            String coordinatesInput = br.readLine();
            String[] coordinates = coordinatesInput.split(",");

            try
            {
                x = Integer.parseInt(coordinates[0]) - 1;
                y = Integer.parseInt(coordinates[1]) - 1;

                //TODO validate coordinates against grid size

                validCoordinates = true;
            }
            catch(NumberFormatException nfe){
                System.out.println("*** Invalid input, please try again ***");
            }
        }

        masterGrid.selectSquare(x,y);

        Unit unit = new Unit(new Boundary(0,0), new Boundary(size-1,size-1), masterGrid.getSelectedSquare());
        unit.solve(masterGrid);

        GridPrinter.printGrid(masterGrid);
    }
}
