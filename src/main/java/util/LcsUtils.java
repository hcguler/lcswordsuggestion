package util;

import java.util.Map;

public class LcsUtils {

  //10 karakterden daha uzun kelimelerin karşılaştırılmasında sistemi çok yavaşlatır.
  public static int calculateLcs(String A, String B) {
    if (A.length() == 0 || B.length() == 0) {
      return 0;
    }
    String remA = A.substring(1);
    String remB = B.substring(1);
    if (A.charAt(0) == B.charAt(0)) {
      int remRes = calculateLcs(remA, remB);
      return 1 + remRes;
    } else {
      int remRes = Math.max(calculateLcs(remA, B), calculateLcs(A, remB));
      return remRes;
    }
  }


  public static int calculateLcsDynamic(String word1, String word2) {

    char[] word1CharArray = word1.toCharArray();
    char[] word2CharArray = word2.toCharArray();
    int word1Length = word1CharArray.length;
    int word2Length = word2CharArray.length;

    int resultMatris[][] = new int[word1Length + 1][word2Length + 1];

    for (int i = 0; i <= word1Length; i++) {
      for (int j = 0; j <= word2Length; j++) {
        if (i == 0 || j == 0) {
          resultMatris[i][j] = 0;
        } else if (word1CharArray[i - 1] == word2CharArray[j - 1]) {
          resultMatris[i][j] = resultMatris[i - 1][j - 1] + 1;
        } else {
          resultMatris[i][j] = max(resultMatris[i - 1][j], resultMatris[i][j - 1]);
        }
      }
    }
    return resultMatris[word1Length][word2Length];
  }

  private static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  public static void calculateEditDistance(String inputWord, Map<String, Integer> lcsResult){
    for (String s : lcsResult.keySet()) {
      int editDistance = 0;
      editDistance = inputWord.length()+s.length()-(2*lcsResult.get(s));
      lcsResult.put(s,editDistance);
    }
  }

}