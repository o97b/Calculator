import sun.misc.Queue;

public class Parser {

    enum State {
        Start, Ok, Error, WaitNumberOrOpenBracket,
        ReadNumber, ReadNumberAfterDot, ReadNumberHasDot, WaitOperator
    }

    State state;
    int brackets;
    StringBuilder number;
    Queue<Token> queue;

    public Queue<Token> parse(String expr) {
        state = State.Start;
        brackets = 0;
        queue = new Queue<>();
        number = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            ProcessChar(expr.charAt(i));
            if (state == State.Error) { return null; }
        }
        ProcessEnd();
        return (state == State.Ok) ? queue : null;
    }

    private void ProcessChar(char ch) {
        if (Character.isWhitespace(ch)) { return; }

        switch (state) {
            case Start:
                switch (ch) {
                    case '(':
                        brackets++;
                        queue.enqueue(new TokenBracket(true));
                        break;
                    case '+': case '-':
                        queue.enqueue(new TokenUnaryOperator(ch));
                        number.append(ch);
                        state = State.WaitNumberOrOpenBracket;
                        break;
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        number.append(ch);
                        state = State.ReadNumber;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case WaitNumberOrOpenBracket:
                switch (ch)
                {
                    case '(':
                        brackets++;
                        queue.enqueue(new TokenBracket(true));
                        state = State.Start;
                        break;
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        number.append(ch);
                        state = State.ReadNumber;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case ReadNumber:
                switch (ch) {
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        number.append(ch);
                        break;
                    case '.':
                        number.append(ch);
                        state = State.ReadNumberAfterDot;
                        break;
                    case '+': case '-':
                    case '*': case '/':
                        queue.enqueue(new TokenNumber(number.toString()));
                        number.setLength(0);
                        queue.enqueue(new TokenOperator(ch));
                        state = State.WaitNumberOrOpenBracket;
                        break;
                    case ')':
                        queue.enqueue(new TokenNumber(number.toString()));
                        number.setLength(0);
                        brackets--;
                        queue.enqueue(new TokenBracket(false));
                        state = brackets >= 0 ? State.WaitOperator : State.Error;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case ReadNumberAfterDot:
                switch (ch) {
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        number.append(ch);
                        state = State.ReadNumberHasDot;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case ReadNumberHasDot:
                switch (ch) {
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        number.append(ch);
                        break;
                    case '+': case '-':
                    case '*': case '/':
                        queue.enqueue(new TokenNumber(number.toString()));
                        number.setLength(0);
                        queue.enqueue(new TokenOperator(ch));
                        state = State.WaitNumberOrOpenBracket;
                        break;
                    case ')':
                        queue.enqueue(new TokenNumber(number.toString()));
                        number.setLength(0);
                        brackets--;
                        queue.enqueue(new TokenBracket(false));
                        state = brackets >= 0 ? State.WaitOperator : State.Error;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;

            case WaitOperator:
                switch (ch)
                {
                    case ')':
                        brackets--;
                        queue.enqueue(new TokenBracket(false));
                        state = brackets >= 0 ? State.WaitOperator : State.Error;
                        break;
                    case '+': case '-':
                    case '*': case '/':
                        queue.enqueue(new TokenOperator(ch));
                        state = State.WaitNumberOrOpenBracket;
                        break;
                    default:
                        state = State.Error;
                        break;
                }
                break;
        }
    }

    private void ProcessEnd()
    {
        switch (state) {
            case Start: case WaitNumberOrOpenBracket:
                state = State.Error;
                break;
            case ReadNumber: case ReadNumberHasDot:
                queue.enqueue(new TokenNumber(number.toString()));
                number.setLength(0);
                state = (brackets == 0) ? State.Ok : State.Error;
                break;
            case WaitOperator:
                state = (brackets == 0) ? State.Ok : State.Error;
                break;
        }
    }
}
