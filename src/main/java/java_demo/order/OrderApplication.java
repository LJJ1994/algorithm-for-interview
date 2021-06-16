package java_demo.order;
import java.util.concurrent.*;

public class OrderApplication {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static boolean payStatus = false;
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

//        Runnable task = () -> {
//            try {
//                Integer tradeNo = 123;
//                Integer outTradeNo = 456;
//                boolean result = SimulateTakeOrder.isPaySuccess(tradeNo, outTradeNo);
//                payStatus = result;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
        Callable<Boolean> task1 = () -> {
            Integer tradeNo = 123;
            Integer outTradeNo = 456;
            boolean result = SimulateTakeOrder.isPaySuccess(tradeNo, outTradeNo);
            return result;
        };

        Future<Boolean> future = executorService.submit(task1);
        Boolean aBoolean = future.get();
        Thread.sleep(1000);
        System.out.println("waiting order pay status.................");
        System.out.println("order pay status: " + aBoolean);
        executorService.shutdownNow();

//        final ThreadLocal<Person> threadLocal=new TransmittableThreadLocal<>();
//        threadLocal.set(new Person("Java架构沉思录"));
//        System.out.println("初始值："+threadLocal.get());
//        Runnable task=()->{
//            System.out.println("----------start------------");
//            System.out.println("父线程的值："+threadLocal.get());
//            threadLocal.set(new Person("沉思君"));
//            System.out.println("子线程覆盖后的值："+threadLocal.get());
//            System.out.println("------------end---------------");
//        };
//        ExecutorService executorService= Executors.newFixedThreadPool(1);
//        Runnable runnable= TtlRunnable.get(task);
//        executorService.submit(runnable);
//        TimeUnit.SECONDS.sleep(1);
//        executorService.submit(runnable);
//        TimeUnit.SECONDS.sleep(1);
//        executorService.submit(runnable);
    }

    private static class Person {
        String name;
        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
