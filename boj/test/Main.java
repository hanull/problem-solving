package boj.test;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String[] arr = {"z", "z", "red", "red", "black", "blue", "blue", "green", "green", "green"};
        System.out.println(solution(arr));
    }

//    private static String solution(String[] arr) {
//        String res = "";
//        Map<String, Integer> treeMap = new TreeMap<>();
//        for (String tmp : arr) {
//            if (!treeMap.containsKey(tmp)) {
//                treeMap.put(tmp, 1);
//            } else {
//                treeMap.put(tmp, treeMap.get(tmp) + 1);
//            }
//        }
//        int max = 0;
//        for (String tmp : treeMap.keySet()) {
//            if (treeMap.get(tmp) > max) {
//                res = tmp;
//                max = treeMap.get(tmp);
//            }
//        }
//        return res;
//    }

    private static String solution(String[] arr) {
        Map<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hm.containsKey(arr[i])) {
                hm.put(arr[i], hm.get(arr[i]) + 1);
            } else {
                hm.put(arr[i], 1);
            }
        }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(hm.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue() == o1.getValue()) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i).getKey());
        }
        return entries.get(0).getKey();
    }

//    private static String solution(String[] arr) {
//        Map<String, Integer> hm = new HashMap<>();
//        for (int i = 0; i < arr.length; i++) {
//            if (hm.containsKey(arr[i])) {
//                hm.put(arr[i], hm.get(arr[i]) + 1);
//            } else {
//                hm.put(arr[i], 1);
//            }
//        }
//        List<Map.Entry<String, Integer>> entries = new ArrayList<>(hm.entrySet());
//        entries = entries.stream().sorted((o1, o2) -> {
//            if (o2.getValue().compareTo(o1.getValue()) == 0) {
//                return o2.getKey().compareTo(o1.getKey());
//            }
//            return o2.getValue().compareTo(o1.getValue());
//        }).collect(Collectors.toList());
//        return entries.get(0).getKey();
//    }
}
