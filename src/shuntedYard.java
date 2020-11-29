import java.util.Stack;

public class shuntedYard {

        private static boolean letterOrDigit(char c) {
            if (Character.isLetterOrDigit(c))
                return true;
            else
                return false;
        }

        // ranks values for ordering them later
        static int getPrecedence(char ch) {

            if (ch == '+' || ch == '-')
                return 1;
            else if (ch == '*' || ch == '/')
                return 2;
            else if (ch == '^')
                return 3;
            else
                return -1;
        }

        //shunts the yard
        static String shuntingOfTheYard(String expression) {
            // Initalising an empty String
            Stack<Character> stack = new Stack<>();

            // initalize
            String out = new String("");


            for (int i = 0; i < expression.length(); ++i) {

                char c = expression.charAt(i);

                //If char is an operator add it to the output
                if (letterOrDigit(c))
                    out += c;

                    // If  a ( is found it is put on the stack
                else if (c == '(')
                    stack.push(c);

                    //takes out everything between ()
                else if (c == ')') {
                    while (!stack.isEmpty()
                            && stack.peek() != '(')
                        out += stack.pop();

                    stack.pop();
                }

                // If operator is found then:
                else {
                    while (
                            !stack.isEmpty()
                                    && getPrecedence(c)
                                    <= getPrecedence(stack.peek())) {
                        // peek (gets top)

                        out += stack.pop();
                    }
                    stack.push(c);
                }
            }
            // pops all remaining symbols
            while (!stack.isEmpty()) {
                //if parenthesis is not closed, returns invalid
                if (stack.peek() == '(') {
                    return "Error: (Invalid Expression) Please check your parenthesis.";
                }
                out += stack.pop();
            }
            return out;
        }
    }

