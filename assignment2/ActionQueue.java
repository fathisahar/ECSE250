package assignment2;


public class ActionQueue extends MyQueue<Direction>{
    private MyStack<String> stack;

    public ActionQueue(){
        super();
        stack = new MyStack<>();
    }

    public void clear(){
        stack.clear();
        super.clear();
    }


        public void loadFromEncodedString(String str) {
            StringBuffer decoded = new StringBuffer();
            int left = 0;
            int right = 0;
            for (int p = 0; p < str.length(); p++){
                if (str.charAt(p)=='['){
                    left++;
                }
                if (str.charAt(p)==']'){
                    right++;
                }
            }
            if (left!=right){
                throw new IllegalArgumentException("Invalid syntax");
            }
            int i = 0;
            if (str.length() == 0){
                throw new IllegalArgumentException("Invalid syntax");
            }
            while (i < str.length()) {
                char c = str.charAt(i);
                if ((int) c == 48){
                    if (i == 0){
                        throw new IllegalArgumentException("Invalid syntax");
                    } else {
                        char before = str.charAt(i - 1);
                        if (!((int) before >= 48 && (int) before <= 57)){
                            throw new IllegalArgumentException("Invalid syntax");
                        }
                        i++;
                    }
                } else if ((int) c > 48 && (int) c <= 57) {
                    int j = i + 1;
                    char after = str.charAt(i+1);
                    if (after==']' || after == 'N' || after == 'S' || after == 'W' || after == 'E' ){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                    while ((int) str.charAt(j) >= 48 && (int) str.charAt(j) <= 57) {
                        j++;
                        if (j> str.length()){
                            throw new IllegalArgumentException("Invalid syntax");
                        }
                    }
                    stack.push(str.substring(i, j));
                    i = j;
                } else if (c == '[') {
                    if (i > 0){
                        char after = str.charAt(i + 1);
                        char before = str.charAt(i - 1);
                        if (before != '[' && !((int) before >= 48 && (int) before <= 57)){
                            throw new IllegalArgumentException("Invalid syntax");
                        } else if (after == ']'){
                            throw new IllegalArgumentException("Invalid syntax");
                        }
                    }
                    stack.push("[");
                    i++;
                } else if (c == ']') {
                    StringBuffer D = new StringBuffer();
                    int popped = 0;
                    while (!stack.peek().equals("[")) {
                        D.insert(0, stack.pop());
                    }
                    stack.pop();
                    String check = stack.pop();
                    if (!((int) check.charAt(0) >= 48) && !((int) check.charAt(0) <= 57)){
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                    int times = Integer.parseInt(check);
                    String directionsDraft = D.toString();
                    for (int k = 0; k < times; k++) {
                        stack.push(directionsDraft);
                    }
                    i++;
                } else if (c == 'N' || c == 'S' || c == 'W' || c == 'E'){
                    stack.push(Character.toString(c));
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid syntax");
                }
            }

            while (!stack.isEmpty()) {
                decoded.insert(0, stack.pop());
            }

            String directionsString = decoded.toString();

            Direction E = Direction.EAST;
            Direction W = Direction.WEST;
            Direction S = Direction.SOUTH;
            Direction N = Direction.NORTH;

            for (int d = 0; d < directionsString.length(); d++){
                switch(directionsString.charAt(d)){
                    case 'E':
                        this.enqueue(E);
                        break;
                    case 'W':
                        this.enqueue(W);
                        break;
                    case 'S':
                        this.enqueue(S);
                        break;
                    case 'N':
                        this.enqueue(N);
                        break;
                }
            }
        }
    }

