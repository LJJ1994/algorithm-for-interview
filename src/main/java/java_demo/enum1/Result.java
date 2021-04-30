package java_demo.enum1;

public enum Result {
    SUCCESS(100, "OK"),
    FAILED(101, "NO");
    private int code;
    private String msg;

    Result(int i, String failed) {
        code = i;
        msg = failed;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
