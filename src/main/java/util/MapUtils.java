package util;

import common.ApplicationConstants;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
        return getReverseSortedMap(resultMap);
      }
    }
    return getReverseSortedMap(result);
  }

}
