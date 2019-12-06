package datapreprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DataPreProcessing {

  public static final String SOURCE_FILE="wordset/full.txt.tr";
  public static final String TARGET_DIRECTORY="wordsetwithlength/";
  public static final String FILE_NAME_SUFFIX="_length_words.txt";
  public static final String SPACE_SPLIT_REGEX="\\s+";

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new FileReader(SOURCE_FILE))) {
      String line = reader.readLine();
      List<String> wordList = new ArrayList<>();
      while (line != null) {
        String[] split = line.split(SPACE_SPLIT_REGEX);
        wordList.add(split[0]);
        line = reader.readLine();
      }
      System.out.println("start");

      Map<Integer, List<String>> mapStringUsingLength = wordList.stream()
          .collect(Collectors.groupingBy(
              String::length,
              TreeMap::new,
              Collectors.toList()));

      for (Integer integer : mapStringUsingLength.keySet()) {
        List<String> stringList = mapStringUsingLength.get(integer);
        System.out.println(integer + " length word writing!");
        try (BufferedWriter bw
                 = new BufferedWriter(new FileWriter(TARGET_DIRECTORY+integer + FILE_NAME_SUFFIX, true))) {
          bw.write(stringList.toString());
          bw.newLine();
          bw.flush();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      System.out.println("finish");
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFoundException: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
