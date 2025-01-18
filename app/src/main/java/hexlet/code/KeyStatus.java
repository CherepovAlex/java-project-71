package hexlet.code;

public final class KeyStatus {

    private final String key;
    private final String status;
    private final Object value1;
    private final Object value2;

    public KeyStatus(String key, String status, Object value1, Object value2) {
        this.key = key;
        this.status = status;
        this.value1 = value1;
        this.value2 = value2;
    }
    public KeyStatus(String key, String status, Object value1) {
        this(key, status, value1, null);
    }
    public String getKey() {
        return key;
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
