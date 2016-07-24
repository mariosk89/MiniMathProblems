/**
 * Created by mario on 23-Jul-16.
 */
public class Square
{
    private final int x;
    private final int y;
    private String displayField = "OO";

    private boolean isUsed;
    private boolean isSelected;

    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    public boolean isUsed()
    {
        return isUsed;
    }

    public void setUsed(boolean used)
    {
        isUsed = used;
    }

    public boolean isSelected()
    {
        return this.isSelected;
    }

    public void setSelected(boolean selected)
    {
        this.isSelected = selected;
        this.displayField = "XX";
    }

    public void setDisplayField(String displayField)
    {
        this.displayField = displayField;
    }

    public String getDisplayField()
    {
        return this.displayField;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
