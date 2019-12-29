package util;

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


  public static int calculateLcsDynamic(String A, String B) {
    char[] X = A.toCharArray();
    char[] Y = B.toCharArray();
    int m = X.length;
    int n = Y.length;
    int L[][] = new int[m + 1][n + 1];


    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0)
          L[i][j] = 0;
        else if (X[i - 1] == Y[j - 1])
          L[i][j] = L[i - 1][j - 1] + 1;
        else
          L[i][j] = max(L[i - 1][j], L[i][j - 1]);
      }
    }
    return L[m][n];
  }

  private static int max(int a, int b) {
    return (a > b) ? a : b;
  }

}