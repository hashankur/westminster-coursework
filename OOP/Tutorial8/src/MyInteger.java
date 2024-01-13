public class MyInteger extends Expression {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate() {
        return getValue();
    }
}
