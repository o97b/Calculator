import sun.misc.Queue;

import java.util.Stack;

public class SortingStation {
    public static void sorting(Queue<Token> queue) throws InterruptedException {
        Token token;
        Queue<Token> queueOut = new Queue<>();
        Stack<Token> stack = new Stack<>();
        while (!queue.isEmpty()) {
            token = queue.dequeue();
            if (token instanceof TokenNumber) {
                queueOut.enqueue(token);
            } else if (token instanceof TokenOperator) {
                while ((stack.peek(), token)) {

                }
            }

        }
    }
}
