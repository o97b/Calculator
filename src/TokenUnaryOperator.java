public class TokenUnaryOperator extends Token {
    public OperatorType type;

    public TokenUnaryOperator(char ch) {
        switch (ch) {
            case '+':
                this.type = OperatorType.UnaryPlus;
                break;
            case '-':
                this.type = OperatorType.UnaryMinus;
                break;
        }
    }

    @Override
    public String toString() { return type.toString(); }
}