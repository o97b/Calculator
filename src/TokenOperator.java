import java.util.Comparator;

public class TokenOperator extends Token implements Comparator<TokenOperator> {
    public OperatorType type;

    public TokenOperator(char ch)  {
        switch (ch) {
            case '+':
                this.type = OperatorType.Add;
                break;
            case '-':
                this.type = OperatorType.Subtract;
                break;
            case '*':
                this.type = OperatorType.Multiply;
                break;
            case '/':
                this.type = OperatorType.Divide;
                break;
        }
    }

    public TokenOperator(char ch, boolean unary ) {
        switch (ch) {
            case '+':
                this.type = unary ? OperatorType.UnaryPlus : OperatorType.Add;
                break;
            case '-':
                this.type = unary ? OperatorType.UnaryMinus : OperatorType.Subtract;
                break;
            case '*':
                this.type = OperatorType.Multiply;
                break;
            case '/':
                this.type = OperatorType.Divide;
                break;
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }

//kkkkk
    @Override
    public int compare(TokenOperator o1, TokenOperator o2) {
        if ((o1.type == OperatorType.Add || o1.type == OperatorType.Subtract)
                && (o2.type == OperatorType.Divide || o2.type == OperatorType.Multiply)) {
            return -1;
        } else if ((o1.type == OperatorType.Divide || o1.type == OperatorType.Multiply)
                && (o2.type == OperatorType.Add || o2.type == OperatorType.Subtract)) {
            return 1;
        } else {
            return 0;
        }
    }
}
