public class Dice {
    private int faceValue;

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public void throw() {
        this.faceValue = Math.random()
    }
}
