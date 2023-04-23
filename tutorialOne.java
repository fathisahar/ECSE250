import java.util.Scanner;

public class tutorialOne {
    public static void main(String[] args) {
        
        System.out.println("Hello world!");

        int x = -5;
        double y = -0.33;
        int absX = Math.abs(x);
        double absY = Math.abs(y);
        System.out.println("The absolute value of x is : " + absX);
        System.out.println("The absolute value of y is : " + absY);

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int n = myObj.nextInt();
        myObj.close();
        double bits =  Math.pow(2, n);
        System.out.println("I can represent the integer with " + bits + " bits");
        System.out.println("The range of the n-bit unsigned integer is [0, " + (bits - 1) + "]");
        System.out.println("The range of the n-bit unsigned integer is " + "[" + -Math.pow(2, n-1) + "," + (Math.pow(2, n-1) - 1) + "]");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a language between English, Spanish and French:");
        String input = scanner.nextLine();
        tutorialOne.sayHi(input);
        sayHi(scanner.nextLine());
        scanner.close();
        

        divisors(28,16);

    }

    public static void sayHi(String input){
        if (input.equals("English")){
            System.out.println("Hi!");
        } else if (input.equals("Spanish")){
            System.out.println("Hola!");
        } else if (input.equals("French")){
            System.out.println("Bonjour!");
        } else {
            System.out.println("Sorry, I don't know that language!");
        }
    }

    public static void divisors(int x, int y){
        int gcd = 1;
        for (int i = 1; i <= x; i++){
            if ((x % i == 0) && (y % i == 0)){
                gcd = i;
            }
        }

        int lcm = 1;
        boolean found = false;
        for (int i = 1; i <= (x*y); i++){
            if (!found){
                if ((i % x == 0) && (i % y == 0)){
                    lcm = i;
                    found = true;}
            }
        }

        System.out.println("The GCD is " + gcd + ". The LCM is " + lcm + ".");
    }

}