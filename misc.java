public class misc {

    public static void myMethod(String t) {
        t = t + "s";
    }
    public static void main(String args[]){
        int x = 5;
        x = x++ + ++x + x++;
        //System.out.println(x);

        String[] pets = {"cat", "dog"};
        pets[0] = pets[0] + "s";

        //System.out.println(pets[0]);

        String h = "word";
        myMethod(h);
        //System.out.println(h);

        String s = "word";
        String t = "";
        for (int i =0; i<s.length(); i++){
            if (s.charAt(i) == 'o'){
                t = t + "a";
            } else {
                t = t + s.charAt(i);
            }
        }
        System.out.println(t);
    }

   

}
