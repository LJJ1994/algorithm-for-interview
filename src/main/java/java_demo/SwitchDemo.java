package java_demo;

public class SwitchDemo {
    public static void main(String[] args) {
        String s = "hello";
        switch (s) {
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            default:
                break;
        }
    }
}
