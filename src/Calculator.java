import sun.misc.Queue;

import java.util.Stack;

public class Calculator {
    public static double calculate(Queue<Token> queue) throws InterruptedException {
        double result;
        Token token;
        Token tokenResult;
        Stack<Token> stack = new Stack<>();
        while (!queue.isEmpty()) {
            token = queue.dequeue();
            if (token instanceof TokenNumber) {
                stack.push(token);
            } else if (token instanceof TokenOperator) {
                switch (((TokenOperator) token).type) {
                    case Add:
                        double add =((TokenNumber)stack.pop()).value
                                + ((TokenNumber)stack.pop()).value;
                        tokenResult = new TokenNumber(add);
                        stack.push(tokenResult);
                        System.out.println("add " + add);
                        break;
                    case Multiply:
                        double multiply =((TokenNumber)stack.pop()).value
                                * ((TokenNumber)stack.pop()).value;
                        tokenResult = new TokenNumber(multiply);
                        stack.push(tokenResult);
                        System.out.println("multiply " + multiply);
                        break;
                    case Divide:
                        double second = ((TokenNumber)stack.pop()).value;
                        double first = ((TokenNumber)stack.pop()).value;
                        double divide = first / second;
                        tokenResult = new TokenNumber(divide);
                        stack.push(tokenResult);
                        System.out.println("divide " + divide);
                        break;
                    case Subtract:
                        double subtract = - ((TokenNumber)stack.pop()).value
                                + ((TokenNumber)stack.pop()).value;
                        tokenResult = new TokenNumber(subtract);
                        stack.push(tokenResult);
                        System.out.println("subtract " + subtract);
                        break;
                }
            }
        }
        result = ((TokenNumber)stack.pop()).value;
        return result;
    }
}
