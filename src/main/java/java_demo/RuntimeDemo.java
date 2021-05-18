package java_demo;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

public class RuntimeDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("max memory: " + runtime.maxMemory());
        System.out.println("total memory: " + runtime.totalMemory());
        System.out.println("available processors: " + runtime.availableProcessors());
        System.out.println("free memory: " + runtime.freeMemory());

//        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//        Map<String, String> systemProperties = runtimeMXBean.getSystemProperties();
//        System.out.println("system properties: ");
//        for (Map.Entry<String, String> entry : systemProperties.entrySet()) {
//            System.out.print("key: " + entry.getKey() + " value: " + entry.getValue());
//        }
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getArch());
        System.out.println(operatingSystemMXBean.getName());
        System.out.println(operatingSystemMXBean.getSystemLoadAverage());
        System.out.println(operatingSystemMXBean.getAvailableProcessors());
        System.out.println(operatingSystemMXBean.getVersion());
    }
}
