package java_demo.enum1;

public class ResultDemo {
    public static void main(String[] args) {
        for (Result value : Result.values()) {
            System.out.println(value.getCode());
            System.out.println(value.getMsg());
            System.out.println(value.name());
        }
    }
}
