import java.util.Stack;
import java.util.StringTokenizer;

public class PostFixEvaluator {
    // Construct a PostFixEvaluator from String s.
    // Assume s is a legitimate postfix expression.

    // the operations we will be performing on the strings
    private String operations = "*+-";

    // the stack we will be using in PostFixEvaluator
    private Stack<String> stack;

    public PostFixEvaluator(String s){
        // we get the two operands
        String operand1 = "";
        String operand2 = "";
        // initializes the stack
        stack = new Stack<String>();
        // we tokenize the string
        StringTokenizer token = new StringTokenizer(s);
        // initialize the string for the tokens
        String thisnumber;

        // while the token has more tokens
        while (token.hasMoreTokens() == true) {
            // we take the token
            thisnumber = token.nextToken();

            // if there are no operations in the number (it is an operand), we push it onto the stack
            if(!operations.contains(thisnumber)){
                stack.push(thisnumber);
            }

            // otherwise, we pop the two operands and do the addition/subtraction/multiplication
            // pushing the answer onto the stack
            else if(operations.contains(thisnumber)){
                operand2 = stack.pop();
                operand1 = stack.pop();

                if(thisnumber.equals("+")){
                    BigNum a1 = new BigNum(operand1);
                    BigNum a2 = new BigNum(operand2);

                    BigNum ans = a1.add(a2);
                    stack.push(ans.toString());

                }
                else if (thisnumber.equals("-")) {
                    BigNum a1 = new BigNum(operand1);
                    BigNum a2 = new BigNum(operand2);

                    BigNum ans = a1.subtract(a2);
                    stack.push(ans.toString());
                }
                else{
                    BigNum a1 = new BigNum(operand1);
                    BigNum a2 = new BigNum(operand2);
                    BigNum ans = a1.multiply(a2);
                    stack.push(ans.toString());
                }

            }
        }

    }

    // Return the evaluation of this postfix expression
    public BigNum evaluate(){
        return new BigNum((stack.pop()));
    }

    // Use this to test your PostFixEvaluator methods
    private static void main() {
    }

}
