package Basic.hashSort;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  HashMap sort
 *  by Value
 *  using ArrayList, Stream
 */
public class Main {

    public static void main(String[] args) {
        //  ArrayList
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("red", 1);
        hm.put("black", 2);
        hm.put("blue", 4);
        hm.put("green", 3);

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        System.out.println("---------using ArrayList---------");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }

        // Stream
        HashMap<String, Integer> hm2 = new HashMap<>();
        hm2.put("red", 1);
        hm2.put("black", 2);
        hm2.put("blue", 4);
        hm2.put("green", 3);

        List<Map.Entry<String, Integer>> list2 = hm2.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .sorted(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return Integer.compare(o2.getValue(), o1.getValue());
                    }
                })
                .collect(Collectors.toList());
        Iterator<Map.Entry<String, Integer>> iterator2 = list2.iterator();
        System.out.println("---------using Stream---------");
        while (iterator2.hasNext()) {
            Map.Entry<String, Integer> next = iterator2.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }
    }
}
