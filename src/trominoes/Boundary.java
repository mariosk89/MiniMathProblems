package trominoes;

/**
 * Created by mario on 23-Jul-16.
 *
 * The class is used to indicate the location of a Square in the Grid
 */
public class Boundary
{

    private final int x;
    private final int y;

    public Boundary(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY()
    {
        return y;
    }

    public int getX()
    {
        return x;
    }

}
