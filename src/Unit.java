/**
 * Created by mario on 24-Jul-16.
 */
public class Unit implements Runnable
{
    private Boundary topLeft;
    private Boundary bottomRight;
    private Square usedSquare;

    public Unit(Boundary topLeft, Boundary bottomRight, Square usedSquare)
    {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.usedSquare = usedSquare;
    }

    public Boundary getTopLeft() {
        return topLeft;
    }

    public Boundary getBottomRight() {
        return bottomRight;
    }

    public Square getUsedSquare() {
        return usedSquare;
    }



    public void solve(Grid grid)
    {
        if(canBeSplit())
        {
            Unit[] subUnits = divideUnit(grid);
            for(int part = 0 ; part < 4 ; part++)
            {
                    subUnits[part].solve(grid);
            }
        }
        else
        {
            //2x2 Unit
            placeCentralTromino(grid, getCentralSquares(grid));
        }
    }

    private Unit[] divideUnit(Grid grid)
    {
        int specialPart = locateSpecialPart();

        Square[] centralSquares = getCentralSquares(grid);

        placeCentralTromino(grid, centralSquares);

        int unitSize = bottomRight.getX() - topLeft.getX();
        Unit[] units = new Unit[84];

        //North-West
        units[0] = new Unit(new Boundary(topLeft.getX() , topLeft.getY()),
                            new Boundary(topLeft.getX() + ((unitSize+1)/2)-1 , topLeft.getY()+((unitSize+1)/2)-1),
                            specialPart == 0 ? usedSquare : centralSquares[0]);
        //North-East
        units[1] = new Unit(new Boundary(topLeft.getX() , topLeft.getY() + (unitSize+1)/2 ),
                            new Boundary(topLeft.getX() + ((unitSize+1)/2)-1 , bottomRight.getY()),
                            specialPart == 1 ? usedSquare : centralSquares[1] );
        //South-West
        units[2] = new Unit(new Boundary(topLeft.getX() + (unitSize+1)/2, topLeft.getY()),
                            new Boundary(bottomRight.getX() , topLeft.getY() + ((unitSize+1)/2)-1),
                            specialPart == 2 ? usedSquare : centralSquares[2] );
        //South-East
        units[3] = new Unit(new Boundary(topLeft.getX() + (unitSize+1)/2 , topLeft.getY() + (unitSize+1)/2 ),
                            new Boundary(bottomRight.getX() , bottomRight.getY()),
                            specialPart == 3 ? usedSquare : centralSquares[3]);
        return units;
    }

    private int locateSpecialPart()
    {
        double middleX = ((double)bottomRight.getX() + (double)topLeft.getX()) / 2;
        double middleY = ((double)bottomRight.getY() + (double)topLeft.getY()) / 2;
        int location = 0;

        if(usedSquare.getX() < middleX && usedSquare.getY() < middleY)
        {
            location = 0; //North-West
        }
        else if(usedSquare.getX() < middleX && usedSquare.getY() > middleY)
        {
            location = 1; //North-East
        }
        else if(usedSquare.getX() > middleX && usedSquare.getY() < middleY)
        {
            location = 2; //South-West
        }
        else if(usedSquare.getX() > middleX && usedSquare.getY() > middleY)
        {
            location = 3; //South-East
        }

        return location;
    }

    private Square[] getCentralSquares(Grid grid)
    {
        double middleX = ((double)bottomRight.getX() + (double)topLeft.getX()) / 2;
        double middleY = ((double)bottomRight.getY() + (double)topLeft.getY()) / 2;
        Square[] squares = new Square[4];

        squares[0] = grid.getSquare(new Double(middleX-0.5).intValue(),new Double(middleY-0.5).intValue());
        squares[1] = grid.getSquare(new Double(middleX-0.5).intValue(),new Double(middleY+0.5).intValue());
        squares[2] = grid.getSquare(new Double(middleX+0.5).intValue(),new Double(middleY-0.5).intValue());
        squares[3] = grid.getSquare(new Double(middleX+0.5).intValue(),new Double(middleY+0.5).intValue());

        return squares;
    }

    private void placeCentralTromino(Grid grid, Square[] squares)
    {
        int specialPart = locateSpecialPart();

        switch(specialPart)
        {
            case 0:
            {
                grid.addTromino(squares[1],squares[2],squares[3]);
                break;
            }
            case 1:
            {
                grid.addTromino(squares[0],squares[2],squares[3]);
                break;
            }
            case 2:
            {
                grid.addTromino(squares[0],squares[1],squares[3]);
                break;
            }
            case 3:
            {
                grid.addTromino(squares[0],squares[1],squares[2]);
                break;
            }
        }
    }

    private boolean canBeSplit()
    {
        return bottomRight.getY() - topLeft.getY() > 2;
    }


    @Override
    public void run()
    {

    }
}
