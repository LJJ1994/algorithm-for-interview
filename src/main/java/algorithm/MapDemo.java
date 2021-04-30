
package algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
