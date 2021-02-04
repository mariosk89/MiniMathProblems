package trominoes;

/**
 * Created by mario on 24-Jul-16.
 */
public final class Unit
{
    private final Boundary topLeft;
    private final Boundary bottomRight;

    private final Square usedSquare;

    public Unit(Boundary topLeft, Boundary bottomRight, Square usedSquare)
    {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.usedSquare = usedSquare;
    }

    public final void solve(Grid grid)
    {
        int specialPart = locateSpecialPart();
        Square[] centralSquares = getCentralSquares(grid);

        if(canBeSplit())
        {
            //the grid is larger than 2x2 so it can be slit to smaller units
            Unit[] subUnits = divideUnit(grid, centralSquares, specialPart);
            for(int part = 0 ; part < 4 ; part++)
            {
                    subUnits[part].solve(grid);
            }
        }
        else
        {
            //2x2 Unit
            placeCentralTromino(grid, centralSquares, specialPart);
        }
    }

    //Divides the current unit in 4 smaller units
    private final Unit[] divideUnit(Grid grid, Square[] centralSquares, int specialPart)
    {
        placeCentralTromino(grid, centralSquares, specialPart);

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

    //locates the part of the unit (before its' division) that contains the used square
    private final int locateSpecialPart()
    {
        double middleX = ((double)bottomRight.getX() + (double)topLeft.getX()) / 2;
        double middleY = ((double)bottomRight.getY() + (double)topLeft.getY()) / 2;
        int location = -1;

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

    //using the boundaries of the unit, the function detects the four central squares
    private final Square[] getCentralSquares(Grid grid)
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

    //Places a tromino at the center of the unit, given the detected specialPart
    private final void placeCentralTromino(Grid grid, Square[] squares, int specialPart)
    {
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

    private final boolean canBeSplit()
    {
        return bottomRight.getY() - topLeft.getY() > 2;
    }
}
