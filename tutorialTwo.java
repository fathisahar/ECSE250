import java.util.Random;

public class tutorialTwo {
    public static void main(String[] args){
        //Random randomGenerator = new Random();
        //int diceRoll = 1 + randomGenerator.nextInt(6);
    

        int[] array = randomArray(6,1,5);
        int[] arraySwap = {1, 2, 3, 4, 5, 6};

        int[] arraySwapped = swap(arraySwap);

        for (int i = 0; i<array.length; i++){
            System.out.println(arraySwapped[i]);
        }
    }

    public static int[] randomArray(int n, int lowerbound, int upperbound){
        int[] array = new int[n];
        Random randomGenerator = new Random();
        for (int i = 0; i<array.length; i++){
            int randomInteger = randomGenerator.nextInt(upperbound-lowerbound) + lowerbound;
            //randomGenerator.nextInt(lowerbound, upperbound);
            array[i] = randomInteger;
        }
        return array;
    }

    public static int[] swap(int[] array){
        int[] arraySwapped = new int[array.length];
        for (int i = 0; i < array.length; i++){
            int temp = array[i];
            arraySwapped[i] = array[array.length - i - 1];
            arraySwapped[array.length - i - 1] = temp;
        }
        return arraySwapped;
    }

    public static void swap(int[] intArr, int index1, int index2){
        int tmp = intArr[index1];
        intArr[index1] = intArr[index2];
        intArr[index2] = tmp;
    }
    
}
