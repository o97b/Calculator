public class TokenNumber extends Token {
    public double value;

    public TokenNumber(double value) {
        this.value = value;
    }

    public TokenNumber(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
