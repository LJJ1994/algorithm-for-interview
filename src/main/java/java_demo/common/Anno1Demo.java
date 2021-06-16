package java_demo.common;

public class Anno1Demo {
    public int value = 1;
    public void doIt() {
        final int value = 2;
        Counter counter = new Counter() {
            private int value = 3;
            @Override
            public void count() {
                int value = 4;
                Anno1Demo.this.value += 1;
                System.out.println("Counter value: " + this.value);
                System.out.println("wrapper class value: " + Anno1Demo.this.value);
            }
        };
        counter.count();
        System.out.println("Anno1Demo value: " + this.value);
    }

    public static void main(String[] args) {
        Anno1Demo anno1Demo = new Anno1Demo();
        anno1Demo.doIt();
        System.out.println("==============");
        anno1Demo.doIt();
        System.out.println("==============");
        anno1Demo.doIt();

    }
    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
    interface Counter {
        void count();
    }
}
