package util;

import common.ApplicationConstants;

import java.util.*;
import java.util.stream.Collectors;

public class MapUtils {
  public static LinkedHashMap<String, Integer> getReverseSortedMap(Map<String, Integer> resultMap) {
    return resultMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public static Map<String, Integer> getReverseSortedSelectedSizeMap(Map<String, Integer> resultMap) {
    Map<String, Integer> result = new HashMap<>();
    Map<String, Integer> tmp = MapUtils.getReverseSortedMap(resultMap);
    int count = 0;
    for (String key : tmp.keySet()) {
      if (count < ApplicationConstants.RESULT_SIZE) {
        result.put(key, tmp.get(key));
        count++;
      }else{
        return getReverseSortedMap(result);
      }
    }
    return getReverseSortedMap(result);
  }
  public static Map<String,Double> getSortedMap(Map<String,Double> map) {
    LinkedHashMap<String, Double> linkedMap = new LinkedHashMap<>();
    ArrayList<Map.Entry<String, Double>> arr = new ArrayList<>();
    for(Map.Entry<String, Double> e: map.entrySet()) {
      arr.add(e);
    }

    Comparator<Map.Entry<String, Double>> valueComparator = new Comparator<Map.Entry<String, Double>>() {

      @Override
      public int compare(Map.Entry<String, Double> e1, Map.Entry<String, Double> e2) {
        Double v1 = e1.getValue();
        Double v2 = e2.getValue();
        return v1.compareTo(v2);
      }
    };

    Collections.sort(arr, valueComparator);

    for(Map.Entry<String, Double> e: arr) {
      linkedMap.put(e.getKey(), e.getValue());
    }
    return linkedMap;
  }
}
