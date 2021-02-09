import sun.misc.Queue;
import java.util.Stack;

public class Calculator {
    public static double calculate(Queue<Token> queue) throws InterruptedException {
        Token token;
        Stack<Token> stack = new Stack<>();

        while (!queue.isEmpty()) {
            token = queue.dequeue();
            if (token instanceof TokenNumber) {
                stack.push(token);
            }
            else if (token instanceof TokenOperator) {
                double secondNum = ((TokenNumber)stack.pop()).value;
                double firstNum = ((TokenNumber)stack.pop()).value;
                Token tokenResult;

                switch (((TokenOperator) token).type) {
                    case Add:
                        tokenResult = new TokenNumber(firstNum + secondNum);
                        stack.push(tokenResult);
                        break;
                    case Multiply:
                        tokenResult = new TokenNumber(firstNum * secondNum);
                        stack.push(tokenResult);
                        break;
                    case Divide:
                        if (secondNum == 0) { throw new NullPointerException("Деление на ноль"); }
                        tokenResult = new TokenNumber(firstNum / secondNum);
                        stack.push(tokenResult);
                        break;
                    case Subtract:
                        tokenResult = new TokenNumber(firstNum - secondNum);
                        stack.push(tokenResult);
                        break;
                }
            }
        }
        return ((TokenNumber)stack.pop()).value;
    }
}
