package hexlet.code;

public final class KeyStatus {

    private final String status;
    private final Object value1;
    private final Object value2;

    public KeyStatus(String status, Object value1, Object value2) {
        this.status = status;
        this.value1 = value1;
        this.value2 = value2;
    }

    public KeyStatus(String status, Object value1) {
        this(status, value1, null);
    }

    public String getStatus() {
        return status;
    }
    public Object getValue1() {
        return value1;
    }
    public Object getValue2() {
        return value2;
    }
}
