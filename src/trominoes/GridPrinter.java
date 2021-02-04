package trominoes;

/**
 * Created by mario on 23-Jul-16.
 */
public final class GridPrinter
{
    public static void printGrid(Grid grid)
    {
        System.out.print("  ");
        for(int i = 0 ; i < grid.getSize() ; i++)
        {
            System.out.print(" " + (i) + " ");
        }
        System.out.println();
        for(int r = 0 ; r < grid.getSize() ; r++)
        {
            System.out.print((r));

            for(int c = 0 ; c < grid.getSize() ; c++)
            {
                System.out.print(" " + grid.getSquare(r,c).getDisplayField());
            }

            System.out.println();
        }
    }
}
