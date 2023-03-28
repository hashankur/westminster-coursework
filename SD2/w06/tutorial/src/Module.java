public class Module {
    private String code;
    private double markICT;
    private double markCW;

    public Module (String code, double markICT, double markCW) {
        this.code = code;
        this.markICT = markICT;
        this.markCW = markCW;
    }

    private double getFinalMark() {
        return (markICT + markCW) / 2;
    }

    public boolean hasPassed() {
        return getFinalMark() >= 40;
    }

    public String getCode() {
        return this.code;
    }
}
