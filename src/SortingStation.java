import sun.misc.Queue;

import java.util.Stack;

public class SortingStation {
    public static Queue<Token> sorting(Queue<Token> queue) throws Exception {
        Token token;
        Queue<Token> queueOut = new Queue<>();
        Stack<Token> stack = new Stack<>();
        while (!queue.isEmpty()) {
            token = queue.dequeue();
            if (token instanceof TokenNumber) {
                queueOut.enqueue(token);
            } else if (token instanceof TokenOperator) {
                if (!stack.isEmpty()) {
                    while (stack.peek() instanceof TokenOperator) {
                        if (((TokenOperator) token).compareTo((TokenOperator) stack.peek()) > 0) break;
                        queueOut.enqueue(stack.pop());
                        if (stack.isEmpty()) break;
                    }
                }
                stack.push(token);
            } else if (token instanceof TokenBracket) {
                if (((TokenBracket)token).isOpening) {
                    stack.push(token);
                } else {
                    if (!stack.isEmpty()) {
                        while (!(stack.peek() instanceof TokenBracket)) {
                            queueOut.enqueue(stack.pop());
                            if (stack.isEmpty()) throw new Exception("В выражении пропущена скобка");
                        }
                        stack.pop();
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() instanceof TokenBracket) {
                throw new Exception("В выражении незакрытая скобка");
            }
            queueOut.enqueue(stack.pop());
        }
        return queueOut;
    }
}
