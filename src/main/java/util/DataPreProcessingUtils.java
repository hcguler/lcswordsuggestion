package util;

import common.ApplicationConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataPreProcessingUtils {
  public static Map<Integer, List<String>> initializeSizeWithStringMap() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ApplicationConstants.SOURCE_FILE), "UTF8"))) {
      String line = reader.readLine();
      List<String> wordList = new ArrayList<>();
      while (line != null) {
        String[] split = line.split(ApplicationConstants.SPACE_SPLIT_REGEX);
        wordList.add(split[0]);
        line = reader.readLine();
      }
      System.out.println("start");

      Map<Integer, List<String>> mapStringUsingLength = wordList.stream()
          .collect(Collectors.groupingBy(
              String::length,
              TreeMap::new,
              Collectors.toList()));
      System.out.println("Finished Size With Str Map...........");
      return mapStringUsingLength;
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    return new HashMap<>();
  }
}
