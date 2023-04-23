package assignment2;

public class TargetQueue extends MyQueue<Position>{

    private MyStack<String> stack;

    public TargetQueue(){
        super();
        stack = new MyStack<String>();
    }

    public void clear(){
        super.clear();
        stack.clear();
    }

    public void addTargets(String str){
        if (str.length()==0){
            throw new IllegalArgumentException("Invalid syntax");
        }
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == '.'){
                if (i==0){
                    char after = str.charAt(1);
                    if (after != '(' && after != '.'){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                } else if (i == str.length()-1) {
                    char before = str.charAt(i-1);
                    if (before != '(' && before != '.' && before != ')') {
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                } else if (i > 0 && i < str.length()-1){
                    char after = str.charAt(i+1);
                    char before = str.charAt(i-1);
                    if (after != '(' && after != '.' && after != ')' ){
                        throw new IllegalArgumentException("Invalid syntax");
                    } else if (before != '(' && before != '.' && before != ')'){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                }
                i++;
            } else if ((int) c >= 48 && (int) c <= 57) {
                if (i == 0){
                    throw new IllegalArgumentException("Invalid syntax");
                }
                if (i==str.length()-1){
                    throw new IllegalArgumentException("Invalid syntax");
                }
                int j = i + 1;
                while ((int) str.charAt(j) >= 48 && (int) str.charAt(j) <= 57) {
                    j++;
                    if (j> str.length()-1){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                }
                stack.push(str.substring(i, j));
                i = j;
            } else if (c == ')'){
                if (stack.isEmpty()){
                    throw new IllegalArgumentException("Invalid syntax");
                } else if (stack.getSize() != 2){
                    throw new IllegalArgumentException("Invalid syntax");
                } else {
                    int y = Integer.parseInt(stack.pop());
                    int x = Integer.parseInt(stack.pop());
                    this.enqueue(new Position(x,y));
                    i++;
                }
            } else if (c == ','){
                if (stack.isEmpty()){
                    throw new IllegalArgumentException("Invalid syntax");
                } else {
                    char after = str.charAt(i + 1);
                    char before = str.charAt(i - 1);
                    if (!((int) after >= 48) && !((int) after <= 57)) {
                        throw new IllegalArgumentException("Invalid syntax");
                    } else if (!((int) before >= 48) && !(before <= 57)) {
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                    i++;
                }
            } else if (c == '('){
                if (i == 0){
                    char after = str.charAt(i + 1);
                    if (!((int) after >= 48) && !((int) after <= 57)){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                } else if (i>0 && i<str.length()-1){
                    char after = str.charAt(i + 1);
                    char before = str.charAt(i - 1);
                    if (before != '.'){
                        throw new IllegalArgumentException("Invalid syntax");
                    } else if (!((int) after >= 48) && !((int) after <= 57)){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                } else if (i == str.length()-1){
                    char before = str.charAt(i - 1);
                    if (before != '.'){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                }
                i++;
            } else {
                throw new IllegalArgumentException("Invalid syntax");
            }

        }
    }

}
