package java_demo.common;

public class DispatcherDemo {
        static class QQ {}
        static class _360 {}

        public static class Father {
            public void hardChoice(QQ arg) {
                System.out.println("father choose QQ");
            }

            public void hardChoice(_360 arg) {
                System.out.println("father choose _360");
            }
        }

        public static class Son extends Father {
            @Override
            public void hardChoice(QQ arg) {
                System.out.println("son choose QQ");
            }

            @Override
            public void hardChoice(_360 arg) {
                System.out.println("son choose 360");
            }
        }

        public static void main(String[] args) {
            Father father = new Father();
            Father son = new Son();
            father.hardChoice(new _360());
            son.hardChoice(new QQ());
        }
}
