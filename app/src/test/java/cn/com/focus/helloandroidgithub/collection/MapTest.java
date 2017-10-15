package cn.com.focus.helloandroidgithub.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by MrLu on 2017/10/15.
 */

public class MapTest {
    public void testLinkedHashMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "a");
        map.put(5, "b");
        map.put(2, "c");
        map.put(10, "d");
        map.put(102, "d");
        map.put(33, "d");
        map.put(55, "d");

        Set<Integer> integers = map.keySet();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println("key = " + key + "  value = " + map.get(key));

        }

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator1 = entries.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, String> next = iterator1.next();
            System.out.println("key = " + next.getKey() + " value=" + next.getValue());
        }
    }

}
