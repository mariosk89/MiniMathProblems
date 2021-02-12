package reversing;

public class ReverseAnArray {

    public static void main(String args[]){

        int[] array = {1,2,3,4,5};

        System.out.println("Original Array");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
        }

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

        System.out.println("\nReversed Array");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
        }
    }
}
