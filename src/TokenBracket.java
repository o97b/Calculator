public class TokenBracket extends Token {
    public boolean isOpening;

    public TokenBracket(boolean isOpening) {
        this.isOpening = isOpening;
    }

    @Override
    public String toString() {
        return isOpening ? "(" : ")";
    }
}
