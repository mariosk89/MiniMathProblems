import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 23-Jul-16.
 */
public final class Grid {

    private final int size;
    private Square selectedSquare;

    private final Square[][] squares;

    public Grid(int size)
    {
       this.size = size;

       this.squares = new Square[size][size];
       for(int x = 0; x < size; x++)
       {
           for(int y = 0; y < size; y++)
           {
               this.squares[x][y] = new Square(x,y);
           }
       }

       int a = 0;
    }

    public final void selectSquare( int x, int y)
    {
        selectedSquare = this.squares[x][y];
        selectedSquare.setUsed(true);
        selectedSquare.setSelected(true);
    }

    public Square getSquare(int x, int y)
    {
        return squares[x][y];
    }

    public Square getSelectedSquare()
    {
        return selectedSquare;
    }

    public int getSize()
    {
        return this.size;
    }

    public void addTromino(Square... squares)
    {
        String id = String.valueOf(squares[1].getY()) + String.valueOf(squares[1].getX());

        if(squares.length == 3)
        {
            for(int i=0 ; i < 3 ; i++)
            {
                squares[i] = squares[i];
                squares[i].setDisplayField(id);
                squares[i].setUsed(true);
            }
        }
    }

}
