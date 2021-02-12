package balancedarray;

public class BalancedArray {

    /**
     * A balanced array is a table where the sum of a subset of its elements is equal to the sum of the rest of the elements, without any re-ordering of the elements
     * @param args
     */
    public static void main(String args[]) {

        int[] balanced = {1,1,1,1,4};
        int[] balancedB = {4,1,1,1,1};

        int[] unbalanced = {1,1,1,1,5};
        int[] unbalancedB = {5,1,1,1,1};

        balance(balanced);
        balance(balancedB);
        balance(unbalanced);
        balance(unbalancedB);
    }


    private static void balance(int[] array) {

        int fromTheStart = 0;
        int fromTheEnd = 0;

        int i = 0;
        int j = array.length - 1;


        fromTheStart += array[0];
        fromTheEnd += array[j];

        while(i < j-1){

            if(fromTheEnd < fromTheStart){
                j--;
                fromTheEnd += array[j];
            }
            else if (fromTheEnd > fromTheStart){
                i++;
                fromTheStart += array[i];
            }
            else{
                fromTheEnd += array[j];
                fromTheStart += array[i];
            }
        }

        if(fromTheEnd == fromTheStart){
            System.out.println("balanced");
        }
        else{
            System.out.println("unbalanced");
        }

    }
}
